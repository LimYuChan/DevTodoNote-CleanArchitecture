package com.devsurfer.domain.model.note

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteImage(
    val id: Long = 0,
    val noteContentId: Long,
    val fileId: Long,
    val fileName: String,
    val fileUrl: String
): Parcelable
