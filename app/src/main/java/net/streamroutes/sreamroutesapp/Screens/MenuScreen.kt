package net.streamroutes.sreamroutesapp.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.R

@Composable
fun MenuScreen(navController: NavController){
    Menu(navController)
}

/*
variable para las coordenadas del mapa principal

0. Paradas
1. Terminales
2. Hospitales
3. Resturantes
4. Entretenimiento
*/

// te retorna el tipo de marcador
fun generaTipo(Tipo: Int): String{
    val tipos = mutableListOf<String>("Paradas","Terminales","Hospitales","Resturantes","Entretenimiento")
    return tipos[Tipo]
}

// esto es para generar las coordenadas de todos los marcadores
data class Coordenadas(val latitud: Double, val longitud: Double, val tipo: Int)
val listaCoordenadas = mutableListOf<Coordenadas>()


val color_texto = Color(0xFFFFF7E7)
val color_fondo = Color(0xFF153040)
val color_rows = Color(0xfffffff)


@Composable
fun Menu(navController: NavController){
    Column(
        modifier = Modifier
            .background(color_fondo)
            .fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Text(text = "Menu",
                    modifier = Modifier
                        .fillMaxWidth()
                )
            },
            navigationIcon = {
                IconButton(onClick = {  }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Te enviara al menu de opciones"
                    )
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {

            // usuario
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "")
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                ) {
                    Text(
                        text = "Nombre Usuario", // texto
                        modifier = Modifier
                            .fillMaxWidth(), // esto acapara el tamaño completo del Row=0.8f
                        color = color_texto,
                        fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                    Text(
                        text = "Tipo Membresia", // texto
                        modifier = Modifier
                            .fillMaxWidth(), // esto acapara el tamaño completo del Row=0.8f
                        color = color_texto,
                        fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "Ciudad", // texto
                        modifier = Modifier
                            .fillMaxWidth(), // esto acapara el tamaño completo del Row=0.8f
                        color = color_texto,
                        fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif,
                        fontSize = 18.sp
                    )
                }
            }

            Spacer(modifier = Modifier.size(30.dp))

            // version premium
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(
                        color_rows,
                        RoundedCornerShape(
                            topEnd = 15.dp,
                            bottomStart = 15.dp,
                            topStart = 15.dp,
                            bottomEnd = 15.dp
                        )
                    )
                    .clickable {
                        /* Agregar la ruta del boton */
                    }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "ads",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 5.dp)
                        .size(45.dp)
                )
                Spacer(modifier = Modifier.size(width = 20.dp, height = 0.dp))
                Text(
                    text = "Version Premium", // texto
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    color = color_texto,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif,
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.size(30.dp))

            // rutas
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(
                        color_rows,
                        RoundedCornerShape(
                            topEnd = 15.dp,
                            bottomStart = 0.dp,
                            topStart = 15.dp,
                            bottomEnd = 0.dp
                        )
                    )
                    .clickable {
                        /* Agregar la ruta del boton */
                    }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "rutas",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 5.dp)
                        .size(45.dp)
                )
                Spacer(modifier = Modifier.size(width = 20.dp, height = 0.dp))
                Text(
                    text = "Rutas", // texto
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    color = color_texto,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif,
                    fontSize = 20.sp
                )
            }

            // planifica tu viaje
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(color_rows)
                    .clickable {
                        /* Agregar la ruta del boton */
                    }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "planifica tu viaje",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 5.dp)
                        .size(45.dp)
                )
                Spacer(modifier = Modifier.size(width = 20.dp, height = 0.dp))
                Text(
                    text = "Planifica tu viaje", // texto
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    color = color_texto,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif,
                    fontSize = 20.sp
                )
            }

            // compartir ubicacion
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(
                        color_rows,
                        RoundedCornerShape(
                            topEnd = 0.dp,
                            bottomStart = 15.dp,
                            topStart = 0.dp,
                            bottomEnd = 15.dp
                        )
                    )
                    .clickable {
                        /* Agregar la ruta del boton */
                    }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "ubicacion",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 5.dp)
                        .size(45.dp)
                )
                Spacer(modifier = Modifier.size(width = 20.dp, height = 0.dp))
                Text(
                    text = "Compartir Ubicacion", // texto
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    color = color_texto,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif,
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.size(30.dp))

            // comparte
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(
                        color_rows,
                        RoundedCornerShape(
                            topEnd = 15.dp,
                            bottomStart = 0.dp,
                            topStart = 15.dp,
                            bottomEnd = 0.dp
                        )
                    )
                    .clickable {
                        /* Agregar la ruta del boton */
                    }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "comparte",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 5.dp)
                        .size(45.dp)
                )
                Spacer(modifier = Modifier.size(width = 20.dp, height = 0.dp))
                Text(
                    text = "Comparte", // texto
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    color = color_texto,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif,
                    fontSize = 20.sp
                )
            }

            // valoranos
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(color_rows)
                    .clickable {
                        /* Agregar la ruta del boton */
                    }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "valoranos",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 5.dp)
                        .size(45.dp)
                )
                Spacer(modifier = Modifier.size(width = 20.dp, height = 0.dp))
                Text(
                    text = "Valoranos", // texto
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    color = color_texto,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif,
                    fontSize = 20.sp
                )
            }

            // configuracion
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(color_rows)
                    .clickable {
                        /* Agregar la ruta del boton */
                    }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "configuracion",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 5.dp)
                        .size(45.dp)
                )
                Spacer(modifier = Modifier.size(width = 20.dp, height = 0.dp))
                Text(
                    text = "Configuracion", // texto
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    color = color_texto,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif,
                    fontSize = 20.sp
                )
            }

            // ayuda y soporte
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(
                        color_rows,
                        RoundedCornerShape(
                            topEnd = 0.dp,
                            bottomStart = 15.dp,
                            topStart = 0.dp,
                            bottomEnd = 15.dp
                        )
                    )
                    .clickable {
                        /* Agregar la ruta del boton */
                    }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "ayuda",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 5.dp)
                        .size(45.dp)
                )
                Spacer(modifier = Modifier.size(width = 20.dp, height = 0.dp))
                Text(
                    text = "Ayuda y Soporte", // texto
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    color = color_texto,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif,
                    fontSize = 20.sp
                )
            }
        }
    }
}