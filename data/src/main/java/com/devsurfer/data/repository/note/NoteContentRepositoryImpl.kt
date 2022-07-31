package com.devsurfer.data.repository.note

import com.devsurfer.data.mapper.note.NoteContentMapper
import com.devsurfer.data.repository.note.dataSource.NoteContentDao
import com.devsurfer.domain.model.note.NoteContent
import com.devsurfer.domain.repository.note.NoteContentRepository
import javax.inject.Inject

class NoteContentRepositoryImpl @Inject constructor(
    private val dao: NoteContentDao
): NoteContentRepository{

    override suspend fun insertNoteContent(noteContent: NoteContent): Long =
        dao.insertContent(noteContentEntity = NoteContentMapper.mapperToEntity(noteContent))

    override suspend fun updateContent(noteContent: NoteContent): Int =
        dao.updateContent(noteContentEntity = NoteContentMapper.mapperToEntity(noteContent))

    override suspend fun deleteContent(vararg noteContent: NoteContent): Int =
        dao.deleteContents(noteContentEntity = noteContent.map { NoteContentMapper.mapperToEntity(it) }.toTypedArray())

    override suspend fun deleteContentsByRepositoryId(repositoryId: Long): Int =
        dao.deleteContentsByRepositoryId(repositoryId = repositoryId)

    override suspend fun getLastContentId(): Long? =
        dao.getLastContentId()
}