package net.streamroutes.sreamroutesapp.Screens.ConfigurationScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
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
            name = myViewModel.languageType().get(235),
            desc = myViewModel.languageType().get(236),
            value = paradas,
            action = { paradas = !paradas }
        ),
        MapItem(
            name = myViewModel.languageType().get(237),
            desc = myViewModel.languageType().get(238),
            value = terminales,
            action = { terminales = !terminales }
        ),
        MapItem(
            name = myViewModel.languageType().get(239),
            desc = myViewModel.languageType().get(240),
            value = ubicacion,
            action = { ubicacion = !ubicacion }
        ),
        MapItem(
            name = myViewModel.languageType().get(241),
            desc = myViewModel.languageType().get(242),
            value = comida,
            action = { comida = !comida }
        ),
        MapItem(
            name = myViewModel.languageType().get(243),
            desc = myViewModel.languageType().get(244),
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
            HeaderMarcadores()

            map_items.forEach(){ item ->
                OptionSwitch(
                    item = item
                )
            }

            Spacer(modifier = Modifier.size(32.dp))
            
            HeaderMapOptions()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .clickable {

                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.size(16.dp))

                Column {
                    Text(
                        text = "Tipo de mapa", // texto
                        style = typography.bodyLarge
                    )
                    Text(
                        text = "Mapnik, etc.", // texto
                        style = typography.labelMedium
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .clickable {

                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.size(16.dp))

                Column {
                    Text(
                        text = "Tema del mapa", // texto
                        style = typography.bodyLarge
                    )
                    Text(
                        text = "Claro, oscuro, neon, etc.", // texto
                        style = typography.labelMedium
                    )
                }
            }
        }
    }
}

@Composable
fun HeaderMapOptions() {
    Text(
        text = "Mapa",
        style = typography.titleSmall,
        modifier = Modifier
            .padding(PaddingValues(16.dp))
    )

    Divider()
}

@Composable
fun HeaderMarcadores() {
    Text(
        text = "Marcadores",
        style = typography.titleSmall,
        modifier = Modifier
            .padding(PaddingValues(16.dp))
    )

    Divider()
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
                    imageVector = Icons.Outlined.KeyboardArrowLeft,
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
private fun OptionSwitch(
    item: MapItem
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .clickable { item.action() },
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
            checked = item.value,
            onCheckedChange = {
                item.action()
            }
        )
    }
}