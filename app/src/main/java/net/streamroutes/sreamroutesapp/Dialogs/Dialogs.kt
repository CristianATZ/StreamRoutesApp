package net.streamroutes.sreamroutesapp.Dialogs

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import net.streamroutes.sreamroutesapp.Colores.color_fondo_topbar
import net.streamroutes.sreamroutesapp.Colores.color_letra_topbar
import net.streamroutes.sreamroutesapp.Colores.color_letra_textfield
import net.streamroutes.sreamroutesapp.Colores.color_letrain
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.R

// DIALOG DE NOTIFICACIONES PUSH (NOTIFICATIONS SCREEN)
// DIALOG DE NOTIFICACIONES PUSH (NOTIFICATIONS SCREEN)
// DIALOG DE NOTIFICACIONES PUSH (NOTIFICATIONS SCREEN)


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
                    checkedThumbColor = Color.White,
                    checkedTrackColor = color_letrain,
                    checkedBorderColor = color_letrain,
                    // cuando esta inactivo
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = Color.DarkGray,
                    uncheckedBorderColor = Color.DarkGray
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
    DialogPermisos(
        image = painterResource(id = R.drawable.ic_launcher_background),
        tittle = "Permitir acceso a tu ubicacion",
        text = "Stream Routes necesita acceder a tu ubicacion para ofrecerte una experiencia mas personalizada, mostarte rutas y lugares de interes cercanos.",
        dialogo = dialogo,
        radio = false
    )
}


// DIALOG PARA HABILITAR PERMISOS DE CONTACTOS
// DIALOG PARA HABILITAR PERMISOS DE CONTACTOS
// DIALOG PARA HABILITAR PERMISOS DE CONTACTOS

@Composable
fun DialogHabilitarContactos(
    dialogo: MutableState<Boolean>,
    onClick: () -> Unit
) {
    DialogPermisos(
        image = painterResource(id = R.drawable.ic_launcher_background),
        tittle = "Permitir acceso a tus contactos",
        text = "Stream Routes necesita acceder a tus contactos para propocionar una experiencia personalizada, facilitar la conexion con tus amigos y contactos.",
        dialogo = dialogo,
        radio = false
    )
}


// DIALOG PARA EL AVISO DE PRIVACIDAD
// DIALOG PARA EL AVISO DE PRIVACIDAD
// DIALOG PARA EL AVISO DE PRIVACIDAD

@Composable
fun DialogAvisoDePrivacidad(
    dialogo: MutableState<Boolean>,
    onClick: () -> Unit
) {
    DialogPermisos(
        image = painterResource(id = R.drawable.ic_launcher_background),
        tittle = "Aviso de privacidad",
        text = "Al utilizar nuestra aplicación, aceptas los términos y condiciones de nuestro aviso de privacidad.",
        dialogo = dialogo,
        radio = true
    )
}

@Composable
fun DialogPermisos(
    image: Painter,
    tittle: String,
    text: String,
    radio: Boolean = false,
    dialogo: MutableState<Boolean>
){
    val context = LocalContext.current

    Dialog(
        onDismissRequest = {
            // NO HAY ON DISMISS
        }
    ) {
        Card(
            shape = RoundedCornerShape(percent = 10),
            modifier = Modifier
                .padding(10.dp, 5.dp, 10.dp, 10.dp)
                .wrapContentHeight()
        ) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .height(450.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Image(
                    painter = image,
                    contentDescription = null, // decorative
                    contentScale = ContentScale.Fit,
                    colorFilter  = ColorFilter.tint(
                        color = MaterialTheme.colorScheme.onPrimary
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
                ) {
                    Text(
                        text = tittle,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Text(
                        text = text,
                        modifier = Modifier
                            .padding(top = 10.dp, start = 15.dp, end = 15.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.bodyMedium,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }

                var acepto = remember { mutableStateOf(false) }

                if(radio) {
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
                                checkedColor = MaterialTheme.colorScheme.tertiary,
                                checkmarkColor = MaterialTheme.colorScheme.background,
                                uncheckedColor = Color.Gray
                            )
                        )
                        Spacer(modifier = Modifier.size(15.dp))
                        Text(
                            text = "Acepto los terminos y condiciones",
                            style = MaterialTheme.typography.labelLarge,
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onPrimary
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
                                dialogo.value = false
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(if (acepto.value) MaterialTheme.colorScheme.tertiary else Color.LightGray),
                            enabled = acepto.value
                        ) {

                            Text(
                                text = "Continuar",
                                fontWeight = FontWeight.ExtraBold,
                                color = if(acepto.value) MaterialTheme.colorScheme.onTertiary else Color.DarkGray,
                                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                                fontSize = 15.sp
                            )
                        }
                    }
                }
                else {
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
                                .background(color_letrain)
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
                                dialogo.value = false
                            },
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .background(color_letrain)
                        ) {

                            Text(
                                text = "Permitir",
                                fontWeight = FontWeight.ExtraBold,
                                color = color_letra_topbar,
                                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                                fontSize = 15.sp
                            )
                        }
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
                    .background(color_fondo_topbar)
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
                            .background(color_letrain)
                    ) {

                        Text(
                            text = "Aceptar",
                            fontWeight = FontWeight.ExtraBold,
                            color = color_letra_topbar,
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
                    .background(color_fondo_topbar)
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
                            .background(color_letrain)
                    ) {

                        Text(
                            text = "Aceptar",
                            fontWeight = FontWeight.ExtraBold,
                            color = color_letra_topbar,
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
                    .background(color_fondo_topbar)
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
                            .background(color_letrain)
                    ) {

                        Text(
                            text = "Aceptar",
                            fontWeight = FontWeight.ExtraBold,
                            color = color_letra_topbar,
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
                    .background(color_fondo_topbar)
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
                            .background(color_letrain)
                    ) {

                        Text(
                            text = "Aceptar",
                            fontWeight = FontWeight.ExtraBold,
                            color = color_letra_topbar,
                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                            fontSize = 15.sp
                        )
                    }
                }
            }
        }
    }
}

// DIALOG PARA LOS TUTORIALES
// DIALOG PARA LOS TUTORIALES
// DIALOG PARA LOS TUTORIALES
// DIALOG PARA LOS TUTORIALES
// DIALOG PARA LOS TUTORIALES


// DIALOG PARA EL TUTORIAL DEL MAINSCREEN
// DIALOG PARA EL TUTORIAL DEL MAINSCREEN
// DIALOG PARA EL TUTORIAL DEL MAINSCREEN

@Composable
fun DialogTutorial(
    dialogo: MutableState<Boolean>,
    sigDialogo: MutableState<Boolean>,
    antDialogo: MutableState<Boolean>,
    index: Int,
    tittle: String,
    subtext: String,
    previous: String,
    next: String
) {
    Dialog(
        onDismissRequest = { /*TODO*/ }
    ) {
        Card(
            shape = RoundedCornerShape(percent = 10),
        ){
            Column(modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .height(450.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween            ) {

                // caja para la simulacion del tutorial
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(top = 20.dp, bottom = 10.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    // simular la cantidad de dialogs para el tutorial
                    items(5){ i ->
                        Box(
                            modifier = Modifier
                                .size(15.dp)
                                .background(
                                    if (i == index) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.primaryContainer,
                                    RoundedCornerShape(percent = 100)
                                )
                        )

                        if(i < 5) Spacer(modifier = Modifier.size(20.dp))
                    }
                }

                // cuerpo del tutorial

                Text(
                    text = tittle,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(vertical = 10.dp, horizontal = 15.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null, // decorative
                    contentScale = ContentScale.Fit,
                    colorFilter  = ColorFilter.tint(
                        color = MaterialTheme.colorScheme.onPrimary
                    ),
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .size(100.dp)
                )

                Text(
                    text = subtext,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(vertical = 10.dp, horizontal = 15.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 15.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                // botones
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.Bottom
                ) {

                    // regresar
                    TextButton(
                        onClick = {
                            if(index == 0) dialogo.value = false
                            else {
                                antDialogo.value = true
                                dialogo.value = false
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .background(color_letrain)
                    ) {
                        Text(
                            text = previous,
                            color = Color.DarkGray,
                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                            fontSize = 15.sp
                        )
                    }

                    // siguiente
                    TextButton(
                        onClick = {
                            if(index == 5) dialogo.value = false
                            else {
                                sigDialogo.value = true
                                dialogo.value = false
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .background(color_letrain)
                    ) {

                        Text(
                            text = next,
                            fontWeight = FontWeight.ExtraBold,
                            color = color_letra_topbar,
                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                            fontSize = 15.sp
                        )
                    }
                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun DialogTutorialMain1(
    dialogo: MutableState<Boolean>,
    sigDialogo: MutableState<Boolean>
) {
    DialogTutorial(
        dialogo = dialogo,
        sigDialogo = sigDialogo,
        antDialogo = mutableStateOf(false),
        index = 0,
        tittle = "Menu de opciones",
        subtext = "El menu de opciones esta posicionado en la parte superior izquierda.",
        previous = "Omitir",
        next = "Siguiente"
    )
}

@Composable
fun DialogTutorialMain2(
    dialogo: MutableState<Boolean>,
    sigDialogo: MutableState<Boolean>,
    antDialogo: MutableState<Boolean>
) {
    DialogTutorial(
        dialogo = dialogo,
        sigDialogo = sigDialogo,
        antDialogo = antDialogo,
        index = 1,
        tittle = "Idioma de la aplicacion",
        subtext = "Puedes cambiar el idioma en el boton de la parte superior derecha, de igual forma en configuraacion.",
        previous = "Regresar",
        next = "Siguiente"
    )
}

@Composable
fun DialogTutorialMain3(
    dialogo: MutableState<Boolean>,
    sigDialogo: MutableState<Boolean>,
    antDialogo: MutableState<Boolean>
) {
    DialogTutorial(
        dialogo = dialogo,
        sigDialogo = sigDialogo,
        antDialogo = antDialogo,
        index = 2,
        tittle = "Tipo de mapa",
        subtext = "Cambia el tipo de mapa en el boton de la parte superior derecha a lado izquierdo de idioma.",
        previous = "Regresar",
        next = "Siguiente"
    )
}

@Composable
fun DialogTutorialMain4(
    dialogo: MutableState<Boolean>,
    sigDialogo: MutableState<Boolean>,
    antDialogo: MutableState<Boolean>
) {
    DialogTutorial(
        dialogo = dialogo,
        sigDialogo = sigDialogo,
        antDialogo = antDialogo,
        index = 3,
        tittle = "Mapa interactivo",
        subtext = "Interactua con el mapa desplazandote por el usando tus dedos.",
        previous = "Regresar",
        next = "Siguiente"
    )
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun DialogTutorialMain5(
    dialogo: MutableState<Boolean>,
    antDialogo: MutableState<Boolean>
) {
    DialogTutorial(
        dialogo = dialogo,
        sigDialogo = mutableStateOf(false),
        antDialogo = antDialogo,
        index = 4,
        tittle = "Marcadores",
        subtext = "Toca los marcadores del mapa para obtener mas informacion sobre el.",
        previous = "Regresar",
        next = "Finalizar"
    )
}


@Composable
fun DialogOcupacionInteres(
    dialogo: MutableState<Boolean>,
    lista: List<Pair<MutableState<Boolean>,String>>,
    myViewModel: MyViewModel?
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
                shape = RoundedCornerShape(percent = 15),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 15.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = color_fondo_topbar
                )
            ) {
                // cuerpo
                Column(
                    modifier = Modifier
                        .padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    lista.forEach(){ opcion ->
                        PushOptions(
                            text = opcion.second,
                            color_letra = color_letra_topbar,
                            variable = opcion.first
                        )
                    }
                }
            }
        }
    )
}


@Composable
fun ChangeCityDialog(
    onClick: () -> Unit
) {
    val options = listOf(
        "Leon",
        "Irapuato",
        "Celaya",
        "Salamanca",
        "Guanajuato",
        "Silao",
        "Acambaro",
        "San francisco del rincon",
        "Moroleon",
        "Salvatierra",
        "Uriangato"
    )

    // Variable
    val selectedOption = remember { mutableStateOf("") }

    fun onOptionSelected(option: String) {
        selectedOption.value = option
    }

    fun isOptionSelected(option: String): Boolean {
        return selectedOption.value == option
    }

    Dialog(
        onDismissRequest = { onClick() }
    ) {
        Card {
            Column {
                options.forEach { option ->
                    Row(
                        modifier = Modifier
                            .clickable {
                                onOptionSelected(option)
                                onClick()
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Spacer(modifier = Modifier.size(16.dp))

                        Text(
                            text = option,
                            style = typography.labelLarge
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        RadioButton(
                            selected = isOptionSelected(option),
                            onClick = {
                                onOptionSelected(option)
                                onClick()
                            }
                        )
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecuDialogEdit(
    onClose: () -> Unit,
    myViewModel: MyViewModel
) {
    var changePhone by remember {
        mutableStateOf(false)
    }

    var sendCodePhone by remember {
        mutableStateOf(false)
    }

    var codeCorrectPhone by remember {
        mutableStateOf(false)
    }

    var changeEmail by remember {
        mutableStateOf(false)
    }

    var sendCodeEmail by remember {
        mutableStateOf(false)
    }

    var codeCorrectEmail by remember {
        mutableStateOf(false)
    }

    var changePass by remember {
        mutableStateOf(false)
    }

    var checkPass by remember {
        mutableStateOf(false)
    }

    Dialog(
        onDismissRequest = { /*TODO*/ },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(

        ) {
            HeaderDialog(
                onClose = { onClose() }
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // telefono actual
                Text(
                    text = "Telefono",
                    style = typography.titleSmall,
                    modifier = Modifier
                        .padding(PaddingValues(16.dp))
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                ) {
                    if(!changePhone){
                        OutlinedTextField(
                            value = "445 141 1834",
                            onValueChange = {},
                            label = {
                                Text(
                                    text = myViewModel.languageType().get(143),
                                    color = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                disabledTextColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                disabledBorderColor = MaterialTheme.colorScheme.outline
                            ),
                            enabled = false,
                            modifier = Modifier
                                .weight(1f)
                        )
                    } else {
                        Column {
                            OutlinedTextField(
                                value = "",
                                onValueChange = {},
                                label = {
                                    Text(
                                        text = myViewModel.languageType().get(138),
                                        color = MaterialTheme.colorScheme.onTertiaryContainer
                                    )
                                },
                                trailingIcon = {
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Icon(
                                            imageVector = Icons.Filled.Clear,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.onTertiaryContainer
                                        )
                                    }
                                },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                    cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                    focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                                    unfocusedBorderColor = MaterialTheme.colorScheme.outline
                                ),
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Next
                                ),
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .padding(vertical = 8.dp)
                            )

                            TextButton(
                                onClick = { sendCodePhone = !sendCodePhone },
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .height(50.dp)
                            ) {
                                Text(
                                    text = myViewModel.languageType().get(115),
                                    color = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }

                            if(sendCodePhone){
                                OutlinedTextField(
                                    value = "",
                                    onValueChange = {},
                                    label = {
                                        Text(
                                            text = myViewModel.languageType().get(110),
                                            color = MaterialTheme.colorScheme.onTertiaryContainer
                                        )
                                    },
                                    trailingIcon = {
                                        IconButton(onClick = { /*TODO*/ }) {
                                            Icon(
                                                imageVector = Icons.Filled.Clear,
                                                contentDescription = null,
                                                tint = MaterialTheme.colorScheme.onTertiaryContainer
                                            )
                                        }
                                    },
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                        cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                        focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                                        unfocusedBorderColor = MaterialTheme.colorScheme.outline
                                    ),
                                    keyboardOptions = KeyboardOptions(
                                        imeAction = ImeAction.Next
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth(1f)
                                        .padding(vertical = 8.dp)
                                )

                                TextButton(
                                    onClick = { codeCorrectPhone = true },
                                    modifier = Modifier
                                        .fillMaxWidth(1f)
                                        .height(50.dp)
                                ) {
                                    Text(
                                        text = myViewModel.languageType().get(150),
                                        color = MaterialTheme.colorScheme.onTertiaryContainer
                                    )
                                }

                                if(codeCorrectPhone){
                                    OutlinedTextField(
                                        value = "",
                                        onValueChange = {},
                                        label = {
                                            Text(
                                                text = myViewModel.languageType().get(138),
                                                color = MaterialTheme.colorScheme.onTertiaryContainer
                                            )
                                        },
                                        trailingIcon = {
                                            IconButton(onClick = { /*TODO*/ }) {
                                                Icon(
                                                    imageVector = Icons.Filled.Clear,
                                                    contentDescription = null,
                                                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                                                )
                                            }
                                        },
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                            cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                                        ),
                                        keyboardOptions = KeyboardOptions(
                                            imeAction = ImeAction.Next
                                        ),
                                        modifier = Modifier
                                            .fillMaxWidth(1f)
                                            .padding(vertical = 8.dp)
                                    )

                                    OutlinedTextField(
                                        value = "",
                                        onValueChange = {},
                                        label = {
                                            Text(
                                                text = myViewModel.languageType().get(100),
                                                color = MaterialTheme.colorScheme.onTertiaryContainer
                                            )
                                        },
                                        trailingIcon = {
                                            IconButton(onClick = { /*TODO*/ }) {
                                                Icon(
                                                    imageVector = Icons.Filled.Clear,
                                                    contentDescription = null,
                                                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                                                )
                                            }
                                        },
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                            cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                                        ),
                                        keyboardOptions = KeyboardOptions(
                                            imeAction = ImeAction.Done
                                        ),
                                        modifier = Modifier
                                            .fillMaxWidth(1f)
                                            .padding(vertical = 8.dp)
                                    )

                                    Button(
                                        onClick = {

                                        },
                                        shape = RoundedCornerShape(16),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = MaterialTheme.colorScheme.primary,
                                            contentColor = MaterialTheme.colorScheme.onPrimary
                                        ),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 16.dp)
                                            .height(50.dp)
                                    ) {
                                        Text(
                                            text = myViewModel.languageType().get(92),
                                            style = typography.bodyLarge
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // cancelar cambio de telefono
                Button(
                    onClick = {
                        changePhone = !changePhone
                    },
                    shape = RoundedCornerShape(16),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.onTertiary
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(vertical = 16.dp)
                        .height(50.dp)
                ) {
                    Text(
                        text = if(!changePhone) myViewModel.languageType().get(92) else myViewModel.languageType().get(94),
                        style = typography.bodyLarge
                    )
                }

                Divider(modifier = Modifier.padding(bottom = 16.dp))

                // correo actual
                Text(
                    text = "Correo",
                    style = typography.titleSmall,
                    modifier = Modifier
                        .padding(PaddingValues(16.dp))
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.9f),
                    horizontalArrangement = Arrangement.Center
                ) {
                    if(!changeEmail){
                        OutlinedTextField(
                            value = "s20120154@alumnos.itsur.edu.mx",
                            onValueChange = {},
                            label = {
                                Text(
                                    text = myViewModel.languageType().get(106),
                                    color = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                disabledTextColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                disabledBorderColor = MaterialTheme.colorScheme.outline
                            ),
                            enabled = false,
                            modifier = Modifier
                                .weight(1f)
                        )
                    } else {
                        Column {
                            OutlinedTextField(
                                value = "",
                                onValueChange = {},
                                label = {
                                    Text(
                                        text = myViewModel.languageType().get(137),
                                        color = MaterialTheme.colorScheme.onTertiaryContainer
                                    )
                                },
                                trailingIcon = {
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Icon(
                                            imageVector = Icons.Filled.Clear,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.onTertiaryContainer
                                        )
                                    }
                                },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                    cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                    focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                                    unfocusedBorderColor = MaterialTheme.colorScheme.outline
                                ),
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Next
                                ),
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .padding(vertical = 8.dp)
                            )

                            TextButton(
                                onClick = { sendCodeEmail = !sendCodeEmail },
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .height(50.dp)
                            ) {
                                Text(
                                    text = myViewModel.languageType().get(115),
                                    color = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }

                            if(sendCodeEmail){
                                OutlinedTextField(
                                    value = "",
                                    onValueChange = {},
                                    label = {
                                        Text(
                                            text = myViewModel.languageType().get(110),
                                            color = MaterialTheme.colorScheme.onTertiaryContainer
                                        )
                                    },
                                    trailingIcon = {
                                        IconButton(onClick = { /*TODO*/ }) {
                                            Icon(
                                                imageVector = Icons.Filled.Clear,
                                                contentDescription = null,
                                                tint = MaterialTheme.colorScheme.onTertiaryContainer
                                            )
                                        }
                                    },
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                        cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                        focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                                        unfocusedBorderColor = MaterialTheme.colorScheme.outline
                                    ),
                                    keyboardOptions = KeyboardOptions(
                                        imeAction = ImeAction.Next
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth(1f)
                                        .padding(vertical = 8.dp)
                                )

                                TextButton(
                                    onClick = { codeCorrectEmail = true },
                                    modifier = Modifier
                                        .fillMaxWidth(1f)
                                        .height(50.dp)
                                ) {
                                    Text(
                                        text = myViewModel.languageType().get(150),
                                        color = MaterialTheme.colorScheme.onTertiaryContainer
                                    )
                                }

                                if(codeCorrectEmail){
                                    OutlinedTextField(
                                        value = "",
                                        onValueChange = {},
                                        label = {
                                            Text(
                                                text = myViewModel.languageType().get(137),
                                                color = MaterialTheme.colorScheme.onTertiaryContainer
                                            )
                                        },
                                        trailingIcon = {
                                            IconButton(onClick = { /*TODO*/ }) {
                                                Icon(
                                                    imageVector = Icons.Filled.Clear,
                                                    contentDescription = null,
                                                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                                                )
                                            }
                                        },
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                            cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                                        ),
                                        keyboardOptions = KeyboardOptions(
                                            imeAction = ImeAction.Next
                                        ),
                                        modifier = Modifier
                                            .fillMaxWidth(1f)
                                            .padding(vertical = 8.dp)
                                    )

                                    OutlinedTextField(
                                        value = "",
                                        onValueChange = {},
                                        label = {
                                            Text(
                                                text = myViewModel.languageType().get(99),
                                                color = MaterialTheme.colorScheme.onTertiaryContainer
                                            )
                                        },
                                        trailingIcon = {
                                            IconButton(onClick = { /*TODO*/ }) {
                                                Icon(
                                                    imageVector = Icons.Filled.Clear,
                                                    contentDescription = null,
                                                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                                                )
                                            }
                                        },
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                            cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                                        ),
                                        keyboardOptions = KeyboardOptions(
                                            imeAction = ImeAction.Done
                                        ),
                                        modifier = Modifier
                                            .fillMaxWidth(1f)
                                            .padding(vertical = 8.dp)
                                    )

                                    Button(
                                        onClick = {

                                        },
                                        shape = RoundedCornerShape(16),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = MaterialTheme.colorScheme.primary,
                                            contentColor = MaterialTheme.colorScheme.onPrimary
                                        ),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 16.dp)
                                            .height(50.dp)
                                    ) {
                                        Text(
                                            text = myViewModel.languageType().get(91),
                                            style = typography.bodyLarge
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // cambiar correo
                Button(
                    onClick = {
                        changeEmail = !changeEmail
                    },
                    shape = RoundedCornerShape(16),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.onTertiary
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(vertical = 16.dp)
                        .height(50.dp)
                ) {
                    Text(
                        text = if(!changeEmail) myViewModel.languageType().get(91) else myViewModel.languageType().get(94),
                        style = typography.bodyLarge
                    )
                }

                Divider(modifier = Modifier.padding(bottom = 16.dp))



                // cambiar la contrasenia

                Text(
                    text = "Contraseña",
                    style = typography.titleSmall,
                    modifier = Modifier
                        .padding(PaddingValues(16.dp))
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.9f),
                    horizontalArrangement = Arrangement.Center
                ) {
                    if(changePass){
                        Column {
                            OutlinedTextField(
                                value = "",
                                onValueChange = {},
                                label = {
                                    Text(
                                        text = myViewModel.languageType().get(101),
                                        color = MaterialTheme.colorScheme.onTertiaryContainer
                                    )
                                },
                                trailingIcon = {
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Icon(
                                            imageVector = Icons.Filled.Clear,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.onTertiaryContainer
                                        )
                                    }
                                },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                    cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                    focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                                    unfocusedBorderColor = MaterialTheme.colorScheme.outline
                                ),
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Next
                                ),
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .padding(vertical = 8.dp)
                            )

                            TextButton(
                                onClick = { checkPass = !checkPass },
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .height(50.dp)
                            ) {
                                Text(
                                    text = myViewModel.languageType().get(149),
                                    color = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }

                            if(checkPass){
                                OutlinedTextField(
                                    value = "",
                                    onValueChange = {},
                                    label = {
                                        Text(
                                            text = myViewModel.languageType().get(101),
                                            color = MaterialTheme.colorScheme.onTertiaryContainer
                                        )
                                    },
                                    trailingIcon = {
                                        IconButton(onClick = { /*TODO*/ }) {
                                            Icon(
                                                imageVector = Icons.Filled.Clear,
                                                contentDescription = null,
                                                tint = MaterialTheme.colorScheme.onTertiaryContainer
                                            )
                                        }
                                    },
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                        cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                        focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                                        unfocusedBorderColor = MaterialTheme.colorScheme.outline
                                    ),
                                    keyboardOptions = KeyboardOptions(
                                        imeAction = ImeAction.Next
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth(1f)
                                        .padding(vertical = 8.dp)
                                )

                                OutlinedTextField(
                                    value = "",
                                    onValueChange = {},
                                    label = {
                                        Text(
                                            text = myViewModel.languageType().get(98),
                                            color = MaterialTheme.colorScheme.onTertiaryContainer
                                        )
                                    },
                                    trailingIcon = {
                                        IconButton(onClick = { /*TODO*/ }) {
                                            Icon(
                                                imageVector = Icons.Filled.Clear,
                                                contentDescription = null,
                                                tint = MaterialTheme.colorScheme.onTertiaryContainer
                                            )
                                        }
                                    },
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                        cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                        focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                                        unfocusedBorderColor = MaterialTheme.colorScheme.outline
                                    ),
                                    keyboardOptions = KeyboardOptions(
                                        imeAction = ImeAction.Next
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth(1f)
                                        .padding(vertical = 8.dp)
                                )

                                Button(
                                    onClick = {

                                    },
                                    shape = RoundedCornerShape(16),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.primary,
                                        contentColor = MaterialTheme.colorScheme.onPrimary
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 16.dp)
                                        .height(50.dp)
                                ) {
                                    Text(
                                        text = myViewModel.languageType().get(90),
                                        style = typography.bodyLarge
                                    )
                                }

                            }
                        }
                    }
                }

                // cambiar contrasenia
                Button(
                    onClick = {
                        changePass = !changePass
                    },
                    shape = RoundedCornerShape(16),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.onTertiary
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(top = 16.dp)
                        .height(50.dp)
                ) {
                    Text(
                        text = if(changePass) myViewModel.languageType().get(94) else myViewModel.languageType().get(90),
                        style = typography.bodyLarge
                    )
                }

                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MembDialogEdit(
    onClose: () -> Unit,
    myViewModel: MyViewModel
) {
    Dialog(
        onDismissRequest = { /*TODO*/ },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(

        ) {
            HeaderDialog(
                onClose = { onClose() }
            )

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // id de membresia
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        value = "1234",
                        onValueChange = {},
                        label = {
                            Text(
                                text = myViewModel.languageType().get(124),
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            disabledTextColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            disabledBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        enabled = false,
                        modifier = Modifier
                            .weight(1f)
                    )

                }

                // estatus de la membresia
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = myViewModel.languageType().get(117),
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    AssistChip(
                        onClick = { },
                        label = {
                            Text(
                                text = myViewModel.languageType().get(87),
                                color = MaterialTheme.colorScheme.onTertiary
                            )
                        },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = MaterialTheme.colorScheme.tertiary,
                        ),
                        border = AssistChipDefaults.assistChipBorder(
                            borderColor = MaterialTheme.colorScheme.tertiary
                        )
                    )
                }

                // usuario
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        value = "CristianToZa",
                        onValueChange = {},
                        label = {
                            Text(
                                text = myViewModel.languageType().get(147),
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            disabledTextColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            disabledBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        enabled = false,
                        modifier = Modifier
                            .weight(1f)
                    )

                }

                // inicio
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        value = "Vie 29 Sep 2023",
                        onValueChange = {},
                        label = {
                            Text(
                                text = myViewModel.languageType().get(126),
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            disabledTextColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            disabledBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        enabled = false,
                        modifier = Modifier
                            .weight(1f)
                    )

                }

                // expiracion
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        value = "Dom 29 Oct 2023",
                        onValueChange = {},
                        label = {
                            Text(
                                text = myViewModel.languageType().get(120),
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            disabledTextColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            disabledBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        enabled = false,
                        modifier = Modifier
                            .weight(1f)
                    )

                }

                // tipo membresia
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        value = myViewModel.languageType().get(130),
                        onValueChange = {},
                        label = {
                            Text(
                                text = myViewModel.languageType().get(144),
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            disabledTextColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            disabledBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        enabled = false,
                        modifier = Modifier
                            .weight(1f)
                    )

                }

                // moneda
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        value = myViewModel.languageType().get(133),
                        onValueChange = {},
                        label = {
                            Text(
                                text = myViewModel.languageType().get(132),
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            disabledTextColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            disabledBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        enabled = false,
                        modifier = Modifier
                            .weight(1f)
                    )

                }

                // mejorar suscripcion
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ){
                    TextButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                    ) {
                        Text(
                            text = myViewModel.languageType().get(128),
                            color = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    }
                }

                // cancelar suscripcion
                Button(
                    onClick = {
                        onClose()
                    },
                    shape = RoundedCornerShape(16),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.onTertiary
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(top = 16.dp)
                        .height(50.dp)
                ) {
                    Text(
                        text = myViewModel.languageType().get(93),
                        style = typography.bodyLarge
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDialogEdit(
    onClose: () -> Unit,
    myViewModel: MyViewModel
) {
    Dialog(
        onDismissRequest = { /*TODO*/ },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(

        ) {
            HeaderDialog(
                onClose = { onClose() }
            )

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // nombre y apellidos
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(
                                text = myViewModel.languageType().get(136),
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier
                            .weight(1f)
                    )

                    Spacer(modifier = Modifier.size(16.dp))

                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(
                                text = myViewModel.languageType().get(88),
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier
                            .weight(1f)
                    )
                }

                // correo electronico
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(
                                text = myViewModel.languageType().get(105),
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier
                            .weight(1f)
                    )
                }

                // pais y etado
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(
                                text = myViewModel.languageType().get(140),
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier
                            .weight(1f)
                    )

                    Spacer(modifier = Modifier.size(16.dp))

                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(
                                text = myViewModel.languageType().get(116),
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier
                            .weight(1f)
                    )
                }

                // direccion y num interior
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(
                                text = myViewModel.languageType().get(111),
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier
                            .weight(2f)
                    )

                    Spacer(modifier = Modifier.size(16.dp))

                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(
                                text = "# Int.",
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier
                            .weight(1f)
                    )
                }

                // colonia y codigo postal
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(
                                text = myViewModel.languageType().get(97),
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier
                            .weight(1f)
                    )

                    Spacer(modifier = Modifier.size(16.dp))

                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(
                                text = myViewModel.languageType().get(108),
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier
                            .weight(1f)
                    )
                }

                // cumpleaños y genero
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(
                                text = myViewModel.languageType().get(109),
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier
                            .weight(1f)
                    )

                    Spacer(modifier = Modifier.size(16.dp))

                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(
                                text = myViewModel.languageType().get(123),
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(

                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier
                            .weight(1f)
                    )
                }

                // ocupacion
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(
                                text = myViewModel.languageType().get(139),
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        ),
                        modifier = Modifier
                            .weight(1f)
                    )
                }

                // guardar cambios
                Button(
                    onClick = {
                        onClose()
                    },
                    shape = RoundedCornerShape(16),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.onTertiary
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(top = 16.dp)
                        .height(50.dp)
                ) {
                    Text(
                        text = myViewModel.languageType().get(122),
                        style = typography.bodyLarge
                    )
                }
            }
        }
    }
}

@Composable
fun HeaderDialog(
    onClose: () -> Unit
) {
    Row {
        IconButton(
            onClick = { onClose() },
            modifier = Modifier
                .size(64.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Close,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp),
                //tint =
            )
        }
    }
}

@Composable
fun DialogPush(onDismiss: () -> Unit) {
    var siempre by remember {
        mutableStateOf(false)
    }

    var nunca by remember {
        mutableStateOf(false)
    }

    Dialog(
        onDismissRequest = { onDismiss() }
    ) {
        Card {
            Column(
                modifier = Modifier
                    .padding(PaddingValues(16.dp)),
            ) {
                // siempre
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Siempre"
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    RadioButton(
                        selected = siempre,
                        onClick = {
                            siempre = !siempre
                            nunca = false
                        }
                    )
                }

                // maybe
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Siempre"
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    RadioButton(
                        selected = siempre,
                        onClick = {
                            siempre = !siempre
                            nunca = false
                        }
                    )
                }

                // nunca
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Nunca"
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    RadioButton(
                        selected = nunca,
                        onClick = {
                            nunca = !nunca
                            siempre = false
                        }
                    )
                }
            }
        }
    }
}