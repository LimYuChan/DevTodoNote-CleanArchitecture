package com.devsurfer.devtodonote_cleanarchitecture.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devsurfer.devtodonote_cleanarchitecture.R
import com.devsurfer.devtodonote_cleanarchitecture.databinding.ItemReferenceLinkBinding
import com.devsurfer.domain.item.ReferenceLink

class ReferenceLinkAdapter(
    val onItemClick: (ReferenceLink) -> Unit
): ListAdapter<ReferenceLink, ReferenceLinkAdapter.ReferenceLinkViewHolder>(diffUtil){
    inner class ReferenceLinkViewHolder(val binding: ItemReferenceLinkBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: ReferenceLink){
            binding.link = data
            binding.layoutItem.setOnClickListener {
                onItemClick(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReferenceLinkViewHolder =
        ReferenceLinkViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_reference_link, parent, false))

    override fun onBindViewHolder(holder: ReferenceLinkViewHolder, position: Int) {
        currentList[position]?.let { holder.bind(it) }
    }

    companion object{
        val diffUtil = object: DiffUtil.ItemCallback<ReferenceLink>(){
            override fun areItemsTheSame(oldItem: ReferenceLink, newItem: ReferenceLink): Boolean = oldItem.url == newItem.url
            override fun areContentsTheSame(oldItem: ReferenceLink, newItem: ReferenceLink): Boolean = oldItem == newItem
        }
    }
}