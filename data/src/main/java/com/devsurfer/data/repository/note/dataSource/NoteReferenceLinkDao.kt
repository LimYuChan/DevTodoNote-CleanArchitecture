package com.devsurfer.data.repository.note.dataSource

import androidx.room.*
import com.devsurfer.data.model.note.NoteReferenceLinkEntity

@Dao
interface NoteReferenceLinkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLinks(vararg noteReferenceLinkEntity: NoteReferenceLinkEntity): List<Long>

    @Update
    suspend fun updateLinks(vararg noteReferenceLinkEntity: NoteReferenceLinkEntity): Int

    @Delete
    suspend fun deleteLinks(vararg noteReferenceLinkEntity: NoteReferenceLinkEntity): Int

    @Query("DELETE FROM note_reference_link WHERE note_content_id = :contentId")
    suspend fun deleteReferenceLinksByContentId(contentId: Long): Int
}