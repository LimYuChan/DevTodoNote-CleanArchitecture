package com.devsurfer.devtodonote_cleanarchitecture.state.ui

sealed class SplashUiState{
    object AuthorizeUser: SplashUiState()
    object UnAuthorizeUser: SplashUiState()
}
