package net.streamroutes.sreamroutesapp.Screens.ProfileScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.Colores.color_fondo_claro
import net.streamroutes.sreamroutesapp.Colores.color_fondo_oscuro
import net.streamroutes.sreamroutesapp.Colores.color_fondo_textfield
import net.streamroutes.sreamroutesapp.Colores.color_fondo_topappbar_alterno
import net.streamroutes.sreamroutesapp.Colores.color_letra
import net.streamroutes.sreamroutesapp.Colores.color_letra_topappbar
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color_fondo_claro),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .align(Alignment.TopCenter)
                    .background(
                        color_fondo_oscuro,
                        //color_fondo_switch_activo.copy(0.5f),
                        RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)
                    )
            ) {
                Row(
                    modifier = Modifier
                        .padding(top = 25.dp, start = 25.dp, end = 25.dp)
                        .fillMaxWidth(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    // regresar
                    IconButton(
                        onClick = {
                            navController.navigate(AppScreens.MainScreen.route)
                        }
                    ) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Regresara a la ventana de perfil",
                            tint = color_fondo_claro
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // image user
                        Image(
                            painter = painterResource(id = R.drawable.circulo_de_usuario),
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp)
                                .padding(end = 10.dp),
                            colorFilter = ColorFilter.tint(color_fondo_claro)
                        )

                        // user name
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(0.7f)
                        ) {
                            CustomText(
                                firstString = "Bienvenido de vuelta",
                                horizontal = Arrangement.Start,
                                color = color_fondo_claro,
                                fontWeight = FontWeight.Normal,
                                size = 12,
                                modifier = Modifier.fillMaxWidth(0.95f)
                            )

                            CustomText(
                                firstString = "Cristian Alexis Torres Zavavla",
                                horizontal = Arrangement.Start,
                                color = color_fondo_claro,
                                size = 17,
                                modifier = Modifier.fillMaxWidth(0.95f)
                            )
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(top = 125.dp)
                        .verticalScroll(rememberScrollState())
                        .background(color_fondo_textfield, RoundedCornerShape(percent = 10)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    InfoOptions(
                        icon = Icons.Filled.Star,
                        first = "Cump",
                        info = "Mier, 27 Nov 2002",
                        color = color_letra
                    )

                    InfoOptions(
                        icon = Icons.Filled.Star,
                        first = "Telefono",
                        info = "445 141 18 34",
                        color = color_letra
                    )

                    InfoOptions(
                        icon = Icons.Filled.Star,
                        first = "Correo",
                        info = "s20120154@alumnos.itsur.edu.mx",
                        color = color_letra
                    )

                    InfoOptions(
                        icon = Icons.Filled.Star,
                        first = "Ciudad",
                        info = "Moroleon, Gto",
                        color = color_letra
                    )

                    // button login
                    Button(
                        onClick = {
                            navController.navigate(route = AppScreens.ProfileDataInfoScreen.route)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF192833), // Cambiamos el color de fondo del botón aquí
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(15.dp),
                        modifier = Modifier
                            .width(300.dp)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Cambiar",
                            fontSize = 26.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }

                }


                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.95f)
                        .verticalScroll(rememberScrollState())
                        .background(color_fondo_textfield, RoundedCornerShape(percent = 10)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Spacer(modifier = Modifier.size(15.dp))

                    CustomText(
                        firstString = "Membresia",
                        horizontal = Arrangement.Center,
                        color = color_letra,
                        size = 20
                    )

                    Spacer(modifier = Modifier.size(15.dp))

                    SuscripcionOptions(
                        first = "Fecha de corte",
                        second = "Mart, 29 Ago 2023"
                    )

                    SuscripcionOptions(
                        first = "Tipo",
                        second = "chingon"
                    )

                    SuscripcionOptions(
                        first = "Duracion",
                        second = "Mes/Anual"
                    )

                    SuscripcionOptions(
                        first = "Periodo de prueba",
                        second = "Si/No"
                    )

                    Button(
                        onClick = {
                            //navController.navigate(route = AppScreens.MainScreen.route)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF192833), // Cambiamos el color de fondo del botón aquí
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(15.dp),
                        modifier = Modifier
                            .width(300.dp)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Gestionar",
                            fontSize = 26.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }

                }

                Spacer(modifier = Modifier.size(10.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileView(){

}

@Composable
private fun CustomText(
    firstString: String,
    horizontal: Arrangement.Horizontal,
    color: Color,
    fontWeight: FontWeight = FontWeight.Bold,
    size: Int,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = horizontal
    ){
        // forgot
        Text(
            text = buildAnnotatedString{
                withStyle(style = SpanStyle(color = color,
                    fontWeight = fontWeight,
                    fontSize = size.sp,
                    fontFamily = FontFamily.SansSerif)
                ) {
                    append(firstString)
                }
            },
            modifier = Modifier
                .wrapContentWidth(),
            textAlign = TextAlign.Center
        )
    }
}


@Composable
private fun InfoOptions(
    icon: ImageVector,
    first: String,
    info: String,
    color: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp, start = 25.dp, end = 25.dp, bottom = 25.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row() {
            Icon(
                imageVector = icon,
                contentDescription = "",
                modifier = Modifier
                    .size(25.dp),
                tint = color
            )
            CustomText(
                firstString = first,
                horizontal = Arrangement.Start,
                color = color,
                size = 17,
                modifier = Modifier.padding(start = 10.dp, end = 40.dp)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 10.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            CustomText(
                firstString = info,
                horizontal = Arrangement.Start,
                color = color,
                size = 17,
                fontWeight = FontWeight.Normal
            )
        }
    }
}


@Composable
private fun SuscripcionOptions(
    first: String,
    second: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(top = 7.5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CustomText(
            firstString = first,
            horizontal = Arrangement.Start,
            color = color_letra,
            size = 17
        )

        CustomText(
            firstString = second,
            horizontal = Arrangement.Start,
            color = color_letra,
            size = 17,
            fontWeight = FontWeight.Normal
        )
    }
}