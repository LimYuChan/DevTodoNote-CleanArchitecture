package com.devsurfer.data.mapper.note

import com.devsurfer.data.model.note.NoteImageEntity
import com.devsurfer.domain.model.note.NoteImage

object NoteImageMapper {
    fun mapperToModel(entity: NoteImageEntity): NoteImage =
        NoteImage(
            id = entity.image_id,
            noteContentId = entity.note_content_id,
            fileUrl = entity.file_url
        )

    fun mapperToEntity(model: NoteImage): NoteImageEntity =
        NoteImageEntity(
            image_id = model.id,
            note_content_id = model.noteContentId,
            file_url = model.fileUrl
        )
}