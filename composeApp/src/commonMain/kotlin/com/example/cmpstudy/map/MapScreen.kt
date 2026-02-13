package com.example.cmpstudy.map

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import cmpstudy.composeapp.generated.resources.Res
import cmpstudy.composeapp.generated.resources.marker
import cmpstudy.composeapp.generated.resources.my_location
import com.mohamedrejeb.calf.permissions.ExperimentalPermissionsApi
import com.mohamedrejeb.calf.permissions.Permission
import com.mohamedrejeb.calf.permissions.isGranted
import com.mohamedrejeb.calf.permissions.rememberPermissionState
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.maplibre.compose.camera.CameraPosition
import org.maplibre.compose.camera.rememberCameraState
import org.maplibre.compose.expressions.dsl.const
import org.maplibre.compose.expressions.dsl.image
import org.maplibre.compose.expressions.value.SymbolAnchor
import org.maplibre.compose.layers.SymbolLayer
import org.maplibre.compose.location.rememberDefaultLocationProvider
import org.maplibre.compose.location.rememberNullLocationProvider
import org.maplibre.compose.location.rememberUserLocationState
import org.maplibre.compose.map.MaplibreMap
import org.maplibre.compose.sources.GeoJsonData
import org.maplibre.compose.sources.rememberGeoJsonSource
import org.maplibre.compose.style.BaseStyle
import org.maplibre.spatialk.geojson.Position
import kotlin.time.Duration.Companion.milliseconds

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapScreenRoot() {
    val scope = rememberCoroutineScope()
    val cameraState = rememberCameraState()
    val permissionState = rememberPermissionState(Permission.FineLocation)
    val hasPermission = permissionState.status.isGranted
    var position by remember { mutableStateOf<Position?>(null) }
    val locationProvider = if (hasPermission) rememberDefaultLocationProvider() else rememberNullLocationProvider()
    val locationState = rememberUserLocationState(locationProvider)
    var pendingLocationRequest by remember { mutableStateOf(false) }

    LaunchedEffect(hasPermission) {
        if (pendingLocationRequest && hasPermission) {
            locationState.location?.let { location ->
                position = location.position
                cameraState.animateTo(
                    finalPosition = CameraPosition(
                        target = location.position,
                        zoom = MapConfig.DEFAULT_ZOOM
                    ),
                    duration = MapConfig.ANIMATION_DURATION_MS.milliseconds
                )
            }
            pendingLocationRequest = false
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        MaplibreMap(
            modifier = Modifier.fillMaxSize(),
            cameraState = cameraState,
            baseStyle = BaseStyle.Uri(MapStyle.OpenFreeMap.BRIGHT)
        ) {
            position?.let { position ->
                Marker(
                    id = "MyLocationMarker",
                    position = position,
                    painter = painterResource(Res.drawable.marker)
                )
            }
        }
        MyLocationButton(
            onClick = {
                if (hasPermission) {
                    locationState.location?.let { location ->
                        position = location.position
                        scope.launch {
                            cameraState.animateTo(
                                finalPosition = CameraPosition(
                                    target = location.position,
                                    zoom = MapConfig.DEFAULT_ZOOM
                                ),
                                duration = MapConfig.ANIMATION_DURATION_MS.milliseconds
                            )
                        }
                    }
                } else {
                    pendingLocationRequest = true
                    permissionState.launchPermissionRequest()
                }
            }
        )
    }
}

@Composable
private fun Marker(id: String, position: Position, painter: Painter) {
    val source = rememberGeoJsonSource(GeoJsonData.JsonString(GeoJsonHelper.createPointFeature(position)))
    SymbolLayer(
        id = id,
        source = source,
        iconImage = image(painter),
        iconSize = const(MapConfig.MARKER_SIZE),
        iconAnchor = const(SymbolAnchor.Bottom),
        iconAllowOverlap = const(true)
    )
}

@Composable
private fun MyLocationButton(onClick: () -> Unit) {
    Image(
        painter = painterResource(Res.drawable.my_location),
        contentDescription = "MyLocation",
        modifier = Modifier
            .padding(end = 16.dp, bottom = 64.dp)
            .size(40.dp)
            .background(color = Color.Blue.copy(alpha = 0.5f), shape = CircleShape)
            .padding(4.dp)
            .clickable(onClick = onClick)
    )
}


