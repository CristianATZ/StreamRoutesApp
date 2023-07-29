package net.streamroutes.sreamroutesapp.Screens.MenuScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.Colores.color_fondo_claro
import net.streamroutes.sreamroutesapp.Colores.color_fondo_topappbar_alterno
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


@Composable
fun RoutesScreen(navController: NavController){
    Routes(navController = navController)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Routes(navController: NavController){

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview()
@Composable
fun RoutesScreenView(){
    val currentTime = remember {
        Calendar.getInstance().time
    }
    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
    val formattedTime = sdf.format(currentTime)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color_fondo_claro)
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(0.15f)
                .background(color_fondo_topappbar_alterno) // Color de fondo del Box
        ) {
            Column(modifier = Modifier.fillMaxSize(1f)
                .background(color_fondo_topappbar_alterno)
                .padding(top = 12.dp, end = 12.dp)){
                Row{
                    IconButton(onClick = { }) {
                        androidx.compose.material.Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Te enviara al menu de opciones",
                            tint = Color.White
                        )
                    }
                    BasicTextField(
                        value = "Origen",
                        onValueChange = { },
                        modifier = Modifier.fillMaxWidth(1f).fillMaxHeight(.33f).
                            background(Color.White)
                            .padding(start = 12.dp)
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                }

                Row{
                    Spacer(modifier = Modifier.width(48.dp))
                    BasicTextField(
                        value = "Destino",
                        onValueChange = { },
                        modifier = Modifier.fillMaxWidth().fillMaxHeight(.53f).
                            background(Color.White)
                            .padding(start = 12.dp)
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                }

                Row{
                    Text(
                        text = "Hora: $formattedTime",
                        color = Color.White,
                        modifier = Modifier.padding(start = 16.dp, top = 5.dp)
                    )
                }
            }
        }

        //MAP


    }
}