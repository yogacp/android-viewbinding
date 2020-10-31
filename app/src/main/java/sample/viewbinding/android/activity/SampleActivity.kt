package sample.viewbinding.android.activity

import android.content.Intent
import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import sample.viewbinding.android.R
import sample.viewbinding.android.databinding.ActivitySampleBinding

/**
 * Created by Yoga C. Pranata on 31/10/2020.
 * Android Engineer
 */
class SampleActivity : AppCompatActivity() {

    private val binding: ActivitySampleBinding by viewBinding()

    override fun onBackPressed() {
        finishAffinity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.tvTitle.text = getString(R.string.sample_title)
        binding.btnSample.text = getString(R.string.sample_button)

        binding.btnSample.setOnClickListener {
            Toast.makeText(this, "Sample Button Clicked", Toast.LENGTH_SHORT).show()
        }

        binding.btnFragment.setOnClickListener {
            val intent = Intent(this, SampleActivityFragment::class.java)
            startActivity(intent)
        }
    }

}