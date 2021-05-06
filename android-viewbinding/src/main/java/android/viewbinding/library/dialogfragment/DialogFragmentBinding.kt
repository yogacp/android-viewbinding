package android.viewbinding.library.dialogfragment

import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Created by Yoga C. Pranata on 5/6/21.
 * Android Engineer
 *
 * How to use:
 * - Call it inside your DialogFragment
 * private val binding: DialogSampleBinding by viewBinding()
 * and access it normally like you use local variable.
 */
inline fun <reified T : ViewBinding> DialogFragment.viewBinding() = DialogFragmentViewBindingDelegate(T::class.java)

class DialogFragmentViewBindingDelegate<T : ViewBinding>(private val bindingClass: Class<T>) :
    ReadOnlyProperty<DialogFragment, T> {
    /**
     * initiate variable for binding view
     */
    private var binding: T? = null

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: DialogFragment, property: KProperty<*>): T {
        binding?.let { return it }

        /**
         * inflate View class
         */
        val inflateMethod = bindingClass.getMethod("inflate", LayoutInflater::class.java)

        /**
         * Bind layout
         */
        val invokeLayout = inflateMethod.invoke(null, LayoutInflater.from(thisRef.context)) as T

        /**
         * Return binding layout
         */
        return invokeLayout.also { this.binding = it }
    }
}