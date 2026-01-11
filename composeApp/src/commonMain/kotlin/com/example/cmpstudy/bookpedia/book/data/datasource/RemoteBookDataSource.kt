package com.example.cmpstudy.bookpedia.book.data.datasource

import com.example.cmpstudy.bookpedia.book.data.model.SearchResponseDto
import com.example.cmpstudy.bookpedia.core.domain.DataError.Remote
import com.example.cmpstudy.bookpedia.core.domain.Result

interface RemoteBookDataSource {
    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ): Result<SearchResponseDto, Remote>
}
