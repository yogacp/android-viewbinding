package sample.viewbinding.android.dialog

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.dialogfragment.viewBinding
import androidx.fragment.app.DialogFragment
import sample.viewbinding.android.R
import sample.viewbinding.android.databinding.DialogSampleBinding

/**
 * Created by Yoga C. Pranata on 5/6/21.
 * Android Engineer
 */
class SampleDialogFragment : DialogFragment() {

    private val binding: DialogSampleBinding by viewBinding()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, R.style.DialogStyle90)
        isCancelable = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBackgroundDialog()
        binding.tvTitle.text = getString(R.string.sample_dialog_title)
        binding.btnClose.setOnClickListener {
            dismissAllowingStateLoss()
        }
    }

    private fun setupBackgroundDialog() {
        if(dialog?.window != null) {
            dialog?.window?.setBackgroundDrawableResource(R.drawable.bg_dialog)
            dialog?.window?.setGravity(Gravity.CENTER)
        }
    }
}