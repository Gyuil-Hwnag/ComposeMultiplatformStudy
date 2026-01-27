package com.example.cmpstudy.login.di

import com.example.cmpstudy.login.data.AuthService
import com.example.cmpstudy.login.data.AuthServiceImpl
import com.example.cmpstudy.login.presentation.LoginViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val loginModule = module {
    singleOf(::AuthServiceImpl).bind<AuthService>()
    viewModelOf(::LoginViewModel)
}
