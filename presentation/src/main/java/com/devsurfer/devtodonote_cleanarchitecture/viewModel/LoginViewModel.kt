package com.devsurfer.devtodonote_cleanarchitecture.viewModel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devsurfer.data.BuildConfig
import com.devsurfer.data.manager.PreferenceManager
import com.devsurfer.domain.state.Failure
import com.devsurfer.domain.state.ResourceState
import com.devsurfer.domain.useCase.auth.GetAccessTokenUseCase
import com.devsurfer.domain.util.StringUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: GetAccessTokenUseCase,
    private val preferenceManager: PreferenceManager
): ViewModel(){
    /**
     * loginStateKey = Github 로그인 방식이 Web으로 보낸 후 redirection 해주는 방식인데
     * redirect 과정에서 위변조가 있을수도 있기 때문에 보안을 위해 임의의 문자열 state를 저장해놓고 로그인 결과값과 비교하는게 좋을 것 같다.
     */

    private val _loginState = MutableSharedFlow<ResourceState<Unit>>()
    val loginState = _loginState.asSharedFlow()


    private var loginStateKey =""

    fun getLoginUri(): Uri{
        loginStateKey = StringUtils.getRandomString(32)
        return Uri.Builder()
            .scheme("https")
            .authority("github.com")
            .appendPath("login")
            .appendPath("oauth")
            .appendPath("authorize")
            .appendQueryParameter(GITHUB_AUTH_RESULT_STATE_KEY, loginStateKey)
            .appendQueryParameter("scope","repo, read:user")
            .appendQueryParameter("client_id", BuildConfig.GITHUB_CLIENT_ID)
            .build()
    }

    fun validateLoginStateKey(state: String): Boolean = loginStateKey == state

    fun getAccessToken(code: String){
        if(preferenceManager.clear()){
            useCase(code = code).onEach {
                when(it){
                    is ResourceState.Success->{
                        updateAccessToken(it.data.accessToken)
                    }
                    is ResourceState.Error->{
                        _loginState.emit(ResourceState.Error(failure = it.failure))
                    }
                    else->{
                        _loginState.emit(ResourceState.Loading())
                    }
                }
            }.catch { exception ->
                Log.e(TAG, "casedBy: ${exception.message}")
                _loginState.emit(ResourceState.Error(failure = Failure.UnHandleError(exception.message ?: "")))
            }.launchIn(viewModelScope)
        }
    }

    private fun updateAccessToken(accessToken: String){
        viewModelScope.launch {
            _loginState.emit(
                if(preferenceManager.updateAccessToken(accessToken)){
                    ResourceState.Success(Unit)
                }else{
                    ResourceState.Error()
                }
            )
        }
    }

    companion object{
        private const val TAG = "LoginViewModel"
        const val GITHUB_AUTH_RESULT_STATE_KEY = "state"
    }
}