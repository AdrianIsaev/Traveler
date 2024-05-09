package com.example.traveler.data.room.storage.entity

data class Publication(
    val title: String,
    val author: String,
    val description: String,
    val numberOfLikes: Int,
    val numberOfComments: Int,
    val address: String,
    val uri: String
)
