package net.streamroutes.sreamroutesapp.Screens.ConfigurationScreens

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.Colores.color_fondo
import net.streamroutes.sreamroutesapp.Colores.color_fondo_topbar
import net.streamroutes.sreamroutesapp.Colores.color_icon
import net.streamroutes.sreamroutesapp.Colores.color_letra_topbar
import net.streamroutes.sreamroutesapp.Colores.color_letraout
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfigurationScreen(myViewModel: MyViewModel, navController: NavController){
    Scaffold(
        topBar = { TopBarBody(myViewModel,navController) },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // cambiar de ciudad
            Options(
                text = myViewModel.languageType().get(49),
                color_texto = MaterialTheme.colorScheme.onBackground,
                onClick = {
                    navController.navigate(route = AppScreens.ChangeCityScreen.route)
                }
            )

            // notificaciones
            Options(
                text = myViewModel.languageType().get(50),
                color_texto = MaterialTheme.colorScheme.onBackground,
                onClick = {
                    navController.navigate(route = AppScreens.NotificationsScreen.route)
                }
            )

            // mapa
            Options(
                text = myViewModel.languageType().get(51),
                color_texto = MaterialTheme.colorScheme.onBackground,
                onClick = {
                    navController.navigate(route = AppScreens.MapOptionsScreen.route)
                }
            )

            // Privacidad
            Options(
                text = myViewModel.languageType().get(52),
                color_texto = MaterialTheme.colorScheme.onBackground,
                onClick = {
                    navController.navigate(route = AppScreens.PrivacityScreen.route)
                }
            )
            Spacer(modifier = Modifier.fillMaxWidth(0.95f).background(MaterialTheme.colorScheme.primary.copy(0.25f)).height(1.dp))
            // Idioma
            Options(
                text = "Idioma de la aplicacion",
                color_texto = MaterialTheme.colorScheme.onBackground,
                onClick = {
                    myViewModel.idioma = if (myViewModel.idioma == 0) 1 else 0
                }
            )
            
            // tema
            Options(
                text = if(!myViewModel.tema) "Modo oscuro" else "Modo claro",
                color_texto = MaterialTheme.colorScheme.onBackground,
                onClick = {
                    myViewModel.tema = !myViewModel.tema
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBarBody(
    myViewModel: MyViewModel,
    navController: NavController
) {
    TopAppBar(
        title = {
            Text(text = myViewModel.languageType().get(48),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigate(AppScreens.MainScreen.route) }) {
                Icon(
                    painterResource(id = R.drawable.back),
                    contentDescription = "Te enviara al menu de opciones",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        colors = TopAppBarDefaults
            .smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
    )
}

@Composable
private fun Options(
    text: String,
    color_texto: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable(onClick = onClick)
    ) {
        Spacer(modifier = Modifier.size(25.dp))
        Text(
            text = text, // texto
            modifier = Modifier
                .align(Alignment.CenterVertically),
            color = color_texto,
            fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif,
            fontSize = 18.sp
        )
    }
}

@Preview (showBackground = true)
@Composable
fun configuracionView(){
    //ConfiguracionScreen()
}