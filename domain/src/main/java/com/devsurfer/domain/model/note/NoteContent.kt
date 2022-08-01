package com.devsurfer.domain.model.note

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteContent(
    val contentId: Long = 0,
    val content: String = "",
    val createdAt: Long? = 0,
    val updatedAt: Long? = 0,
    val repositoryId: Int = 0,
    val branch: String? = null,
    val branchState: Int = 0,
    val status: Int = 0
): Parcelable
