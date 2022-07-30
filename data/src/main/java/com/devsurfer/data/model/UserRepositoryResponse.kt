package com.devsurfer.data.model

data class UserRepositoryResponse(
    val id: Int,
    val name: String,
    val private: Boolean,
    val html_url: String,
    val description: String? = null,
    val created_at: String? = null,
    val updated_at: String? = null,
    val language: String? = null,
    val default_branch: String
)
