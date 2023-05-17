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
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.twotone.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
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

@Composable
fun MainScreen(navController: NavController) {
    Main(navController)
}

@Composable
fun Main(navController: NavController){
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ){
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

        val gradientColors = listOf(Color(0xFFE8AA42), Color(0xFFE8AA42))
        val roundCornerShape = RoundedCornerShape(topEnd = 15.dp, bottomStart = 15.dp, topStart = 15.dp, bottomEnd = 15.dp)
        Row(
            modifier = Modifier
                .padding(5.dp, 10.dp)
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(colors = gradientColors),
                    shape = roundCornerShape
                )
                .clip(roundCornerShape),
            verticalAlignment = Alignment.Bottom
        ) {
            // perfil
            Box(
                modifier = Modifier
                    .weight(0.25f)
                    .background(
                        brush = Brush.horizontalGradient(colors = gradientColors)
                    )
                    .padding(PaddingValues(horizontal = 5.dp, vertical = 5.dp))
                    .clickable {
                        navController.navigate(route = AppScreens.ProfileScreen.route)
                    },
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(imageVector = Icons.Rounded.AccountBox, contentDescription = null)
                    Text(
                        text = "Perfil",
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Column(
                modifier = Modifier
                    .padding(0.dp,10.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Box(modifier = Modifier
                    .height(50.dp)
                    .width(0.5.dp)
                    .background(Color(0xFF153040)))
            }

            // rutas
            Box(
                modifier = Modifier
                    .weight(0.25f)
                    .background(
                        brush = Brush.horizontalGradient(colors = gradientColors)
                    )
                    .padding(PaddingValues(horizontal = 5.dp, vertical = 5.dp))
                    .clickable {
                        navController.navigate(route = AppScreens.RoutesScreen.route)
                    },
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(imageVector = Icons.Rounded.Place, contentDescription = null)
                    Text(
                        text = "Rutas",
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Column(
                modifier = Modifier
                    .padding(0.dp,10.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Box(modifier = Modifier
                    .height(50.dp)
                    .width(0.5.dp)
                    .background(Color(0xFF153040)))
            }

            // clientes
            Box(
                modifier = Modifier
                    .weight(0.25f)
                    .background(
                        brush = Brush.horizontalGradient(colors = gradientColors)
                    )
                    .padding(PaddingValues(horizontal = 5.dp, vertical = 5.dp))
                    .clickable {
                        navController.navigate(route = AppScreens.CustomerScreen.route)
                    },
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(imageVector = Icons.Rounded.Person, contentDescription = null)
                    Text(
                        text = "Cliente",
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Column(
                modifier = Modifier
                    .padding(0.dp,10.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Box(modifier = Modifier
                    .height(50.dp)
                    .width(0.5.dp)
                    .background(Color(0xFF153040)))
            }

            // ayuda
            Box(
                modifier = Modifier
                    .weight(0.25f)
                    .background(
                        brush = Brush.horizontalGradient(colors = gradientColors)
                    )
                    .padding(PaddingValues(horizontal = 5.dp, vertical = 5.dp))
                    .clickable {
                        navController.navigate(route = AppScreens.HelpScreen.route)
                    },
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(imageVector = Icons.Rounded.Info, contentDescription = null)
                    Text(
                        text = "Ayuda",
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainView(){

}