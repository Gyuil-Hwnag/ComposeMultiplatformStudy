package com.example.cmpstudy.bookpedia.book.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cmpstudy.bookpedia.book.domain.Book
import com.example.cmpstudy.bookpedia.book.presentation.DEFAULT_BOOKS
import com.example.cmpstudy.bookpedia.core.presentation.PreviewDevices

@Composable
fun BookList(
    books: List<Book>,
    onBookClick: (Book) -> Unit,
    modifier: Modifier = Modifier,
    scrollState: LazyListState = rememberLazyListState()
) {
    LazyColumn(
        modifier = modifier,
        state = scrollState,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(
            items = books,
            key = { it.id }
        ) { book ->
            BookListItem(
                book = book,
                modifier = Modifier
                    .widthIn(max = 700.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onClick = { onBookClick(book) }
            )
        }
    }
}

@PreviewDevices
@Composable
private fun BookListPreview() {
    BookList(
        books = DEFAULT_BOOKS,
        onBookClick = {},
        modifier = Modifier.fillMaxSize()
    )
}
