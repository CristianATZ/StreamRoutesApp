package net.streamroutes.sreamroutesapp.Screens.ConfigurationScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
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
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacityScreen(myViewModel: MyViewModel,navController: NavController){

    // variables globales
    var localizacion = remember { mutableStateOf(true) }
    var anuncios = remember { mutableStateOf(true) }
    var rutas = remember { mutableStateOf(true) }
    var suscripcion = remember { mutableStateOf(true) }

    // ventana
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color_fondo_claro)
    ) {
        // top app bar
        TopAppBar(
            title = {
                Text(text = myViewModel.languageType().get(77),
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

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.size(15.dp))

            // localizacion
            Options(
                text = myViewModel.languageType().get(78),
                sub_text = myViewModel.languageType().get(79),
                color_letra = color_letra,
                variable = localizacion
            )

            // anuncios
            Options(
                text = myViewModel.languageType().get(80), // texto
                sub_text = myViewModel.languageType().get(81),
                color_letra = color_letra,
                variable = anuncios
            )

            // rutas
            Options(
                text = myViewModel.languageType().get(82), // texto
                sub_text = myViewModel.languageType().get(83),
                color_letra = color_letra,
                variable = rutas
            )

            // suscripcion
            Options(
                text = myViewModel.languageType().get(84), // texto
                sub_text = myViewModel.languageType().get(85),
                color_letra = color_letra,
                variable = suscripcion
            )
        }
    }


}

@Composable
private fun Options(
    text: String,
    sub_text: String,
    color_letra: Color,
    variable: MutableState<Boolean>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
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
                fontFamily = FontFamily.SansSerif,
                fontSize = 20.sp
            )
            Text(
                text = sub_text, // texto
                color = color_letra,
                fontFamily = FontFamily.SansSerif,
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

@Preview (showBackground = true)
@Composable
fun view(){
    //priva()
}