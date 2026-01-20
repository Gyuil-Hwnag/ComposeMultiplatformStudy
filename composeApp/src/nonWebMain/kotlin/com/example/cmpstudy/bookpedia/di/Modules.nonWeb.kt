package com.example.cmpstudy.bookpedia.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.example.cmpstudy.bookpedia.book.data.database.DatabaseFactory
import com.example.cmpstudy.bookpedia.book.data.database.FavoriteBookDataSource
import com.example.cmpstudy.bookpedia.book.data.database.FavoriteBookDataSourceImpl
import com.example.cmpstudy.bookpedia.book.data.database.FavoriteBookDatabase
import org.koin.dsl.module

val nonWebDatabaseModule = module {
    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }

    single { get<FavoriteBookDatabase>().favoriteBookDao }

}
