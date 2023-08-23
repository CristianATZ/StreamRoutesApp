package net.streamroutes.sreamroutesapp.Screens.ConfigurationScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import net.streamroutes.sreamroutesapp.Dialogs.DialogNotificationPush
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens


@Preview (showBackground = true)
@Composable
fun notificaciones(){
    //notificacionesView()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsScreen(myViewModel: MyViewModel,navController: NavController){

    // variables para el funcionamiento del codigo
    var dialogo = remember { mutableStateOf(false) }
    var siempre = remember { mutableStateOf(true) }
    var durante = remember { mutableStateOf(false) }
    var nunca = remember { mutableStateOf(false) }

    var noticias = remember { mutableStateOf(true) }
    var alertas = remember { mutableStateOf(true) }
    var suscripcion = remember { mutableStateOf(true) }

    // logica de los switch (deberian ser radio, luego los cambio)
    if( siempre.value == true ){
        durante.value = false
        nunca.value = false
    }

    if( durante.value == true ){
        siempre.value = false
        nunca.value = false
    }

    if( nunca.value == true ){
        durante.value = false
        siempre.value = false
    }

    // presentacion del dialog
    if (dialogo.value) {
        DialogNotificationPush(dialogo = dialogo, siempre = siempre, durante = durante, nunca = nunca, myViewModel)
    }

    Scaffold(
        topBar = { TopBarBody(myViewModel,navController) },
        containerColor = color_fondo
    ) { paddingValues ->
        // cuerpo
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            // notificaciones push
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .clickable {
                        dialogo.value = true
                    },
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
                        text = myViewModel.languageType().get(55), // texto
                        color = color_letraout,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 18.sp
                    )
                    Text(
                        text = myViewModel.languageType().get(56), // texto
                        color = color_letraout,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 12.sp
                    )
                }
            }

            // noticias
            OptionSwitch(
                variable = noticias,
                text = myViewModel.languageType().get(60),
                sub_text = myViewModel.languageType().get(61)
            )

            // alertas
            OptionSwitch(
                variable = alertas,
                text = myViewModel.languageType().get(62),
                sub_text = myViewModel.languageType().get(63)
            )

            // suscripcion
            OptionSwitch(
                variable = suscripcion,
                text = myViewModel.languageType().get(64),
                sub_text = myViewModel.languageType().get(65)
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
    // top app bar
    TopAppBar(
        title = {
            Text(text = myViewModel.languageType().get(54),
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
private fun OptionSwitch(
    variable: MutableState<Boolean>,
    text: String,
    sub_text: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
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
                color = color_letraout,
                fontFamily = FontFamily.SansSerif,
                fontSize = 18.sp
            )
            Text(
                text = sub_text, // texto
                color = color_letraout,
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