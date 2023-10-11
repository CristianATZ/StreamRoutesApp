package net.streamroutes.sreamroutesapp.Screens.Routes

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.Screens.Start.TopBar
import net.streamroutes.sreamroutesapp.R
import java.net.URI

@Composable
fun TurismScreen(myViewModel: MyViewModel, navController: NavController) {
    Tourism(myViewModel, navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tourism(myViewModel: MyViewModel, navController: NavController){
    Scaffold (
        topBar = { TopBar(myViewModel, navController) }
    ){ paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
                //.fillMaxWidth(0.8f),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            item {
                Text(
                    text = "\"No hay que llegar primero... pero hay que saber llegar\"",
                    modifier = Modifier.fillMaxWidth(0.9f),
                    textAlign = TextAlign.Center
                )
            }
            item {
                Text(
                    text = "- José Alfredo Jiménez",
                    modifier = Modifier.fillMaxWidth(0.9f),
                    textAlign = TextAlign.End
                )
            }
            item{
                Tarjeta("Alhóndiga de Granaditas", R.drawable.lugar_alhondiga)
                Tarjeta("Universidad de Guanajuato", R.drawable.lugar_universidad_gto)
                Tarjeta("Parque Bicentenario", R.drawable.lugar_parque_bicentenario)
                Tarjeta("Palacio de Gobierno", R.drawable.lugar_palacio)
                Tarjeta("Minas", R.drawable.lugar_minas)
                Tarjeta("Teatro Juárez", R.drawable.lugar_teatro_juarez)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    myViewModel: MyViewModel,
    navController: NavController
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = myViewModel.languageType().get(297),
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.navigate(AppScreens.MainScreen.route)
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowLeft,
                    contentDescription = "regresar a MainScreen"
                )
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tarjeta(titulo: String, id_foto: Int){
    var rotated by remember { mutableStateOf(false) }

    val rotationFront by animateFloatAsState(
        targetValue = if (rotated) 180f else 0f,
        animationSpec = tween(500)
    )

    val rotationBack by animateFloatAsState(
        targetValue = if (rotated) -180f else 0f,
        animationSpec = tween(500)
    )

    val animateFront by animateFloatAsState(
        targetValue = if (!rotated) 1f else 0f,
        animationSpec = tween(500)
    )

    val animateBack by animateFloatAsState(
        targetValue = if (rotated) 1f else 0f,
        animationSpec = tween(500)
    )

    Column {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(10.dp)
                .graphicsLayer {
                    rotationY = rotationFront
                    cameraDistance = 8 * density
                },
            elevation = CardDefaults.cardElevation(8.dp),
            onClick = {
                rotated = !rotated
            }
        ) {
            if(!rotated){
                // FRENTE
                Box(
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.BottomStart
                ){
                    Image(
                        painter = painterResource(id = id_foto),
                        contentDescription = titulo,
                        modifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer {
                                alpha = animateFront
                            },
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = titulo,
                        color = Color.White,
                        fontSize = 24.sp,
                        modifier = Modifier
                            .padding(6.dp)
                            .graphicsLayer {
                                alpha = animateFront
                            }
                    )
                }
            }
            else{
                // REVERSO
                Box(
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.BottomEnd
                ){
                    Column {
                        Text(
                            text = "REVERSO DE LA CARD. Estás viendo " + titulo,
                            modifier = Modifier
                                .graphicsLayer {
                                    alpha = animateBack
                                    rotationY = rotationBack
                                }
                        )
                        Button(
                            onClick = {
                                /* TODO */
                            },
                            modifier = Modifier
                                .graphicsLayer {
                                    alpha = animateBack
                                    rotationY = rotationBack
                                }
                        ) {
                            Text(text = "Soy un botón jajaja")
                        }
                    }
                }
            }
            
        }
    }
}