package com.devsurfer.data.repository.note.dataSource

import androidx.room.*
import com.devsurfer.data.model.note.NoteImageEntity

@Dao
interface NoteImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImages(vararg noteImageEntity: NoteImageEntity): List<Long>

    @Update
    suspend fun updateImages(vararg noteImageEntity: NoteImageEntity): Int

    @Delete
    suspend fun deleteImages(vararg noteImageEntity: NoteImageEntity): Int

    @Query("DELETE FROM note_image WHERE note_content_id = :contentId")
    suspend fun deleteImagesByContentId(contentId: Long): Int
}