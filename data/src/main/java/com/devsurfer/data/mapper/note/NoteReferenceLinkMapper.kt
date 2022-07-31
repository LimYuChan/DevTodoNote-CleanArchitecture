package com.devsurfer.data.mapper.note

import com.devsurfer.data.model.note.NoteReferenceLinkEntity
import com.devsurfer.domain.model.note.NoteReferenceLink

object NoteReferenceLinkMapper {
    fun mapperToModel(entity: NoteReferenceLinkEntity): NoteReferenceLink =
        NoteReferenceLink(
            id = entity.note_reference_link_id,
            noteContentId = entity.note_content_id,
            link = entity.link
        )

    fun mapperToEntity(model: NoteReferenceLink): NoteReferenceLinkEntity =
        NoteReferenceLinkEntity(
            note_reference_link_id = model.id,
            note_content_id = model.noteContentId,
            link = model.link
        )
}