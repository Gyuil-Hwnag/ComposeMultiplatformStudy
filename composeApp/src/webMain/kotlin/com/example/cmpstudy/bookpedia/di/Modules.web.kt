package com.example.cmpstudy.bookpedia.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single<HttpClientEngine> { HttpClient().engine }
    }
