package com.example.cmpstudy

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.cmpstudy.bookpedia.book.presentation.BookListScreenRoot
import com.example.cmpstudy.bookpedia.book.presentation.BookListViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    MaterialTheme {
        val viewModel = koinViewModel<BookListViewModel>()
        BookListScreenRoot(
            viewModel = viewModel,
            onBookClick = {}
        )
    }
}
