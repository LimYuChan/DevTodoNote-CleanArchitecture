package com.devsurfer.domain.useCase.note

import com.devsurfer.domain.model.note.Note
import com.devsurfer.domain.model.note.NoteContent
import com.devsurfer.domain.repository.note.NoteContentRepository
import javax.inject.Inject

class UpdateNoteContentUseCase @Inject constructor(
    private val noteContentRepository: NoteContentRepository
) {
    suspend operator fun invoke(noteContent: NoteContent){
        noteContentRepository.updateContent(noteContent = noteContent)
    }
}