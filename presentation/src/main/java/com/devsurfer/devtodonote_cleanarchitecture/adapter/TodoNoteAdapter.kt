package com.devsurfer.devtodonote_cleanarchitecture.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devsurfer.devtodonote_cleanarchitecture.R
import com.devsurfer.devtodonote_cleanarchitecture.databinding.ItemTodoNoteBinding
import com.devsurfer.domain.model.note.Note

class TodoNoteAdapter(
    val onItemClick: (Note) -> Unit
): ListAdapter<Note, TodoNoteAdapter.TodoNoteViewHolder>(diffUtil){

    inner class TodoNoteViewHolder(val binding: ItemTodoNoteBinding): RecyclerView.ViewHolder(binding.itemRoot){
        fun bind(data: Note){
            binding.note = data
            binding.itemRoot.setOnClickListener {
                onItemClick(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoNoteViewHolder =
        TodoNoteViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_todo_note, parent, false))

    override fun onBindViewHolder(holder: TodoNoteViewHolder, position: Int) {
        currentList[position]?.let{ holder.bind(it) }
    }

    companion object{
        val diffUtil = object: DiffUtil.ItemCallback<Note>(){
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean = oldItem.content.contentId == newItem.content.contentId
            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean = oldItem == newItem
        }
    }
}