package com.devsurfer.domain.repository.note

import com.devsurfer.domain.model.note.NoteContent
import com.devsurfer.domain.state.ResourceState

interface NoteContentRepository {
    suspend fun insertNoteContent(noteContent: NoteContent): Long
    suspend fun updateContent(noteContent: NoteContent): Int
    suspend fun deleteContent(vararg noteContent: NoteContent): Int
    suspend fun deleteContentsByRepositoryId(repositoryId: Long): Int
    suspend fun getLastContentId(): Long?
}