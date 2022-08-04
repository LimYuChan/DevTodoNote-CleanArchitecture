package com.devsurfer.devtodonote_cleanarchitecture.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T: ViewDataBinding>(
    @LayoutRes val layoutRes: Int
): AppCompatActivity(){
    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        initData()
        initUI()
        initObserver()
        initListener()
    }


    abstract fun initData()
    abstract fun initUI()
    abstract fun initListener()
    // 2022.08.05 loading 과 LiveData 를 처리할 함수. by. jaehyeon
    abstract fun initObserver()

    protected fun showShortToast(message: String?) =
        Toast.makeText(this, message ?: "", Toast.LENGTH_SHORT).show()

    protected fun showLongToast(message: String?) =
        Toast.makeText(this, message ?: "", Toast.LENGTH_LONG).show()
}