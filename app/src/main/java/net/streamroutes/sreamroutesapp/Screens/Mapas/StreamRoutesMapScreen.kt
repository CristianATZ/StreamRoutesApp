package net.streamroutes.sreamroutesapp.Screens.Mapas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.utsman.osmandcompose.DefaultMapProperties
import com.utsman.osmandcompose.Marker
import com.utsman.osmandcompose.OpenStreetMap
import com.utsman.osmandcompose.Polyline
import com.utsman.osmandcompose.ZoomButtonVisibility
import com.utsman.osmandcompose.rememberCameraState
import com.utsman.osmandcompose.rememberMarkerState
import com.utsman.osmandcompose.rememberOverlayManagerState
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.CopyrightOverlay

@Composable
fun StreamRoutesMapScreen() {
    val context = LocalContext.current


    val cameraState = rememberCameraState {
        geoPoint = GeoPoint(20.132195, -101.186513)
        zoom = 15.0
    }

    var mapProperties by remember {
        mutableStateOf(DefaultMapProperties)
    }

    val overlayManagerState = rememberOverlayManagerState()

    SideEffect {
        mapProperties = mapProperties
            .copy(isTilesScaledToDpi = true)
            .copy(tileSources = TileSourceFactory.MAPNIK)
            .copy(isEnableRotationGesture = true)
            .copy(zoomButtonVisibility = ZoomButtonVisibility.ALWAYS)
    }


    val ruta1I = listOf(
        Pair(20.132195, -101.186513),
        Pair(20.130103, -101.187440),
        Pair(20.129952, -101.185994),
        Pair(20.126200, -101.186821),
        Pair(20.126371, -101.189060),
        Pair(20.126477, -101.190613),
        Pair(20.126474, -101.190628),
        Pair(20.128377, -101.190353),
        Pair(20.129459, -101.190800),
        Pair(20.128658, -101.191782),
        Pair(20.127031, -101.192631),
        Pair(20.126338, -101.193629),
        Pair(20.126167, -101.193720),
        Pair(20.125250, -101.193859),
        Pair(20.125212, -101.194554),
        Pair(20.122248, -101.194691),
        Pair(20.119407, -101.195200),
        Pair(20.118674, -101.195259),
        Pair(20.117485, -101.195490),
        Pair(20.117352, -101.194554),
        Pair(20.116483, -101.194825),
        Pair(20.114481, -101.191281),
        Pair(20.114421, -101.190940),
        Pair(20.114470, -101.187700),
        Pair(20.114447, -101.187574),
        Pair(20.114374, -101.187461),
        Pair(20.114706, -101.187499),
        Pair(20.115807, -101.187730),
        Pair(20.115848, -101.186948),
        Pair(20.116284, -101.187047),
        Pair(20.118583, -101.187491)
    )

    val ruta1IGeoPoint = ruta1I.map { GeoPoint(it.first, it.second) }

    // define marker state
    val inicioR1MarkerState = rememberMarkerState(
        geoPoint = ruta1IGeoPoint.first()
    )
    val finR1MarkerState = rememberMarkerState(
        geoPoint = ruta1IGeoPoint.last()
    )

    val ruta1V = listOf(
        Pair(20.118606, -101.187491),
        Pair(20.119055, -101.187579),
        Pair(20.120636, -101.188366),
        Pair(20.121256, -101.188500),
        Pair(20.122626, -101.188650),
        Pair(20.122738, -101.192298),
        Pair(20.119184, -101.193945),
        Pair(20.118572, -101.194194),
        Pair(20.117353, -101.194556),
        Pair(20.117613, -101.196209),
        Pair(20.118717, -101.195987),
        Pair(20.122084, -101.195424),
        Pair(20.121973, -101.194759),
        Pair(20.121487, -101.194837),
        Pair(20.121321, -101.193517),
        Pair(20.124381, -101.193349),
        Pair(20.125227, -101.193325),
        Pair(20.125245, -101.193861),
        Pair(20.126181, -101.193715),
        Pair(20.126410, -101.193377),
        Pair(20.126455, -101.190625),
        Pair(20.126125, -101.185931),
        Pair(20.126125, -101.185931),
        Pair(20.130110, -101.187422),
        Pair(20.131608, -101.186768),
        Pair(20.132157, -101.186326)
    )

    val ruta1VGeoPoint = ruta1V.map { GeoPoint(it.first, it.second) }

    // define marker state
    val inicioR1VMarkerState = rememberMarkerState(
        geoPoint = ruta1VGeoPoint.first()
    )
    val finR1VMarkerState = rememberMarkerState(
        geoPoint = ruta1VGeoPoint.last()
    )

    OpenStreetMap(
        modifier = Modifier.fillMaxSize(),
        cameraState = cameraState,
        properties = mapProperties,
        overlayManagerState = overlayManagerState,
        onFirstLoadListener = {
            val copyright = CopyrightOverlay(context)
            overlayManagerState.overlayManager.add(copyright)

        }
    ) {
        // add marker here
        Marker(
            state = inicioR1MarkerState // add marker state
            , title = "Ruta 1(12 Octubre)", visible = true
        ) {
            // create info window node
            Column(
                modifier = Modifier
                    .size(100.dp)
                    .background(color = Color.Gray, shape = RoundedCornerShape(7.dp)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // setup content of info window
                Text(text = it.title)
                Text(text = it.snippet, fontSize = 10.sp)
            }
        }

        Marker(
            state = finR1MarkerState // add marker state
            , title = "Ruta 1(Auditorio)", visible = true, snippet = "Auditorio"

        ) {
            // create info window node
            Column(
                modifier = Modifier
                    .size(100.dp)
                    .background(color = Color.Gray, shape = RoundedCornerShape(7.dp)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // setup content of info window
                Text(text = it.title)
                Text(text = it.snippet, fontSize = 10.sp)
            }

        }

        // add polylinerur
        Polyline(geoPoints = ruta1IGeoPoint)



    ////////////////////////////////////////////////////////

    // add marker here
    Marker(
        state = inicioR1VMarkerState // add marker state
        , title = "Ruta 1(Audirorio)", visible = true
    ) {
        // create info window node
        Column(
            modifier = Modifier
                .size(100.dp)
                .background(color = Color.Gray, shape = RoundedCornerShape(7.dp)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // setup content of info window
            Text(text = it.title)
            Text(text = it.snippet, fontSize = 10.sp)
        }
    }

    Marker(
        state = finR1VMarkerState // add marker state
        , title = "Ruta 1 vuelta", visible = true, snippet = "12 octubew"

    ) {
        // create info window node
        Column(
            modifier = Modifier
                .size(100.dp)
                .background(color = Color.Gray, shape = RoundedCornerShape(7.dp)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // setup content of info window
            Text(text = it.title)
            Text(text = it.snippet, fontSize = 10.sp)
        }

    }

    // add polylinerur
    Polyline(geoPoints = ruta1VGeoPoint, color = Color.Blue)
    }

}
