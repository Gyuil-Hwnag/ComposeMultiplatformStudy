package com.example.cmpstudy.bookpedia.di

import com.example.cmpstudy.bookpedia.book.data.database.DatabaseFactory
import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        includes(nonWebDatabaseModule)
        single<HttpClientEngine> { OkHttp.create() }
        single { DatabaseFactory() }
    }
