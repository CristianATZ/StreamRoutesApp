package net.streamroutes.sreamroutesapp.features.maps.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.core.domain.model.RouteInformation
import net.streamroutes.sreamroutesapp.features.components.CardOption

@Composable
fun StopInformationBottomSheet(
    routeInformation: RouteInformation,
    onClose: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // nombre de la ruta
        Text(
            text = routeInformation.name,
            style = typography.headlineSmall
        )

        // lugares turisticos
        LazyRow(
            contentPadding = PaddingValues(16.dp)
        ) {
            items(routeInformation.officialStops) {
                CardOption(
                    modifier = Modifier.size(200.dp)
                )

                Spacer(modifier = Modifier.size(16.dp))
            }
        }

        // informacion de la ruta
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.lblRouteDetails),
                style = typography.titleLarge,
                modifier = Modifier.graphicsLayer(alpha = 0.5f)
            )

            // tiempo aproximado
            Text(
                text = stringResource(id = R.string.lblAproxTime, routeInformation.hourAprox, routeInformation.minutesAprox),
                style = typography.labelLarge
            )

            Spacer(modifier = Modifier.size(8.dp))

            // paradas oficiales
            Text(
                text = stringResource(
                    id = R.string.lblOfficialStops,
                    routeInformation.officialStops
                ),
                style = typography.labelLarge
            )

            Spacer(modifier = Modifier.size(8.dp))

            // inicio de ruta
            Text(
                text = stringResource(id = R.string.lblStartRoute, routeInformation.start),
                style = typography.labelLarge
            )

            Spacer(modifier = Modifier.size(8.dp))

            // fin de ruta
            Text(
                text = stringResource(id = R.string.lblEndRoute, routeInformation.end),
                style = typography.labelLarge
            )
        }

        // cerrar
        Button(
            onClick = onClose,
            shape = shapes.small,
            modifier = Modifier
                .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.btnClose))
        }
    }
}