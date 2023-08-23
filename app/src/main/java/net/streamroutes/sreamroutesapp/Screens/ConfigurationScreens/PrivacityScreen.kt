package net.streamroutes.sreamroutesapp.Screens.ConfigurationScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
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
import net.streamroutes.sreamroutesapp.Colores.color_botones
import net.streamroutes.sreamroutesapp.Colores.color_fondo
import net.streamroutes.sreamroutesapp.Colores.color_fondo_topbar
import net.streamroutes.sreamroutesapp.Colores.color_icon
import net.streamroutes.sreamroutesapp.Colores.color_letra_topbar
import net.streamroutes.sreamroutesapp.Colores.color_letrain
import net.streamroutes.sreamroutesapp.Colores.color_letraout
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


    Scaffold(
        topBar = { TopBarBody(myViewModel,navController) },
        containerColor = color_fondo
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            // localizacion
            Options(
                text = myViewModel.languageType().get(78),
                sub_text = myViewModel.languageType().get(79),
                color_letra = color_letraout,
                variable = localizacion
            )

            // anuncios
            Options(
                text = myViewModel.languageType().get(80), // texto
                sub_text = myViewModel.languageType().get(81),
                color_letra = color_letraout,
                variable = anuncios
            )

            // rutas
            Options(
                text = myViewModel.languageType().get(82), // texto
                sub_text = myViewModel.languageType().get(83),
                color_letra = color_letraout,
                variable = rutas
            )

            // suscripcion
            Options(
                text = myViewModel.languageType().get(84), // texto
                sub_text = myViewModel.languageType().get(85),
                color_letra = color_letraout,
                variable = suscripcion
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBarBody(
    myViewModel: MyViewModel,
    navController: NavController
) {
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
                    contentDescription = "Te enviara al menu de configuraciones",
                    tint = color_icon
                )
            }
        },
        colors = TopAppBarDefaults
            .smallTopAppBarColors(
                containerColor = color_fondo_topbar,
                titleContentColor = color_letra_topbar
            )
    )
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
                .padding(vertical = 10.dp)
        ) {
            Text(
                text = text, // texto
                color = color_letra,
                fontFamily = FontFamily.SansSerif,
                fontSize = 18.sp
            )
            Text(
                text = sub_text, // texto
                color = color_letra,
                fontFamily = FontFamily.SansSerif,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.size(5.dp))
        // switch
        Switch(
            checked = variable.value,
            onCheckedChange = null,
            colors = SwitchDefaults.colors(
                // cuando esta activo
                checkedThumbColor = Color.White,
                checkedTrackColor = color_letrain,
                checkedBorderColor = color_letrain,
                // cuando esta inactivo
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = color_botones,
                uncheckedBorderColor = color_botones
            )
        )
    }
}

@Preview (showBackground = true)
@Composable
fun view(){
    //priva()
}