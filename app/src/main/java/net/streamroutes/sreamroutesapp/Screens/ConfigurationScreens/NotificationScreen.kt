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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.Colores.color_bola_switch
import net.streamroutes.sreamroutesapp.Colores.color_fondo_claro
import net.streamroutes.sreamroutesapp.Colores.color_fondo_oscuro
import net.streamroutes.sreamroutesapp.Colores.color_fondo_switch_activo
import net.streamroutes.sreamroutesapp.Colores.color_fondo_switch_inactivo
import net.streamroutes.sreamroutesapp.Colores.color_fondo_topappbar_alterno
import net.streamroutes.sreamroutesapp.Colores.color_letra
import net.streamroutes.sreamroutesapp.Colores.color_letra_alterno
import net.streamroutes.sreamroutesapp.Colores.color_letra_topappbar
import net.streamroutes.sreamroutesapp.Navigation.AppScreens


@Preview (showBackground = true)
@Composable
fun notificaciones(){
    //notificacionesView()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsScreen(navController: NavController){

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
        Dialog(
            onDismissRequest = {
                dialogo.value = false
            },
            content = {
                // plano
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(percent = 30),
                    elevation = cardElevation(
                        defaultElevation = 15.dp
                    ),
                    colors = cardColors(
                        containerColor = color_fondo_oscuro
                    )
                ) {
                    // cuerpo
                    Column(
                        modifier = Modifier
                            .padding(10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // siempre
                        DialogOptions(
                            text = "Siempre",
                            color_letra = color_letra_alterno,
                            variable = siempre
                        )

                        // durante
                        DialogOptions(
                            text = "Durante",
                            color_letra = color_letra_alterno,
                            variable = durante
                        )

                        // nunca
                        DialogOptions(
                            text = "Nunca",
                            color_letra = color_letra_alterno,
                            variable = nunca
                        )
                    }
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color_fondo_claro)
    ) {
        // top app bar
        TopAppBar(
            title = {
                Text(text = "Notificaciones",
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            navigationIcon = {
                IconButton(onClick = {  }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "configurar el tipo de notificaciones"
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
                        text = "Notificaciones push", // texto
                        color = color_letra,
                        fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif,
                        fontSize = 20.sp
                    )
                    Text(
                        text = "Tipo de notificacion", // texto
                        color = color_letra,
                        fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif,
                        fontSize = 12.sp
                    )
                }
            }

            // noticias
            OptionSwitch(
                variable = noticias,
                text = "Noticias de la aplicacion",
                sub_text = "Nuevas versiones, promociones, etc."
            )

            // alertas
            OptionSwitch(
                variable = alertas,
                text = "Alertas",
                sub_text = "Cambios de horarios, rutas, etc."
            )

            // suscripcion
            OptionSwitch(
                variable = suscripcion,
                text = "Suscripcion",
                sub_text = "Expiracion de la suscripcion, etc."
            )
        }
    }
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

@Composable
fun DialogOptions(
    text: String,
    color_letra: Color,
    variable: MutableState<Boolean>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.size(15.dp))
        Text(
            text = text, // texto
            color = color_letra,
            fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif,
            fontSize = 20.sp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            // switch
            Switch(
                checked = variable.value,
                onCheckedChange = { variable.value = it },
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
            Spacer(modifier = Modifier.size(15.dp))
        }
    }
}