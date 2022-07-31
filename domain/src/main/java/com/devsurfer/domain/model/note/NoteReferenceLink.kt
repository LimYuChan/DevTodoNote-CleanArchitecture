package com.devsurfer.domain.model.note

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteReferenceLink(
    val id: Long = 0,
    val noteContentId: Long,
    val link: String
): Parcelable
