package com.example.cmpstudy.bookpedia.book.presentation.list

import com.example.cmpstudy.bookpedia.book.domain.Book
import com.example.cmpstudy.core.presentation.UiText

data class BookListState(
    val searchQuery: String = "Kotlin",
    val searchResults: List<Book> = emptyList(),
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = true,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null
)

val DEFAULT_BOOK = Book(
    id = "1",
    title = "Book Title",
    imageUrl = "https://test.com",
    authors = listOf("Test Author"),
    description = "Book Description",
    languages = emptyList(),
    firstPublishYear = "1996",
    averageRating = 4.8765,
    ratingCount = 5,
    numPages = 100,
    numEditions = 3
)

val DEFAULT_BOOKS = (1..100).map {
    Book(
        id = it.toString(),
        title = "Book Title $it",
        imageUrl = "https://test.com",
        authors = listOf("Test Author $it"),
        description = "Book Description $it",
        languages = emptyList(),
        firstPublishYear = "1996",
        averageRating = 4.8765,
        ratingCount = 5,
        numPages = 100,
        numEditions = 3
    )
}
