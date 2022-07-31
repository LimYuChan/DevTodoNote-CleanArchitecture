package com.devsurfer.domain.repository.note

import com.devsurfer.domain.model.note.NoteImage
import com.devsurfer.domain.state.ResourceState

interface NoteImageRepository {
    suspend fun insertImages(vararg noteImage: NoteImage): List<Long>
    suspend fun updateImages(vararg noteImage: NoteImage): Int
    suspend fun deleteImages(vararg noteImage: NoteImage): Int
    suspend fun deleteImagesByContentId(contentId: Long): Int
}