package com.devsurfer.data.repository.note

import com.devsurfer.data.mapper.note.NoteMapper
import com.devsurfer.data.repository.note.dataSource.NoteDao
import com.devsurfer.domain.model.note.Note
import com.devsurfer.domain.repository.note.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val dao: NoteDao
): NoteRepository{
    override suspend fun getNotes(): List<Note> =
        dao.getNotes().map { NoteMapper.mapperToModel(it) }

    override suspend fun getNotesByRepositoryId(repositoryId: Int): List<Note> = dao.getNotesByRepositoryId(repositoryId = repositoryId).map { NoteMapper.mapperToModel(it) }

    override suspend fun getTodoNotesByRepositoryId(repositoryId: Int): List<Note> =
        dao.getTodoNotesByRepositoryId(repositoryId = repositoryId).map { NoteMapper.mapperToModel(it) }

    override suspend fun getDoneNotesByRepositoryId(repositoryId: Int): List<Note> =
        dao.getDoneNotesByRepositoryId(repositoryId = repositoryId).map { NoteMapper.mapperToModel(it) }

    override suspend fun getNote(contentId: Long): Note?{
        val note = dao.getNote(contentId = contentId)
        return if(note == null){
            null
        }else{
            NoteMapper.mapperToModel(note)
        }
    }

    override suspend fun deleteNote(contentId: Long): Int = dao.deleteNoteByContentId(contentId)
}