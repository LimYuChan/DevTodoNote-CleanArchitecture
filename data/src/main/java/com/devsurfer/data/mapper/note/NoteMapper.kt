package com.devsurfer.data.mapper.note

import com.devsurfer.data.model.note.NoteEntity
import com.devsurfer.domain.model.note.Note

object NoteMapper {
    fun mapperToModel(entity: NoteEntity): Note =
        Note(
            content = NoteContentMapper.mapperToModel(entity.content),
            imageList = entity.imageList.map { NoteImageMapper.mapperToModel(it) },
            drawingBoardList = entity.drawingBoardList.map { NoteDrawingBoardMapper.mapperToModel(it) },
            referenceLinkList = entity.referenceLinkList.map { NoteReferenceLinkMapper.mapperToModel(it) }
        )

    fun mapperToEntity(model: Note): NoteEntity =
        NoteEntity(
            content = NoteContentMapper.mapperToEntity(model.content),
            imageList = model.imageList.map { NoteImageMapper.mapperToEntity(it) },
            drawingBoardList = model.drawingBoardList.map { NoteDrawingBoardMapper.mapperToEntity(it) },
            referenceLinkList = model.referenceLinkList.map { NoteReferenceLinkMapper.mapperToEntity(it) }
        )
}