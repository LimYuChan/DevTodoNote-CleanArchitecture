package com.devsurfer.domain.state

import com.devsurfer.domain.util.Constants

sealed class Failure(val message: String){
    object UnAuthorizeUser: Failure(Constants.TOAST_ERROR_UN_AUTHORIZE)
    object BadRequest: Failure(Constants.TOAST_ERROR_NOT_FOUND)
    object NetworkConnection: Failure(Constants.TOAST_ERROR_INTERNET_CONNECTED)
    class UnHandleError(message: String = Constants.TOAST_ERROR_UNHANDLED): Failure(message)
}
