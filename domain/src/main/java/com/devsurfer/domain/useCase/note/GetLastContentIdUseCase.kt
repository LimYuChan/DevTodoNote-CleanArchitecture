package com.devsurfer.domain.useCase.note

import com.devsurfer.domain.repository.note.NoteContentRepository
import com.devsurfer.domain.repository.note.NoteRepository
import javax.inject.Inject

class GetLastContentIdUseCase @Inject constructor(
    private val repository: NoteContentRepository
){
    suspend operator fun invoke(): Long = repository.getLastContentId() ?: 0
}