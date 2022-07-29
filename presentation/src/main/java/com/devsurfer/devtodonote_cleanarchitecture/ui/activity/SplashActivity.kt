package com.devsurfer.devtodonote_cleanarchitecture.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.devsurfer.devtodonote_cleanarchitecture.BuildConfig
import com.devsurfer.devtodonote_cleanarchitecture.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Log.d(TAG, "onCreate: ${BuildConfig.GITHUB_CLIENT_ID}")
    }

    companion object{
        private const val TAG = "SplashActivity"
    }
}