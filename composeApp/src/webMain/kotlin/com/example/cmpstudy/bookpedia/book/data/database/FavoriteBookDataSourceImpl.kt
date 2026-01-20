package com.example.cmpstudy.bookpedia.book.data.database

import com.example.cmpstudy.bookpedia.book.data.model.BookDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavoriteBookDataSourceImpl : FavoriteBookDataSource {

    private val cacheFavoriteBooks = mutableMapOf<String, BookDto>()
    private val favoriteBooks = MutableStateFlow<List<BookDto>>(emptyList())

    override suspend fun upsert(book: BookDto) {
        cacheFavoriteBooks[book.id] = book
        favoriteBooks.value = cacheFavoriteBooks.values.toList()
    }

    override fun getFavoriteBooks(): Flow<List<BookDto>> {
        return favoriteBooks.asStateFlow()
    }

    override suspend fun getFavoriteBook(id: String): BookDto? {
        return cacheFavoriteBooks[id]
    }

    override suspend fun deleteFavoriteBook(id: String) {
        cacheFavoriteBooks.remove(id)
        favoriteBooks.value = cacheFavoriteBooks.values.toList()
    }
}
