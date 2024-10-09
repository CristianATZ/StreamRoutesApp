package net.streamroutes.sreamroutesapp.features.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import net.streamroutes.sreamroutesapp.R

@Composable
fun MapFullSize(
    cameraPositionState: CameraPositionState,
    onMapClick: (LatLng) -> Unit,
    onMapLoaded: () -> Unit,
    content: @Composable () -> Unit
) {
    GoogleMap(
        cameraPositionState = cameraPositionState,
        uiSettings = MapUiSettings(
            zoomControlsEnabled = false,
            myLocationButtonEnabled = false,
            mapToolbarEnabled = false,
            indoorLevelPickerEnabled = false,
            tiltGesturesEnabled = false,
        ),
        properties = MapProperties(
            isTrafficEnabled = true,
            isBuildingEnabled = false,
            isIndoorEnabled = false,
            isMyLocationEnabled = false,
            maxZoomPreference = 16f,
            minZoomPreference = 14f,
            mapStyleOptions = MapStyleOptions(stringResource(id = R.string.mapStyleLight))
        ),
        onMapClick = {
            onMapClick(it)
        },
        onMapLoaded = onMapLoaded,
        modifier = Modifier.fillMaxSize()
    ) {
        content()
    }
}