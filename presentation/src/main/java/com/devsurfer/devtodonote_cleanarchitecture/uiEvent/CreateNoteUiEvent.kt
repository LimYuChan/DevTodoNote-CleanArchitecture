package com.devsurfer.devtodonote_cleanarchitecture.uiEvent

sealed class CreateNoteUiEvent{
    data class AddImages(val fileUrls: List<String>): CreateNoteUiEvent()
    data class RemoveImage(val fileUrl: String): CreateNoteUiEvent()
    data class AddDrawingBoard(val fileUrl: String): CreateNoteUiEvent()
    data class RemoveDrawingBoard(val fileUrl: String): CreateNoteUiEvent()
    data class AddReferenceLink(val link: String): CreateNoteUiEvent()
    data class RemoveReferenceLink(val link: String): CreateNoteUiEvent()
    data class ChangeBranchName(val branchName: String): CreateNoteUiEvent()
    object ResetBranchName: CreateNoteUiEvent()
    data class Submit(val content: String): CreateNoteUiEvent()
}
