package com.devsurfer.data.repository.note.dataSource

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.devsurfer.data.model.note.NoteEntity

@Dao
interface NoteDao {
    @Transaction
    @Query("SELECT * FROM note_content ORDER BY content_id DESC")
    suspend fun getNotes(): List<NoteEntity>

    @Transaction
    @Query("SELECT * FROM note_content WHERE repository_id = :repositoryId ORDER BY content_id DESC")
    suspend fun getNotesByRepositoryId(repositoryId: Int): List<NoteEntity>

    @Transaction
    @Query("SELECT * FROM note_content WHERE repository_id = :repositoryId AND status = 0 ORDER BY content_id DESC")
    suspend fun getTodoNotesByRepositoryId(repositoryId: Int): List<NoteEntity>

    @Transaction
    @Query("SELECT * FROM note_content WHERE repository_id = :repositoryId AND status = 1 ORDER BY content_id DESC")
    suspend fun getDoneNotesByRepositoryId(repositoryId: Int): List<NoteEntity>

    @Transaction
    @Query("SELECT * FROM note_content WHERE content_id = :contentId")
    suspend fun getNote(contentId: Long): NoteEntity?
}