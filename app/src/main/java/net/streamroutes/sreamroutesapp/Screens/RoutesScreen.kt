package net.streamroutes.sreamroutesapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
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
import net.streamroutes.sreamroutesapp.Navigation.AppScreens

@Composable
fun RoutesScreen(navController: NavController){
    Routes(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Routes(navController: NavController){
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ){
        val jardin = LatLng(20.141624881223645, -101.17883478807325)
        val jardinState = MarkerState(position = jardin)
        val cameraPositionState = rememberCameraPositionState(){
            position = CameraPosition.fromLatLngZoom(jardin,17f)
        }
        GoogleMap(
            modifier = Modifier
                .fillMaxSize(),
            cameraPositionState = cameraPositionState
        ){
            Marker(
                state = jardinState,
                title = "Jardín Principal De Uriangato"
            )
        }

        val roundCornerShape = RoundedCornerShape(topEnd = 15.dp, bottomStart = 15.dp, topStart = 15.dp, bottomEnd = 15.dp)
        Row(
            modifier = Modifier
                .padding(5.dp, 10.dp)
                .fillMaxWidth()
                .clip(roundCornerShape),
            verticalAlignment = Alignment.Bottom
        ) {
           // textfield destino
            var destiny by remember { mutableStateOf(TextFieldValue("")) }
            BasicTextField(
                value = destiny,
                onValueChange = {destiny = it},
                singleLine = true,
                modifier = Modifier
                    .height(70.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                decorationBox = { innerTextField ->
                    Row(
                        Modifier
                            .background(Color(0xFFFFE5B4), RoundedCornerShape(percent = 30))
                            .padding(16.dp)
                            .fillMaxWidth(0.8f)
                    ){
                        if (destiny.text.isEmpty()){
                            Text(
                                text = "Destino",
                                color = Color(0xFF807B71),
                                letterSpacing = 3.sp,
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                            )
                        }
                        innerTextField()
                    }
                }
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun RoutesScreenView(){

}