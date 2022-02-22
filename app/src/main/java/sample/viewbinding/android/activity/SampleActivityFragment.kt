package sample.viewbinding.android.activity

import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.appcompat.app.AppCompatActivity
import sample.viewbinding.android.databinding.ActivityFragmentSampleBinding
import sample.viewbinding.android.fragment.SampleFragment

/**
 * Created by Yoga C. Pranata on 31/10/2020.
 * Android Engineer
 */
class SampleActivityFragment : AppCompatActivity() {

    private val binding: ActivityFragmentSampleBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragment = SampleFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.container.id, fragment)
        transaction.commit()
    }

}