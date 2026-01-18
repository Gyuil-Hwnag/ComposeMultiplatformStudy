package com.example.cmpstudy.bookpedia.book.presentation.detail

import com.example.cmpstudy.bookpedia.book.domain.Book

sealed interface BookDetailAction {
    data object OnBackClick: BookDetailAction
    data object OnFavoriteClick: BookDetailAction
    data class OnSelectedBookChange(val book: Book): BookDetailAction
}
