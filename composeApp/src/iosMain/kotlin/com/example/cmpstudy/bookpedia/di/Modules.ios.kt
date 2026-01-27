package com.example.cmpstudy.bookpedia.di

import com.example.cmpstudy.bookpedia.book.data.database.DatabaseFactory
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.auth
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        includes(nonWebDatabaseModule)
        single<HttpClientEngine> { Darwin.create() }
        single { DatabaseFactory() }
        single<FirebaseAuth> { Firebase.auth }
    }
