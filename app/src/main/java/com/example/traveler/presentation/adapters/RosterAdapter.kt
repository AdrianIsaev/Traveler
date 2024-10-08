package com.example.traveler.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.traveler.R
import com.example.traveler.data.core.RosterAdapterNavigationInterface
import com.example.traveler.data.room.storage.entity.Publication
import com.example.traveler.databinding.ListItemLayoutBinding
import com.example.traveler.presentation.fragments.Roster
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class RosterAdapter(private val context: Roster, private val listener: RosterAdapterNavigationInterface) : RecyclerView.Adapter<RosterAdapter.Holder>(){
    private val arrayRoster = ArrayList<Publication>()

    class Holder(view: View, private val context: Roster, private val listener: RosterAdapterNavigationInterface) : RecyclerView.ViewHolder(view) {
        private val binding = ListItemLayoutBinding.bind(view)


        private val db = FirebaseFirestore.getInstance()
        private val prefsWithName = context.requireContext().getSharedPreferences("NAME", Context.MODE_PRIVATE)
        private var prefsReading = context.requireContext().getSharedPreferences("INDEXES", Context.MODE_PRIVATE)
        private var prefsWriting = context.requireContext().getSharedPreferences("INDEXES", Context.MODE_PRIVATE).edit()
        @SuppressLint("RestrictedApi", "ResourceAsColor")
        fun bind(roster: Publication) = with(binding) {
            if (prefsWithName.getString("nameGlobal", "") == roster.author){
                prinadleznost.setText("your publication")
            }



            val originalText = "Hey! Lets visit ${roster.title}, i need all of you!! ${roster.description}! This will be amazing -_-"
            val maxLength = 30
            val words = originalText.split(" ")
            val lines = mutableListOf<String>()
            var currentLine = StringBuilder()

            for (word in words) {
                if (currentLine.length + word.length + 1 <= maxLength) {
                    if (currentLine.isNotEmpty()) {
                        currentLine.append(" ")
                    }
                    currentLine.append(word)
                } else {
                    lines.add(currentLine.toString())
                    currentLine = StringBuilder(word)
                }
            }

            if (currentLine.isNotEmpty()) {
                lines.add(currentLine.toString())
            }
            val formattedText = lines.joinToString("\n")
            val bundle = bundleOf("1" to formattedText)
                //val bundle = bundleOf("1" to "Hey! Lets visit ${roster.title}, i need all of you!! " +
                 //   "${roster.description}! This will be amazing -_-)")


            val pathToDocument = roster.id
            val pathSegments = pathToDocument.split("/")
            val documentId = pathSegments.last()
            val redColor = ContextCompat.getColor(numberOfLikes.context,R.color.red)
            val whiteColor = ContextCompat.getColor(numberOfLikes.context, R.color.white)
            val publicationRef = db.collection("list_items").document(documentId)

            binding.navigationView.setOnClickListener {
                listener.onShareClick(roster, bundle)
            }
            if (prefsReading.getString(roster.description, "0") == "1"){
                Button2.backgroundTintList = ContextCompat.getColorStateList(Button2.context, R.color.red)
                numberOfLikes.setTextColor(redColor)
            }
            title.text = roster.description
            author.text = roster.author
            description.text = roster.title
            numberOfLikes.text = roster.numberOfLikes.toString()
            numberOfComments.text = roster.numberOfComments.toString()

            Button2.setOnClickListener {
                if (prefsReading.getString(roster.description, "0") == "0") {
                    prefsWriting.putString(roster.description.toString(), "1").commit()

                    Button2.backgroundTintList = ContextCompat.getColorStateList(Button2.context, R.color.red)
                    numberOfLikes.setTextColor(redColor)
                    numberOfLikes.text = (numberOfLikes.text.toString().toInt() + 1).toString()
                    publicationRef.update("numberOfLikes", FieldValue.increment(1))
                        .addOnSuccessListener {
                            Log.d("1", "all's okay")
                        }
                } else {
                    prefsWriting.putString(roster.description.toString(), "0").commit()
                    Button2.backgroundTintList = ContextCompat.getColorStateList(Button2.context, R.color.white)
                    numberOfLikes.setTextColor(whiteColor)
                    numberOfLikes.text = (numberOfLikes.text.toString().toInt() - 1).toString()
                    publicationRef.update("numberOfLikes", FieldValue.increment(-1))
                        .addOnSuccessListener {
                            Log.d("1", "all's okay")
                        }
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
        return Holder(view, context, listener)
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