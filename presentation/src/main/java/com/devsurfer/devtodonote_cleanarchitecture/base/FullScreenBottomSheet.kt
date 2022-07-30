package com.devsurfer.devtodonote_cleanarchitecture.base

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

abstract class FullScreenBottomSheet<T: ViewDataBinding>(
    @LayoutRes val layoutRes: Int
): BaseBottomSheet<T>(layoutResource = layoutRes){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        if(context == null){
            return super.onCreateDialog(savedInstanceState)
        }else{
            val dialog = BottomSheetDialog(requireContext(), theme)
            dialog.setOnShowListener {
                val sheetDialog = it as BottomSheetDialog
                val parentLayout =
                    sheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)

                parentLayout?.let { parentLayout ->
                    val behavior = BottomSheetBehavior.from(parentLayout)
                    setFullHeight(parentLayout)
                    behavior.state = BottomSheetBehavior.STATE_EXPANDED
                    behavior.isDraggable = false
                }
            }
            return dialog
        }
    }

    private fun setFullHeight(sheetView: View){
        val layoutParams = sheetView.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        sheetView.layoutParams = layoutParams
    }
}