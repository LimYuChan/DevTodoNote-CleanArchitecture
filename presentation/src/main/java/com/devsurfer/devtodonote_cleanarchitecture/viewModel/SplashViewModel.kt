package com.devsurfer.devtodonote_cleanarchitecture.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devsurfer.data.manager.PreferenceManager
import com.devsurfer.devtodonote_cleanarchitecture.BuildConfig
import com.devsurfer.devtodonote_cleanarchitecture.state.ui.SplashUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    preferenceManager: PreferenceManager
): ViewModel(){

    private var accessToken: String = ""

    private val _authorizeState  = MutableSharedFlow<SplashUiState>()
    val authorizeState = _authorizeState.asSharedFlow()

    init {
        accessToken = try{
            preferenceManager.getAccessToken()
        }catch (e: UnsupportedOperationException){
            ""
        }
    }

    fun checkAuthorize(){
        val delayTime = if(BuildConfig.DEBUG) 1_000L else 2_000L
        viewModelScope.launch {
            delay(delayTime)
            if(accessToken.isBlank()){
                _authorizeState.emit(SplashUiState.UnAuthorizeUser)
            }else{
                _authorizeState.emit(SplashUiState.AuthorizeUser)
            }
        }
    }
}