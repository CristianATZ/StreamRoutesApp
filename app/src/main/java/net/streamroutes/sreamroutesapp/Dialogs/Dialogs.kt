package net.streamroutes.sreamroutesapp.Dialogs

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
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

@Preview (showBackground = true)
@Composable
fun view() {

}

// DIALOG DE NOTIFICACIONES PUSH (NOTIFICATIONS SCREEN)
// DIALOG DE NOTIFICACIONES PUSH (NOTIFICATIONS SCREEN)
// DIALOG DE NOTIFICACIONES PUSH (NOTIFICATIONS SCREEN)

@Composable
fun DialogNotificationPush(
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
// DIALOG PARA HABILITAR PERMISOS DE UBICACION
// DIALOG PARA HABILITAR PERMISOS DE UBICACION

@Composable
fun DialogHabilitarUbicacion(
    dialogo: MutableState<Boolean>,
    onClick: () -> Unit
) {

    val context = LocalContext.current

    Dialog(
        onDismissRequest = {
            // NO HAY ON DISMISS
        }
    ) {
        Card(
            //shape = MaterialTheme.shapes.medium,
            shape = RoundedCornerShape(percent = 10),
            // modifier = modifier.size(280.dp, 240.dp)
            modifier = Modifier
                .padding(10.dp, 5.dp, 10.dp, 10.dp)
                .wrapContentHeight()
        ) {
            Column(
                modifier = Modifier
                    .background(color_fondo_oscuro),
                verticalArrangement = Arrangement.Center
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
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.size(50.dp))

                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Permitir acceso a tu ubicación",
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
                        text = "Stream Routes necesita acceder a tu ubicación para ofrecerte una experiencia más " +
                                "personalizada y mostrarte rutas y lugares de interés cercanos.",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 10.dp, start = 15.dp, end = 15.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.bodyMedium,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 15.sp,
                        color = color_letra_topappbar
                    )
                }

                Spacer(modifier = Modifier.size(50.dp))

                // botones
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.Bottom
                ) {

                    // no habilitar
                    TextButton(
                        onClick = {
                            // cerrar la aplicacion
                            Toast.makeText(context, "La aplicacion no tendra acceso a algunas funciones.", Toast.LENGTH_LONG).show()
                            dialogo.value = false
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .background(color_fondo_switch_activo)
                    ) {
                        Text(
                            text ="No permitir",
                            color = Color.DarkGray,
                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                            fontSize = 15.sp
                        )
                    }

                    // habilitar
                    TextButton(
                        onClick = {
                            onClick
                            dialogo.value = false
                        },
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .background(color_fondo_switch_activo)
                    ) {

                        Text(
                            text = "Permitir",
                            fontWeight = FontWeight.ExtraBold,
                            color = color_letra,
                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                            fontSize = 15.sp
                        )
                    }
                }
            }
        }
    }
}


// DIALOG PARA HABILITAR PERMISOS DE CONTACTOS
// DIALOG PARA HABILITAR PERMISOS DE CONTACTOS
// DIALOG PARA HABILITAR PERMISOS DE CONTACTOS

@Composable
fun DialogHabilitarContactos(
    dialogo: MutableState<Boolean>,
    onClick: () -> Unit
) {
    val context = LocalContext.current

    Dialog(
        onDismissRequest = {
            // NO HAY ON DISMISS
        }
    ) {
        Card(
            //shape = MaterialTheme.shapes.medium,
            shape = RoundedCornerShape(percent = 10),
            // modifier = modifier.size(280.dp, 240.dp)
            modifier = Modifier
                .padding(10.dp, 5.dp, 10.dp, 10.dp)
                .wrapContentHeight()
        ) {
            Column(
                modifier = Modifier
                    .background(color_fondo_oscuro),
                verticalArrangement = Arrangement.Center
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
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.size(50.dp))

                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Permitir acceso a tus contactos",
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
                        text = "Stream Routes necesita acceder a tus contactos para proporcionar una " +
                                "experiencia personalizada y facilitar la conexión con tus amigos y " +
                                "contactos.",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 10.dp, start = 15.dp, end = 15.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.bodyMedium,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 15.sp,
                        color = color_letra_topappbar
                    )
                }

                Spacer(modifier = Modifier.size(50.dp))

                // botones
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.Bottom
                ) {

                    // no habilitar
                    TextButton(
                        onClick = {
                            // cerrar la aplicacion
                            dialogo.value = false
                            Toast.makeText(context, "La aplicacion no tendra acceso a algunas funciones.", Toast.LENGTH_LONG).show()
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .background(color_fondo_switch_activo)
                    ) {
                        Text(
                            text ="No permitir",
                            color = Color.DarkGray,
                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                            fontSize = 15.sp
                        )
                    }

                    // habilitar
                    TextButton(
                        onClick = {
                            onClick
                            dialogo.value = false
                        },
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .background(color_fondo_switch_activo)
                    ) {

                        Text(
                            text = "Permitir",
                            fontWeight = FontWeight.ExtraBold,
                            color = color_letra,
                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                            fontSize = 15.sp
                        )
                    }
                }
            }
        }
    }
}


// DIALOG PARA EL AVISO DE PRIVACIDAD
// DIALOG PARA EL AVISO DE PRIVACIDAD
// DIALOG PARA EL AVISO DE PRIVACIDAD

@Composable
fun DialogAvisoDePrivacidad(
    dialogo: MutableState<Boolean>,
    onClick: () -> Unit
) {
    val context = LocalContext.current

    Dialog(
        onDismissRequest = {
            // NO HAY ON DISMISS
        }
    ) {
        Card(
            //shape = MaterialTheme.shapes.medium,
            shape = RoundedCornerShape(percent = 10),
            // modifier = modifier.size(280.dp, 240.dp)
            modifier = Modifier
                .padding(10.dp, 5.dp, 10.dp, 10.dp)
                .wrapContentHeight()
        ) {
            Column(
                modifier = Modifier
                    .background(color_fondo_oscuro),
                verticalArrangement = Arrangement.Center
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
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.size(10.dp))

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                        .height(400.dp)
                ) {
                    Text(
                        text = "Aviso de privacidad",
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
                        text = "Bienvenido(a) a nuestra aplicación de transporte público. En Stream Routes, nos preocupamos " +
                                "por proteger tus datos personales y respetar tu privacidad. A continuación, te proporcionamos " +
                                "información importante sobre cómo recopilamos, utilizamos y protegemos tus datos. Al utilizar " +
                                "nuestra aplicación, aceptas los términos y condiciones de este aviso de privacidad.",
                        modifier = Modifier
                            .padding(top = 10.dp, start = 15.dp, end = 15.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.bodyMedium,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 15.sp,
                        color = color_letra_topappbar
                    )
                }

                var acepto = remember { mutableStateOf(false) }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                        .clickable {
                            acepto.value = !acepto.value
                        },
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = acepto.value,
                        onCheckedChange = null,
                        colors = CheckboxDefaults.colors(
                            checkedColor = color_letra_textfield,
                            checkmarkColor = color_fondo_claro,
                            uncheckedColor = Color.Gray
                        )
                    )
                    Spacer(modifier = Modifier.size(15.dp))
                    Text(
                        text = "Acepto los terminos y condiciones",
                        style = MaterialTheme.typography.labelLarge,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 12.sp,
                        color = color_letra_topappbar
                    )
                }
                // botones
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.Bottom
                ) {

                    // aceptar
                    TextButton(
                        onClick = {
                            dialogo.value = false;
                            onClick
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(if (acepto.value) color_fondo_switch_activo else Color.LightGray),
                        enabled = acepto.value
                    ) {

                        Text(
                            text = "Continuar",
                            fontWeight = FontWeight.ExtraBold,
                            color = if(acepto.value) color_letra else Color.DarkGray,
                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                            fontSize = 15.sp
                        )
                    }
                }
            }
        }
    }
}



// DIALOG PARA AVISO DE AUTOBUS CERCANO
// DIALOG PARA AVISO DE AUTOBUS CERCANO
// DIALOG PARA AVISO DE AUTOBUS CERCANO

@Composable
fun DialogAutobusCercano(
    dialogo: MutableState<Boolean>,
    onClick: () -> Unit
) {
    Dialog(
        onDismissRequest = { /*TODO*/ }
    ) {
        Card(
            shape = RoundedCornerShape(percent = 10),
        ) {
            Column(
                modifier = Modifier
                    .background(color_fondo_oscuro)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // icono de informacion
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(vertical = 10.dp),
                    tint = color_letra_textfield
                )

                // texto informativo
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style =
                            SpanStyle(
                                color = color_letra_textfield,
                                fontSize = 18.sp,
                                fontFamily = FontFamily.SansSerif
                            )
                        ) {
                            append("Tu ")
                        }
                        withStyle(
                            style =
                            SpanStyle(
                                color = color_letra_textfield,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 18.sp,
                                fontFamily = FontFamily.SansSerif
                            )
                        ) {
                            append("autobús")
                        }
                        withStyle(
                            style =
                            SpanStyle(
                                color = color_letra_textfield,
                                fontSize = 18.sp,
                                fontFamily = FontFamily.SansSerif
                            )
                        ) {
                            append(" está cerca. Mantente alerta para abordarlo.")
                        }
                    },
                    modifier = Modifier
                        .padding(top = 15.dp, bottom = 20.dp, start = 20.dp, end = 20.dp),
                    textAlign = TextAlign.Center
                )

                // boton
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.Bottom
                ) {

                    // aceptar
                    TextButton(
                        onClick = {
                            onClick
                            dialogo.value = false
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color_fondo_switch_activo)
                    ) {

                        Text(
                            text = "Aceptar",
                            fontWeight = FontWeight.ExtraBold,
                            color = color_letra,
                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                            fontSize = 15.sp
                        )
                    }
                }
            }
        }
    }
}


// DIALOG DE AVISO DE PARADA
// DIALOG DE AVISO DE PARADA
// DIALOG DE AVISO DE PARADA

@Composable
fun DialogParada(
    dialogo: MutableState<Boolean>,
    onClick: () -> Unit
) {
    Dialog(
        onDismissRequest = { /*TODO*/ }
    ) {
        Card(
            shape = RoundedCornerShape(percent = 10),
        ) {
            Column(
                modifier = Modifier
                    .background(color_fondo_oscuro)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // icono de informacion
                Icon(
                    imageVector = Icons.Filled.Place,
                    contentDescription = "",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(vertical = 10.dp),
                    tint = color_letra_textfield
                )

                // texto informativo
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style =
                            SpanStyle(
                                color = color_letra_textfield,
                                fontSize = 18.sp,
                                fontFamily = FontFamily.SansSerif
                            )
                        ) {
                            append("Hemos llegado a una ")
                        }
                        withStyle(
                            style =
                            SpanStyle(
                                color = color_letra_textfield,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 18.sp,
                                fontFamily = FontFamily.SansSerif
                            )
                        ) {
                            append("parada")
                        }
                        withStyle(
                            style =
                            SpanStyle(
                                color = color_letra_textfield,
                                fontSize = 18.sp,
                                fontFamily = FontFamily.SansSerif
                            )
                        ) {
                            append(", verifica si necesitas bajarte aqui.")
                        }
                    },
                    modifier = Modifier
                        .padding(top = 15.dp, bottom = 20.dp, start = 20.dp, end = 20.dp),
                    textAlign = TextAlign.Center
                )

                // boton
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.Bottom
                ) {

                    // aceptar
                    TextButton(
                        onClick = {
                            onClick
                            dialogo.value = false
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color_fondo_switch_activo)
                    ) {

                        Text(
                            text = "Aceptar",
                            fontWeight = FontWeight.ExtraBold,
                            color = color_letra,
                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                            fontSize = 15.sp
                        )
                    }
                }
            }
        }
    }
}

// DIALOG DE AVISO DE TERMINACION DE RUTA
// DIALOG DE AVISO DE TERMINACION DE RUTA
// DIALOG DE AVISO DE TERMINACION DE RUTA

@Composable
fun DialogDestino(
    dialogo: MutableState<Boolean>,
    onClick: () -> Unit
) {
    Dialog(
        onDismissRequest = { /*TODO*/ }
    ) {
        Card(
            shape = RoundedCornerShape(percent = 10),
        ) {
            Column(
                modifier = Modifier
                    .background(color_fondo_oscuro)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // icono de informacion
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(vertical = 10.dp),
                    tint = color_letra_textfield
                )

                // texto informativo
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style =
                            SpanStyle(
                                color = color_letra_textfield,
                                fontSize = 18.sp,
                                fontFamily = FontFamily.SansSerif
                            )
                        ) {
                            append("Has llegado a tu ")
                        }
                        withStyle(
                            style =
                            SpanStyle(
                                color = color_letra_textfield,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 18.sp,
                                fontFamily = FontFamily.SansSerif
                            )
                        ) {
                            append("destino")
                        }
                        withStyle(
                            style =
                            SpanStyle(
                                color = color_letra_textfield,
                                fontSize = 18.sp,
                                fontFamily = FontFamily.SansSerif
                            )
                        ) {
                            append(", baja del autobus con cuidado. ¡Esperamos verte de nuevo!")
                        }
                    },
                    modifier = Modifier
                        .padding(top = 15.dp, bottom = 20.dp, start = 20.dp, end = 20.dp),
                    textAlign = TextAlign.Center
                )

                // boton
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.Bottom
                ) {

                    // aceptar
                    TextButton(
                        onClick = {
                            onClick
                            dialogo.value = false
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color_fondo_switch_activo)
                    ) {

                        Text(
                            text = "Aceptar",
                            fontWeight = FontWeight.ExtraBold,
                            color = color_letra,
                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                            fontSize = 15.sp
                        )
                    }
                }
            }
        }
    }
}

// DIALOG PARA LA CONEXION A INTERNET
// DIALOG PARA LA CONEXION A INTERNET
// DIALOG PARA LA CONEXION A INTERNET

@Composable
fun DialogInternet(
    dialogo: MutableState<Boolean>,
    onClick: () -> Unit
) {
    Dialog(
        onDismissRequest = { /*TODO*/ }
    ) {
        Card(
            shape = RoundedCornerShape(percent = 10),
        ) {
            Column(
                modifier = Modifier
                    .background(color_fondo_oscuro)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // icono de informacion
                Icon(
                    imageVector = Icons.Filled.Warning,
                    contentDescription = "",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(vertical = 10.dp),
                    tint = color_letra_textfield
                )

                // texto informativo
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style =
                            SpanStyle(
                                color = color_letra_textfield,
                                fontSize = 18.sp,
                                fontFamily = FontFamily.SansSerif
                            )
                        ) {
                            append("Verifica tu ")
                        }
                        withStyle(
                            style =
                            SpanStyle(
                                color = color_letra_textfield,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 18.sp,
                                fontFamily = FontFamily.SansSerif
                            )
                        ) {
                            append("conexion a internet")
                        }
                        withStyle(
                            style =
                            SpanStyle(
                                color = color_letra_textfield,
                                fontSize = 18.sp,
                                fontFamily = FontFamily.SansSerif
                            )
                        ) {
                            append(" y vuelve a intentarlo nuevamente.")
                        }
                    },
                    modifier = Modifier
                        .padding(top = 15.dp, bottom = 20.dp, start = 20.dp, end = 20.dp),
                    textAlign = TextAlign.Center
                )

                // boton
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.Bottom
                ) {

                    // aceptar
                    TextButton(
                        onClick = {
                            // cerrar app ahora si xd
                            onClick
                            dialogo.value = false
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color_fondo_switch_activo)
                    ) {

                        Text(
                            text = "Aceptar",
                            fontWeight = FontWeight.ExtraBold,
                            color = color_letra,
                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                            fontSize = 15.sp
                        )
                    }
                }
            }
        }
    }
}