package com.kents.core.domain

import com.kents.core.commons.logd
import com.kents.core.data.repositories.BookRepository
import com.kents.core.domain.models.BookDetails
import com.kents.core.domain.models.toModel
import javax.inject.Inject

class GetBook @Inject constructor(
    private val bookRepository: BookRepository,
) {
    suspend operator fun invoke(id: String): Result<BookDetails> {
        return bookRepository.getBookDetails(id).map { bookDetails ->
            logd("Title: " + bookDetails.title)
            bookDetails.toModel()
        }
    }
}
