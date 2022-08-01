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
            when(it){
                is ResourceState.Success->{
                    binding.user = it.data
                    binding.layoutUserProfile.setOnClickListener { view ->
                        if(isAttachInActivity()){
                            Utils.openProfileBottomSheet(childFragmentManager, it.data.htmlUrl)
                        }
                    }
                }
                is ResourceState.Error->{
                    errorHandler(it.failure)
                }
                else->{

                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenResumed { 
            viewModel.repositories.collectLatest {
                Log.d(TAG, "initListener: $it")
                when(it){
                    is ResourceState.Success->{
                        binding.layoutLoadingProgress.root.visibility = View.GONE
                        adapter.submitList(it.data)
                    }
                    is ResourceState.Error->{
                        binding.layoutLoadingProgress.root.visibility = View.GONE
                        errorHandler(it.failure)
                    }
                    else->{
                        binding.layoutLoadingProgress.root.visibility = View.VISIBLE
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