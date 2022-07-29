package com.devsurfer.domain.model.userData

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserRepository(
    val id: Int,
    val name: String,
    val private: Boolean,
    val htmlUrl: String,
    val description: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val language: String? = null,
    val defaultBranch: String
): Parcelable
