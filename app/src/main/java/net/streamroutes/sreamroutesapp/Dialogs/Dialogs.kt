package net.streamroutes.sreamroutesapp.Dialogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import net.streamroutes.sreamroutesapp.Colores.color_bola_switch
import net.streamroutes.sreamroutesapp.Colores.color_fondo_claro
import net.streamroutes.sreamroutesapp.Colores.color_fondo_oscuro
import net.streamroutes.sreamroutesapp.Colores.color_fondo_switch_activo
import net.streamroutes.sreamroutesapp.Colores.color_fondo_switch_inactivo
import net.streamroutes.sreamroutesapp.Colores.color_letra
import net.streamroutes.sreamroutesapp.Colores.color_letra_alterno
import net.streamroutes.sreamroutesapp.Colores.color_letra_textfield
import net.streamroutes.sreamroutesapp.Colores.color_letra_topappbar
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.ui.theme.Purple40
import net.streamroutes.sreamroutesapp.ui.theme.Purple80
import net.streamroutes.sreamroutesapp.ui.theme.PurpleGrey40

@Preview (showBackground = true)
@Composable
fun view() {

}

// DIALOG DE NOTIFICACIONES PUSH (NOTIFICATIONS SCREEN)
// DIALOG DE NOTIFICACIONES PUSH (NOTIFICATIONS SCREEN)
// DIALOG DE NOTIFICACIONES PUSH (NOTIFICATIONS SCREEN)

@Composable
fun NotificationPush(
    dialogo: MutableState<Boolean>,
    siempre: MutableState<Boolean>,
    durante: MutableState<Boolean>,
    nunca: MutableState<Boolean>
){
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
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 15.dp
                ),
                colors = CardDefaults.cardColors(
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
                    PushOptions(
                        text = "Siempre",
                        color_letra = color_letra_alterno,
                        variable = siempre
                    )

                    // durante
                    PushOptions(
                        text = "Durante",
                        color_letra = color_letra_alterno,
                        variable = durante
                    )

                    // nunca
                    PushOptions(
                        text = "Nunca",
                        color_letra = color_letra_alterno,
                        variable = nunca
                    )
                }
            }
        }
    )
}

@Composable
fun PushOptions(
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
            fontFamily = FontFamily.SansSerif,
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



// DIALOG PARA HABILITAR PERMISOS DE UBICACION
@Composable
fun HabilitarUbicacion(
    dialogo: MutableState<Boolean>,
    onClick: () -> Unit
) {
    Dialog(
        onDismissRequest = {
            dialogo.value = false
        }
    ) {
        Card(
            //shape = MaterialTheme.shapes.medium,
            shape = RoundedCornerShape(percent = 10),
            // modifier = modifier.size(280.dp, 240.dp)
            modifier = Modifier
                .padding(10.dp,5.dp,10.dp,10.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(color_fondo_oscuro)
            ) {

                //.......................................................................
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null, // decorative
                    contentScale = ContentScale.Fit,
                    colorFilter  = ColorFilter.tint(
                        color = Purple40
                    ),
                    modifier = Modifier
                        .padding(top = 35.dp)
                        .height(70.dp)
                        .fillMaxWidth(),

                    )

                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Habilitar Ubicacion",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 20.sp,
                        color = color_letra_topappbar
                    )
                    Text(
                        text = "La aplicacion requiere que habilites tu ubicacion.",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.bodyMedium,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 15.sp,
                        color = color_letra_topappbar
                    )
                }

                // botones
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceAround) {

                    // habilitar
                    TextButton(
                        onClick = onClick,
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .background(color_fondo_switch_activo)
                    ) {

                        Text(
                            text = "Habilitar",
                            fontWeight = FontWeight.ExtraBold,
                            color = color_letra,
                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                            fontSize = 15.sp
                        )
                    }

                    // no habilitar
                    TextButton(
                        onClick = {
                            dialogo.value = false
                        },
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .background(color_fondo_switch_activo)
                    ) {
                        Text(
                            text ="No habilitar",
                            color = Color.DarkGray,
                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                            fontSize = 15.sp
                        )
                    }
                }
            }
        }
    }
}


// DIALOG PARA HABILITAR PERMISOS DE SMS
@Composable
fun HabilitarSMS(
    dialogo: MutableState<Boolean>,
    onClick: () -> Unit
) {
    Dialog(
        onDismissRequest = {
            dialogo.value = false
        }
    ) {
        Card(
            //shape = MaterialTheme.shapes.medium,
            shape = RoundedCornerShape(percent = 10),
            // modifier = modifier.size(280.dp, 240.dp)
            modifier = Modifier
                .padding(10.dp,5.dp,10.dp,10.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(color_fondo_oscuro)
            ) {

                //.......................................................................
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null, // decorative
                    contentScale = ContentScale.Fit,
                    colorFilter  = ColorFilter.tint(
                        color = Purple40
                    ),
                    modifier = Modifier
                        .padding(top = 35.dp)
                        .height(70.dp)
                        .fillMaxWidth(),

                    )

                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Habilitar SMS",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 20.sp,
                        color = color_letra_topappbar
                    )
                    Text(
                        text = "La aplicacion requiere que habilites los SMS.",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.bodyMedium,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 15.sp,
                        color = color_letra_topappbar
                    )
                }

                // botones
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceAround) {

                    // habilitar
                    TextButton(
                        onClick = onClick,
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .background(color_fondo_switch_activo)
                    ) {

                        Text(
                            text = "Habilitar",
                            fontWeight = FontWeight.ExtraBold,
                            color = color_letra,
                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                            fontSize = 15.sp
                        )
                    }

                    // no habilitar
                    TextButton(
                        onClick = {
                            dialogo.value = false
                        },
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .background(color_fondo_switch_activo)
                    ) {
                        Text(
                            text ="No habilitar",
                            color = Color.DarkGray,
                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                            fontSize = 15.sp
                        )
                    }
                }
            }
        }
    }
}