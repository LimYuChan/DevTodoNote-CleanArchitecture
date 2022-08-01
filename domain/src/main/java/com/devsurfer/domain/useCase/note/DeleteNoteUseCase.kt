package com.devsurfer.domain.useCase.note

import com.devsurfer.domain.repository.note.NoteRepository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
){
    suspend operator fun invoke(contentId: Long) = noteRepository.deleteNote(contentId)
}