package com.devsurfer.data.repository.note.dataSource

import androidx.room.*
import com.devsurfer.data.model.note.NoteDrawingBoardEntity

@Dao
interface NoteDrawingBoardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBoards(vararg noteDrawingBoardEntity: NoteDrawingBoardEntity): List<Long>

    @Update
    suspend fun updateBoards(vararg noteDrawingBoardEntity: NoteDrawingBoardEntity): Int

    @Delete
    suspend fun deleteBoards(vararg noteDrawingBoardEntity: NoteDrawingBoardEntity): Int

    @Query("DELETE FROM note_drawing_board WHERE note_content_id = :contentId")
    suspend fun deleteBoardsByContentId(contentId: Long): Int
}