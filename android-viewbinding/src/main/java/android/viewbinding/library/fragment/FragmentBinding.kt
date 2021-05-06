package android.viewbinding.library.fragment

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Updated by Yoga C. Pranata on 06/05/2021.
 * Android Engineer
 *
 * How to use:
 * - Call it inside your fragment
 * private val binding: FragmentSampleBinding by viewBinding()
 * and access it normally like you use local variable.
 *
 * For the referrence:
 * Thanks to Gabor Varadi for his article
 * https://link.medium.com/TvisGhIS1ab
 */
inline fun <reified T : ViewBinding> Fragment.viewBinding() = FragmentViewBindingDelegate(T::class.java, this)

class FragmentViewBindingDelegate<T : ViewBinding>(
    bindingClass: Class<T>,
    private val fragment: Fragment
) : ReadOnlyProperty<Fragment, T> {

    /**
     * initiate variable for binding view
     */
    private var binding: T? = null

    /**
     * get the bind method from View class
     */
    private val bindMethod = bindingClass.getMethod("bind", View::class.java)

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        binding?.let { return it }

        /**
         * Adding observer to the fragment lifecycle
         */
        fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                fragment.viewLifecycleOwnerLiveData.observe(fragment) { viewLifecycleOwner ->
                    viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                        override fun onDestroy(owner: LifecycleOwner) {
                            /**
                             * Clear the binding when Fragment lifecycle called the onDestroy
                             */
                            binding = null
                        }
                    })
                }
            }
        })


        /**
         * Checking the fragment lifecycle
         */
        val lifecycle = fragment.viewLifecycleOwner.lifecycle
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            error("Cannot access view bindings. View lifecycle is ${lifecycle.currentState}!")
        }


        /**
         * Bind layout
         */
        val invoke = bindMethod.invoke(null, thisRef.requireView()) as T

        return invoke.also { this.binding = it }
    }
}