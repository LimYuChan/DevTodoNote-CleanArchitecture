package com.devsurfer.data.mapper.auth

import com.devsurfer.data.model.auth.AuthTokenResponse
import com.devsurfer.domain.model.auth.AuthToken

object AuthTokenMapper{
    fun mapperToModel(response: AuthTokenResponse): AuthToken =
        AuthToken(
            accessToken = response.access_token,
            expiresIn = response.expires_in,
            refreshToken = response.refresh_token,
            refreshTokenExpiresIn = response.refresh_token_expires_in,
            scope = response.scope,
            tokenType = response.token_type
        )

    fun mapperToResponse(authToken: AuthToken): AuthTokenResponse =
        AuthTokenResponse(
            access_token = authToken.accessToken,
            expires_in = authToken.expiresIn,
            refresh_token = authToken.refreshToken,
            refresh_token_expires_in = authToken.refreshTokenExpiresIn,
            scope = authToken.scope,
            token_type = authToken.tokenType
        )

}