package com.example.cmpstudy.map.utils

import kotlin.math.pow
import kotlin.math.round

fun Double.roundTo(decimals: Int): String {
    val factor = 10.0.pow(decimals)
    return (round(this * factor) / factor).toString()
}
