package net.streamroutes.sreamroutesapp.Screens.ConfigurationScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.Colores.color_bola_switch
import net.streamroutes.sreamroutesapp.Colores.color_fondo_claro
import net.streamroutes.sreamroutesapp.Colores.color_fondo_switch_activo
import net.streamroutes.sreamroutesapp.Colores.color_fondo_switch_inactivo
import net.streamroutes.sreamroutesapp.Colores.color_fondo_topappbar_alterno
import net.streamroutes.sreamroutesapp.Colores.color_letra
import net.streamroutes.sreamroutesapp.Colores.color_letra_topappbar
import net.streamroutes.sreamroutesapp.Navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapOptionsScreen(navController: NavController){
    var paradas = remember { mutableStateOf(true) }
    var terminales = remember { mutableStateOf(true) }
    var ubicacion = remember { mutableStateOf(true) }
    var comida = remember { mutableStateOf(true) }
    var salud = remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color_fondo_claro)
    ) {
        // top app bar
        TopAppBar(
            title = {
                Text(text = "Mapa",
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.navigate(AppScreens.ConfigurationScreen.route) }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Te enviara al menu de configuraciones"
                    )
                }
            },
            colors = TopAppBarDefaults
                .smallTopAppBarColors(
                    containerColor = color_fondo_topappbar_alterno,
                    titleContentColor = color_letra_topappbar
                )
        )

        // cuerpo
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // paradas
            Options(
                text = "Marcadores de paradas",
                sub_text = "Indica donde son las paradas.",
                variable = paradas,
                altura = 70
            )

            // terminales
            Options(
                text = "Marcadores de terminales",
                sub_text = "Indica estaciones de autobuses.",
                variable = terminales,
                altura = 70
            )

            // ubicacion
            Options(
                text = "Marcadores de ubicacion act.",
                sub_text = "Mira tu ubicacion actual.",
                variable = ubicacion,
                altura = 90
            )

            // comida
            Options(
                text = "Lugares de comida",
                sub_text = "Marcadores de restaurantes, etc.",
                variable = comida,
                altura = 70
            )

            // salud
            Options(
                text = "Salud",
                sub_text = "Marcadores de salud.",
                variable = salud,
                altura = 70
            )
        }
    }
}

@Composable
private fun Options(
    text: String,
    sub_text: String,
    variable: MutableState<Boolean>,
    altura: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(altura.dp)
            .toggleable(
                value = variable.value,
                onValueChange = { variable.value = it }
            ),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Spacer(modifier = Modifier.size(25.dp))
        // textos
        Column(
            modifier = Modifier
                .fillMaxWidth(0.75f)
        ) {
            Text(
                text = text, // texto
                color = color_letra,
                fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif,
                fontSize = 20.sp
            )
            Text(
                text = sub_text, // texto
                color = color_letra,
                fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif,
                fontSize = 12.sp
            )
        }

        // switch
        Switch(
            checked = variable.value,
            onCheckedChange = null,
            colors = SwitchDefaults.colors(
                // cuando esta activo
                checkedThumbColor = color_bola_switch,
                checkedTrackColor = color_fondo_switch_activo,
                checkedBorderColor = color_fondo_switch_activo,
                // cuando esta inactivo
                uncheckedThumbColor = color_bola_switch,
                uncheckedTrackColor = color_fondo_switch_inactivo,
                uncheckedBorderColor = color_fondo_switch_inactivo
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun mapaView(){
    //MapOptionsScreen()
}