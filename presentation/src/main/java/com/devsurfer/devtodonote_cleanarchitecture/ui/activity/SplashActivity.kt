package com.devsurfer.devtodonote_cleanarchitecture.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.devsurfer.data.manager.PreferenceManager
import com.devsurfer.devtodonote_cleanarchitecture.BuildConfig
import com.devsurfer.devtodonote_cleanarchitecture.R
import com.devsurfer.devtodonote_cleanarchitecture.base.BaseActivity
import com.devsurfer.devtodonote_cleanarchitecture.databinding.ActivitySplashBinding
import com.devsurfer.devtodonote_cleanarchitecture.state.ui.SplashUiState
import com.devsurfer.devtodonote_cleanarchitecture.viewModel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class SplashActivity: BaseActivity<ActivitySplashBinding>(R.layout.activity_splash){

    private val viewModel: SplashViewModel by viewModels()

    override fun initData() {
    }

    override fun initUI() {
        viewModel.checkAuthorize()
    }

    override fun initListener() {
        lifecycleScope.launchWhenCreated {
            viewModel.authorizeState.collectLatest {
                if(it is SplashUiState.AuthorizeUser){
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }else{
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                    finish()
                }
            }
        }
    }
    
    companion object{
        private const val TAG = "SplashActivity"
    }
}