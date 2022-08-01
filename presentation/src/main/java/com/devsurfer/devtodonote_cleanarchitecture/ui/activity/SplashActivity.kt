package com.devsurfer.devtodonote_cleanarchitecture.ui.activity

import android.Manifest
import android.content.Intent
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.devsurfer.devtodonote_cleanarchitecture.R
import com.devsurfer.devtodonote_cleanarchitecture.base.BaseActivity
import com.devsurfer.devtodonote_cleanarchitecture.databinding.ActivitySplashBinding
import com.devsurfer.devtodonote_cleanarchitecture.viewModel.SplashViewModel
import com.devsurfer.domain.state.ResourceState
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class SplashActivity: BaseActivity<ActivitySplashBinding>(R.layout.activity_splash){

    private val viewModel: SplashViewModel by viewModels()

    override fun initData() {
    }

    override fun initUI() {
        val permissionListener: PermissionListener = object : PermissionListener {
            override fun onPermissionGranted() {
                viewModel.checkAuthorize()
            }

            override fun onPermissionDenied(deniedPermissions: List<String>) {
                showShortToast(getString(R.string.permission_denied_message))
            }
        }
        TedPermission.create()
            .setPermissionListener(permissionListener)
            .setDeniedMessage(getString(R.string.permission_denied_message))
            .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
            .check();
    }

    override fun initListener() {
        lifecycleScope.launchWhenCreated {
            viewModel.authorizeState.collectLatest {
                if(it is ResourceState.Success){
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