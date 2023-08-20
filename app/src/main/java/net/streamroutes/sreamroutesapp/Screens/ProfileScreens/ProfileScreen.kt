package net.streamroutes.sreamroutesapp.Screens.ProfileScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.Colores.color_fondo
import net.streamroutes.sreamroutesapp.Colores.color_fondo_claro
import net.streamroutes.sreamroutesapp.Colores.color_fondo_oscuro
import net.streamroutes.sreamroutesapp.Colores.color_fondo_textfield
import net.streamroutes.sreamroutesapp.Colores.color_fondo_topbar
import net.streamroutes.sreamroutesapp.Colores.color_letra
import net.streamroutes.sreamroutesapp.Colores.color_letra_topbar
import net.streamroutes.sreamroutesapp.Colores.color_letraout
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController){
    val info = listOf(
        "Cumpleaños" to "Mie, 27 Nov 2002",
        "Teléfono" to "445 141 1834",
        "País" to "México",
        "Ciudad" to "Moroleon, Gto",
        "Usuario" to "#12345678"
    )

    val memb = listOf(
        "Tipo" to "chingon",
        "Duracion" to "Mes/Anual",
        "Corte" to "Mar, 29 Ago 2023",
        "Prueba" to "Si/No"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color_fondo),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent)
                        .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)),
                    colorFilter = ColorFilter.tint(color_fondo_topbar)
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp), // Agrega el relleno que desees
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 25.dp),
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
                                tint = color_letra_topbar,
                                modifier = Modifier
                                    .size(50.dp)
                            )
                        }

                        // user name
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                        ) {
                            CustomText(
                                firstString = "Bienvenido de vuelta",
                                horizontal = Arrangement.Start,
                                color = color_letra_topbar,
                                fontWeight = FontWeight.Normal,
                                size = 14,
                                modifier = Modifier.fillMaxWidth(0.95f)
                            )

                            CustomText(
                                firstString = "Cristian Alexis Torres Zavavla",
                                horizontal = Arrangement.Start,
                                color = color_letra_topbar,
                                size = 20,
                                modifier = Modifier.fillMaxWidth(0.95f)
                            )

                            CustomText(
                                firstString = "s20120154@alumnos.itsur.edu.mx",
                                horizontal = Arrangement.Start,
                                color = color_letra_topbar,
                                fontWeight = FontWeight.Normal,
                                size = 15,
                                modifier = Modifier.fillMaxWidth(0.95f)
                            )
                        }
                    }
                }
            }


            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                UserProfileInfoSection(
                    infoItems = info,
                    icon = Icons.Filled.Info,
                    text = "Informacion",
                    color = color_letraout,
                    onClick = {
                        navController.navigate(AppScreens.ProfileConfigureScreen.route)
                    }
                )
                UserProfileInfoSection(
                    infoItems = memb,
                    icon = Icons.Filled.Star,
                    text = "Membresia",
                    color = color_letraout,
                    onClick = {

                    }
                )
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
                .wrapContentWidth()
        )
    }
}

@Composable
private fun HeaderText(
    icon: ImageVector,
    text: String,
    color: Color
) {
    Row(
        modifier = Modifier
            .padding(start = 25.dp, top = 10.dp)
            .fillMaxWidth(0.8f),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = color,
            modifier = Modifier
                .size(30.dp),
        )

        Spacer(modifier = Modifier.width(50.dp))

        CustomText(
            firstString = text,
            horizontal = Arrangement.Start,
            color = color,
            size = 20
        )
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
            color = color_letraout,
            size = 17
        )

        CustomText(
            firstString = second,
            horizontal = Arrangement.Start,
            color = color_letraout,
            size = 17,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
fun UserProfileInfoSection(
    infoItems: List<Pair<String, String>>,
    icon: ImageVector,
    text: String,
    color: Color,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(top = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(modifier = Modifier
            .height(1.dp)
            .fillMaxWidth(0.9f)
            .background(color_letra))

        HeaderText(
            icon = icon,
            text = text,
            color = color.copy(0.5f)
        )

        Spacer(modifier = Modifier.height(5.dp))

        LazyColumn {
            items(infoItems.size) { index ->
                val info = infoItems[index]
                Spacer(modifier = Modifier.height(5.dp))
                SuscripcionOptions(
                    first = info.first,
                    second = info.second
                )
            }
        }
        
        Spacer(modifier = Modifier.size(10.dp))
        Box(
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth(0.5f)
                .background(color_fondo_textfield, RoundedCornerShape(100))
                .clickable(onClick = onClick),
            contentAlignment = Alignment.Center
        ){
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = null,
                modifier = Modifier.size(25.dp),
                tint = color
            )
        }
    }
}