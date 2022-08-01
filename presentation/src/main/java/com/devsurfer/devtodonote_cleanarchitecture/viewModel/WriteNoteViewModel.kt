package com.devsurfer.devtodonote_cleanarchitecture.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devsurfer.devtodonote_cleanarchitecture.enums.WriteNoteState
import com.devsurfer.devtodonote_cleanarchitecture.util.ListLiveData
import com.devsurfer.domain.model.note.Note
import com.devsurfer.domain.model.note.NoteContent
import com.devsurfer.domain.state.ResourceState
import com.devsurfer.devtodonote_cleanarchitecture.uiEvent.CreateNoteUiEvent
import com.devsurfer.domain.item.DrawingBoard
import com.devsurfer.domain.item.ReferenceLink
import com.devsurfer.domain.model.note.NoteImage
import com.devsurfer.domain.state.Failure
import com.devsurfer.domain.useCase.note.CreateNoteUseCase
import com.devsurfer.domain.useCase.note.GetLastContentIdUseCase
import com.devsurfer.domain.useCase.note.UpdateNoteUseCase
import com.devsurfer.domain.util.Constants
import com.devsurfer.domain.util.StringUtils
import com.esafirm.imagepicker.model.Image
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import java.lang.NullPointerException
import javax.inject.Inject

@HiltViewModel
class WriteNoteViewModel @Inject constructor(
    private val getLastContentIdUseCase: GetLastContentIdUseCase,
    private val createNoteUseCase: CreateNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase
): ViewModel(){

    private var originBranch = ""

    private var writeNoteState = WriteNoteState.Create

    private val _content = MutableLiveData(NoteContent())
    val content: LiveData<NoteContent> get() = _content

    private val _imageList = ListLiveData<Image>()
    val imageList: LiveData<ArrayList<Image>> get() = _imageList

    private val _drawingBoardList = ListLiveData<DrawingBoard>()
    val drawingBoardList: LiveData<ArrayList<DrawingBoard>> get() = _drawingBoardList

    private val _referenceLinkList = ListLiveData<ReferenceLink>()
    val referenceLinkList: LiveData<ArrayList<ReferenceLink>> get() = _referenceLinkList

    private val _submit = Channel<ResourceState<Unit>>()
    val submit = _submit.receiveAsFlow()

    /**
     * update data
     */
    fun initData(note: Note?){
        if(note == null){
            writeNoteState = WriteNoteState.Create
            _content.value = _content.value?.copy(branch = createNewBranch())
        }else{
            writeNoteState = WriteNoteState.Edit
            _content.value = note.content
            if(note.content.branch == null){
                _content.value = _content.value?.copy(branch = createNewBranch())
            }else{
                originBranch = note.content.branch!!
            }
            _imageList.update(note.imageList.map { Image(it.fileId, it.fileName, it.fileUrl) })
            _drawingBoardList.update(note.drawingBoardList.map { DrawingBoard(it.fileJsonString, it.fileImageUrl) })
            _referenceLinkList.update(note.referenceLinkList.map { ReferenceLink(it.title, it.description, it.link, it.image) })
        }
    }

    fun initRepositoryId(repositoryId: Int){
        _content.value = _content.value?.copy(repositoryId = repositoryId)
    }

    private fun updateContentMemoData(content: String){
        _content.value = _content.value?.copy(content = content)
    }

    fun updateBranchName(branchName: String? = null){
        _content.value = _content.value?.copy(branch = branchName ?: createNewBranch())
        Log.d(TAG, "updateBranchName: ${_content.value}")
    }

    fun getWriterState(): WriteNoteState{
        return this.writeNoteState
    }

    fun getNowBranch(): String{
        return _content.value?.branch ?: createNewBranch()
    }
    /**
     * ui event receive
     */
    fun onEvent(event: CreateNoteUiEvent){
        when(event){
            is CreateNoteUiEvent.AddImages-> _imageList.addAll(event.fileUrls)
            is CreateNoteUiEvent.RemoveImage-> _imageList.remove(event.fileUrl)
            is CreateNoteUiEvent.AddDrawingBoard-> _drawingBoardList.add(event.fileUrl)
            is CreateNoteUiEvent.RemoveDrawingBoard-> _drawingBoardList.remove(event.fileUrl)
            is CreateNoteUiEvent.AddReferenceLink-> _referenceLinkList.add(event.link)
            is CreateNoteUiEvent.RemoveReferenceLink-> _referenceLinkList.remove(event.link)
            is CreateNoteUiEvent.ChangeBranchName-> _content.value = _content.value?.copy(branch = event.branchName)
            is CreateNoteUiEvent.ResetBranchName-> _content.value = _content.value?.copy(branch = createNewBranch())
            is CreateNoteUiEvent.Submit->{
                updateContentMemoData(event.content)
                if(writeNoteState == WriteNoteState.Edit && _content.value != null){
                    editNote()
                }else{
                    createNote()
                }
            }
        }
    }

    private fun createNote(){
        _content.value?.let { content ->
            CoroutineScope(Dispatchers.IO).launch(coroutineExceptionHandler) {
                _submit.send(ResourceState.Loading())
                val createNoteResult = createNoteUseCase(
                    content = content,
                    images = _imageList.value?.toList()?.map { NoteImage(fileId = it.id, fileName = it.name, fileUrl = it.path) } ?: emptyList(),
                    drawingBoards = _drawingBoardList.value?.toList() ?: emptyList(),
                    referenceLinks = _referenceLinkList.value?.toList() ?: emptyList()
                )
                _submit.send(createNoteResult)
            }
        }
    }

    private fun editNote(){
        _content.value?.let { content ->
            CoroutineScope(Dispatchers.IO).launch(coroutineExceptionHandler) {
                _submit.send(ResourceState.Loading())
                val createNoteResult = updateNoteUseCase(
                    content = content,
                    images = _imageList.value?.toList()?.map { NoteImage(fileId = it.id, fileName = it.name, fileUrl = it.path) } ?: emptyList(),
                    drawingBoards = _drawingBoardList.value?.toList() ?: emptyList(),
                    referenceLinks = _referenceLinkList.value?.toList() ?: emptyList()
                )
                _submit.send(createNoteResult)
            }
        }
    }


    private fun createNewBranch():String = runBlocking {
        try{
            "${Constants.DEFAULT_NEW_BRANCH_TITLE}-${getLastContentIdUseCase().plus(1)}"
        }catch (e: NullPointerException){
            "${Constants.DEFAULT_NEW_BRANCH_TITLE}-${StringUtils.getRandomBranchNumber()}"
        }
    }


    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, exception ->
        viewModelScope.launch {
            Log.d(TAG, ":${exception.message} ")
            _submit.send(ResourceState.Error(failure = Failure.UnHandleError(exception.message ?: "")))
        }
    }

    companion object{
        private const val TAG = "WriteNoteViewModel"
    }
}