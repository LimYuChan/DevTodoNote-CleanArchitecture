package com.devsurfer.devtodonote_cleanarchitecture.util.connect

import kotlinx.coroutines.flow.Flow

/**
 * Created by Jaehyeon on 2022/08/11.
 * Observing 해야 하는 SystemService 개수 대로 구현.
 */
interface ConnectObserver {

    fun observer(): Flow<Status>

    enum class Status {
        Available, Unavailable, Losing, Lost
    }
}