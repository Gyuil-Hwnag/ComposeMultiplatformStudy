package com.example.cmpstudy.bookpedia.di

import com.example.cmpstudy.login.di.loginModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(sharedModule, platformModule, loginModule)
    }
}
