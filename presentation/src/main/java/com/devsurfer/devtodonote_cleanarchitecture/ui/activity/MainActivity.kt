package com.devsurfer.devtodonote_cleanarchitecture.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devsurfer.devtodonote_cleanarchitecture.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}