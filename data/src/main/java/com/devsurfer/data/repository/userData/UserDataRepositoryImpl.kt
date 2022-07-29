package com.devsurfer.data.repository.userData

import com.devsurfer.data.repository.userData.dataSource.UserDataRemoteDataSource
import com.devsurfer.domain.model.userData.User
import com.devsurfer.domain.repository.UserDataRepository
import com.devsurfer.domain.state.ResourceState
import javax.inject.Inject

class UserDataRepositoryImpl @Inject constructor(
    private val dataSource: UserDataRemoteDataSource
): UserDataRepository{
    override suspend fun getUserData(): ResourceState<User> =
        dataSource.getUserData()
}