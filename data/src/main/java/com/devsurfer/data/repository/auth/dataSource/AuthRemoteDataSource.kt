package com.devsurfer.data.repository.auth.dataSource

import com.devsurfer.data.di.RetrofitModule
import com.devsurfer.data.extension.errorHandler
import com.devsurfer.data.mapper.AuthTokenMapper
import com.devsurfer.data.service.AuthService
import com.devsurfer.data.state.ResponseErrorState
import com.devsurfer.domain.model.auth.AuthToken
import com.devsurfer.domain.state.ResourceState
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    @RetrofitModule.Auth private val service: AuthService
){
    suspend fun getAccessToken(code: String): ResourceState<AuthToken>{
        return when(val response = service.getAccessToken(code = code).errorHandler()){
            is ResponseErrorState.Success ->
                ResourceState.Success(data = AuthTokenMapper.mapperToAuthToken(response.data))
            is ResponseErrorState.Error->{
                ResourceState.Error(failure = response.failure)
            }
        }
    }
}