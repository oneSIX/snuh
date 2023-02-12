package com.kents.snuh.features.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.kents.core.commons.PublishFlow
import com.kents.core.domain.GetObservation
import com.kents.core.domain.models.StateDisplayModel
import com.kents.core.domain.models.StateData
import com.kents.snuh.features.list.ListViewModel.ListScreenUiState.ErrorFromAPI
import com.kents.snuh.features.list.ListViewModel.ListScreenUiState.LoadingFromAPI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getStateCapitals: GetObservation
) : ViewModel() {

    private val events = PublishFlow<Event>()

    private val _state = MutableStateFlow<ListScreenUiState>(LoadingFromAPI)
    val state = _state.asStateFlow()

    init {
        events
            .filterIsInstance<Event.Refresh>()
            .onStart { updateObservationList() }
            .onEach {
                _state.value = LoadingFromAPI
                updateObservationList()
            }
            .launchIn(viewModelScope)
    }

    fun refresh() {
        viewModelScope.launch { events.emit(Event.Refresh) }
    }

    //THis may be a BAAAD thing and call the API way in teh heck too often...
    private suspend fun updateObservationList() {

        val newList = mutableListOf<StateDisplayModel>()
        StateData.capitals.forEach { stateCapital ->
            getStateCapitals(stateCapital.stationId)
                .onSuccess {
                    // create a display model from the StateCapital + Observation response
                    newList.add(
                        StateDisplayModel(
                            cityName = stateCapital.cityName,
                            state = stateCapital.state,
                            currentTemp = it.currentTemp ?: 0.0, // TODO handle nulls ehh
                            unitCode = it.unitCode,
                            timeStamp = it.timeStamp
                        )
                    )
                    _state.value = ListScreenUiState.UpdateSuccess(newList.toList())
                }
                .onFailure {
                    _state.value = ErrorFromAPI
                }
        }
    }


    private sealed class Event {
        object Refresh : Event()
    }

    sealed interface ListScreenUiState {
        object LoadingFromAPI : ListScreenUiState
        data class UpdateSuccess(val stateCapitals: List<StateDisplayModel>) : ListScreenUiState
        object ErrorFromAPI : ListScreenUiState
    }

}
