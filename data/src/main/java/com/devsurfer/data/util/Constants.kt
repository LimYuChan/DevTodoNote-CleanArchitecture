package com.devsurfer.data.util

object Constants {
    //Github api 에서 발생할 수 있는 exception
    const val ERROR_CODE_NOT_MODIFIED = 304
    const val ERROR_CODE_BAD_REQUEST = 400
    const val ERROR_CODE_REQUIRES_AUTHENTICATION = 401
    const val ERROR_CODE_FORBIDDEN = 403
    const val ERROR_CODE_RESOURCE_NOT_FOUND = 404
    const val ERROR_CODE_VALIDATION_FAILED = 422

    //todo error message 재정의
    const val ERROR_MESSAGE_NOT_MODIFIED = "not modified"
    const val ERROR_MESSAGE_BAD_REQUEST = "bad request"
    const val ERROR_MESSAGE_REQUIRES_AUTHENTICATION = "requires authentication"
    const val ERROR_MESSAGE_FORBIDDEN = "forbidden"
    const val ERROR_MESSAGE_RESOURCE_NOT_FOUNT = "resource not found"
    const val ERROR_MESSAGE_VALIDATION_FAILED = "validation failed"
}