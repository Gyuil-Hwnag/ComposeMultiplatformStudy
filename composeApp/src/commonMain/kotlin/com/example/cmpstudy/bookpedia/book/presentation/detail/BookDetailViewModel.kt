package com.example.cmpstudy.bookpedia.book.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.cmpstudy.bookpedia.app.Routes
import com.example.cmpstudy.bookpedia.book.domain.BookRepository
import com.example.cmpstudy.bookpedia.core.domain.onSuccess
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class BookDetailViewModel(
    private val bookRepository: BookRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val bookId = savedStateHandle.toRoute<Routes.BookDetail>().id

    private val _state = MutableStateFlow(BookDetailState())
    val state = _state
        .onStart { fetchBookDescription() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    fun onAction(action: BookDetailAction) {
        when (action) {
            is BookDetailAction.OnSelectedBookChange -> {
                _state.update { it.copy(book = action.book) }
            }

            is BookDetailAction.OnFavoriteClick -> {}
            else -> Unit
        }
    }

    private fun fetchBookDescription() {
        viewModelScope.launch {
            bookRepository.getBookDescription(bookId)
                .onSuccess { description ->
                    _state.update {
                        it.copy(
                            book = it.book?.copy(description = description),
                            isLoading = false
                        )
                    }
                }
        }
    }
}
