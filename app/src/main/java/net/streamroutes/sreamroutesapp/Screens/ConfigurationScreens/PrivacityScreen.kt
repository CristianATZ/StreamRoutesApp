package net.streamroutes.sreamroutesapp.Screens.ConfigurationScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.Colores.color_botones
import net.streamroutes.sreamroutesapp.Colores.color_fondo
import net.streamroutes.sreamroutesapp.Colores.color_fondo_topbar
import net.streamroutes.sreamroutesapp.Colores.color_icon
import net.streamroutes.sreamroutesapp.Colores.color_letra_topbar
import net.streamroutes.sreamroutesapp.Colores.color_letrain
import net.streamroutes.sreamroutesapp.Colores.color_letraout
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R

data class PrivacyItem(
    val name: String,
    val desc: String,
    val value: Boolean,
    val action: () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacityScreen(myViewModel: MyViewModel,navController: NavController){

    // variables globales
    var localizacion by remember { mutableStateOf(true) }
    var anuncios by remember { mutableStateOf(true) }
    var rutas by remember { mutableStateOf(true) }
    var suscripcion by remember { mutableStateOf(true) }

    val privacy_items = listOf(
        PrivacyItem(
            name = myViewModel.languageType().get(246),
            desc = myViewModel.languageType().get(247),
            value = localizacion,
            action = {
                localizacion = !localizacion
            }
        ),
        PrivacyItem(
            name = myViewModel.languageType().get(248),
            desc = myViewModel.languageType().get(249),
            value = anuncios,
            action = {
                anuncios = !anuncios
            }
        ),
        PrivacyItem(
            name = myViewModel.languageType().get(250),
            desc = myViewModel.languageType().get(251),
            value = rutas,
            action = {
                rutas = !rutas
            }
        ),
        PrivacyItem(
            name = myViewModel.languageType().get(252),
            desc = myViewModel.languageType().get(253),
            value = suscripcion,
            action = {
                suscripcion = !suscripcion
            }
        )
    )

    Scaffold(
        topBar = { TopBarBody(myViewModel,navController) },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            privacy_items.forEach(){ item ->
                Options(item = item)
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
                text = myViewModel.languageType().get(245),
                style = typography.titleLarge
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigate(AppScreens.ConfigurationScreen.route) }) {
                Icon(
                    painterResource(id = R.drawable.back),
                    contentDescription = "Te enviara al menu de configuraciones",
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
private fun Options(
    item: PrivacyItem
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .toggleable(
                value = item.value,
                onValueChange = { item.action() }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Spacer(modifier = Modifier.size(16.dp))

        // textos
        Column(
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = item.name,
                style = typography.bodyLarge
            )

            Text(
                text = item.desc,
                style = typography.labelMedium
            )
        }

        Spacer(modifier = Modifier.size(16.dp))

        // switch
        Switch(
            checked = item.value,
            onCheckedChange = {
                item.action()
            }
        )
    }
}

@Preview (showBackground = true)
@Composable
fun view(){
    //priva()
}