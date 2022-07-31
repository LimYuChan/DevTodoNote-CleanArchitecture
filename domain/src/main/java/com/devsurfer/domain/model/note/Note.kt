package com.devsurfer.domain.model.note

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(
    val content: NoteContent,
    val imageList: List<NoteImage>,
    val drawingBoardList: List<NoteDrawingBoard>,
    val referenceLinkList: List<NoteReferenceLink>
): Parcelable
