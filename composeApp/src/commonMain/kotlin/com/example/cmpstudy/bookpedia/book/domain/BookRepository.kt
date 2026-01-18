package com.example.cmpstudy.bookpedia.book.domain

import com.example.cmpstudy.bookpedia.core.domain.DataError
import com.example.cmpstudy.bookpedia.core.domain.Result

interface BookRepository {
    suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>
    suspend fun getBookDescription(bookId: String): Result<String?, DataError.Remote>
}
