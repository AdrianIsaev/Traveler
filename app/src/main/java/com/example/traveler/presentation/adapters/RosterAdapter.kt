package com.example.traveler.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.traveler.R
import com.example.traveler.data.room.storage.entity.Publication
import com.example.traveler.databinding.ListItemLayoutBinding

class ProjectAdapter : RecyclerView.Adapter<ProjectAdapter.Holder>(){

   private val arrayRoster = ArrayList<Publication>()

    class Holder(view: View) : RecyclerView.ViewHolder(view){
        private val binding = ListItemLayoutBinding.bind(view)

        fun bind(roster: Publication) = with(binding){

            title.text = roster.description
            author.text = roster.author
            description.text = roster.title
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_layout, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return arrayRoster.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(arrayRoster[position])

      }

    fun addRosterLine(roster: Publication){
        arrayRoster.add(roster)
        notifyDataSetChanged()
    }
}