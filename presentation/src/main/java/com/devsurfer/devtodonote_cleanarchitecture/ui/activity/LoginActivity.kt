package com.devsurfer.devtodonote_cleanarchitecture.ui.activity

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.lifecycleScope
import com.devsurfer.devtodonote_cleanarchitecture.R
import com.devsurfer.devtodonote_cleanarchitecture.base.BaseActivity
import com.devsurfer.devtodonote_cleanarchitecture.databinding.ActivityLoginBinding
import com.devsurfer.devtodonote_cleanarchitecture.viewModel.LoginViewModel
import com.devsurfer.domain.state.ResourceState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class LoginActivity: BaseActivity<ActivityLoginBinding>(R.layout.activity_login){

    private val viewModel: LoginViewModel by viewModels()

    override fun initData() {
    }

    override fun initUI() {
        binding.buttonLogin.setOnClickListener{
            onLoginClick()
        }
    }

    override fun initListener() {
        lifecycleScope.launchWhenResumed {
            viewModel.loginState.collectLatest {
                when(it){
                    is ResourceState.Success->{
                        binding.layoutProgress.visibility = View.GONE
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    }
                    is ResourceState.Error->{
                        binding.layoutProgress.visibility = View.GONE
                        showShortToast(it.failure.message)
                    }
                    else ->{
                        binding.layoutProgress.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun onLoginClick(){
        CustomTabsIntent.Builder().build().also {
            it.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            it.launchUrl(applicationContext, viewModel.getLoginUri())
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.data?.let {
            val code = it.getQueryParameter("code")
            val state = it.getQueryParameter(LoginViewModel.GITHUB_AUTH_RESULT_STATE_KEY)
            if(code != null && state != null && viewModel.validateLoginStateKey(state)){
                viewModel.getAccessToken(code)
            }
        }
    }

    companion object{
        private const val TAG = "LoginActivity"
    }
}