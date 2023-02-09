package com.kents.core.domain.models

import com.kents.core.data.models.BookDetailsDto

data class BookDetails(
    val title: String
)

fun BookDetailsDto.toModel(): BookDetails = BookDetails(
    title = title
)
