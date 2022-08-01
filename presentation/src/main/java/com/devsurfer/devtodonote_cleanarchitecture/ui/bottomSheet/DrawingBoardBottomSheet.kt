package com.devsurfer.devtodonote_cleanarchitecture.ui.bottomSheet

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.util.Log
import android.view.View
import androidx.core.view.drawToBitmap
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.devsurfer.devtodonote_cleanarchitecture.R
import com.devsurfer.devtodonote_cleanarchitecture.base.FullScreenBottomSheet
import com.devsurfer.devtodonote_cleanarchitecture.databinding.BottomSheetDrawingBoardBinding
import com.devsurfer.devtodonote_cleanarchitecture.extension.resolveColorAttr
import com.devsurfer.devtodonote_cleanarchitecture.util.DrawingBoardViewUtil
import com.devsurfer.devtodonote_cleanarchitecture.util.Utils
import com.devsurfer.devtodonote_cleanarchitecture.viewModel.DrawingBoardViewModel
import com.devsurfer.domain.item.DrawingBoard
import com.devsurfer.domain.item.DrawingPoint
import com.devsurfer.domain.state.ResourceState
import com.esafirm.imagepicker.model.Image
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class DrawingBoardBottomSheet(
    private val drawingBoard: DrawingBoard? = null,
    val onSaveFile: (DrawingBoard) -> Unit
): FullScreenBottomSheet<BottomSheetDrawingBoardBinding>(R.layout.bottom_sheet_drawing_board) {

    private val viewModel: DrawingBoardViewModel by viewModels()

    override fun initData() {

    }

    override fun initUI() {
        with(binding){
            dialog = this@DrawingBoardBottomSheet

            selectedColor = 0
            drawingBoardView.setPaintColor(context?.resolveColorAttr(android.R.attr.textColorPrimary) ?: Color.BLACK)

            if(drawingBoard != null){
                val pointList = Gson().fromJson(drawingBoard.fileJsonString, Array<DrawingPoint>::class.java).toList()
                drawingBoardView.setDrawing(pointList)
            }

            buttonRefresh.setOnClickListener {
                drawingBoardView.reset()
            }
            buttonRemoveLastPath.setOnClickListener {
                drawingBoardView.removeLastPath()
            }
            buttonActionSave.setOnClickListener {
                val config: Bitmap.Config = Bitmap.Config.ARGB_8888
                val bitmap = drawingBoardView.drawToBitmap(config)
                viewModel.saveDrawingBoard(drawingBoardView.getDrawing(), bitmap)
            }
            buttonTextPrimaryColor.setOnClickListener {
                selectedColor = 0
                drawingBoardView.setPaintColor(context?.resolveColorAttr(android.R.attr.textColorPrimary) ?: Color.BLACK)
            }
            buttonPrimaryColor.setOnClickListener {
                selectedColor = 1
                drawingBoardView.setPaintColor(context?.getColor(R.color.primary) ?: Color.BLACK)
            }
            buttonSubPrimaryColor.setOnClickListener {
                selectedColor = 2
                drawingBoardView.setPaintColor(context?.getColor(R.color.sub_primary) ?: Color.BLACK)
            }
        }
    }

    override fun initListener() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.saveState.collectLatest {
                Log.d(TAG, "initListener: $it")
                when(it){
                    is ResourceState.Success->{
                        binding.layoutLoadingProgress.root.visibility = View.GONE
                        showShortToast(getString(R.string.toast_complete_save_drawing_board))
                        onSaveFile(it.data)
                        dismiss()
                    }
                    is ResourceState.Error->{
                        binding.layoutLoadingProgress.root.visibility = View.GONE
                        showShortToast(it.failure.message)
                    }
                    else->{
                        binding.layoutLoadingProgress.root.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    companion object{
        private const val TAG = "DrawingBoardBottomSheet"
    }
}