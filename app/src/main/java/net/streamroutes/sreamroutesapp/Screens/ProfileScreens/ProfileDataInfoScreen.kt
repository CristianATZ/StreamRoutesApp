package net.streamroutes.sreamroutesapp.Screens.ProfileScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.smallTopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.Colores.color_fondo_claro
import net.streamroutes.sreamroutesapp.Colores.color_fondo_textfield
import net.streamroutes.sreamroutesapp.Colores.color_fondo_topappbar_alterno
import net.streamroutes.sreamroutesapp.Colores.color_letra
import net.streamroutes.sreamroutesapp.Colores.color_letra_topappbar
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileDataInfoScreen(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Datos personales",
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            // navController.navigate(AppScreens.MainScreen.route)
                        }
                    ) {
                        androidx.compose.material.Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Te enviara a la ventana principal",
                            tint = Color.White
                        )
                    }
                },
                colors = smallTopAppBarColors(
                    containerColor = color_fondo_topappbar_alterno,
                    titleContentColor = color_letra_topappbar
                )
            )
        },
        containerColor = color_fondo_claro
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            OptionsWithSubOptions(text = "Informacion de contacto", sub_text = "Telefono, correo, etc.") {
                Row() {
                    Spacer(modifier = Modifier.size(20.dp))
                    GeneralOptions(
                        text = "Cambiar telefono",
                        sub_text = "445 141 1834",
                        onClick = { /*TODO*/ }
                    )
                }
                Row() {
                    Spacer(modifier = Modifier.size(20.dp))
                    GeneralOptions(
                        text = "Cambiar correo",
                        sub_text = "s20120154@alumnos.itsur.edu.mx",
                        onClick = { /*TODO*/ }
                    )
                }
            }

            OptionsWithSubOptions(text = "Informacion personal", sub_text = "Fecha nacimiento, ocupacion, intereses, etc.") {
                Row() {
                    Spacer(modifier = Modifier.size(20.dp))
                    GeneralOptions(
                        text = "Fecha nacimiento",
                        sub_text = "Mie, 27 Nov 2002",
                        onClick = { /*TODO*/ }
                    )
                }
                Row() {
                    Spacer(modifier = Modifier.size(20.dp))
                    GeneralOptions(
                        text = "Ocupacion",
                        sub_text = "Estudiante",
                        onClick = { /*TODO*/ }
                    )
                }
                Row() {
                    Spacer(modifier = Modifier.size(20.dp))
                    GeneralOptions(
                        text = "Intereses",
                        sub_text = "Lista de intereses",
                        onClick = { /*TODO*/ }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ProfileDataInfoView(){

}

@Composable
private fun GeneralOptions(
    text: String,
    sub_text: String,
    onClick: () -> Unit,
    text_color: Color = color_letra
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(70.dp)
            .clickable(onClick = onClick),
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
                color = text_color,
                fontFamily = FontFamily.SansSerif,
                fontSize = 20.sp
            )
            Text(
                text = sub_text, // texto
                color = text_color,
                fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
private fun Options(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(70.dp)
            .clickable(onClick = onClick)
            .background(
                if (isSelected) color_fondo_textfield else Color.Transparent
            ),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.size(width = 20.dp, height = 0.dp))
        Text(
            text = text, // texto
            modifier = Modifier
                .align(Alignment.CenterVertically),
            color = color_letra,
            fontFamily = FontFamily.SansSerif,
            fontSize = 20.sp
        )
    }
}


@Composable
private fun OptionsWithSubOptions(
    text: String,
    sub_text: String = "",
    subOptionsComposable: @Composable () -> Unit
) {
    var subOptionsVisibleState by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Options(text = text, isSelected = subOptionsVisibleState) {
            subOptionsVisibleState = !subOptionsVisibleState
        }

        if (subOptionsVisibleState) {
            subOptionsComposable()
        }
    }
}