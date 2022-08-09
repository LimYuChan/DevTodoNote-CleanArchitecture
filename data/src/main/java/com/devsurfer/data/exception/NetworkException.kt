package com.devsurfer.data.exception

import com.devsurfer.data.util.Constants

sealed class NetworkException(message: String): Throwable(message = message){
    class NotModified(message: String = Constants.ERROR_MESSAGE_NOT_MODIFIED): NetworkException(message)
    class BadRequest(message: String = Constants.ERROR_MESSAGE_BAD_REQUEST): NetworkException(message)
    class RequiresAuthentication(message: String = Constants.ERROR_MESSAGE_REQUIRES_AUTHENTICATION): NetworkException(message)
    class Forbidden(message: String = Constants.ERROR_MESSAGE_FORBIDDEN): NetworkException(message)
    class ResourceNotFound(message: String = Constants.ERROR_MESSAGE_RESOURCE_NOT_FOUNT): NetworkException(message)
    class ValidationFailed(message: String = Constants.ERROR_MESSAGE_VALIDATION_FAILED): NetworkException(message)
}
