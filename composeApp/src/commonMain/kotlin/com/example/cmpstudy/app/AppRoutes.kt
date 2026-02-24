package com.example.cmpstudy.app

import kotlinx.serialization.Serializable

sealed interface AppRoutes {

    @Serializable
    data object AppGraph: AppRoutes

    @Serializable
    data object Start: AppRoutes

    @Serializable
    data object BookPedia: AppRoutes


    @Serializable
    data object DateTime: AppRoutes

    @Serializable
    data object FilePicker: AppRoutes

    @Serializable
    data object Location: AppRoutes

    @Serializable
    data object Login: AppRoutes

    @Serializable
    data object Map: AppRoutes

    @Serializable
    data object Permission: AppRoutes

    @Serializable
    data object WebView: AppRoutes
}
