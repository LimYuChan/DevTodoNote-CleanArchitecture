package com.devsurfer.data.repository.note.dataSource

import androidx.room.*
import com.devsurfer.data.model.note.NoteContentEntity

@Dao
interface NoteContentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContent(noteContentEntity: NoteContentEntity): Long

    @Update
    suspend fun updateContent(noteContentEntity: NoteContentEntity): Int

    @Delete
    suspend fun deleteContents(vararg noteContentEntity: NoteContentEntity): Int

    @Query("DELETE FROM note_content WHERE repository_id = :repositoryId")
    suspend fun deleteContentsByRepositoryId(repositoryId: Long): Int

    @Query("SELECT content_id FROM note_content ORDER BY content_id DESC LIMIT 1")
    suspend fun getLastContentId(): Long?
}