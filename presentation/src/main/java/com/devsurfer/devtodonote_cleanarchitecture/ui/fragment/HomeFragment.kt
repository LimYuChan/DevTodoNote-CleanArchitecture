package com.devsurfer.devtodonote_cleanarchitecture.ui.fragment

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.devsurfer.devtodonote_cleanarchitecture.R
import com.devsurfer.devtodonote_cleanarchitecture.adapter.UserRepositoryAdapter
import com.devsurfer.devtodonote_cleanarchitecture.base.BaseFragment
import com.devsurfer.devtodonote_cleanarchitecture.databinding.FragmentHomeBinding
import com.devsurfer.devtodonote_cleanarchitecture.util.Utils
import com.devsurfer.devtodonote_cleanarchitecture.viewModel.HomeViewModel
import com.devsurfer.domain.state.ResourceState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()

    private val adapter = UserRepositoryAdapter{

    }

    override fun initData() {
        viewModel.loadUserData()
    }

    override fun initUI() {
        with(binding){
            rvMyRepos.adapter = adapter
            rvMyRepos.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            swipeRefreshLayout.setOnRefreshListener { 
                viewModel.loadUserData()
            }
        }
    }

    override fun initListener() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.userData.observe(viewLifecycleOwner){
                when(it){
                    is ResourceState.Success->{
                        binding.user = it.data
                        binding.layoutUserProfile.setOnClickListener { view ->
                            if(activity != null && isAdded){
                                Utils.openProfileBottomSheet(childFragmentManager, it.data.htmlUrl)
                            }
                        }
                    }
                    is ResourceState.Error->{
                        errorHandler(it.failure)
                    }
                    else -> {}
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenResumed { 
            viewModel.repositories.collectLatest {
                when(it){
                    is ResourceState.Success->{
                        adapter.submitList(it.data)
                    }
                    is ResourceState.Error->{
                        errorHandler(it.failure)
                    }
                    else->{
                        
                    }
                }
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }
    }
    
    companion object{
        private const val TAG = "HomeFragment"
    }
}