package com.devsurfer.devtodonote_cleanarchitecture.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.devsurfer.devtodonote_cleanarchitecture.R
import com.devsurfer.devtodonote_cleanarchitecture.base.BaseActivity
import com.devsurfer.devtodonote_cleanarchitecture.databinding.ActivityMainBinding
import com.devsurfer.devtodonote_cleanarchitecture.util.Utils
import com.devsurfer.devtodonote_cleanarchitecture.util.connect.ConnectObserver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var navController: NavController

    @Inject
    lateinit var networkStatus: ConnectObserver

    override fun initData() {

    }

    override fun initUI() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    /**
     * Network 가 끊겼을 때 UI를 만들어서 넣는게 좋아보입니다.
     */
    override fun initListener() {
        lifecycleScope.launchWhenCreated {
            networkStatus.observer().collectLatest { status ->
                when (status) {
                    ConnectObserver.Status.Available -> {

                    }

                    else -> {
                        showShortToast(getString(R.string.plz_check_internet))
                        Utils.logError(javaClass.simpleName, "Network Status : ${status.name}")
                    }
                }
            }
        }
    }

    override fun initObserver() {

    }
}