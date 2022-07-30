package com.devsurfer.data.di

import com.devsurfer.data.repository.auth.AuthRepositoryImpl
import com.devsurfer.data.repository.auth.dataSource.AuthRemoteDataSource
import com.devsurfer.data.repository.userData.UserDataRepositoryImpl
import com.devsurfer.data.repository.userData.dataSource.UserDataRemoteDataSource
import com.devsurfer.domain.repository.AuthRepository
import com.devsurfer.domain.repository.UserDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideAuthRepository(dataSource: AuthRemoteDataSource): AuthRepository =
        AuthRepositoryImpl(dataSource = dataSource)

    @Singleton
    @Provides
    fun provideUserDataRepository(dataSource: UserDataRemoteDataSource): UserDataRepository =
        UserDataRepositoryImpl(dataSource = dataSource)
}