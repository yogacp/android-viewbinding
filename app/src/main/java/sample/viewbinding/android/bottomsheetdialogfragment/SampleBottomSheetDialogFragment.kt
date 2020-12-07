package sample.viewbinding.android.bottomsheetdialogfragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.bottomsheetdialogfragment.viewBinding
import android.widget.FrameLayout
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import sample.viewbinding.android.R
import sample.viewbinding.android.databinding.BottomsheetSampleBinding

/**
 * Created by Yoga C. Pranata on 07/12/2020.
 * Android Engineer
 */
class SampleBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private val binding: BottomsheetSampleBinding by viewBinding()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener { setupHeight(it as BottomSheetDialog) }
        return dialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
    }

    private fun setupHeight(bottomSheetDialog: BottomSheetDialog) {
        val frameLayout = bottomSheetDialog.findViewById<FrameLayout>(R.id.design_bottom_sheet)
        val behavior = frameLayout?.let { BottomSheetBehavior.from(it) }
        frameLayout?.background =
            ResourcesCompat.getDrawable(resources, R.drawable.bg_bottomsheet_dialog, null)
        behavior?.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun setupButtons() {
        binding.btnClose.setOnClickListener {
            dismissAllowingStateLoss()
        }
    }

}