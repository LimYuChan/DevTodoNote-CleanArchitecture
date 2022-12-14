package com.devsurfer.domain.useCase.note

import android.util.Log
import com.devsurfer.domain.item.DrawingBoard
import com.devsurfer.domain.item.ReferenceLink
import com.devsurfer.domain.model.note.NoteContent
import com.devsurfer.domain.model.note.NoteDrawingBoard
import com.devsurfer.domain.model.note.NoteImage
import com.devsurfer.domain.model.note.NoteReferenceLink
import com.devsurfer.domain.repository.note.NoteContentRepository
import com.devsurfer.domain.repository.note.NoteDrawingBoardRepository
import com.devsurfer.domain.repository.note.NoteImageRepository
import com.devsurfer.domain.repository.note.NoteReferenceLinkRepository
import com.devsurfer.domain.state.Failure
import com.devsurfer.domain.state.ResourceState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CreateNoteUseCase @Inject constructor(
    private val contentRepository: NoteContentRepository,
    private val imageRepository: NoteImageRepository,
    private val drawingBoardRepository: NoteDrawingBoardRepository,
    private val referenceLinkRepository: NoteReferenceLinkRepository
) {

    operator fun invoke(
        content: NoteContent,
        images: List<NoteImage>,
        drawingBoards: List<DrawingBoard>,
        referenceLinks: List<ReferenceLink>
    ): ResourceState<Unit> = runBlocking(Dispatchers.Unconfined) {

        val contentId = withContext(Dispatchers.Unconfined) {
            val nowTime = System.currentTimeMillis()
            contentRepository.insertNoteContent(content.copy(createdAt = nowTime, updatedAt = nowTime))
        }

        if (contentId <= 0) {
            return@runBlocking ResourceState.Error(failure = Failure.UnHandleError())
        } else {
            val saveImageIds = withContext(Dispatchers.Unconfined) {
                imageRepository.insertImages(*images.map {
                    it.copy(noteContentId = contentId)
                }.toTypedArray())
            }
            val saveDrawingBoardIds = withContext(Dispatchers.Unconfined){
                drawingBoardRepository.insertBoards(*drawingBoards.map {
                    NoteDrawingBoard(noteContentId = contentId, fileJsonString = it.fileJsonString, fileImageUrl = it.fileImageUrl)
                }.toTypedArray())
            }
            val saveReferenceLinkIds = withContext(Dispatchers.Unconfined){
                referenceLinkRepository.insertLinks(*referenceLinks.map {
                    NoteReferenceLink(noteContentId = contentId, title = it.title, description = it.description, image = it.image, link = it.url ?: "")
                }.toTypedArray())
            }

            //?????? ??????????????? ???????????? ?????? ???????????? ??????
            if(saveImageIds.size == images.size && saveDrawingBoardIds.size == drawingBoards.size && saveReferenceLinkIds.size == referenceLinks.size){
                return@runBlocking ResourceState.Success(Unit)
            }else{
                return@runBlocking ResourceState.Error(failure = Failure.UnHandleError())
            }
        }
    }

    companion object {
        private const val TAG = "CreateNoteUseCase"
    }
}