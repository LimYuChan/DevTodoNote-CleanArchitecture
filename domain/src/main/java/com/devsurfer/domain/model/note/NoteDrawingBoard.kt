package com.devsurfer.domain.model.note

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteDrawingBoard(
    val id: Long = 0,
    val noteContentId: Long,
    val fileJsonString: String,
    val fileImageUrl: String
): Parcelable
