package com.devsurfer.devtodonote_cleanarchitecture.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devsurfer.devtodonote_cleanarchitecture.R
import com.devsurfer.devtodonote_cleanarchitecture.databinding.ItemRepositoryBinding
import com.devsurfer.devtodonote_cleanarchitecture.extension.getLanguageColor
import com.devsurfer.devtodonote_cleanarchitecture.extension.setDrawableTint
import com.devsurfer.domain.model.userData.UserRepository
import com.devsurfer.domain.util.StringUtils.convertCurrentDateTime

class UserRepositoryAdapter(
    val onRepoItemClick: (UserRepository) -> Unit
): ListAdapter<UserRepository, UserRepositoryAdapter.RepositoryItemViewHolder>(diffUtil){
    inner class RepositoryItemViewHolder(val binding: ItemRepositoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: UserRepository) {
            with(binding) {
                repo = data
                tvRepoLanguage.compoundDrawables
                tvRepoLanguage.setDrawableTint(tvRepoLanguage.context.getLanguageColor(data.language))
                val updatedAt = if (data.updatedAt == null) {
                    if (data.createdAt == null) {
                        ""
                    } else {
                        data.createdAt!!.convertCurrentDateTime()
                    }
                } else {
                    data.updatedAt!!.convertCurrentDateTime()
                }
                tvRepoAccess.background.setTint(
                    if (data.private) tvRepoAccess.context.getColor(R.color.icon_color)
                    else tvRepoAccess.context.getColor(R.color.repo_public_color)
                )
                tvRepoUpdatedAt.text = updatedAt
                tvRepoUpdatedAt.visibility = if (updatedAt.isBlank()) View.GONE else View.VISIBLE
                itemRoot.setOnClickListener {
                    onRepoItemClick(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryItemViewHolder =
        RepositoryItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_repository,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: RepositoryItemViewHolder, position: Int) {
        currentList[position]?.let { holder.bind(it) }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<UserRepository>() {
            override fun areItemsTheSame(oldItem: UserRepository, newItem: UserRepository): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: UserRepository, newItem: UserRepository): Boolean = oldItem == newItem
        }
    }
}