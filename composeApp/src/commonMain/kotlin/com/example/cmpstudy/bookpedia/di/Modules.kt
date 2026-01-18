package com.example.cmpstudy.bookpedia.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.example.cmpstudy.bookpedia.book.data.database.DatabaseFactory
import com.example.cmpstudy.bookpedia.book.data.database.FavoriteBookDatabase
import com.example.cmpstudy.bookpedia.book.data.datasource.RemoteBookDataSource
import com.example.cmpstudy.bookpedia.book.data.datasource.RemoteBookDataSourceImpl
import com.example.cmpstudy.bookpedia.book.data.repository.BookRepositoryImpl
import com.example.cmpstudy.bookpedia.book.domain.BookRepository
import com.example.cmpstudy.bookpedia.book.presentation.SelectedBookViewModel
import com.example.cmpstudy.bookpedia.book.presentation.detail.BookDetailViewModel
import com.example.cmpstudy.bookpedia.book.presentation.list.BookListViewModel
import com.example.cmpstudy.bookpedia.core.data.HttpClientFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::RemoteBookDataSourceImpl).bind<RemoteBookDataSource>()
    singleOf(::BookRepositoryImpl).bind<BookRepository>()

    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }

    single { get<FavoriteBookDatabase>().favoriteBookDao }

    viewModelOf(::BookListViewModel)
    viewModelOf(::SelectedBookViewModel)

    // BookDetailViewModel은 SavedStateHandle을 필요로 하므로 factory로 정의
    factory { params ->
        BookDetailViewModel(
            bookRepository = get(),
            savedStateHandle = params.get()
        )
    }
}
