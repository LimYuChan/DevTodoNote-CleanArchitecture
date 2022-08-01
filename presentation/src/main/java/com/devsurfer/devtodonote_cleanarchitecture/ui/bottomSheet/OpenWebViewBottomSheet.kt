package com.devsurfer.devtodonote_cleanarchitecture.ui.bottomSheet

import com.devsurfer.devtodonote_cleanarchitecture.R
import com.devsurfer.devtodonote_cleanarchitecture.base.FullScreenBottomSheet
import com.devsurfer.devtodonote_cleanarchitecture.databinding.BottomSheetOpenWebViewBinding

class OpenWebViewBottomSheet(
    private val url: String?
): FullScreenBottomSheet<BottomSheetOpenWebViewBinding>(R.layout.bottom_sheet_open_web_view) {

    override fun initData() {
        if(url.isNullOrBlank()){
            showShortToast(getString(R.string.toast_error_not_fount_link))
            dismiss()
        }
    }

    override fun initUI() {
        binding.dialog = this
        binding.webview.loadUrl(url!!)
    }

    override fun initListener() {

    }
}