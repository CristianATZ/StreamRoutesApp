@file:OptIn(ExperimentalMaterial3Api::class)

package net.streamroutes.sreamroutesapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.twotone.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R

@Composable
fun MainScreen(navController: NavController) {
    Main(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main( navController: NavController ){
    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        TopAppBar(
            title = {
                Text(text = "Ciudad",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            navigationIcon = {
                IconButton(onClick = {  navController.navigate(route = AppScreens.MenuScreen.route)}) {
                    Icon(
                        Icons.Filled.Menu,
                        contentDescription = "Te enviara al menu de opciones"
                    )
                }
            },
            actions = {
                IconButton(onClick = {  }) {
                    Icon(
                        Icons.Filled.Notifications,
                        contentDescription = "Te dira tus notificaciones del dia"
                    )
                }
            }
        )

        val itsur = LatLng(20.139468718311957, -101.15069924573676)
        val itsurState = MarkerState(position = itsur)
        val cameraPositionState = rememberCameraPositionState(){
            position = CameraPosition.fromLatLngZoom(itsur,17f)
        }
        GoogleMap(
            modifier = Modifier
                .fillMaxSize(),
            cameraPositionState = cameraPositionState
        ){
            Marker(
                state = itsurState,
                title = "Nuetra Universidad"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainView(){
    preview()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun preview(){
    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        TopAppBar(
            title = {
                Text(text = "Ciudad",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            navigationIcon = {
                IconButton(onClick = {  }) {
                    Icon(
                        Icons.Filled.Menu,
                        contentDescription = "Te enviara al menu de opciones"
                    )
                }
            },
            actions = {
                IconButton(onClick = {  }) {
                    Icon(
                        Icons.Filled.Notifications,
                        contentDescription = "Te dira tus notificaciones del dia"
                    )
                }
            }
        )

        val itsur = LatLng(20.139468718311957, -101.15069924573676)
        val itsurState = MarkerState(position = itsur)
        val cameraPositionState = rememberCameraPositionState(){
            position = CameraPosition.fromLatLngZoom(itsur,17f)
        }
        GoogleMap(
            modifier = Modifier
                .fillMaxSize(),
            cameraPositionState = cameraPositionState
        ){
            Marker(
                state = itsurState,
                title = "Nuetra Universidad"
            )
        }
    }
}