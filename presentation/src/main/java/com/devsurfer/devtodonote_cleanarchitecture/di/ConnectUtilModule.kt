package com.devsurfer.devtodonote_cleanarchitecture.di

import android.content.Context
import com.devsurfer.devtodonote_cleanarchitecture.util.connect.ConnectObserver
import com.devsurfer.devtodonote_cleanarchitecture.util.connect.NetworkConnectObserver
import com.devsurfer.devtodonote_cleanarchitecture.util.connect.NetworkObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Jaehyeon on 2022/08/11.
 * ConnectObserver 를 통해서 Network 상태를 실시간으로 파악해서 기능의 예외처리 양을 줄이는 의도.
 * 추가 기능으로 만약 다른 Observer가 필요한 경우
 * ConnectObserver 를 Implementation 하는 방향성으로 구현해서
 * Dagger-Hilt 에서 Annotation 으로 구분해서 구현.
 */
@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object ConnectUtilModule {

    @Provides
    @NetworkObserver
    fun provideNetworkConnectObserver(
        @ApplicationContext context: Context
    ): ConnectObserver {
        return NetworkConnectObserver(context)
    }

}