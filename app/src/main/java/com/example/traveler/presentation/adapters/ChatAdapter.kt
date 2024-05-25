package com.example.traveler.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.traveler.data.room.storage.entity.User
import com.example.traveler.databinding.ChatListItemLayoutBinding
import com.example.traveler.databinding.ChatListItemLayoutRightBinding

class ChatAdapter(private val context: Context) : ListAdapter<User, RecyclerView.ViewHolder>(ItemComparator()) {

    companion object {
        private const val VIEW_TYPE_SENT = 0
        private const val VIEW_TYPE_RECEIVED = 1
    }

    class ItemHolder(private val binding: ChatListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            with(binding) {
                tvMessage.text = user.message
                tvName.text = user.name
                tvTime.text = user.currentTime
            }
        }
    }

    class ReceivedMessageHolder(private val binding: ChatListItemLayoutRightBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            with(binding) {
                tvMessage.text = user.message
                tvName.text = user.name
                tvTime.text = user.currentTime
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val user = getItem(position)
        val prefs = context.getSharedPreferences("NAME", android.content.Context.MODE_PRIVATE)
        val name = prefs.getString("nameGlobal", null)
        if (user.name.toString() == name.toString()) return VIEW_TYPE_SENT else return VIEW_TYPE_RECEIVED
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_RECEIVED -> {
                val binding = ChatListItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ItemHolder(binding)
            }
            VIEW_TYPE_SENT -> {
                val binding = ChatListItemLayoutRightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ReceivedMessageHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val user = getItem(position)
        when (holder) {
            is ItemHolder -> holder.bind(user)
            is ReceivedMessageHolder -> holder.bind(user)
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
}