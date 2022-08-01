package com.devsurfer.domain.di

import android.content.Context
import com.devsurfer.domain.useCase.util.SaveBitmapToImageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilModule {

    @Singleton
    @Provides
    fun provideSaveBitmapToImageUseCase(@ApplicationContext context: Context) = SaveBitmapToImageUseCase(context)
}