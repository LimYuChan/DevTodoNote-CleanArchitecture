package com.devsurfer.devtodonote_cleanarchitecture.util.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.devsurfer.devtodonote_cleanarchitecture.ui.activity.MainActivity

/**
 * Created by Jaehyeon on 2022/08/10.
 */
class NotificationService constructor(
    private val context: Context
) {

    private val manager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    //Todo 언제 보여줄지 기능 정의가 필요.
    fun showNotification(notificationType: NotificationType) {

        val activityIntent = Intent(context, MainActivity::class.java)

        when (notificationType) {
            NotificationType.D_DAY -> {
                val activityPendingIntent = PendingIntent.getActivity(
                    context,
                    1,
                    activityIntent.apply {
                        putExtra("repo_id", "")
                    },
                    PendingIntent.FLAG_IMMUTABLE
                )
            }
            else -> {

            }
        }
    }

    companion object {
        const val CHANNEL_ID = "d-day_channel"
    }
}