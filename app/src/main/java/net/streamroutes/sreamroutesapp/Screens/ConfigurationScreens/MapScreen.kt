package net.streamroutes.sreamroutesapp.Screens.ConfigurationScreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.viewmodel.MyViewModel
import net.streamroutes.sreamroutesapp.navigation.AppScreens

data class MapItem(
    val name: String,
    val desc: String,
    val value: Boolean,
    val action: () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapOptionsScreen(myViewModel: MyViewModel = MyViewModel()){
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

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .verticalScroll(rememberScrollState())
    ) {
        HeaderMarcadores(myViewModel)

        map_items.forEach(){ item ->
            OptionSwitch(
                item = item
            )
        }

        Spacer(modifier = Modifier.size(32.dp))

        HeaderMapOptions(myViewModel)

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
                    text = myViewModel.languageType()[365], // texto
                    style = typography.bodyLarge
                )
                Text(
                    text = myViewModel.languageType()[366], // texto
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
                    text = myViewModel.languageType()[367], // texto
                    style = typography.bodyLarge
                )
                Text(
                    text = myViewModel.languageType()[368], // texto
                    style = typography.labelMedium
                )
            }
        }
    }
}

@Composable
fun HeaderMapOptions(
    myViewModel: MyViewModel
) {
    Text(
        text = myViewModel.languageType()[364],
        style = typography.titleSmall,
        modifier = Modifier
            .padding(PaddingValues(16.dp))
    )

    Divider()
}

@Composable
fun HeaderMarcadores(
    myViewModel: MyViewModel
) {
    Text(
        text = myViewModel.languageType()[363],
        style = typography.titleSmall,
        modifier = Modifier
            .padding(PaddingValues(16.dp))
    )

    Divider()
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