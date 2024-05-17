package net.streamroutes.sreamroutesapp.ui.routes_screens.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.transport),
            contentDescription = null,
            modifier = Modifier.size(250.dp).shadow(8.dp, CircleShape)
        )

        Spacer(modifier = Modifier.size(48.dp))

        Text(
            text = stringResource(id = R.string.lblTransportePublico),
            style = typography.displayMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(0.7f)
        )
        
        Spacer(modifier = Modifier.size(32.dp))

        Text(
            text = stringResource(id = R.string.lblDisfrutaMovilidad),
            style = typography.titleLarge,
            textAlign = TextAlign.Center    ,
            modifier = Modifier.fillMaxWidth(0.7f)
        )

    }

    //MapBody(cameraPositionState = cameraState)
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