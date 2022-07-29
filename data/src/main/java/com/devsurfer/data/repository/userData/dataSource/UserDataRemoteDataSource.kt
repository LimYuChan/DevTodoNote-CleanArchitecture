package com.devsurfer.data.repository.userData.dataSource

import com.devsurfer.data.di.RetrofitModule
import com.devsurfer.data.extension.errorHandler
import com.devsurfer.data.mapper.AuthTokenMapper
import com.devsurfer.data.mapper.UserMapper
import com.devsurfer.data.service.UserDataService
import com.devsurfer.data.state.ResponseErrorState
import com.devsurfer.domain.model.User
import com.devsurfer.domain.state.ResourceState
import javax.inject.Inject

class UserDataRemoteDataSource @Inject constructor(
    @RetrofitModule.Api private val service: UserDataService
){
    suspend fun getUserData(): ResourceState<User>{
        return when(val response = service.getUserData().errorHandler()){
            is ResponseErrorState.Success ->
                ResourceState.Success(data = UserMapper.mapperToUser(response = response.data))
            is ResponseErrorState.Error->{
                ResourceState.Error(failure = response.failure)
            }
        }
    }
}