package com.example.cmpstudy.bookpedia.app

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.cmpstudy.bookpedia.book.presentation.SelectedBookViewModel
import com.example.cmpstudy.bookpedia.book.presentation.detail.BookDetailAction
import com.example.cmpstudy.bookpedia.book.presentation.detail.BookDetailScreenRoot
import com.example.cmpstudy.bookpedia.book.presentation.detail.BookDetailViewModel
import com.example.cmpstudy.bookpedia.book.presentation.list.BookListScreenRoot
import com.example.cmpstudy.bookpedia.book.presentation.list.BookListViewModel
import com.example.cmpstudy.core.presentation.koinViewModel
import com.example.cmpstudy.core.presentation.sharedKoinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun BookPediaScreen() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.BookGraph
    ) {
        navigation<Routes.BookGraph>(
            startDestination = Routes.BookList
        ) {
            composable<Routes.BookList>(
                exitTransition = { slideOutHorizontally() },
                popEnterTransition = { slideInHorizontally() },
            ) {
                val viewModel = it.koinViewModel<BookListViewModel>()
                val selectedBookViewModel = it.sharedKoinViewModel<SelectedBookViewModel>(navController)

                LaunchedEffect(Unit) {
                    selectedBookViewModel.onSelectBook(null)
                }

                BookListScreenRoot(
                    viewModel = viewModel,
                    onBookClick = { book ->
                        selectedBookViewModel.onSelectBook(book)
                        navController.navigate(Routes.BookDetail(book.id))
                    }
                )
            }

            composable<Routes.BookDetail>(
                enterTransition = { slideInHorizontally { initialOffset -> initialOffset } },
                exitTransition = { slideOutHorizontally { initialOffset -> initialOffset } },
            ) {
                val selectedBookViewModel = it.sharedKoinViewModel<SelectedBookViewModel>(navController)

                val route = it.toRoute<Routes.BookDetail>()
                val viewModel = it.koinViewModel<BookDetailViewModel>(key = route.id) {
                    parametersOf(SavedStateHandle(mapOf("id" to route.id)))
                }
                val selectedBook by selectedBookViewModel.selectedBook.collectAsStateWithLifecycle()

                LaunchedEffect(selectedBook) {
                    selectedBook?.let { book -> viewModel.onAction(BookDetailAction.OnSelectedBookChange(book)) }
                }

                BookDetailScreenRoot(
                    viewModel = viewModel,
                    onBackClick = { navController.navigateUp() },
                )
            }
        }
    }
}


