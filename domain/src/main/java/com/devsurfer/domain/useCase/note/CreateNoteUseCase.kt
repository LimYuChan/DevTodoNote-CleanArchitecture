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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

/**
 * Update by Yuchan 2022.08.09
 */
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
    ): Flow<ResourceState<Unit>> = flow {
        val contentId = withContext(Dispatchers.IO) {
            val nowTime = System.currentTimeMillis()
            contentRepository.insertNoteContent(content.copy(createdAt = nowTime, updatedAt = nowTime))
        }

        if (contentId <= 0) {
            emit(ResourceState.Error(failure = Failure.UnHandleError()))
        } else {
            imageRepository.insertImages(*images.map { it.copy(noteContentId = contentId) }.toTypedArray())

            drawingBoardRepository.insertBoards(*drawingBoards.map {
                NoteDrawingBoard(
                    noteContentId = contentId,
                    fileJsonString = it.fileJsonString,
                    fileImageUrl = it.fileImageUrl
                )
            }.toTypedArray())

            referenceLinkRepository.insertLinks(*referenceLinks.map {
                NoteReferenceLink(
                    noteContentId = contentId,
                    title = it.title,
                    description = it.description,
                    image = it.image,
                    link = it.url ?: ""
                )
            }.toTypedArray())

            emit(ResourceState.Success(Unit))
        }
    }

    companion object {
        private const val TAG = "CreateNoteUseCase"
    }
}