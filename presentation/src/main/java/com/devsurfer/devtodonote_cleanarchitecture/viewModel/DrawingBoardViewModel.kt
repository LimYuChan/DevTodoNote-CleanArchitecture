package com.devsurfer.devtodonote_cleanarchitecture.viewModel

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devsurfer.devtodonote_cleanarchitecture.base.BaseViewModel
import com.devsurfer.domain.item.DrawingBoard
import com.devsurfer.domain.item.DrawingPoint
import com.devsurfer.domain.state.Failure
import com.devsurfer.domain.state.ResourceState
import com.devsurfer.domain.useCase.util.SaveBitmapToImageUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import java.lang.NullPointerException
import javax.inject.Inject

@HiltViewModel
class DrawingBoardViewModel @Inject constructor(
    private val bitmapSaveUseCase: SaveBitmapToImageUseCase
) : BaseViewModel() {

    private val _saveState = Channel<ResourceState<DrawingBoard>>()
    val saveState = _saveState.receiveAsFlow()

    val isLoading: LiveData<Boolean> get() = _isLoading

    fun saveDrawingBoard(pathPointList: List<DrawingPoint>, canvasScreenShot: Bitmap) {
        modelScope.launch {
            _isLoading.postValue(true)
            val pathPointSaveJob = Gson().toJson(pathPointList)
            val saveBitmap = bitmapSaveUseCase.invoke(bitmap = canvasScreenShot)

            if(saveBitmap.isNullOrBlank()){
                _saveState.send(ResourceState.Error(failure = Failure.UnHandleError()))
            }else{
                _saveState.send(
                    ResourceState.Success(
                        data = DrawingBoard(
                            fileJsonString = pathPointSaveJob,
                            fileImageUrl = saveBitmap
                        )
                    )
                )
            }
            _isLoading.postValue(false)
        }
    }
}