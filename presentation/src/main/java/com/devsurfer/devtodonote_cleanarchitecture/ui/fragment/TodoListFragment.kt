package com.devsurfer.devtodonote_cleanarchitecture.ui.fragment

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.devsurfer.devtodonote_cleanarchitecture.R
import com.devsurfer.devtodonote_cleanarchitecture.adapter.TodoNoteAdapter
import com.devsurfer.devtodonote_cleanarchitecture.base.BaseFragment
import com.devsurfer.devtodonote_cleanarchitecture.databinding.FragmentTodoListBinding
import com.devsurfer.devtodonote_cleanarchitecture.viewModel.TodoListViewModel
import com.devsurfer.domain.enums.TodoState
import com.devsurfer.domain.state.ResourceState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class TodoListFragment(
    private val todoState: TodoState,
    private val repositoryId: Int,
    private val repositoryName: String
): BaseFragment<FragmentTodoListBinding>(R.layout.fragment_todo_list){

    private val viewModel: TodoListViewModel by viewModels()

    private val adapter = TodoNoteAdapter{

    }

    override fun initData() {
        viewModel.getRepositoryEvents(repositoryName)
    }

    override fun initUI() {
        with(binding){
            rvTodoNote.adapter = adapter
            swipeRefreshLayout.setOnRefreshListener {
                viewModel.getTodoList(repositoryId = repositoryId, state = todoState)
            }
        }
    }

    override fun initListener() {
        viewModel.getTodoList(repositoryId = repositoryId, state = todoState)
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.todoList.collectLatest {
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
                    is ResourceState.Loading->{
                        binding.layoutLoadingProgress.root.visibility = View.VISIBLE
                    }
                }
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.repositoryEvents.collectLatest {
                when(it){
                    is ResourceState.Success->{
                        Log.d(TAG, "initListener: ${it.data.filter { it.type == "PullRequestEvent" }}")
                    }
                    is ResourceState.Error->{
                        errorHandler(it.failure)
                    }
                    is ResourceState.Loading->{

                    }
                }
            }
        }
    }

    companion object{
        private const val TAG = "TodoListFragment"
    }
}