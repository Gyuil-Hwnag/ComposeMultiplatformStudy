package com.example.cmpstudy.map

import org.maplibre.spatialk.geojson.Position

object GeoJsonHelper {
    fun createPointFeature(position: Position): String {
        return createPointFeature(position.longitude, position.latitude)
    }

    fun createPointFeature(longitude: Double, latitude: Double): String {
        return """{"type":"FeatureCollection","features":[{"type":"Feature","geometry":{"type":"Point","coordinates":[$longitude,$latitude]},"properties":{}}]}"""
    }
}
