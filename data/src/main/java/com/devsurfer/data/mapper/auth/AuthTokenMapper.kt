package com.devsurfer.data.mapper.auth

import com.devsurfer.data.model.auth.AuthTokenResponse
import com.devsurfer.domain.model.auth.AuthToken

fun AuthTokenResponse.toModel(): AuthToken =
    AuthToken(
        accessToken = this.access_token,
        expiresIn = this.expires_in,
        refreshToken = this.refresh_token,
        refreshTokenExpiresIn = this.refresh_token_expires_in,
        scope = this.scope,
        tokenType = this.token_type
    )


fun AuthToken.toResponse(): AuthTokenResponse =
    AuthTokenResponse(
        access_token = this.accessToken,
        expires_in = this.expiresIn,
        refresh_token = this.refreshToken,
        refresh_token_expires_in = this.refreshTokenExpiresIn,
        scope = this.scope,
        token_type = this.tokenType
    )