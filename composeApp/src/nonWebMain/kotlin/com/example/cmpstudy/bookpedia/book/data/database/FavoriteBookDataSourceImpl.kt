package com.example.cmpstudy.bookpedia.book.data.database

import com.example.cmpstudy.bookpedia.book.data.model.BookDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteBookDataSourceImpl(
    private val dao: FavoriteBookDao
) : FavoriteBookDataSource {

    override suspend fun upsert(book: BookDto) {
        dao.upsert(book.toEntity())
    }

    override fun getFavoriteBooks(): Flow<List<BookDto>> {
        return dao.getFavoriteBooks().map { roomEntities ->
            roomEntities.map { it.toModel() }
        }
    }

    override suspend fun getFavoriteBook(id: String): BookDto? {
        return dao.getFavoriteBook(id)?.toModel()
    }

    override suspend fun deleteFavoriteBook(id: String) {
        dao.deleteFavoriteBook(id)
    }
}
