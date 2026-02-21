package com.example.cmpstudy.map.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import com.example.cmpstudy.map.utils.GeoJsonHelper
import com.example.cmpstudy.map.utils.MapConfig
import org.maplibre.compose.expressions.dsl.const
import org.maplibre.compose.expressions.dsl.image
import org.maplibre.compose.expressions.value.SymbolAnchor
import org.maplibre.compose.layers.SymbolLayer
import org.maplibre.compose.sources.GeoJsonData
import org.maplibre.compose.sources.rememberGeoJsonSource
import org.maplibre.spatialk.geojson.Position

@Composable
fun Marker(id: String, position: Position, painter: Painter) {
    val source = rememberGeoJsonSource(GeoJsonData.JsonString(GeoJsonHelper.createPoint(position)))
    SymbolLayer(
        id = id,
        source = source,
        iconImage = image(painter),
        iconSize = const(MapConfig.MARKER_SIZE),
        iconAnchor = const(SymbolAnchor.Bottom),
        iconAllowOverlap = const(true)
    )
}
