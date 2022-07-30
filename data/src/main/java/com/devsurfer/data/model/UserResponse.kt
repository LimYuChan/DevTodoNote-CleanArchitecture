package com.devsurfer.data.model

data class UserResponse(
    val id: Int,
    val email: String? = "",
    val login: String? = "",
    val public_repos: Int,
    val total_private_repos: Int,
    val owned_private_repos: Int,
    val avatar_url: String,
    val html_url: String,
)
