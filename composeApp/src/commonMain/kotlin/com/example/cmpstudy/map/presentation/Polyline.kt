package com.example.cmpstudy.map.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cmpstudy.map.utils.GeoJsonHelper
import org.maplibre.compose.expressions.dsl.const
import org.maplibre.compose.layers.LineLayer
import org.maplibre.compose.sources.GeoJsonData
import org.maplibre.compose.sources.rememberGeoJsonSource
import org.maplibre.spatialk.geojson.Position

@Composable
fun Polyline(
    id: String,
    start: Position,
    end: Position,
    color: Color = Color.Blue,
    dashPattern: List<Float> = listOf(2f, 2f)
) {
    val source = rememberGeoJsonSource(GeoJsonData.JsonString(GeoJsonHelper.createPolyline(start, end)))
    LineLayer(
        id = id,
        source = source,
        color = const(color),
        width = const(3.dp),
        dasharray = const(dashPattern)
    )
}
