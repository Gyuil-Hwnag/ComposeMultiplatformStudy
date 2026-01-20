package com.example.cmpstudy.bookpedia.book.data.database

import com.example.cmpstudy.bookpedia.book.data.model.BookDto
import kotlinx.coroutines.flow.Flow

/**
 * Platform-agnostic interface for favorite books storage.
 * Implementations:
 * - Room-based (Android, iOS, JVM)
 * - In-memory (Web/JS/Wasm)
 */
interface FavoriteBookDataSource {
    suspend fun upsert(book: BookDto)
    fun getFavoriteBooks(): Flow<List<BookDto>>
    suspend fun getFavoriteBook(id: String): BookDto?
    suspend fun deleteFavoriteBook(id: String)
}
