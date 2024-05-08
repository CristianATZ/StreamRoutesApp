package net.streamroutes.sreamroutesapp.ui.routes_screens.menu.help

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import net.streamroutes.sreamroutesapp.R

@Composable
fun HomeScreen() {
    val itsur = LatLng(19.057988677624586, -98.180047630148)
    val cameraState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(itsur, 17f)
    }

    MapBody(cameraPositionState = cameraState)
}

@Composable
private fun MapBody(
    cameraPositionState: CameraPositionState,
) {
    val context = LocalContext.current
    GoogleMap(
        modifier = Modifier
            .fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(
            mapStyleOptions = MapStyleOptions(stringResource(id = R.string.mapStyleLight)),
            maxZoomPreference = 18f,
            minZoomPreference = 15f
        ),
        uiSettings = MapUiSettings(
            zoomControlsEnabled = false
        )
    ) {

    }
}