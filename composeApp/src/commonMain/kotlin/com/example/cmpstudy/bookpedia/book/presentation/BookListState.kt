package com.example.cmpstudy.bookpedia.book.presentation

import com.example.cmpstudy.bookpedia.book.domain.Book
import com.example.cmpstudy.bookpedia.core.presentation.UiText

data class BookListState(
    val searchQuery: String = "Kotlin",
    val searchResults: List<Book> = emptyList(),
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null
)
