package com.example.cmpstudy

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.cmpstudy.bookpedia.book.data.datasource.RemoteBookDataSourceImpl
import com.example.cmpstudy.bookpedia.book.data.repository.BookRepositoryImpl
import com.example.cmpstudy.bookpedia.book.presentation.BookListScreenRoot
import com.example.cmpstudy.bookpedia.book.presentation.BookListViewModel
import com.example.cmpstudy.bookpedia.core.data.HttpClientFactory
import com.example.cmpstudy.location.LocationScreen
import io.ktor.client.engine.HttpClientEngine
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun App(engine: HttpClientEngine) {
    MaterialTheme {
        BookListScreenRoot(
            viewModel = remember { BookListViewModel(
                repository = BookRepositoryImpl(
                    remoteBookDataSource = RemoteBookDataSourceImpl(
                        httpClient = HttpClientFactory.create(engine = engine)
                    ),
                )
            ) },
            onBookClick = {}
        )
    }
}
