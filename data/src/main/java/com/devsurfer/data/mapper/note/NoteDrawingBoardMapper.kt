package com.devsurfer.data.mapper.note

import com.devsurfer.data.model.note.NoteDrawingBoardEntity
import com.devsurfer.domain.model.note.NoteDrawingBoard

object NoteDrawingBoardMapper {
    fun mapperToModel(entity: NoteDrawingBoardEntity): NoteDrawingBoard =
        NoteDrawingBoard(
            id = entity.drawing_board_id,
            noteContentId = entity.note_content_id,
            fileJsonString = entity.file_json_string,
            fileImageUrl = entity.file_image_url
        )

    fun mapperToEntity(model: NoteDrawingBoard): NoteDrawingBoardEntity =
        NoteDrawingBoardEntity(
            drawing_board_id = model.id,
            note_content_id = model.noteContentId,
            file_json_string = model.fileJsonString,
            file_image_url = model.fileImageUrl
        )
}