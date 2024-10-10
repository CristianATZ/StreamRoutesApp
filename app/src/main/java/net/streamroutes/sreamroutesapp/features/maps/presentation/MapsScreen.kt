package net.streamroutes.sreamroutesapp.features.maps.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import net.streamroutes.sreamroutesapp.features.maps.components.MapsSmallTopAppBar
import net.streamroutes.sreamroutesapp.features.maps.presentation.planner.PlannerScreen
import net.streamroutes.sreamroutesapp.features.maps.presentation.transport.MapRouteScreen
import net.streamroutes.sreamroutesapp.features.maps.presentation.transport.MapStopScreen
import net.streamroutes.sreamroutesapp.features.maps.presentation.transport.TransportScreen

@Preview(showBackground = true)
@Composable
fun MapsScreen(
    modifier: Modifier = Modifier
) {

    Scaffold(
        topBar = {
            MapsSmallTopAppBar()
        }
    ) { innrPadding ->
        // UTILIZAR UN NAVHOST EN ESTE SCAFFOLD
        // PARA MANEJAR TRANSPORTE, PLANIFICA Y CAMINA
        // DENTRO DE TRANSPORTE MANEJAR OTRO NAVHOST
        // PARA MANEJAR LA RUTA Y EL MAPA COMPLETO


        /*TransportScreen(
            modifier = Modifier.padding(innrPadding)
        )*/

        /*MapRouteScreen(
            modifier = Modifier.padding(innrPadding)
        )*/

        /*MapStopScreen(
            modifier = Modifier.padding(innrPadding)
        )*/

        PlannerScreen(
            modifier = Modifier.padding(innrPadding)
        )
    }
}