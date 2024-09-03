package com.example.traveler.data.room.storage.entity

import java.util.UUID

val uniqueId = UUID.randomUUID().toString()
data class Publication(
    val id: String = uniqueId,
    val title: String,
    val author: String,
    val description: String,
    val numberOfLikes: Int,
    val numberOfComments: Int,
    val address: String,
    val uri: String
)