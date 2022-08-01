package com.devsurfer.devtodonote_cleanarchitecture.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devsurfer.domain.enums.TodoState
import com.devsurfer.domain.manager.UserDataManager
import com.devsurfer.domain.model.note.Note
import com.devsurfer.domain.model.userData.RepositoryEvent
import com.devsurfer.domain.state.Failure
import com.devsurfer.domain.state.ResourceState
import com.devsurfer.domain.useCase.todo.GetTodoListUseCase
import com.devsurfer.domain.useCase.userData.GetRepositoryEventsUseCase
import com.devsurfer.domain.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val getTodoListUseCase: GetTodoListUseCase,
    private val getRepositoryEventsUseCase: GetRepositoryEventsUseCase,
    private val userDataManager: UserDataManager
): ViewModel(){

    private val _todoList = Channel<ResourceState<List<Note>>>()
    val todoList = _todoList.receiveAsFlow()

    private val _repositoryEvents = Channel<ResourceState<List<RepositoryEvent>>>()
    val repositoryEvents = _repositoryEvents.receiveAsFlow()

    fun getTodoList(repositoryId: Int, state: TodoState){

       getTodoListUseCase(repositoryId = repositoryId, state = state).onEach {
            _todoList.send(it)
        }.catch {
            Log.d(TAG, "getTodoList: ${it.message}")
            _todoList.send(ResourceState.Error(failure = Failure.UnHandleError(it.message ?: Constants.TOAST_ERROR_UNHANDLED)))
        }.launchIn(viewModelScope)
    }

    fun getRepositoryEvents(repo: String){
        getRepositoryEventsUseCase(owner = userDataManager.getUser()?.login ?: "", repo = repo).onEach {
            _repositoryEvents.send(it)
        }.catch {
            Log.d(TAG, "getRepositoryEvents: ${it.message}")
            _repositoryEvents.send(ResourceState.Error(failure = Failure.UnHandleError(it.message ?: Constants.TOAST_ERROR_UNHANDLED)))
        }.launchIn(viewModelScope)
    }

    companion object{
        private const val TAG = "TodoListViewModel"
    }
}