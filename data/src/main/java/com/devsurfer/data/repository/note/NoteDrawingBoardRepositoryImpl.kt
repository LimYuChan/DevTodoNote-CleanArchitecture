package com.devsurfer.data.repository.note

import com.devsurfer.data.mapper.note.NoteDrawingBoardMapper
import com.devsurfer.data.repository.note.dataSource.NoteDrawingBoardDao
import com.devsurfer.domain.model.note.NoteDrawingBoard
import com.devsurfer.domain.repository.note.NoteDrawingBoardRepository
import javax.inject.Inject

class NoteDrawingBoardRepositoryImpl @Inject constructor(
    private val dao: NoteDrawingBoardDao
): NoteDrawingBoardRepository{
    override suspend fun insertBoards(vararg noteDrawingBoard: NoteDrawingBoard): List<Long> =
        dao.insertBoards(noteDrawingBoardEntity = noteDrawingBoard.map { NoteDrawingBoardMapper.mapperToEntity(it) }.toTypedArray())

    override suspend fun updateBoards(vararg noteDrawingBoard: NoteDrawingBoard): Int =
        dao.updateBoards(noteDrawingBoardEntity = noteDrawingBoard.map { NoteDrawingBoardMapper.mapperToEntity(it) }.toTypedArray())

    override suspend fun deleteBoards(vararg noteDrawingBoard: NoteDrawingBoard): Int =
        dao.deleteBoards(noteDrawingBoardEntity = noteDrawingBoard.map { NoteDrawingBoardMapper.mapperToEntity(it) }.toTypedArray())

    override suspend fun deleteBoardsByContentId(contentId: Long): Int =
        dao.deleteBoardsByContentId(contentId = contentId)
}