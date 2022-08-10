package com.devsurfer.devtodonote_cleanarchitecture.util.notification

import androidx.annotation.StringRes
import com.devsurfer.devtodonote_cleanarchitecture.R

// Todo Notification 종류 추가 해야 함.
enum class NotificationType(@StringRes message: Int) {
    D_DAY(R.string.notification_d_day),
}
