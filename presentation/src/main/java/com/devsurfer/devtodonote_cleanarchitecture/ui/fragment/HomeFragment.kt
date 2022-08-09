package com.devsurfer.devtodonote_cleanarchitecture.ui.fragment

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
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
        val action = HomeFragmentDirections.actionHomeFragmentToTodoListWrapperFragment(it)
        view?.let { view->
            Navigation.findNavController(view).navigate(action)
        }
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
        viewModel.userData.observe(viewLifecycleOwner){
            binding.user = it
            binding.layoutUserProfile.setOnClickListener { view ->
                if(isAttachInActivity()){
                    Utils.openProfileBottomSheet(childFragmentManager, it.htmlUrl)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenResumed { 
            viewModel.repositories.collectLatest {
                adapter.submitList(it)
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }
    }
    
    companion object{
        private const val TAG = "HomeFragment"
    }
}