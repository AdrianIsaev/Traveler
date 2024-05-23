package com.example.traveler.presentation.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.traveler.R
import com.example.traveler.data.room.storage.entity.Publication
import com.example.traveler.databinding.ListItemLayoutBinding
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

class ProjectAdapter : RecyclerView.Adapter<ProjectAdapter.Holder>(){

   private val arrayRoster = ArrayList<Publication>()

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ListItemLayoutBinding.bind(view)
        val db = FirebaseFirestore.getInstance()
        fun bind(roster: Publication) = with(binding) {
            val pathToDocument = roster.id
            val pathSegments = pathToDocument.split("/")
            val documentId = pathSegments.last()
            val publicationRef = db.collection("list_items").document(documentId)
            title.text = roster.description
            author.text = roster.author
            description.text = roster.title
            numberOfLikes.text = roster.numberOfLikes.toString()
            numberOfComments.text = roster.numberOfComments.toString()
            Button2.setOnClickListener {
                    publicationRef.update("numberOfLikes", FieldValue.increment(1))
                        .addOnSuccessListener {
                            Log.d("1", "alls okey")
                        }
            }
            if (roster.uri != "empty") {
                Glide.with(itemView)
                    .load(roster.uri)
                    .into(imageViewRoster)
            }
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