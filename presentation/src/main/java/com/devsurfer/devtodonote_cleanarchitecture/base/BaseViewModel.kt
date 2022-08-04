package com.devsurfer.devtodonote_cleanarchitecture.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devsurfer.devtodonote_cleanarchitecture.BuildConfig
import com.devsurfer.devtodonote_cleanarchitecture.util.Event
import kotlinx.coroutines.*
import java.net.UnknownHostException

/**
 * Created by Jaehyeon on 2022/08/05.
 */
abstract class BaseViewModel: ViewModel() {

    // Loading 에 관한 변수. 뷰모델 LiveData 선언 해서 사용.
    protected val isLoading = MutableLiveData(false)
    // state 에 관한 변수. 특히 Error 처리 할 때 사용.
    protected val error = MutableLiveData<Event<BaseViewModelState>>()

    private val job = SupervisorJob()
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        if (BuildConfig.DEBUG) Log.e(javaClass.simpleName, "CoroutineExceptionHandler: ${throwable.message}", )

        //todo 유찬님이 Throwable 정의 해주면 그거 활용해서 사용.
//        when(throwable) {
//            is UnknownHostException -> error.postValue(Event(BaseViewModelState.UnknownErrorState))
//        }
        coroutineContext.job.cancel()
        error.postValue(Event(BaseViewModelState.UnknownErrorState))
    }

    // todo Scope를 여러개 만들어 놓긴 했는데 ViewModel에서는 modelScope 만 쓰는 것이 좋아보입니다.
    protected val uiScope = CoroutineScope(Dispatchers.Main + job + exceptionHandler)
    protected val ioScope = CoroutineScope(Dispatchers.IO + job + exceptionHandler)
    protected val defaultScope = CoroutineScope(Dispatchers.Default + job + exceptionHandler)
    protected val modelScope = viewModelScope + job + exceptionHandler

    override fun onCleared() {
        super.onCleared()
        if (!job.isCancelled || !job.isCompleted)
            job.cancel()
    }

}