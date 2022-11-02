package com.rijaldev.travelist.data

import com.rijaldev.travelist.model.Tourism

interface TourismRepository {
    fun getTourismPlaces(): List<Tourism>

    fun getTourismPlaceById(id: Int): Tourism

    fun searchTourismPlaces(query: String): List<Tourism>

    fun updateTourismPlace(id: Int, newState: Boolean)
}