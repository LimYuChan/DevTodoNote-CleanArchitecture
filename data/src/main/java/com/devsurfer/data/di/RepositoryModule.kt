package com.devsurfer.data.di

import com.devsurfer.data.repository.auth.AuthRepositoryImpl
import com.devsurfer.data.repository.auth.data_source.AuthRemoteDataSource
import com.devsurfer.domain.repository.AuthRepository
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
}