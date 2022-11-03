package com.rijaldev.travelist.data

import com.rijaldev.travelist.model.Tourism
import com.rijaldev.travelist.model.TourismData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TourismRepositoryImpl @Inject constructor() : TourismRepository {

    private val dummyTourism = mutableListOf<Tourism>()

    init {
        if (dummyTourism.isEmpty()) {
            dummyTourism.addAll(TourismData.dummyTourism)
        }
    }

    override fun getTourismPlaces() = flow {
        emit(dummyTourism)
    }

    override fun getTourismPlaceById(id: Int): Flow<Tourism> {
        return flowOf(dummyTourism.first { it.id == id })
    }

    override fun getFavoriteTourism(): Flow<List<Tourism>> {
        return flowOf(dummyTourism.filter { it.isFavorite })
    }

    override fun searchTourismPlaces(query: String) = flow {
        val data = dummyTourism.filter {
            it.name.contains(query, ignoreCase = true)
        }
        emit(data)
    }

    override fun updateTourismPlace(id: Int, newState: Boolean): Flow<Boolean> {
        val index = dummyTourism.indexOfFirst { it.id == id }
        val result = if (index >= 0) {
            val tourism = dummyTourism[index]
            dummyTourism[index] = tourism.copy(isFavorite = newState)
            true
        } else {
            false
        }
        return flowOf(result)
    }
}