package com.kents.core.data.repositories

import com.kents.core.data.models.BookDetailsDto
import com.kents.core.data.models.BookRecords
import com.kents.core.data.network.WeatherService
import javax.inject.Inject

class BookRepository
@Inject constructor(
    private val openLibraryService: WeatherService
) {
    suspend fun getBooks(keyword: String = "Android"): Result<BookRecords> =
        openLibraryService.getBooks(keyword)

    suspend fun getBookDetails(id: String): Result<BookDetailsDto> =
        openLibraryService.getBook(id)
}
