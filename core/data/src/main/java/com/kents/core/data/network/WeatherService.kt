package com.kents.core.data.network

import com.kents.core.commons.BackgroundDispatcher
import com.kents.core.commons.endpoints.WeatherApiEndpoint
import com.kents.core.commons.loge
import com.kents.core.data.models.BookDetailsDto
import com.kents.core.data.models.BookRecords
import com.kents.core.data.models.forecastdto.ForecastDTO
import com.kents.core.data.models.forecastdto.ForecastPointsDTO
import com.kents.core.data.models.observationdto.ObservationDTO
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class WeatherService
@Inject constructor(
    private val httpClient: WeatherHttpClient,
    @BackgroundDispatcher private val coroutineContext: CoroutineContext
) {
    suspend fun getForecast(forecastUrl: String) : Result<ForecastDTO> =
        withContext(coroutineContext) {
            return@withContext try {
                Result.success(
                    httpClient().get {
                        url(path = forecastUrl)
                    }.body()
                )
            }catch (e: Exception) {
                loge("Failed to get weather forecast")
                Result.failure(e)
            }
        }

    suspend fun getForecastUrl(geoCodes: String) : Result<ForecastPointsDTO> =
        withContext(coroutineContext) {
            return@withContext try {
                Result.success(
                    httpClient().get {
                        url(path = WeatherApiEndpoint.points(geoCodes))
                    }.body()
                )
            } catch (e: Exception) {
                loge("Failed to get forecast url for $geoCodes")
                Result.failure(e)
            }
        }

    suspend fun getObservation(stationCode: String): Result<ObservationDTO> =
        withContext(coroutineContext) {
            return@withContext try {
                Result.success(
                    httpClient().get {
                        url(path = WeatherApiEndpoint.observation(stationCode))
                    }.body()
                )
            } catch (e: Exception) {
                loge("Failed to get observation for $stationCode", e)
                Result.failure(e)
            }
        }

    suspend fun getBooks(keyword: String): Result<BookRecords> = withContext(coroutineContext) {
        return@withContext try {
            Result.success(
                httpClient().get {
                    url(path = WeatherApiEndpoint.search(keyword))
                }.body()
            )
        } catch (e: Exception) {
            loge("Failed to get Weather", e)
            Result.failure(e)
        }
    }

    suspend fun getBook(id: String): Result<BookDetailsDto> = withContext(coroutineContext) {
        return@withContext try {
            Result.success(
                httpClient().get {
                    url(path = WeatherApiEndpoint.work(id))
                }.body()
            )
        } catch (e: Exception) {
            loge("Failed to get Weather", e)
            Result.failure(e)
        }
    }
}
