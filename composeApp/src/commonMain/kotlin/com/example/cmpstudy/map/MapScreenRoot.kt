package com.example.cmpstudy.map

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cmpstudy.composeapp.generated.resources.*
import com.example.cmpstudy.map.presentation.Polyline
import com.example.cmpstudy.map.presentation.Marker
import com.example.cmpstudy.map.presentation.MyLocationButton
import com.example.cmpstudy.map.presentation.PinLocationButton
import com.example.cmpstudy.map.utils.MapConfig
import com.example.cmpstudy.map.utils.MapStyle
import com.example.cmpstudy.map.utils.roundTo
import com.mohamedrejeb.calf.permissions.ExperimentalPermissionsApi
import com.mohamedrejeb.calf.permissions.Permission
import com.mohamedrejeb.calf.permissions.isGranted
import com.mohamedrejeb.calf.permissions.rememberPermissionState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString
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
    var isPinMode by rememberSaveable { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(isPinMode, cameraState.position.target) {
        if (!isPinMode) return@LaunchedEffect
        delay(500)
        val target = cameraState.position.target
        snackbarHostState.showSnackbar(
            getString(
                Res.string.map_pin_location_format,
                target.latitude.roundTo(6), target.longitude.roundTo(6)
            )
        )
    }

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

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
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

                if (myLocationPosition != null && clickPosition != null) {
                    Polyline(
                        id = "ConnectionPolyline",
                        start = myLocationPosition!!,
                        end = clickPosition!!
                    )
                }
            }

            if (isPinMode) {
                Image(
                    painter = painterResource(Res.drawable.pin_marker),
                    contentDescription = "PinMarker",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(y = (-24).dp)
                        .size(48.dp)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.BottomEnd),
                contentAlignment = Alignment.BottomEnd
            ) {
                PinLocationButton(
                    isActive = isPinMode,
                    onClick = { isPinMode = !isPinMode }
                )
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
    }
}
