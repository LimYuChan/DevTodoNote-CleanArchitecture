package com.devsurfer.devtodonote_cleanarchitecture.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devsurfer.domain.model.note.Note
import com.devsurfer.domain.useCase.note.GetNoteUseCase
import com.devsurfer.domain.useCase.userData.GetRepositoryEventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoNoteViewerViewModel @Inject constructor(
    private val getRepositoryEventsUseCase: GetRepositoryEventsUseCase,
    private val getNoteUseCase: GetNoteUseCase
): ViewModel(){

    private val _note = MutableLiveData<Note>()
    val note: LiveData<Note> get() = _note

    fun initData(note: Note){
        _note.value = note
    }

    fun getNoteData(){
        _note.value?.let { oldNote->
            viewModelScope.launch{
                getNoteUseCase(oldNote.content.contentId)?.let { newNote ->
                    _note.value = newNote
                }
            }
        }
    }
}