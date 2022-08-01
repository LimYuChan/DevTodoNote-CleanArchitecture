package com.devsurfer.devtodonote_cleanarchitecture.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devsurfer.devtodonote_cleanarchitecture.R
import com.devsurfer.devtodonote_cleanarchitecture.databinding.ItemSelectedImageBinding

class SelectedImageAdapter(
    val deleteItem: (String) -> Unit
): ListAdapter<String, SelectedImageAdapter.SelectedImageViewHolder>(diffUtil){

    inner class SelectedImageViewHolder(val binding: ItemSelectedImageBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: String){
            binding.fileUrl = data
            binding.itemRoot.setOnClickListener {
                deleteItem(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedImageViewHolder =
        SelectedImageViewHolder(DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_selected_image,
            parent,
            false
            )
        )


    override fun onBindViewHolder(holder: SelectedImageViewHolder, position: Int) {
        currentList[position]?.let{ holder.bind(it) }
    }

    companion object{
        val diffUtil = object: DiffUtil.ItemCallback<String>(){
            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
        }
    }
}