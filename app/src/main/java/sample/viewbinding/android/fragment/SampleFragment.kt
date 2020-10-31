package sample.viewbinding.android.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import sample.viewbinding.android.R
import sample.viewbinding.android.activity.SampleActivity
import sample.viewbinding.android.databinding.FragmentSampleBinding

/**
 * Created by Yoga C. Pranata on 31/10/2020.
 * Android Engineer
 */
class SampleFragment : Fragment(R.layout.fragment_sample) {

    private val binding: FragmentSampleBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvFragmentTitle.text = getString(R.string.sample_fragment_title)
        binding.btnFragmentSample.text = getString(R.string.sample_fragment_button)

        binding.btnFragmentSample.setOnClickListener {
            Toast.makeText(context, "Sample Fragment Button Clicked", Toast.LENGTH_SHORT).show()
        }

        binding.btnSample.setOnClickListener {
            val intent = Intent(context, SampleActivity::class.java)
            startActivity(intent)
        }
    }
}