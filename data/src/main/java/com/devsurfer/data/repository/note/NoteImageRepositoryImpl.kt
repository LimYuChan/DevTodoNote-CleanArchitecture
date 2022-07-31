package com.devsurfer.data.repository.note

import com.devsurfer.data.mapper.note.NoteImageMapper
import com.devsurfer.data.repository.note.dataSource.NoteImageDao
import com.devsurfer.domain.model.note.NoteImage
import com.devsurfer.domain.repository.note.NoteImageRepository
import javax.inject.Inject

class NoteImageRepositoryImpl @Inject constructor(
    private val dao: NoteImageDao
): NoteImageRepository{
    override suspend fun insertImages(vararg noteImage: NoteImage): List<Long> =
        dao.insertImages(noteImageEntity = noteImage.map { NoteImageMapper.mapperToEntity(it) }.toTypedArray())

    override suspend fun updateImages(vararg noteImage: NoteImage): Int =
        dao.updateImages(noteImageEntity = noteImage.map { NoteImageMapper.mapperToEntity(it) }.toTypedArray())

    override suspend fun deleteImages(vararg noteImage: NoteImage): Int =
        dao.deleteImages(noteImageEntity = noteImage.map { NoteImageMapper.mapperToEntity(it) }.toTypedArray())

    override suspend fun deleteImagesByContentId(contentId: Long): Int =
        dao.deleteImagesByContentId(contentId = contentId)
}