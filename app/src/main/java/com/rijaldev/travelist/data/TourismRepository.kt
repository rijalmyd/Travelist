package com.rijaldev.travelist.data

import com.rijaldev.travelist.model.Tourism
import kotlinx.coroutines.flow.Flow

interface TourismRepository {
    fun getTourismPlaces(): Flow<List<Tourism>>

    fun getTourismPlaceById(id: Int): Flow<Tourism>

    fun getFavoriteTourism(): Flow<List<Tourism>>

    fun searchTourismPlaces(query: String): Flow<List<Tourism>>

    fun updateTourismPlace(id: Int, newState: Boolean): Flow<Boolean>
}