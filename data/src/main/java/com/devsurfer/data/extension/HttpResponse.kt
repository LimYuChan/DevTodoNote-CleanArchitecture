package com.devsurfer.data.extension

import com.devsurfer.data.state.ResponseErrorState
import com.devsurfer.domain.state.Failure
import com.devsurfer.domain.state.ResourceState
import com.devsurfer.domain.util.Constants
import okhttp3.internal.http2.ErrorCode
import retrofit2.Response
import java.io.IOException

inline fun <reified T: Any> Response<T>.errorHandler(): ResponseErrorState<T>{
    return try{
        when(this.code()){
            Constants.CODE_UN_AUTHORIZE -> ResponseErrorState.Error(failure = Failure.UnAuthorizeUser)
            Constants.CODE_BAD_REQUEST -> ResponseErrorState.Error(failure = Failure.BadRequest)
            else->{
                val body = this.body()
                return if(body == null){
                    ResponseErrorState.Error(failure = Failure.UnHandleError())
                }else{
                    ResponseErrorState.Success(data = body as T)
                }
            }
        }
    }catch (e: IOException){
        ResponseErrorState.Error(failure = Failure.NetworkConnection)
    }catch (t: Throwable){
        ResponseErrorState.Error(failure = Failure.UnHandleError())
    }
}