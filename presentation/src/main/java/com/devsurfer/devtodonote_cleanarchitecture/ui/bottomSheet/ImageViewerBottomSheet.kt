package com.devsurfer.devtodonote_cleanarchitecture.ui.bottomSheet

import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import com.devsurfer.devtodonote_cleanarchitecture.R
import com.devsurfer.devtodonote_cleanarchitecture.adapter.ImageViewerAdapter
import com.devsurfer.devtodonote_cleanarchitecture.base.FullScreenBottomSheet
import com.devsurfer.devtodonote_cleanarchitecture.databinding.BottomSheetImageViewerBinding

class ImageViewerBottomSheet(
    private val list: List<String>,
    private val viewIndex: Int = 0,
    private val isCacheFile: Boolean = false
): FullScreenBottomSheet<BottomSheetImageViewerBinding>(R.layout.bottom_sheet_image_viewer) {

    private val adapter = ImageViewerAdapter(list, isCacheFile)

    override fun initData() {
        if(list.isEmpty()){
            dismiss()
        }
    }

    override fun initUI() {
        binding.rvImageViewer.adapter = adapter
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvImageViewer)
        val index = if(viewIndex >= list.size) 0 else viewIndex
        binding.rvImageViewer.scrollToPosition(index)
        binding.actionBar.setNavigationOnClickListener {
            dismiss()
        }
    }

    override fun initListener() {
    }
}