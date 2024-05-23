package com.example.traveler.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.traveler.data.room.storage.entity.User
import com.example.traveler.databinding.ChatListItemLayoutBinding

class ChatAdapter : ListAdapter<User, ChatAdapter.ItemHolder>(ItemComparator()) {
    class ItemHolder(private val binding: ChatListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) = with(binding) {
            tvMessage.text = user.message
            tvName.text = user.name
            tvTime.text = user.currentTime
        }
        companion object {
            fun create(parent: ViewGroup): ItemHolder {
                return ItemHolder(
                    ChatListItemLayoutBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }
        }
    }

    class ItemComparator : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(getItem(position))
    }
}