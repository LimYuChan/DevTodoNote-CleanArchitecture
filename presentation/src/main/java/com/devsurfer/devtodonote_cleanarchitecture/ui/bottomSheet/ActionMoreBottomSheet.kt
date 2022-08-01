package com.devsurfer.devtodonote_cleanarchitecture.ui.bottomSheet

import android.view.View
import com.devsurfer.devtodonote_cleanarchitecture.R
import com.devsurfer.devtodonote_cleanarchitecture.base.BaseBottomSheet
import com.devsurfer.devtodonote_cleanarchitecture.databinding.BottomSheetActionMoreBinding

class ActionMoreBottomSheet(
    private val isOnlyDelete: Boolean = false,
    val onDelete: () -> Unit = {},
    val onEdit: () ->Unit = {}
): BaseBottomSheet<BottomSheetActionMoreBinding>(layoutResource =  R.layout.bottom_sheet_action_more, isBackgroundTransparentEnable = true){

    override fun initData() {

    }

    override fun initUI() {
        with(binding){
            buttonEdit.visibility = if(isOnlyDelete) View.GONE else View.VISIBLE
            divider.visibility = if(isOnlyDelete) View.GONE else View.VISIBLE
            buttonCancel.setOnClickListener {
                dismiss()
            }
            buttonDelete.setOnClickListener {
                onDelete()
                dismiss()
            }
            buttonEdit.setOnClickListener {
                onEdit()
                dismiss()
            }
        }
    }

    override fun initListener() {

    }
}