package com.example.cmpstudy.map.utils

import org.maplibre.spatialk.geojson.Position
import kotlin.math.*

object DistanceUtils {
    private const val EARTH_RADIUS_KM = 6371.0

    // NOTE: Haversine 공식을 사용하여 두 위치 간의 거리를 km로 계산
    fun calculateDistance(start: Position, end: Position): Double {
        val lat1 = start.latitude.toRadians()
        val lat2 = end.latitude.toRadians()
        val deltaLat = (end.latitude - start.latitude).toRadians()
        val deltaLon = (end.longitude - start.longitude).toRadians()

        val a = sin(deltaLat / 2).pow(2) + cos(lat1) * cos(lat2) * sin(deltaLon / 2).pow(2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        return EARTH_RADIUS_KM * c
    }

    private fun Double.toRadians(): Double = this * PI / 180
}
