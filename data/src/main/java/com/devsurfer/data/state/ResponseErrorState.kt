package com.devsurfer.data.state

import com.devsurfer.domain.state.Failure

sealed class ResponseErrorState<T>{
    data class Success<T>(val data: T): ResponseErrorState<T>()
    data class Error<T>(val failure: Failure = Failure.UnHandleError()): ResponseErrorState<T>()
}
