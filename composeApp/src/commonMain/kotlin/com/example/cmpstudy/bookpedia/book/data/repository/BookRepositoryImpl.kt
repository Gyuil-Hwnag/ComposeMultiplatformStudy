package com.example.cmpstudy.bookpedia.book.data.repository

import com.example.cmpstudy.bookpedia.book.data.datasource.RemoteBookDataSource
import com.example.cmpstudy.bookpedia.book.data.mapper.toBook
import com.example.cmpstudy.bookpedia.book.domain.Book
import com.example.cmpstudy.bookpedia.book.domain.BookRepository
import com.example.cmpstudy.bookpedia.core.domain.DataError
import com.example.cmpstudy.bookpedia.core.domain.Result
import com.example.cmpstudy.bookpedia.core.domain.map

class BookRepositoryImpl(
    private val remoteBookDataSource: RemoteBookDataSource
) : BookRepository {

    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> {
        return remoteBookDataSource
            .searchBooks(query)
            .map { dto -> dto.results.map { it.toBook() } }
    }
}
