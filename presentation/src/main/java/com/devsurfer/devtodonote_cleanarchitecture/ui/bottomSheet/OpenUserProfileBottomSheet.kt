package com.devsurfer.devtodonote_cleanarchitecture.ui.bottomSheet

import com.devsurfer.devtodonote_cleanarchitecture.R
import com.devsurfer.devtodonote_cleanarchitecture.base.FullScreenBottomSheet
import com.devsurfer.devtodonote_cleanarchitecture.databinding.BottomSheetOpenUserProfileBinding

class OpenUserProfileBottomSheet(
    private val profileUrl: String?
): FullScreenBottomSheet<BottomSheetOpenUserProfileBinding>(R.layout.bottom_sheet_open_user_profile) {

    override fun initData() {
        if(profileUrl.isNullOrBlank()){
            showShortToast(getString(R.string.toast_error_not_found_user))
            dismiss()
        }
    }

    override fun initUI() {
        binding.dialog = this
        binding.webview.loadUrl(profileUrl!!)
    }

    override fun initListener() {

    }
}