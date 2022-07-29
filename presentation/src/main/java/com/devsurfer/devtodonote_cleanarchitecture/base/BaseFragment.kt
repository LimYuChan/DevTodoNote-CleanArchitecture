package com.devsurfer.devtodonote_cleanarchitecture.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.devsurfer.devtodonote_cleanarchitecture.extension.errorHandler
import com.devsurfer.domain.state.Failure

abstract class BaseFragment<T: ViewDataBinding>(
    @LayoutRes val layoutRes: Int
): Fragment(){

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
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this.viewLifecycleOwner
        initUI()
        initListener()
    }

    abstract fun initData()
    abstract fun initUI()
    abstract fun initListener()


    fun showShortToast(message: String?){
        context?.let {
            Toast.makeText(it, message ?: "", Toast.LENGTH_SHORT).show()
        }
    }

    fun showLongToast(message: String?){
        context?.let {
            Toast.makeText(it, message ?: "", Toast.LENGTH_LONG).show()
        }
    }

    fun onBackPress(){
        if(activity != null && isAdded){
            requireActivity().onBackPressed()
        }
    }

    fun errorHandler(failure: Failure?){
        if(activity != null && isAdded){
            requireActivity().errorHandler(failure)
        }
    }
}