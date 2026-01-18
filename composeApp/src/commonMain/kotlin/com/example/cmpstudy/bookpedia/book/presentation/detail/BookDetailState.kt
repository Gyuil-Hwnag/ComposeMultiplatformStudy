package com.example.cmpstudy.bookpedia.book.presentation.detail

import com.example.cmpstudy.bookpedia.book.domain.Book

data class BookDetailState(
    val isLoading: Boolean = true,
    val isFavorite: Boolean = false,
    val book: Book? = null
)
