package com.example.cmpstudy.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cmpstudy.composeapp.generated.resources.Res
import cmpstudy.composeapp.generated.resources.marker
import cmpstudy.composeapp.generated.resources.pointer_marker
import com.example.cmpstudy.map.presentation.Marker
import com.example.cmpstudy.map.presentation.MyLocationButton
import com.example.cmpstudy.map.utils.MapConfig
import com.example.cmpstudy.map.utils.MapStyle
import com.mohamedrejeb.calf.permissions.ExperimentalPermissionsApi
import com.mohamedrejeb.calf.permissions.Permission
import com.mohamedrejeb.calf.permissions.isGranted
import com.mohamedrejeb.calf.permissions.rememberPermissionState
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.maplibre.compose.camera.CameraPosition
import org.maplibre.compose.camera.rememberCameraState
import org.maplibre.compose.location.rememberDefaultLocationProvider
import org.maplibre.compose.location.rememberNullLocationProvider
import org.maplibre.compose.location.rememberUserLocationState
import org.maplibre.compose.map.MapOptions
import org.maplibre.compose.map.MaplibreMap
import org.maplibre.compose.map.OrnamentOptions
import org.maplibre.compose.style.BaseStyle
import org.maplibre.compose.util.ClickResult
import org.maplibre.spatialk.geojson.Position
import kotlin.time.Duration.Companion.milliseconds

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapScreenRoot() {
    val scope = rememberCoroutineScope()
    val cameraState = rememberCameraState()
    val permissionState = rememberPermissionState(Permission.FineLocation)
    val isGranted = permissionState.status.isGranted
    val locationProvider = if (isGranted) rememberDefaultLocationProvider() else rememberNullLocationProvider()
    val locationState = rememberUserLocationState(locationProvider)
    var myLocationPosition by remember { mutableStateOf<Position?>(null) }
    var pendingLocationRequest by remember { mutableStateOf(false) }
    var clickPosition by remember { mutableStateOf<Position?>(null) }

    LaunchedEffect(isGranted, locationState.location) {
        if (!pendingLocationRequest) return@LaunchedEffect
        if (!isGranted) return@LaunchedEffect
        val location = locationState.location ?: return@LaunchedEffect
        myLocationPosition = location.position
        cameraState.animateTo(
            finalPosition = CameraPosition(
                target = location.position,
                zoom = MapConfig.DEFAULT_ZOOM
            ),
            duration = MapConfig.ANIMATION_DURATION_MS.milliseconds
        )
        pendingLocationRequest = false
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        MaplibreMap(
            modifier = Modifier.fillMaxSize(),
            cameraState = cameraState,
            baseStyle = BaseStyle.Uri(MapStyle.OpenFreeMap.BRIGHT),
            options = MapOptions(ornamentOptions = OrnamentOptions.AllDisabled),
            onMapClick = { position, _ ->
                clickPosition = position
                ClickResult.Pass
            }
        ) {
            myLocationPosition?.let { pos ->
                Marker(
                    id = "MyLocationMarker",
                    position = pos,
                    painter = painterResource(Res.drawable.marker)
                )
            }
            clickPosition?.let { pos ->
                Marker(
                    id = "ClickMarker",
                    position = pos,
                    painter = painterResource(Res.drawable.pointer_marker)
                )
            }
        }
        MyLocationButton(
            onClick = {
                if (isGranted) {
                    locationState.location?.let { location ->
                        myLocationPosition = location.position
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


