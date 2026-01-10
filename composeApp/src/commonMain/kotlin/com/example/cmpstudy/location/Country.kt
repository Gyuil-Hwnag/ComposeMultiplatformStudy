package com.example.cmpstudy.location

import cmpstudy.composeapp.generated.resources.Res
import cmpstudy.composeapp.generated.resources.egypt
import cmpstudy.composeapp.generated.resources.france
import cmpstudy.composeapp.generated.resources.indonesia
import cmpstudy.composeapp.generated.resources.japan
import cmpstudy.composeapp.generated.resources.korea
import cmpstudy.composeapp.generated.resources.mexico
import kotlinx.datetime.TimeZone
import org.jetbrains.compose.resources.DrawableResource

data class Country(
    val name: String,
    val zone: TimeZone,
    val imageResId: DrawableResource
)

val COUNTRIES = listOf(
    Country("South Korea", TimeZone.of("Asia/Seoul"), Res.drawable.korea),
    Country("Japan", TimeZone.of("Asia/Tokyo"), Res.drawable.japan),
    Country("France", TimeZone.of("Europe/Paris"), Res.drawable.france),
    Country("Mexico", TimeZone.of("America/Mexico_City"), Res.drawable.mexico),
    Country("Indonesia", TimeZone.of("Asia/Jakarta"), Res.drawable.indonesia),
    Country("Egypt", TimeZone.of("Africa/Cairo"), Res.drawable.egypt),
)
