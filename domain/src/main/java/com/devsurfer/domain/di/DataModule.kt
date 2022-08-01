package com.devsurfer.domain.di

import com.devsurfer.domain.manager.UserDataManager
import com.devsurfer.domain.useCase.note.LinkParseUseCase
import com.devsurfer.domain.useCase.userData.GetUserDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideUserDataManager(userDataUseCase: GetUserDataUseCase): UserDataManager =
        UserDataManager(userDataUseCase)

    @Singleton
    @Provides
    fun provideLinkParseUseCase(): LinkParseUseCase = LinkParseUseCase()
}