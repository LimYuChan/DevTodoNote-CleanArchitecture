package com.devsurfer.domain.di

import com.devsurfer.domain.manager.UserDataManager
import com.devsurfer.domain.useCase.userData.GetUserDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserDataModule {

    @Singleton
    @Provides
    fun provideUserDataManager(userDataUseCase: GetUserDataUseCase): UserDataManager =
        UserDataManager(userDataUseCase)
}