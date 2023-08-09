package net.streamroutes.sreamroutesapp.Screens.MenuScreens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.Colores.color_fondo_claro
import net.streamroutes.sreamroutesapp.Colores.color_fondo_topappbar_alterno
import net.streamroutes.sreamroutesapp.Colores.color_letra_topappbar
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R

@Composable
fun ValoranoScreen(myViewModel: MyViewModel,navController: NavController){
    TopBar(myViewModel,navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(myViewModel: MyViewModel, navController: NavController) {
    //Fondo
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color_fondo_claro)
    ){
        TopAppBar(
            title = {
                Text(text = myViewModel.languageType().get(44),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.navigate(AppScreens.MainScreen.route) }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Te enviara al menu de opciones",
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults
                .smallTopAppBarColors(
                    containerColor = color_fondo_topappbar_alterno,
                    titleContentColor = color_letra_topappbar
                )
        )
        Down(myViewModel)
        // Espacio para empujar el contenido hacia abajo
        Spacer(modifier = Modifier.weight(1f))
    }
}


//Parte envio de calificacion
@Composable
fun Down(myViewModel: MyViewModel) {
    Column(){
        //Primer texto
        Row {
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
                    .background(Color(android.graphics.Color.parseColor("#FFF7E7")))
            ){
                Text(
                    text = myViewModel.languageType().get(45),
                    fontSize = 45.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(android.graphics.Color.parseColor("#153040")),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        //Reaccion
        Calification()
        //Segundo Texto
        Row {
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .background(Color(android.graphics.Color.parseColor("#FFF7E7")))
            ){
                Text(
                    text = myViewModel.languageType().get(46),
                    fontSize = 20.sp,
                    color = Color(android.graphics.Color.parseColor("#153040")),
                    modifier = Modifier.align(Alignment.Center)
                        .padding(start = 50.dp, end = 50.dp)
                )
            }
        }

        //Boton ENVIAR
        Row {
            Box(
                modifier = Modifier
                    .height(140.dp)
                    .fillMaxWidth()
                    .background(Color(android.graphics.Color.parseColor("#FFF7E7")))
            ){
                val roundCornerShape = RoundedCornerShape(topEnd = 30.dp, bottomStart = 30.dp, topStart = 10.dp, bottomEnd = 10.dp)
                Button(
                    onClick = {

                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF192833), // Cambiamos el color de fondo del botón aquí
                        contentColor = Color.White
                    ),
                    shape = roundCornerShape,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(16.dp)
                        .align(Alignment.Center)
                ) {
                    Text(
                        text = myViewModel.languageType().get(47),
                        fontSize = 26.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}



// calificacion por estrellas
@Composable
fun Calification(modifier: Modifier = Modifier) {
    val stars = listOf(
        R.drawable.staroff,
        R.drawable.staroff,
        R.drawable.staroff,
        R.drawable.staroff,
        R.drawable.staroff
    )
    var selectedStars by remember { mutableStateOf(stars) }

    Row(modifier = modifier) {
        Box(
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .background(Color(android.graphics.Color.parseColor("#FFF7E7")))
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                selectedStars.forEachIndexed { index, star ->
                    IconButton(
                        onClick = {
                            selectedStars = selectedStars.mapIndexed { i, _ ->
                                if (i <= index) {
                                    R.drawable.staron
                                } else {
                                    R.drawable.staroff
                                }
                            }
                        },
                        modifier = Modifier.size(48.dp)
                    ) {
                        Image(
                            painter = painterResource(id = star),
                            contentDescription = stringResource(id = R.string.staroff),
                            modifier = Modifier.fillMaxSize()
                                .background(Color.Transparent),
                            contentScale = ContentScale.FillBounds
                        )
                    }

                    if (index < stars.size - 1) {
                        Spacer(modifier = Modifier.width(11.dp))
                    }
                }
            }
        }
    }
}

