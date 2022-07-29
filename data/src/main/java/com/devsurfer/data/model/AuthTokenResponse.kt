package com.devsurfer.data.model

data class AuthTokenResponse(
    val access_token: String,
    val expires_in: Int,
    val refresh_token: String? = null,
    val refresh_token_expires_in: Int,
    val scope: String? = null,
    val token_type: String? = null
)
