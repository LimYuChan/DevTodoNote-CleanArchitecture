package com.devsurfer.devtodonote_cleanarchitecture.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devsurfer.domain.enums.TodoState
import com.devsurfer.domain.model.note.Note
import com.devsurfer.domain.state.Failure
import com.devsurfer.domain.state.ResourceState
import com.devsurfer.domain.useCase.todo.GetTodoListUseCase
import com.devsurfer.domain.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val getTodoListUseCase: GetTodoListUseCase
): ViewModel(){

    private val _todoList = MutableSharedFlow<ResourceState<List<Note>>>()
    val todoList = _todoList.asSharedFlow()

    fun getTodoList(repositoryId: Int, state: TodoState){
        getTodoListUseCase(repositoryId = repositoryId, state = state).onEach {
            _todoList.emit(it)
        }.catch {
            Log.d(TAG, "getTodoList: ${it.message}")
            _todoList.emit(ResourceState.Error(failure = Failure.UnHandleError(it.message ?: Constants.TOAST_ERROR_UNHANDLED)))
        }.launchIn(viewModelScope)
    }

    companion object{
        private const val TAG = "TodoListViewModel"
    }
}