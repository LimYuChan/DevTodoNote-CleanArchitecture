package com.devsurfer.devtodonote_cleanarchitecture.ui.activity

import android.Manifest
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.devsurfer.devtodonote_cleanarchitecture.R
import com.devsurfer.devtodonote_cleanarchitecture.base.BaseActivity
import com.devsurfer.devtodonote_cleanarchitecture.databinding.ActivitySplashBinding
import com.devsurfer.devtodonote_cleanarchitecture.util.Utils
import com.devsurfer.devtodonote_cleanarchitecture.util.connect.ConnectObserver
import com.devsurfer.devtodonote_cleanarchitecture.viewModel.SplashViewModel
import com.devsurfer.domain.state.ResourceState
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject


@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    private val viewModel: SplashViewModel by viewModels()

    private val permissionListener: PermissionListener = object : PermissionListener {
        override fun onPermissionGranted() {
            viewModel.checkAuthorize()
        }

        override fun onPermissionDenied(deniedPermissions: List<String>) {
            showShortToast(getString(R.string.permission_denied_message))
        }
    }

    @Inject
    lateinit var networkStatus: ConnectObserver

    override fun initData() {
    }

    override fun initUI() {
        lifecycleScope.launchWhenCreated {
            networkStatus.observer().collectLatest { status ->
                when(status) {
                    ConnectObserver.Status.Available -> {
                        TedPermission.create()
                            .setPermissionListener(permissionListener)
                            .setDeniedMessage(getString(R.string.permission_denied_message))
                            .setPermissions(
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE
                            )
                            .check()
                    }

                    else -> {
                        showShortToast(getString(R.string.plz_check_internet))
                        Utils.logError(javaClass.simpleName, "Network Status : ${status.name}")
                    }
                }
            }
        }

    }

    override fun initListener() {
        lifecycleScope.launchWhenCreated {
            viewModel.authorizeState.collectLatest {
                if (it is ResourceState.Success) {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                } else {
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                    finish()
                }
            }
        }

    }

    companion object {
        private const val TAG = "SplashActivity"
    }

    override fun initObserver() {
        viewModel.loading.observe(this) {
            if (it) binding.layoutLoadingProgress.root.visibility = View.VISIBLE
            else binding.layoutLoadingProgress.root.visibility = View.GONE
        }

    }
}