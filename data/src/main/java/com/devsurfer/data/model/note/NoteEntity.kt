package com.devsurfer.data.model.note

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.parcelize.Parcelize

data class NoteEntity(
    @Embedded val content: NoteContentEntity,
    @Relation(
        parentColumn = "content_id",
        entityColumn = "note_content_id"
    ) val imageList: List<NoteImageEntity>,
    @Relation(
        parentColumn = "content_id",
        entityColumn = "note_content_id"
    ) val drawingBoardList: List<NoteDrawingBoardEntity>,
    @Relation(
        parentColumn = "content_id",
        entityColumn = "note_content_id"
    ) val referenceLinkList: List<NoteReferenceLinkEntity>
)
