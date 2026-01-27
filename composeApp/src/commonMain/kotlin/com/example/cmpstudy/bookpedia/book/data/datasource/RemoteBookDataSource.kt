package com.example.cmpstudy.bookpedia.book.data.datasource

import com.example.cmpstudy.bookpedia.book.data.model.BookWorkDto
import com.example.cmpstudy.bookpedia.book.data.model.SearchResponseDto
import com.example.cmpstudy.core.domain.DataError.Remote
import com.example.cmpstudy.core.domain.Result

interface RemoteBookDataSource {
    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ): Result<SearchResponseDto, Remote>

    suspend fun getBookDetails(bookWorkId: String): Result<BookWorkDto, Remote>
}
