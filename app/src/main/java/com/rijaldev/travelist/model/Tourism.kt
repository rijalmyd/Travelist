package com.rijaldev.travelist.model

data class Tourism(
    val id: Int,
    val name: String,
    val description: String,
    val location: String,
    val photoUrl: String,
    val rating: Double,
    var isFavorite: Boolean = false,
)
