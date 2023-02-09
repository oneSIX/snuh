package com.kents.core.domain

import com.kents.core.data.repositories.BookRepository
import com.kents.core.domain.models.Book
import com.kents.core.domain.models.toModel
import javax.inject.Inject

class GetBooks @Inject constructor(
    private val bookRepository: BookRepository
) {
    suspend operator fun invoke(): Result<List<Book>> {
        return bookRepository.getBooks()
            .map { it.docs }
            .map { bookList -> bookList.map { it.toModel() } }
    }
}
