package com.devsurfer.data.di

import com.devsurfer.data.service.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    @RetrofitModule.Auth
    fun provideAuthService(@RetrofitModule.Auth retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)
}