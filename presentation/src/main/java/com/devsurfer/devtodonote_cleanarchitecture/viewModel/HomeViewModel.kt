package com.devsurfer.devtodonote_cleanarchitecture.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devsurfer.domain.manager.UserDataManager
import com.devsurfer.domain.model.userData.User
import com.devsurfer.domain.model.userData.UserRepository
import com.devsurfer.domain.state.Failure
import com.devsurfer.domain.state.ResourceState
import com.devsurfer.domain.useCase.userData.GetUserRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRepositoriesUseCase: GetUserRepositoriesUseCase,
    private val userDataManager: UserDataManager
): ViewModel(){

    private val _repositories = Channel<ResourceState<List<UserRepository>>>()
    val repositories = _repositories.receiveAsFlow()
    private val _userData = MutableLiveData<ResourceState<User>>()
    val userData: LiveData<ResourceState<User>> get() = _userData


    fun loadUserData(){
        viewModelScope.launch {
            userDataManager.getUserWithUpdate().apply {
                if(this == null){
                    _userData.value = ResourceState.Error(failure = Failure.UnAuthorizeUser)
                }else{
                    Log.d(TAG, "loadUserData: $this")
                    _userData.value = ResourceState.Success(data = this)
                    getUserRepositories()
                }
            }
        }
    }

    private fun getUserRepositories(){
        getRepositoriesUseCase().onEach {
            _repositories.send(it)
        }.catch { exception ->
            Log.e(TAG, "casedBy: ${exception.message}")
            _repositories.send(ResourceState.Error(failure = Failure.UnHandleError(exception.message ?: "")))
        }.launchIn(viewModelScope)
    }

    companion object{
        private const val TAG = "HomeViewModel"
    }
}