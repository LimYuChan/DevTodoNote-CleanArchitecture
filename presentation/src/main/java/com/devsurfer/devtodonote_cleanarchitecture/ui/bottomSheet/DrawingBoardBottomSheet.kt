package com.devsurfer.devtodonote_cleanarchitecture.ui.bottomSheet

import android.graphics.Color
import com.devsurfer.devtodonote_cleanarchitecture.R
import com.devsurfer.devtodonote_cleanarchitecture.base.FullScreenBottomSheet
import com.devsurfer.devtodonote_cleanarchitecture.databinding.BottomSheetDrawingBoardBinding
import com.devsurfer.devtodonote_cleanarchitecture.extension.resolveColorAttr
import com.devsurfer.domain.item.DrawingBoard
import com.esafirm.imagepicker.model.Image

class DrawingBoardBottomSheet(
    onSaveFile: (DrawingBoard) -> Unit
): FullScreenBottomSheet<BottomSheetDrawingBoardBinding>(R.layout.bottom_sheet_drawing_board) {

    override fun initData() {

    }

    override fun initUI() {
        with(binding){
            selectedColor = 0
            drawingBoard.setPaintColor(context?.resolveColorAttr(android.R.attr.textColorPrimary) ?: Color.BLACK)

            buttonRefresh.setOnClickListener {
                drawingBoard.reset()
            }
            buttonRemoveLastPath.setOnClickListener {
                drawingBoard.removeLastPath()
            }
            buttonTextPrimaryColor.setOnClickListener {
                selectedColor = 0
                drawingBoard.setPaintColor(context?.resolveColorAttr(android.R.attr.textColorPrimary) ?: Color.BLACK)
            }
            buttonPrimaryColor.setOnClickListener {
                selectedColor = 1
                drawingBoard.setPaintColor(context?.getColor(R.color.primary) ?: Color.BLACK)
            }
            buttonSubPrimaryColor.setOnClickListener {
                selectedColor = 2
                drawingBoard.setPaintColor(context?.getColor(R.color.sub_primary) ?: Color.BLACK)
            }
        }
    }

    override fun initListener() {
    }
}