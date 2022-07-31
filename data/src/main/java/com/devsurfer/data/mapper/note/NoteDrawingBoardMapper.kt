package com.devsurfer.data.mapper.note

import com.devsurfer.data.model.note.NoteDrawingBoardEntity
import com.devsurfer.domain.model.note.NoteDrawingBoard

object NoteDrawingBoardMapper {
    fun mapperToModel(entity: NoteDrawingBoardEntity): NoteDrawingBoard =
        NoteDrawingBoard(
            id = entity.drawing_board_id,
            noteContentId = entity.note_content_id,
            fileUrl = entity.file_url
        )

    fun mapperToEntity(model: NoteDrawingBoard): NoteDrawingBoardEntity =
        NoteDrawingBoardEntity(
            drawing_board_id = model.id,
            note_content_id = model.noteContentId,
            file_url = model.fileUrl
        )
}