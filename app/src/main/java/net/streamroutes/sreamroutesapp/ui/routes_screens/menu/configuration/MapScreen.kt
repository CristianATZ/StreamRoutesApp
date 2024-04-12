package net.streamroutes.sreamroutesapp.ui.routes_screens.menu.configuration

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.viewmodel.ConfigurationViewModel
import net.streamroutes.sreamroutesapp.R

data class MapItem(
    val name: String,
    val desc: String,
    val value: Boolean,
    val action: () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapOptionsScreen(configurationViewModel: ConfigurationViewModel){
    /*
    var paradas by remember { mutableStateOf(true) }
    var terminales by remember { mutableStateOf(true) }
    var ubicacion by remember { mutableStateOf(true) }
    var comida by remember { mutableStateOf(true) }
    var salud by remember { mutableStateOf(true) }
     */

    val map_items = listOf(
        MapItem(
            name = stringResource(id = R.string.lblParadas),
            desc = stringResource(id = R.string.lblParadasDescription),
            value = configurationViewModel.stops,
            action = { configurationViewModel.updateStops(!configurationViewModel.stops) }
        ),
        MapItem(
            name = stringResource(id = R.string.lblTerminales),
            desc = stringResource(id = R.string.lblTerminalesDescription),
            value = configurationViewModel.stations,
            action = { configurationViewModel.updateStations(!configurationViewModel.stations) }
        ),
        MapItem(
            name = stringResource(id = R.string.lblUbiActual),
            desc = stringResource(id = R.string.lblUbiActualDescription),
            value = configurationViewModel.currentLocation,
            action = { configurationViewModel.updateCurrentLocation(!configurationViewModel.currentLocation) }
        ),
        MapItem(
            name = stringResource(id = R.string.lblComida),
            desc = stringResource(id = R.string.lblComidaDescription),
            value = configurationViewModel.food,
            action = { configurationViewModel.updateFood(!configurationViewModel.food) }
        ),
        MapItem(
            name = stringResource(id = R.string.lblSalud),
            desc = stringResource(id = R.string.lblSaludDescription),
            value = configurationViewModel.health,
            action = { configurationViewModel.updateHealth(!configurationViewModel.health) }
        ),
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .verticalScroll(rememberScrollState())
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
                    text = stringResource(id = R.string.lblTipoMapa), // texto
                    style = typography.bodyLarge
                )
                Text(
                    text = stringResource(id = R.string.lblTipoMapaDescription), // texto
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
                    text = stringResource(id = R.string.lblTemaMapa), // texto
                    style = typography.bodyLarge
                )
                Text(
                    text = stringResource(id = R.string.lblTemaMapaDescription), // texto
                    style = typography.labelMedium
                )
            }
        }
    }
}

@Composable
fun HeaderMapOptions() {
    Text(
        text = stringResource(id = R.string.lblMapa),
        style = typography.titleSmall,
        modifier = Modifier
            .padding(PaddingValues(16.dp))
    )

    Divider()
}

@Composable
fun HeaderMarcadores() {
    Text(
        text = stringResource(id = R.string.lblMarcadores),
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