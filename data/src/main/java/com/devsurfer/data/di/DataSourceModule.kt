package com.devsurfer.data.di

import com.devsurfer.data.repository.auth.data_source.AuthRemoteDataSource
import com.devsurfer.data.service.AuthService
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
}