package com.devsurfer.devtodonote_cleanarchitecture.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devsurfer.devtodonote_cleanarchitecture.R
import com.devsurfer.devtodonote_cleanarchitecture.databinding.ItemSelectedDrawingBoardBinding
import com.devsurfer.domain.item.DrawingBoard

class DrawingBoardAdapter(
    val onViewItemClick: (Int) -> Unit = {},
    val onItemClick: (DrawingBoard) -> Unit = {}
): ListAdapter<DrawingBoard, DrawingBoardAdapter.SelectedDrawingBoardViewHolder>(diffUtil){

    inner class SelectedDrawingBoardViewHolder(val binding: ItemSelectedDrawingBoardBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: DrawingBoard, position: Int){
            binding.drawingBoard = data
            binding.layoutRoot.setOnClickListener {
                onItemClick(data)
                onViewItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedDrawingBoardViewHolder =
        SelectedDrawingBoardViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_selected_drawing_board, parent, false))

    override fun onBindViewHolder(holder: SelectedDrawingBoardViewHolder, position: Int) {
        currentList[position]?.let{ holder.bind(it, position) }
    }


    companion object{
        val diffUtil = object: DiffUtil.ItemCallback<DrawingBoard>(){
            override fun areItemsTheSame(oldItem: DrawingBoard, newItem: DrawingBoard): Boolean = oldItem.fileJsonString == newItem.fileJsonString
            override fun areContentsTheSame(oldItem: DrawingBoard, newItem: DrawingBoard): Boolean = oldItem == newItem
        }
    }
}