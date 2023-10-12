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
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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

data class ConfItem(
    val name: String,
    val action: () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfigurationScreen(myViewModel: MyViewModel, navController: NavController){
    val conf_items = listOf(
        ConfItem(
            name = myViewModel.languageType().get(217),
            action = {
                navController.navigate(route = AppScreens.ChangeCityScreen.route)
            }
        ),
        ConfItem(
            name = myViewModel.languageType().get(218),
            action = {
                navController.navigate(route = AppScreens.NotificationsScreen.route)
            }
        ),
        ConfItem(
            name = myViewModel.languageType().get(219),
            action = {
                navController.navigate(route = AppScreens.MapOptionsScreen.route)
            }
        ),
        ConfItem(
            name = myViewModel.languageType().get(220),
            action = {
                navController.navigate(route = AppScreens.PrivacityScreen.route)
            }
        ),
        ConfItem(
            name = "Idioma de la aplicacion",
            action = {
                myViewModel.idioma = if (myViewModel.idioma == 0) 1 else 0
            }
        ),
        ConfItem(
            name = if(!myViewModel.tema) "Modo oscuro" else "Modo claro",
            action = {
                myViewModel.tema = !myViewModel.tema
            }
        ),
    )


    Scaffold(
        topBar = { TopBarBody(myViewModel,navController) },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            conf_items.forEach(){ item ->
                Options(text = item.name) {
                    item.action()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBarBody(
    myViewModel: MyViewModel,
    navController: NavController
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = myViewModel.languageType().get(216),
                style = typography.titleLarge
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { navController.navigate(AppScreens.MainScreen.route) }
            ) {
                Icon(
                    painterResource(id = R.drawable.back),
                    contentDescription = "Te enviara al menu de opciones"
                )
            }
        }
    )
}

@Composable
private fun Options(
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = text, // texto
            style = typography.bodyLarge
        )
    }
}