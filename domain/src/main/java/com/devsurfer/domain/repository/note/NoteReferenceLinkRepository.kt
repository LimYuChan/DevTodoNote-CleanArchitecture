package com.devsurfer.domain.repository.note

import com.devsurfer.domain.model.note.NoteReferenceLink
import com.devsurfer.domain.state.ResourceState

interface NoteReferenceLinkRepository {
    suspend fun insertLinks(vararg noteReferenceLink: NoteReferenceLink): List<Long>
    suspend fun updateLinks(vararg noteReferenceLink: NoteReferenceLink): Int
    suspend fun deleteLinks(vararg noteReferenceLink: NoteReferenceLink): Int
    suspend fun deleteReferenceLinksByContentId(contentId: Long): Int
}