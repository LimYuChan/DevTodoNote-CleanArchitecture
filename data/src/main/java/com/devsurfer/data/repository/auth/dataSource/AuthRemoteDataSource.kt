package com.devsurfer.data.repository.auth.dataSource

import com.devsurfer.data.di.RetrofitModule
import com.devsurfer.data.mapper.auth.toModel
import com.devsurfer.data.service.auth.AuthService
import com.devsurfer.domain.model.auth.AuthToken
import com.devsurfer.domain.state.ResourceState
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    @RetrofitModule.Auth private val service: AuthService
){
    suspend fun getAccessToken(code: String): AuthToken = service.getAccessToken(code = code).toModel()
}