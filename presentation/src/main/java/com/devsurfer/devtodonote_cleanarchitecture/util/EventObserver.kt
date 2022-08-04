package com.devsurfer.devtodonote_cleanarchitecture.util

import androidx.lifecycle.Observer

/**
 * Created by Jaehyeon on 2022/08/05.
 * Event를 Observer 할 클래스.
 */
class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>?> {

    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let { value ->
            onEventUnhandledContent(value)
        }
    }

}