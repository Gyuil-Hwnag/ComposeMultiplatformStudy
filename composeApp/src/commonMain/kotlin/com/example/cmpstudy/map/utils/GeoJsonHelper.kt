package com.example.cmpstudy.map.utils

import org.maplibre.spatialk.geojson.Position

object GeoJsonHelper {
    fun createPoint(position: Position): String {
        return createPoint(position.longitude, position.latitude)
    }

    fun createPoint(longitude: Double, latitude: Double): String {
        return """{"type":"FeatureCollection","features":[{"type":"Feature","geometry":{"type":"Point","coordinates":[$longitude,$latitude]},"properties":{}}]}"""
    }

    fun createPolyline(start: Position, end: Position): String {
        return """{"type":"FeatureCollection","features":[{"type":"Feature","geometry":{"type":"LineString","coordinates":[[${start.longitude},${start.latitude}],[${end.longitude},${end.latitude}]]},"properties":{}}]}"""
    }
}
