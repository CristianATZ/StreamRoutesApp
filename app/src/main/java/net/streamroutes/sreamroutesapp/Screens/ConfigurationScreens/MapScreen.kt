package net.streamroutes.sreamroutesapp.Screens.ConfigurationScreens

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

data class MapItem(
    val name: String,
    val desc: String,
    val value: Boolean,
    val action: () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapOptionsScreen(myViewModel: MyViewModel,navController: NavController){
    var paradas by remember { mutableStateOf(true) }
    var terminales by remember { mutableStateOf(true) }
    var ubicacion by remember { mutableStateOf(true) }
    var comida by remember { mutableStateOf(true) }
    var salud by remember { mutableStateOf(true) }

    val map_items = listOf(
        MapItem(
            name = myViewModel.languageType().get(225),
            desc = myViewModel.languageType().get(226),
            value = paradas,
            action = { paradas = !paradas }
        ),
        MapItem(
            name = myViewModel.languageType().get(226),
            desc = myViewModel.languageType().get(227),
            value = terminales,
            action = { terminales = !terminales }
        ),
        MapItem(
            name = myViewModel.languageType().get(228),
            desc = myViewModel.languageType().get(229),
            value = ubicacion,
            action = { ubicacion = !ubicacion }
        ),
        MapItem(
            name = myViewModel.languageType().get(230),
            desc = myViewModel.languageType().get(231),
            value = comida,
            action = { comida = !comida }
        ),
        MapItem(
            name = myViewModel.languageType().get(232),
            desc = myViewModel.languageType().get(233),
            value = salud,
            action = { salud = !salud }
        ),
    )

    Scaffold(
        topBar = { TopBarBody(myViewModel,navController) }
    ) { paddingValues ->
        // cuerpo
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBarBody(
    myViewModel: MyViewModel,
    navController: NavController
) {
    // top app bar
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = myViewModel.languageType().get(234),
                style = typography.titleLarge
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigate(AppScreens.ConfigurationScreen.route) }) {
                Icon(
                    painterResource(id = R.drawable.back),
                    contentDescription = "Te enviara al menu de configuraciones"
                )
            }
        }
    )
}

@Composable
private fun OptionSwitch(
    item: MapItem,
    value: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {

        Spacer(modifier = Modifier.size(16.dp))
        // textos
        Column(
            modifier = Modifier
                .fillMaxWidth(0.75f)
        ) {
            Text(
                text = item.name, // texto
                style = typography.bodyLarge
            )
            Text(
                text = item.desc, // texto
                style = typography.labelMedium
            )
        }

        // switch
        Switch(
            checked = value,
            onCheckedChange = {
                onClick()
            }
        )
    }
}