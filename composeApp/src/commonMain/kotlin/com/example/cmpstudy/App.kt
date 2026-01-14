package com.example.cmpstudy

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.cmpstudy.bookpedia.book.presentation.BookListScreenRoot
import com.example.cmpstudy.bookpedia.book.presentation.BookListViewModel
import org.koin.compose.getKoin
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    MaterialTheme {
        // NOTE: koinViewModel 이 WebMain 에서는 동작하지 않아서 koin.get() 으로 주입 받아야 함.
        // val viewModel = koinViewModel<BookListViewModel>()

        val koin = getKoin()
        val viewModel = remember { koin.get<BookListViewModel>() }
        BookListScreenRoot(
            viewModel = viewModel,
            onBookClick = {}
        )
    }
}
