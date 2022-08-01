package com.devsurfer.devtodonote_cleanarchitecture.base

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.devsurfer.devtodonote_cleanarchitecture.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheet<T: ViewDataBinding>(
    @LayoutRes val layoutResource: Int
): BottomSheetDialogFragment(){

    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResource, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    override fun onResume() {
        super.onResume()
        initListener()
    }

    abstract fun initData()
    abstract fun initUI()
    abstract fun initListener()

    protected fun showShortToast(message: String?){
        if(activity != null && isAdded){
            Toast.makeText(context, message ?: "", Toast.LENGTH_SHORT).show()
        }
    }

    protected fun showLongToast(message: String?){
        if(activity != null && isAdded){
            Toast.makeText(context, message ?: "", Toast.LENGTH_LONG).show()
        }
    }

    override fun dismiss() {
        if(activity != null && isAdded){
            super.dismiss()
        }
    }
}