package com.kents.snuh.features.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.kents.core.domain.models.StateDisplayModel
import com.kents.snuh.R
import com.kents.snuh.component.Toast
import com.kents.snuh.features.list.ListViewModel.ListScreenUiState
import com.kents.snuh.features.list.ListViewModel.ListScreenUiState.ErrorFromAPI
import com.kents.snuh.features.list.ListViewModel.ListScreenUiState.LoadingFromAPI
import com.kents.snuh.features.list.ListViewModel.ListScreenUiState.UpdateSuccess
import com.kents.snuh.theme.SnuhTheme
import com.kents.snuh.theme.normalPadding
import com.kents.snuh.theme.smallPadding
import com.kents.snuh.utils.preview.DeviceFormatPreview
import com.kents.snuh.utils.preview.FontScalePreview
import com.kents.snuh.utils.preview.ThemeModePreview

@Composable
fun ListScreen(
    listViewModel: ListViewModel,
    openDetailsClicked: (String) -> Unit,
) {
    val bookListUpdateState by listViewModel.state.collectAsState()

    when (val state = bookListUpdateState) {
        ErrorFromAPI -> ErrorFromApi()
        is UpdateSuccess, LoadingFromAPI -> ListCapitals(
            state = state,
            onRefresh = listViewModel::refresh,
            onDetailsClicked = openDetailsClicked,
        )
    }
}

@Composable
@Suppress("DEPRECATION") // SwipeRefresh migration not available in material 3 just for 2
private fun ListCapitals(
    state: ListScreenUiState,
    onRefresh: () -> Unit,
    onDetailsClicked: (String) -> Unit,
) = SwipeRefresh(
    state = rememberSwipeRefreshState(state == LoadingFromAPI),
    onRefresh = onRefresh,
    modifier = Modifier
        .scrollable(rememberScrollState(), Orientation.Vertical)
        .systemBarsPadding()
        .background(MaterialTheme.colorScheme.surface)
        .padding(normalPadding)
) {

    var stateCapitals by remember { mutableStateOf(emptyList<StateDisplayModel>()) }

    (state as? UpdateSuccess)?.let {
        stateCapitals = it.stateCapitals
    }

    Column {
        Text(
            text = stringResource(id = R.string.state_capital_list),
            style = MaterialTheme.typography.headlineLarge
        )

        LazyColumn {
            itemsIndexed(stateCapitals) { _, capital ->
                Card(
                    shape = RoundedCornerShape(8.dp),
                    onClick = { onDetailsClicked(capital.geoCode) },
                    modifier = Modifier
                        .padding(normalPadding)

                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(normalPadding)
                    ) {
                        Column(horizontalAlignment =  Alignment.Start){
                            Text(
                                text = "${capital.cityName} ${capital.state}",
                                style = MaterialTheme.typography.headlineSmall
                            )
                            Text(
                                text = "Temp ${capital.currentTemp}º ${capital.unitCode}",
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = "Last updated: ${capital.timeStamp}",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))

                        Image(
                            modifier = Modifier.padding(smallPadding),
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Post options.",
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                        )

                    }
                }
            }
        }
    }
}

@Composable
private fun ErrorFromApi() = Toast(R.string.api_error)

@ThemeModePreview
@FontScalePreview
@DeviceFormatPreview
@Composable
private fun ListScreenPreview() {
    SnuhTheme {
        ListCapitals(
            state = UpdateSuccess(
                (1..10).map {
                    StateDisplayModel(
                        cityName = "Lansing",
                        state = "Michigan",
                        currentTemp = 75.0,
                        unitCode = "F",
                        timeStamp = "2023-02-11 +13:52",
                        "GEOCODES"
                    )
                }
            ),
            onRefresh = {},
            onDetailsClicked = {}
        )
    }
}
