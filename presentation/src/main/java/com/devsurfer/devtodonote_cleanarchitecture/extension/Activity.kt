package com.devsurfer.devtodonote_cleanarchitecture.extension

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import com.devsurfer.devtodonote_cleanarchitecture.ui.activity.LoginActivity
import com.devsurfer.domain.state.Failure

fun Activity.errorHandler(failure: Failure?){
    failure?.let {
        Toast.makeText(this.applicationContext, it.message, Toast.LENGTH_SHORT).show()
        if(it is Failure.UnAuthorizeUser){
            this.startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}