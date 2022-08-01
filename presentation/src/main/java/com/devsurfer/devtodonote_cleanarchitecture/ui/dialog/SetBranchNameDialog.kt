package com.devsurfer.devtodonote_cleanarchitecture.ui.dialog

import android.util.Log
import com.devsurfer.devtodonote_cleanarchitecture.R
import com.devsurfer.devtodonote_cleanarchitecture.base.BaseDialog
import com.devsurfer.devtodonote_cleanarchitecture.databinding.DialogSetBranchNameBinding

class SetBranchNameDialog(
    val branchName: String,
    val onChange: (String) -> Unit,
    val onReset: () -> Unit
): BaseDialog<DialogSetBranchNameBinding>(R.layout.dialog_set_branch_name){

    override fun initData() {

    }

    override fun initUI() {
        with(binding){
            dialog = this@SetBranchNameDialog
            etBranchName.setText(branchName)
            buttonDone.setOnClickListener {
                if(etBranchName.text.isNullOrBlank()){
                    showShortToast(getString(R.string.toast_error_blank_content))
                }else{
                    onChange(etBranchName.text.toString())
                    dismiss()
                }
            }

            buttonReset.setOnClickListener {
                onReset()
                dismiss()
            }
        }
    }

    override fun initListener() {

    }

    companion object{
        private const val TAG = "SetBranchNameDialog"
    }
}