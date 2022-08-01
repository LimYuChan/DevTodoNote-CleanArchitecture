package com.devsurfer.domain.model.note

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteReferenceLink(
    val id: Long = 0,
    val noteContentId: Long,
    val title: String? = null,
    val description: String? = null,
    val image: String? = null,
    val link: String
): Parcelable
