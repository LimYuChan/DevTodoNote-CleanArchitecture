package com.devsurfer.domain.model.userData

data class User(
    val id: Int,
    val email: String? = "",
    val login: String? = "",
    val publicRepos: Int,
    val totalPrivateRepos: Int,
    val ownedPrivateRepos: Int,
    val avatarUrl: String,
    val htmlUrl: String,
)
