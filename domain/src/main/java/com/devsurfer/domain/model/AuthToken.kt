package com.devsurfer.domain.model

data class AuthToken(
    val accessToken: String,
    val expiresIn: Int,
    val refreshToken: String? = null,
    val refreshTokenExpiresIn: Int,
    val scope: String? = null,
    val tokenType: String? = null
)
