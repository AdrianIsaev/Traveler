package com.example.traveler.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.traveler.R
import com.example.traveler.data.room.storage.entity.LocalRosterModel
import com.example.traveler.databinding.ListItemLayoutBinding

class ProjectAdapter(private val context: Context) :
    RecyclerView.Adapter<ProjectAdapter.ViewHolder>() {
    private var localRosterModelList: List<LocalRosterModel> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ListItemLayoutBinding>(
            LayoutInflater.from(parent.context),
            R.layout.list_item_layout,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (localRosterModelList.isNotEmpty()) {
            val rosterModel = localRosterModelList[position]
            holder.binding.localRosterModel = rosterModel
            holder.binding.executePendingBindings()
        }
    }

    fun setProjects(projects: List<LocalRosterModel>) {
        localRosterModelList = projects
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return localRosterModelList.size
    }

    inner class ViewHolder(val binding: ListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}