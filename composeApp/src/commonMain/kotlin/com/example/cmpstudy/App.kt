package com.example.cmpstudy

import androidx.compose.material3.MaterialTheme
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
import kotlin.reflect.KClass
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.cmpstudy.bookpedia.app.Routes
import com.example.cmpstudy.bookpedia.book.presentation.SelectedBookViewModel
import com.example.cmpstudy.bookpedia.book.presentation.detail.BookDetailAction
import com.example.cmpstudy.bookpedia.book.presentation.detail.BookDetailScreenRoot
import com.example.cmpstudy.bookpedia.book.presentation.detail.BookDetailViewModel
import com.example.cmpstudy.bookpedia.book.presentation.list.BookListScreenRoot
import com.example.cmpstudy.bookpedia.book.presentation.list.BookListViewModel
import org.koin.compose.getKoin
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.ParametersHolder
import org.koin.core.parameter.parametersOf

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

                composable<Routes.BookDetail> {
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
}

/**
 * NOTE
 * 1. koinViewModel 이 WebMain 에서는 동작하지 않아서 koin.get() 으로 주입 받아야 함.
 * do not val viewModel = koinViewModel<BookListViewModel>()
 *
 * 2. koin.get() 으로 하는 경우에는 SavedStateHandle이 재대로 전달이 안됨.
 * Route 에서 정보를 추출해서 SavedStateHandle을 생성하여 전달
 *
 * 3. getKoin()으로 ViewModel을 생성할 경우 해당 화면으로 돌아오면 ViewModel 이 초기화 되어 ViewModelStoreOwner를 지정해야 함.
 **/
@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.koinViewModel(
    key: String? = null,
    noinline getParameters: (() -> ParametersHolder)? = null
): T {
    val koin = getKoin()
    val factory = remember(key) {
        object : ViewModelProvider.Factory {
            override fun <VM : ViewModel> create(modelClass: KClass<VM>, extras: CreationExtras): VM {
                @Suppress("UNCHECKED_CAST")
                return if (getParameters != null) {
                    koin.get<T>(parameters = getParameters) as VM
                } else {
                    koin.get<T>() as VM
                }
            }
        }
    }
    return viewModel(
        viewModelStoreOwner = this,
        key = key,
        factory = factory
    )
}


@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) { navController.getBackStackEntry(navGraphRoute) }
    return koinViewModel(viewModelStoreOwner = parentEntry)
}
