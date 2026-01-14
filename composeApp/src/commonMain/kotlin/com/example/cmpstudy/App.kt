package com.example.cmpstudy

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.cmpstudy.bookpedia.app.Routes
import com.example.cmpstudy.bookpedia.book.presentation.list.BookListScreenRoot
import com.example.cmpstudy.bookpedia.book.presentation.list.BookListViewModel
import org.koin.compose.getKoin

@Composable
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Routes.BookGraph
        ) {
            navigation<Routes.BookGraph>(
                startDestination = Routes.BookList
            ) {
                composable<Routes.BookList> {
                    // NOTE: koinViewModel 이 WebMain 에서는 동작하지 않아서 koin.get() 으로 주입 받아야 함.
                    // val viewModel = koinViewModel<BookListViewModel>()
                    val koin = getKoin()
                    val viewModel = remember { koin.get<BookListViewModel>() }
                    BookListScreenRoot(
                        viewModel = viewModel,
                        onBookClick = { book -> navController.navigate(Routes.BookDetail(book.id)) }
                    )
                }
 
                composable<Routes.BookDetail> { entry ->
                    val args = entry.toRoute<Routes.BookDetail>()
                }
            }
        }
    }
}
