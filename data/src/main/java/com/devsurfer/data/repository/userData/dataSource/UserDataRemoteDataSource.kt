package com.devsurfer.data.repository.userData.dataSource

import com.devsurfer.data.di.RetrofitModule
import com.devsurfer.data.extension.errorHandler
import com.devsurfer.data.mapper.userData.RepositoryEventMapper
import com.devsurfer.data.mapper.userData.UserMapper
import com.devsurfer.data.mapper.userData.UserRepositoryMapper
import com.devsurfer.data.service.userData.UserDataService
import com.devsurfer.data.state.ResponseErrorState
import com.devsurfer.domain.model.userData.RepositoryEvent
import com.devsurfer.domain.model.userData.User
import com.devsurfer.domain.model.userData.UserRepository
import com.devsurfer.domain.state.ResourceState
import javax.inject.Inject

class UserDataRemoteDataSource @Inject constructor(
    @RetrofitModule.Api private val service: UserDataService
){
    suspend fun getUserData(): ResourceState<User>{
        return when(val response = service.getUserData().errorHandler()){
            is ResponseErrorState.Success ->{
                ResourceState.Success(data = UserMapper.mapperToModel(response = response.data))
            }
            is ResponseErrorState.Error->{
                ResourceState.Error(failure = response.failure)
            }
        }
    }

    suspend fun getUserRepositories(): ResourceState<List<UserRepository>>{
        return when(val response = service.getUserRepos().errorHandler()){
            is ResponseErrorState.Success->{
                val repositories = response.data.map { UserRepositoryMapper.mapperToModel(it) }
                ResourceState.Success(data = repositories)
            }
            is ResponseErrorState.Error->{
                ResourceState.Error(failure = response.failure)
            }
        }
    }

    suspend fun getRepositoryEvents(owner: String, repo: String): ResourceState<List<RepositoryEvent>>{
        return when(val response = service.getUserRepositoryEvents(owner, repo).errorHandler()){
            is ResponseErrorState.Success->{
                val events = response.data.map { RepositoryEventMapper.mapperToModel(it) }
                ResourceState.Success(data = events)
            }
            is ResponseErrorState.Error->{
                ResourceState.Error(failure = response.failure)
            }
        }
    }
}