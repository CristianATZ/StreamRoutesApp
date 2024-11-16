package net.streamroutes.sreamroutesapp.features.maps.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.maps.presentation.transport.TransportViewModel

@Composable
fun PlannerInfoWindow(
    //ubication: String ="Padre Luis Gaytan, Col. San Isidro, Cp. 38887, Moroleon, Guanajuato Mexico"
    address: String
) {
    ElevatedCard(
        modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // encabezado
            Text(
                text = stringResource(id = R.string.lblDestinationSelected),
                style = typography.headlineSmall
            )

            // direccion
            Text(
                text = address,
                style = typography.labelLarge
            )
        }
    }
}