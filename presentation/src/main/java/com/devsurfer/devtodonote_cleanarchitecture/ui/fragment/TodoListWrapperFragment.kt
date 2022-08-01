package com.devsurfer.devtodonote_cleanarchitecture.ui.fragment

import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.devsurfer.devtodonote_cleanarchitecture.R
import com.devsurfer.devtodonote_cleanarchitecture.adapter.TodoListViewpagerFragmentAdapter
import com.devsurfer.devtodonote_cleanarchitecture.base.BaseFragment
import com.devsurfer.devtodonote_cleanarchitecture.databinding.FragmentTodoListWrapperBinding
import com.devsurfer.devtodonote_cleanarchitecture.util.ViewpagerTransform
import com.devsurfer.domain.enums.TodoState
import com.google.android.material.tabs.TabLayoutMediator

class TodoListWrapperFragment(): BaseFragment<FragmentTodoListWrapperBinding>(R.layout.fragment_todo_list_wrapper){

    private lateinit var adapter: TodoListViewpagerFragmentAdapter
    private val args: TodoListWrapperFragmentArgs by navArgs()

    override fun initData() {
    }

    override fun initUI() {
        with(binding){
            repositoryName = args.itemUserRepository.name
            adapter = TodoListViewpagerFragmentAdapter(fragment = this@TodoListWrapperFragment, repositoryId = args.itemUserRepository.id, repositoryName = args.itemUserRepository.name)
            viewpagerNoteState.adapter = adapter
            viewpagerNoteState.isUserInputEnabled = false
            TabLayoutMediator(tabNoteState, viewpagerNoteState){ tab, position ->
                tab.text = tabList[position]
            }.attach()
            viewpagerNoteState.setPageTransformer(ViewpagerTransform())
            buttonCreateNote.setOnClickListener {
                val action = TodoListWrapperFragmentDirections.actionTodoListWrapperFragmentToWriteNoteFragment(args.itemUserRepository.id)
                view?.let { view->
                    Navigation.findNavController(view).navigate(action)
                }
            }
        }
    }

    override fun initListener() {
    }

    companion object{
        val tabList = listOf("All", "Todo", "Done")
    }
}