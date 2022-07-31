package com.devsurfer.data.repository.note

import com.devsurfer.data.mapper.note.NoteReferenceLinkMapper
import com.devsurfer.data.repository.note.dataSource.NoteReferenceLinkDao
import com.devsurfer.domain.model.note.NoteReferenceLink
import com.devsurfer.domain.repository.note.NoteReferenceLinkRepository
import javax.inject.Inject

class NoteReferenceLinkRepositoryImpl @Inject constructor(
    private val dao: NoteReferenceLinkDao
): NoteReferenceLinkRepository{
    override suspend fun insertLinks(vararg noteReferenceLink: NoteReferenceLink): List<Long> =
        dao.insertLinks(noteReferenceLinkEntity = noteReferenceLink.map { NoteReferenceLinkMapper.mapperToEntity(it) }.toTypedArray())

    override suspend fun updateLinks(vararg noteReferenceLink: NoteReferenceLink): Int =
        dao.updateLinks(noteReferenceLinkEntity = noteReferenceLink.map { NoteReferenceLinkMapper.mapperToEntity(it) }.toTypedArray())

    override suspend fun deleteLinks(vararg noteReferenceLink: NoteReferenceLink): Int =
        dao.deleteLinks(noteReferenceLinkEntity = noteReferenceLink.map { NoteReferenceLinkMapper.mapperToEntity(it) }.toTypedArray())

    override suspend fun deleteReferenceLinksByContentId(contentId: Long): Int =
        dao.deleteReferenceLinksByContentId(contentId = contentId)
}