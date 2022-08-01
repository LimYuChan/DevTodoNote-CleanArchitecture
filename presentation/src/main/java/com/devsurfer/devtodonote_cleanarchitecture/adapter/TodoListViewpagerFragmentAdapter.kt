package com.devsurfer.devtodonote_cleanarchitecture.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.devsurfer.devtodonote_cleanarchitecture.ui.fragment.TodoListFragment
import com.devsurfer.devtodonote_cleanarchitecture.ui.fragment.TodoListWrapperFragment
import com.devsurfer.domain.enums.TodoState

class TodoListViewpagerFragmentAdapter(
    val fragment: Fragment,
    private val repositoryId: Int,
    private val repositoryName: String
): FragmentStateAdapter(fragment){

    override fun getItemCount(): Int = TodoListWrapperFragment.tabList.size

    override fun createFragment(position: Int): Fragment =
        when(position){
            1 -> TodoListFragment(TodoState.TODO, repositoryId, repositoryName)
            2 -> TodoListFragment(TodoState.DONE, repositoryId, repositoryName)
            else -> TodoListFragment(TodoState.VISIBLE_ALL, repositoryId, repositoryName)
        }
}