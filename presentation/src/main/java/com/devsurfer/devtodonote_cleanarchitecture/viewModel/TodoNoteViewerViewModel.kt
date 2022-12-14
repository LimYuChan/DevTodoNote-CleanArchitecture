package com.devsurfer.devtodonote_cleanarchitecture.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devsurfer.devtodonote_cleanarchitecture.base.BaseViewModel
import com.devsurfer.domain.enums.BranchState
import com.devsurfer.domain.enums.TodoState
import com.devsurfer.domain.manager.UserDataManager
import com.devsurfer.domain.model.note.Note
import com.devsurfer.domain.state.Failure
import com.devsurfer.domain.state.ResourceState
import com.devsurfer.domain.useCase.note.DeleteNoteUseCase
import com.devsurfer.domain.useCase.note.GetNoteUseCase
import com.devsurfer.domain.useCase.note.UpdateNoteContentUseCase
import com.devsurfer.domain.useCase.userData.GetBranchEventUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class TodoNoteViewerViewModel @Inject constructor(
    private val getBranchEventUseCase: GetBranchEventUseCase,
    private val getNoteUseCase: GetNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val userDataManager: UserDataManager,
    private val updateNoteContentUseCase: UpdateNoteContentUseCase
): BaseViewModel(){

    private val _note = MutableLiveData<Note>()
    val note: LiveData<Note> get() = _note

    private val _deleteState = Channel<ResourceState<Unit>>()
    val deleteState = _deleteState.receiveAsFlow()

    fun initData(note: Note){
        _note.value = note
    }

    fun getNoteData(){
        _note.value?.let { oldNote->
            modelScope.launch{
                getNoteUseCase(oldNote.content.contentId)?.let { newNote ->
                    _note.value = newNote
                }
            }
        }
    }

    fun deleteNote(){
        _note.value?.let {
            modelScope.launch(coroutineExceptionHandler) {
                _deleteState.send(ResourceState.Loading())
                val deleteCount = async {
                    deleteNoteUseCase(it.content.contentId)
                }.await()
                if(deleteCount > 0){
                    _deleteState.send(ResourceState.Success(Unit))
                }else{
                    _deleteState.send(ResourceState.Error(failure = Failure.UnHandleError()))
                }
            }
        }
    }
    
    fun updateBranchState(repo: String){
        _note.value?.let {
            modelScope.launch{
                val result = getBranchEventUseCase(owner = userDataManager.getUser()?.login ?: "", repo = repo, branchName = it.content.branch ?: "")
                if(result is ResourceState.Success){
                    _note.value?.content?.let {
                        withContext(Dispatchers.Default) {
                            updateNoteContentUseCase(
                                it.copy(branchState = result.data.value, status = if(result.data.value != BranchState.NONE.value) TodoState.DONE.value else TodoState.TODO.value)
                            )
                        }
                        getNoteData()
                    }
                }
            }
        }
    }
    
    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, exception ->
        modelScope.launch {
            Log.d(TAG, ":${exception.message} ")
            _deleteState.send(ResourceState.Error(failure = Failure.UnHandleError(exception.message ?: "")))
        }
    }

    companion object{
        private const val TAG = "TodoNoteViewerViewModel"
    }
}