package com.devsurfer.devtodonote_cleanarchitecture.util.connect

import javax.inject.Qualifier

/**
 * 구현 해야 하는 Observer 구분을 위한 Annotation
 */

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NetworkObserver