package com.devsurfer.devtodonote_cleanarchitecture.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devsurfer.data.manager.PreferenceManager
import com.devsurfer.devtodonote_cleanarchitecture.BuildConfig
import com.devsurfer.domain.manager.UserDataManager
import com.devsurfer.domain.state.Failure
import com.devsurfer.domain.state.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    preferenceManager: PreferenceManager,
    private val userDataManager: UserDataManager
): ViewModel(){

    private var accessToken: String = ""

    private val _authorizeState  = MutableSharedFlow<ResourceState<Unit>>()
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
                _authorizeState.emit(ResourceState.Error(failure = Failure.UnAuthorizeUser))
            }else{
                if(userDataManager.getUser() != null){
                    _authorizeState.emit(ResourceState.Success(Unit))
                }else{
                    _authorizeState.emit(ResourceState.Error(failure = Failure.UnAuthorizeUser))
                }
            }
        }
    }
}