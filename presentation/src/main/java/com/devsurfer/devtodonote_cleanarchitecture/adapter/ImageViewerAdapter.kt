package com.devsurfer.devtodonote_cleanarchitecture.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.devsurfer.devtodonote_cleanarchitecture.R
import com.devsurfer.devtodonote_cleanarchitecture.databinding.ItemImageViewerBinding

class ImageViewerAdapter(
    val list: List<String>,
    val isCacheFile: Boolean
): RecyclerView.Adapter<ImageViewerAdapter.ImageViewerViewHolder>(){

    inner class ImageViewerViewHolder(val binding: ItemImageViewerBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: String){
            binding.image = data
            binding.isCacheFile = isCacheFile
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewerViewHolder =
        ImageViewerViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_image_viewer, parent, false))

    override fun onBindViewHolder(holder: ImageViewerViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}