package com.devsurfer.domain.repository.note

import com.devsurfer.domain.model.note.Note
import com.devsurfer.domain.model.note.NoteContent
import com.devsurfer.domain.state.ResourceState

interface NoteRepository {
    suspend fun getNotes(): List<Note>
    suspend fun getNotesByRepositoryId(repositoryId: Int): List<Note>
    suspend fun getTodoNotesByRepositoryId(repositoryId: Int): List<Note>
    suspend fun getDoneNotesByRepositoryId(repositoryId: Int): List<Note>
    suspend fun deleteNote(contentId: Long): Int
    suspend fun getNote(contentId: Long): Note?
}