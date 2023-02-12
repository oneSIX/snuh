package com.kents.snuh.features.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kents.core.commons.PublishFlow
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import com.kents.core.domain.GetForecast
import com.kents.core.domain.models.Forecast
import com.kents.core.domain.models.ForecastPeriod
import com.kents.snuh.features.details.DetailsViewModel.DetailScreenUiState.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.launch

class DetailsViewModel @AssistedInject constructor(
    @Assisted geoCode: String,
    private val getForecast: GetForecast,
) : ViewModel() {

    private val events = PublishFlow<Event>()

    private val _state =
        MutableStateFlow<DetailScreenUiState>(LoadingFromAPI)
    val state = _state.asStateFlow()

    init {
        events
            .filterIsInstance<Event.Refresh>()
            .onStart {
                requestForecast(geoCode)
            }
            .onEach {
                _state.value = LoadingFromAPI
                requestForecast(geoCode)
            }
            .launchIn(viewModelScope)
    }

    private suspend fun requestForecast(geoCode: String) {
        getForecast(geoCode)
            .onSuccess { _state.value = Success(it) }
            .onFailure { _state.value = ErrorFromAPI }
    }

    fun refresh() {
        viewModelScope.launch { events.emit(Event.Refresh) }
    }

    private sealed class Event {
        object Refresh : Event()
    }

    sealed interface DetailScreenUiState {
        object LoadingFromAPI : DetailScreenUiState

        // I'm not sure how to model the state correctly for a non-empty list.
        data class Success(val forecasts: List<ForecastPeriod>) : DetailScreenUiState
        object ErrorFromAPI : DetailScreenUiState
    }

    @AssistedFactory
    interface Factory {
        fun create(geoCode: String): DetailsViewModel
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
