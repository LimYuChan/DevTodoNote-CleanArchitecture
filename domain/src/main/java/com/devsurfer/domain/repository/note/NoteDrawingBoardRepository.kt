package com.devsurfer.domain.repository.note

import com.devsurfer.domain.model.note.NoteDrawingBoard
import com.devsurfer.domain.state.ResourceState

interface NoteDrawingBoardRepository {
    suspend fun insertBoards(vararg noteDrawingBoard: NoteDrawingBoard): List<Long>
    suspend fun updateBoards(vararg noteDrawingBoard: NoteDrawingBoard): Int
    suspend fun deleteBoards(vararg noteDrawingBoard: NoteDrawingBoard): Int
    suspend fun deleteBoardsByContentId(contentId: Long): Int
}