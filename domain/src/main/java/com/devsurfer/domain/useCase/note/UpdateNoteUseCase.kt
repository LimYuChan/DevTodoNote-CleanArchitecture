package com.devsurfer.domain.useCase.note

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
import com.devsurfer.domain.state.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateNoteUseCase @Inject constructor(
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
    ): Flow<ResourceState<Unit>> = flow{
        val nowTime = System.currentTimeMillis()
        contentRepository.updateContent(content.copy(updatedAt = nowTime))

        imageRepository.deleteImagesByContentId(content.contentId)
        drawingBoardRepository.deleteBoardsByContentId(content.contentId)
        referenceLinkRepository.deleteReferenceLinksByContentId(content.contentId)

        imageRepository.insertImages(*images.map {
            it.copy(noteContentId = content.contentId)
        }.toTypedArray())

        drawingBoardRepository.insertBoards(*drawingBoards.map {
            NoteDrawingBoard(noteContentId = content.contentId, fileJsonString = it.fileJsonString, fileImageUrl = it.fileImageUrl)
        }.toTypedArray())

        referenceLinkRepository.insertLinks(*referenceLinks.map {
            NoteReferenceLink(noteContentId = content.contentId, title = it.title, description = it.description, image = it.image, link = it.url ?: "")
        }.toTypedArray())

        emit(ResourceState.Success(Unit))
    }

    companion object {
        private const val TAG = "CreateNoteUseCase"
    }
}