package com.rijaldev.travelist.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rijaldev.travelist.data.TourismRepository
import com.rijaldev.travelist.model.Tourism
import com.rijaldev.travelist.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val tourismRepository: TourismRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<Tourism>> = MutableStateFlow(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getTourismPlaceById(id: Int) = viewModelScope.launch {
        tourismRepository.getTourismPlaceById(id)
            .catch {
                _uiState.value = UiState.Error(it.message.toString())
            }
            .collect {
                _uiState.value = UiState.Success(it)
            }
    }

    fun updateTourismPlace(id: Int, newState: Boolean) = viewModelScope.launch {
        tourismRepository.updateTourismPlace(id, !newState)
            .collect { isUpdated ->
                if (isUpdated) getTourismPlaceById(id)
            }
    }
}