package com.devsurfer.data.di

import com.devsurfer.data.repository.auth.dataSource.AuthRemoteDataSource
import com.devsurfer.data.repository.userData.dataSource.UserDataRemoteDataSource
import com.devsurfer.data.service.auth.AuthService
import com.devsurfer.data.service.userData.UserDataService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    @RetrofitModule.Auth
    fun provideAuthDataSource(@RetrofitModule.Auth service: AuthService): AuthRemoteDataSource =
        AuthRemoteDataSource(service = service)

    @Singleton
    @Provides
    @RetrofitModule.Api
    fun provideUserDataSource(@RetrofitModule.Api service: UserDataService): UserDataRemoteDataSource =
        UserDataRemoteDataSource(service = service)
}