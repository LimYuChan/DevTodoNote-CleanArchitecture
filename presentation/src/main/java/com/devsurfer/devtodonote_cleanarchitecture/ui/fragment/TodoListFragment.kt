package com.devsurfer.devtodonote_cleanarchitecture.ui.fragment

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.devsurfer.devtodonote_cleanarchitecture.R
import com.devsurfer.devtodonote_cleanarchitecture.base.BaseFragment
import com.devsurfer.devtodonote_cleanarchitecture.databinding.FragmentTodoListBinding
import com.devsurfer.devtodonote_cleanarchitecture.viewModel.TodoListViewModel
import com.devsurfer.domain.enums.TodoState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class TodoListFragment(
    private val todoState: TodoState,
    private val repositoryId: Int
): BaseFragment<FragmentTodoListBinding>(R.layout.fragment_todo_list){

    private val viewModel: TodoListViewModel by viewModels()

    override fun initData() {
        viewModel.getTodoList(repositoryId = repositoryId, state = todoState)
    }

    override fun initUI() {

    }

    override fun initListener() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.todoList.collectLatest {
                Log.d(TAG, "initListener: ${it.toString()}")
            }
        }
    }

    companion object{
        private const val TAG = "TodoListFragment"
    }
}