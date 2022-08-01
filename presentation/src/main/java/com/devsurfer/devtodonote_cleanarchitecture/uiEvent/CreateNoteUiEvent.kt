package com.devsurfer.devtodonote_cleanarchitecture.uiEvent

import com.devsurfer.domain.item.DrawingBoard
import com.devsurfer.domain.item.ReferenceLink
import com.esafirm.imagepicker.model.Image

sealed class CreateNoteUiEvent{
    data class AddImages(val fileUrls: List<Image>): CreateNoteUiEvent()
    data class RemoveImage(val fileUrl: Image): CreateNoteUiEvent()
    data class AddDrawingBoard(val fileUrl: DrawingBoard): CreateNoteUiEvent()
    data class RemoveDrawingBoard(val fileUrl: DrawingBoard): CreateNoteUiEvent()
    data class AddReferenceLink(val link: ReferenceLink): CreateNoteUiEvent()
    data class RemoveReferenceLink(val link: ReferenceLink): CreateNoteUiEvent()
    data class ChangeBranchName(val branchName: String): CreateNoteUiEvent()
    object ResetBranchName: CreateNoteUiEvent()
    data class Submit(val content: String): CreateNoteUiEvent()
}
