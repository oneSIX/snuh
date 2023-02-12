package com.kents.snuh.features.details

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.kents.core.domain.models.ForecastPeriod
import com.kents.snuh.R
import com.kents.snuh.component.Toast
import com.kents.snuh.features.details.DetailsViewModel.*
import com.kents.snuh.features.details.DetailsViewModel.DetailScreenUiState.*
import com.kents.snuh.theme.SnuhTheme
import com.kents.snuh.theme.imageSize
import com.kents.snuh.theme.normalPadding
import com.kents.snuh.theme.smallPadding
import com.kents.snuh.utils.preview.DeviceFormatPreview
import com.kents.snuh.utils.preview.FontScalePreview
import com.kents.snuh.utils.preview.ThemeModePreview
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun DetailsScreen(detailsViewModel: DetailsViewModel) {
    val bookListUpdateState by detailsViewModel.state.collectAsState()

    when (val state = bookListUpdateState) {
        ErrorFromAPI -> ErrorFromApi()
        is Success, LoadingFromAPI -> {
            ListForecasts(state = state, onRefresh = detailsViewModel::refresh)
        }
    }
}

@Composable
@Suppress("DEPRECATION") // SwipeRefresh migration not available in material 3 just for 2
private fun ListForecasts(
    state: DetailScreenUiState,
    onRefresh: () -> Unit
) = SwipeRefresh(
    state = rememberSwipeRefreshState(state == LoadingFromAPI),
    onRefresh = onRefresh,
    modifier = Modifier
        .scrollable(rememberScrollState(), Orientation.Vertical)
        .systemBarsPadding()
        .background(MaterialTheme.colorScheme.surface)
        .padding(normalPadding)
) {
    // We only really care about the first item now, but i'm not sure how to start this off
    // without modeling it as an empty list.
    var forecasts by remember { mutableStateOf(emptyList<ForecastPeriod>()) }

    (state as? Success)?.let {
        forecasts = it.forecasts
    }

    Column {
        Row {
            Text(
                text = stringResource(id = R.string.forecast_title),
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.alignByBaseline()
            )
            Spacer(modifier = Modifier.weight(1f))

            val currentMoment: Instant = Clock.System.now()
            val localDateTime: LocalDateTime =
                currentMoment.toLocalDateTime(TimeZone.currentSystemDefault())
            val pretty = "${localDateTime.hour}:${localDateTime.minute}"

            Text(
                text = "Updated at: $pretty",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.alignByBaseline()
            )
            //TODO align baselines
        }

        LazyColumn {
            itemsIndexed(forecasts) { index, forecast ->
                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .padding(normalPadding)
                ) {
                    // Dispaly each forecast in here nicely!

                    Row(modifier = Modifier.fillMaxSize()) {
                        AsyncImage(
                            model = forecast.iconUrl.replace("medium", "large"),
                            placeholder = debugPlaceholder(R.drawable.placeholder),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .size(imageSize)
                                .padding(normalPadding)
                                .clip(MaterialTheme.shapes.small)
                        )

                        Column(modifier = Modifier.padding(normalPadding)) {
                            Text(
                                text = forecast.forecastName,
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier.padding(
                                    start = smallPadding,
                                    top = smallPadding,
                                    end = smallPadding,
                                    bottom = 0.dp
                                )
                            )
                            Text(
                                text = forecast.detailedForecast,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(smallPadding)
                            )

                            Text(
                                text = "Temp: ${forecast.temperature}F",
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(smallPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}

@ThemeModePreview
@FontScalePreview
@DeviceFormatPreview
@Composable
fun DetailsPreview() {
    SnuhTheme {
        ListForecasts(
            state = Success(
                listOf(
                    ForecastPeriod(
                        forecastName = "Today",
                        detailedForecast = "Partly sunny, with a high near 57. Northwest wind 10 to 15 mph, with gusts as high as 25 mph. New rainfall amounts less than a tenth of an inch possible.",
                        "2023-02-12T11:00:00-06:00",
                        7,
                        "",
                        "https://cdn.masto.host/androiddevsocial/accounts/avatars/109/294/498/529/082/611/original/53acbb94088d0585.jpg"
                    ),
                    ForecastPeriod(
                        "",
                        "",
                        "",
                        11,
                        "",
                        "https://cdn.masto.host/androiddevsocial/accounts/avatars/109/294/498/529/082/611/original/53acbb94088d0585.jpg"
                    )
                ),
            ),
            onRefresh = {}
        )
    }
}


@Composable
private fun ErrorFromApi() = Toast(R.string.api_error)

@Composable
fun debugPlaceholder(@DrawableRes debugPreview: Int) =
    if (LocalInspectionMode.current) {
        painterResource(id = debugPreview)
    } else {
        null
    }
