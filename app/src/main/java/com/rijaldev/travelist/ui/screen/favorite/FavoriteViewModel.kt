package com.rijaldev.travelist.ui.screen.favorite

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
class FavoriteViewModel @Inject constructor(
    private val tourismRepository: TourismRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<Tourism>>> = MutableStateFlow(UiState.Loading)
    val uiState get() = _uiState.asStateFlow()

    fun getFavoriteTourism() = viewModelScope.launch {
        tourismRepository.getFavoriteTourism()
            .catch {
                _uiState.value = UiState.Error(it.message.toString())
            }
            .collect {
                _uiState.value = UiState.Success(it)
            }
    }

    fun updateTourismPlace(id: Int, newState: Boolean) {
        tourismRepository.updateTourismPlace(id, newState)
        getFavoriteTourism()
    }
}