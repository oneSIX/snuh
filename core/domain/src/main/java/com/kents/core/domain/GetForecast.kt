package com.kents.core.domain

import com.kents.core.data.repositories.BookRepository
import com.kents.core.domain.models.Forecast
import javax.inject.Inject


class GetForecast @Inject constructor(
    private val bookRepository: BookRepository
) {
    suspend operator fun invoke(stateCapital: String) : Result<List<Forecast>>{
        return TODO()//bookRepository.get
    }

}