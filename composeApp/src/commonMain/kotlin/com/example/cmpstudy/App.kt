package com.example.cmpstudy

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.cmpstudy.bookpedia.book.presentation.BookListScreenRoot
import com.example.cmpstudy.bookpedia.book.presentation.BookListViewModel
import com.example.cmpstudy.location.LocationScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        BookListScreenRoot(
            viewModel = remember { BookListViewModel() },
            onBookClick = {}
        )
    }
}
