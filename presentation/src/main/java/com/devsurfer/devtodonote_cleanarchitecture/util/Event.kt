package com.devsurfer.devtodonote_cleanarchitecture.util

/**
 * Created by Jaehyeon on 2022/08/05.
 * Error Event 를 위해서 만듦.
 * 추가로 ViewModel 에서 이벤트 처리를 할 때는 이걸 통해서 하는 것이 좀 더 깔끔할 듯 합니다.
 */
open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): T = content
}