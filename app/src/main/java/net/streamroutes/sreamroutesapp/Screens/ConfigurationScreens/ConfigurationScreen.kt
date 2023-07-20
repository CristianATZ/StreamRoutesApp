package net.streamroutes.sreamroutesapp.Screens.ConfigurationScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.Colores.color_fondo_claro
import net.streamroutes.sreamroutesapp.Colores.color_letra
import net.streamroutes.sreamroutesapp.Navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfigurationScreen(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color_fondo_claro)
    ) {

        // top app bar configuracion
        TopAppBar(
            title = {
                Text(text = "Configuracion",
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
            }
        )

        // cambiar de ciudad
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // cambiar de ciudad
            Options(
                text = "Cambiar de ciudad",
                text_des = "icono de cambiar ciudad",
                imageVector = Icons.Filled.Add,
                color_texto = color_letra,
                color_icon = Color.Black,
                onClick = {

                }
            )

            // notificaciones
            Options(
                text = "Notificaciones",
                text_des = "icono de tipo de notificaciones",
                imageVector = Icons.Filled.Add,
                color_texto = color_letra,
                color_icon = Color.Black,
                onClick = {

                }
            )

            // mapa
            Options(
                text = "Mapa",
                text_des = "icono de mapa",
                imageVector = Icons.Filled.Add,
                color_texto = color_letra,
                color_icon = Color.Black,
                onClick = {
                    navController.navigate(route = AppScreens.MapOptionsScreen.route)
                }
            )

            // Privacidad
            Options(
                text = "Privacidad",
                text_des = "icono de configuracion de privacidad",
                imageVector = Icons.Filled.Add,
                color_texto = color_letra,
                color_icon = Color.Black,
                onClick = {

                }
            )
        }
    }
}

@Composable
private fun Options(
    text: String,
    text_des: String,
    imageVector: ImageVector,
    color_texto: Color,
    color_icon: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable(onClick = onClick)
    ) {
        Spacer(modifier = Modifier.size(10.dp))
        Icon(
            imageVector = imageVector,
            contentDescription = text_des,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(horizontal = 5.dp)
                .size(45.dp),
            tint = color_icon
        )
        Spacer(modifier = Modifier.size(width = 20.dp, height = 0.dp))
        Text(
            text = text, // texto
            modifier = Modifier
                .align(Alignment.CenterVertically),
            color = color_texto,
            fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif,
            fontSize = 20.sp
        )
    }
}

@Preview (showBackground = true)
@Composable
fun configuracionView(){
    //ConfiguracionScreen()
}