package com.kents.snuh.features.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import com.kents.core.domain.GetForecast
import com.kents.core.domain.models.Forecast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel @AssistedInject constructor(
    @Assisted stateCapital: String,
    getForecast: GetForecast,
) : ViewModel() {

    private val _updateState =
        MutableStateFlow<UiState>(UiState.LoadingFromAPI)
    val updateState = _updateState.asStateFlow()

    init {
        viewModelScope.launch {
//            getBook(capitalName)
//                .onSuccess { _updateState.value = UiState.Success(it) }
//                .onFailure { _updateState.value = UiState.ErrorFromAPI }
            getForecast(stateCapital)
                .onSuccess { _updateState.value = UiState.Success(it) }
                .onFailure { _updateState.value = UiState.ErrorFromAPI }

        }
    }

//    sealed interface UiState {
//        object LoadingFromAPI : UiState
//        data class Success(val book: BookDetails) : UiState
//        object ErrorFromAPI : UiState
//    }
    sealed interface UiState {
        object LoadingFromAPI : UiState
        data class Success(val forecast: List<Forecast>) : UiState
        object ErrorFromAPI : UiState
    }

    @AssistedFactory
    interface Factory {
        fun create(stateCapital: String): DetailsViewModel
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun provideFactory(
            factory: Factory,
            stateCapital: String,
        ) = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return factory.create(stateCapital) as T
            }
        }
    }
}
