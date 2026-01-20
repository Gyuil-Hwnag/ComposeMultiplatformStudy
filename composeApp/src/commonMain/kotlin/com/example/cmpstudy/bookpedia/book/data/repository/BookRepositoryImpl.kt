package com.example.cmpstudy.bookpedia.book.data.repository

import com.example.cmpstudy.bookpedia.book.data.database.FavoriteBookDataSource
import com.example.cmpstudy.bookpedia.book.data.datasource.RemoteBookDataSource
import com.example.cmpstudy.bookpedia.book.data.mapper.toBook
import com.example.cmpstudy.bookpedia.book.data.mapper.toDto
import com.example.cmpstudy.bookpedia.book.domain.Book
import com.example.cmpstudy.bookpedia.book.domain.BookRepository
import com.example.cmpstudy.bookpedia.core.domain.DataError
import com.example.cmpstudy.bookpedia.core.domain.EmptyResult
import com.example.cmpstudy.bookpedia.core.domain.Result
import com.example.cmpstudy.bookpedia.core.domain.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookRepositoryImpl(
    private val remoteBookDataSource: RemoteBookDataSource,
    private val favoriteBookDataSource: FavoriteBookDataSource
) : BookRepository {

    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> {
        return remoteBookDataSource
            .searchBooks(query)
            .map { dto -> dto.results.map { it.toBook() } }
    }

    override suspend fun getBookDescription(bookId: String): Result<String?, DataError.Remote> {
        val localResult = favoriteBookDataSource.getFavoriteBook(bookId)

        return if (localResult == null) {
            remoteBookDataSource
                .getBookDetails(bookId)
                .map { it.description }
        } else {
            Result.Success(localResult.description)
        }
    }

    override fun getFavoriteBooks(): Flow<List<Book>> {
        return favoriteBookDataSource
            .getFavoriteBooks()
            .map { entities -> entities.map { it.toBook() } }
    }

    override fun isBookFavorite(id: String): Flow<Boolean> {
        return favoriteBookDataSource
            .getFavoriteBooks()
            .map { entities -> entities.any { it.id == id } }
    }

    override suspend fun markAsFavorite(book: Book): EmptyResult<DataError.Local> {
        return try {
            favoriteBookDataSource.upsert(book.toDto())
            Result.Success(Unit)
        } catch (_: Exception) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteFromFavorites(id: String) {
        favoriteBookDataSource.deleteFavoriteBook(id)
    }
}
