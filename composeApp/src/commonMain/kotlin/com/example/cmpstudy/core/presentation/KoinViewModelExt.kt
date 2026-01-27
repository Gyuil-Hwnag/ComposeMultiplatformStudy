package com.example.cmpstudy.core.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import org.koin.compose.getKoin
import org.koin.core.parameter.ParametersHolder
import kotlin.reflect.KClass

/**
 * Web에서 koinViewModel()이 작동하지 않는 문제를 해결하기 위한 확장 함수
 *
 * 사용법:
 * ```
 * val viewModel = it.koinViewModel<MyViewModel>()
 * val viewModel = it.koinViewModel<MyViewModel>(key = "unique-key")
 * val viewModel = it.koinViewModel<MyViewModel> { parametersOf(...) }
 * ```
 *
 * 주의:
 * 1. Web에서는 koinViewModel()이 동작하지 않아 koin.get()으로 주입받아야 함
 * 2. koin.get()으로 하는 경우 SavedStateHandle이 제대로 전달되지 않아 수동으로 생성하여 전달
 * 3. getKoin()으로 ViewModel을 생성할 경우 화면으로 돌아오면 ViewModel이 초기화되므로 ViewModelStoreOwner를 지정해야 함
 */
@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.koinViewModel(
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

/**
 * ViewModelStoreOwner를 지정할 수 있는 koinViewModel 오버로드
 */
@Composable
inline fun <reified T : ViewModel> koinViewModel(
    viewModelStoreOwner: ViewModelStoreOwner,
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
        viewModelStoreOwner = viewModelStoreOwner,
        key = key,
        factory = factory
    )
}

/**
 * Navigation 그래프 내에서 ViewModel을 공유하기 위한 확장 함수
 *
 * 사용법:
 * ```
 * val sharedViewModel = it.sharedKoinViewModel<SharedViewModel>(navController)
 * ```
 *
 * Navigation 그래프의 parent entry를 ViewModelStoreOwner로 사용하여
 * 그래프 내의 모든 화면에서 동일한 ViewModel 인스턴스를 공유합니다.
 */
@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) { navController.getBackStackEntry(navGraphRoute) }
    return koinViewModel(viewModelStoreOwner = parentEntry)
}

/**
 * 일반 Composable에서 사용할 수 있는 koinViewModel (Navigation 없이)
 *
 * 사용법:
 * ```
 * @Composable
 * fun MyScreen() {
 *     val viewModel = koinViewModel<MyViewModel>()
 * }
 * ```
 */
@Composable
inline fun <reified T : ViewModel> koinViewModel(
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
        key = key,
        factory = factory
    )
}
