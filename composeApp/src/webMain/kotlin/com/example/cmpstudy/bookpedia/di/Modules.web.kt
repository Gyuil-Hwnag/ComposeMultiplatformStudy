package com.example.cmpstudy.bookpedia.di

import com.example.cmpstudy.bookpedia.book.data.database.FavoriteBookDataSource
import com.example.cmpstudy.bookpedia.book.data.database.FavoriteBookDataSourceImpl
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.auth
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single<HttpClientEngine> { HttpClient().engine }
        single<FavoriteBookDataSource> { FavoriteBookDataSourceImpl() }
        single<FirebaseAuth> { Firebase.auth }
    }
