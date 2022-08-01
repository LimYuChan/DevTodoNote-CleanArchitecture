package com.devsurfer.devtodonote_cleanarchitecture.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devsurfer.devtodonote_cleanarchitecture.R
import com.devsurfer.devtodonote_cleanarchitecture.databinding.ItemSelectedImageBinding
import com.esafirm.imagepicker.model.Image

class SelectedImageAdapter(
    val deleteItem: (Image) -> Unit
): ListAdapter<Image, SelectedImageAdapter.SelectedImageViewHolder>(diffUtil){

    inner class SelectedImageViewHolder(val binding: ItemSelectedImageBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: Image){
            binding.image = data
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
        val diffUtil = object: DiffUtil.ItemCallback<Image>(){
            override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean = oldItem == newItem
        }
    }
}