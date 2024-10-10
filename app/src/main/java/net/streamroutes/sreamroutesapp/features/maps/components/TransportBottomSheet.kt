package net.streamroutes.sreamroutesapp.features.maps.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RouteBottomSheet(
    routeName: String = "Ruta 11 - El charco",
    timeToStop: String = "5 minutos",
    colorNearStop: Color = Color.Red, // este dato probablemente obtener del viewmodel
    colorRoute: Color = Color.Black, // este dato tambien del viewmodel
    colorStop: Color = Color.Green
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // nombre de la ruta
        Text(
            text = routeName,
            style = typography.headlineSmall
        )

        Spacer(
            modifier = Modifier.size(16.dp)
        )

        // parada mas cercana
        RouteInfowRow(
            description = stringResource(id = R.string.lblGoNearStop),
            color = colorNearStop
        )

        // color de la ruta
        RouteInfowRow(
            description = stringResource(id = R.string.lblRouteColor),
            color = colorRoute
        )

        // color de la siguiente parada
        RouteInfowRow(
            description = stringResource(id = R.string.lblBusTimeToStop),
            color = colorStop
        )

        Spacer(modifier = Modifier.size(8.dp))

        // siguiente parada
        Card(
            colors = CardDefaults.cardColors(
                containerColor = colorScheme.tertiaryContainer,
                contentColor = colorScheme.onTertiaryContainer
            ),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = timeToStop,
                    style = typography.displayMedium
                )

                Spacer(modifier = Modifier.size(8.dp))

                Text(
                    text = stringResource(id = R.string.lblNextStop),
                    style = typography.bodyLarge
                )
            }
        }

        Spacer(modifier = Modifier.size(16.dp))

        TextButton(
            onClick = {
                // ABRIR HOJA PARA COMPARTIR UBICAION
            },
            shape = shapes.small,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.btnShareLocation),
                textDecoration = TextDecoration.Underline
            )
        }

        Button(
            onClick = {

            },
            shape = shapes.small,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .combinedClickable(
                    onClick = {
                        // MANDAR INDICACION PARA MANTENER
                        // PARA CANCELAR
                    },
                    onLongClick = {
                        // CANCELAR RUTA
                        // REGRESAR A RUTAS DE TRANSPORTE PUBLICO
                    }
                )
        ) {
            Text(text = stringResource(id = R.string.btnCancelRoute))
        }

        Spacer(modifier = Modifier.size(16.dp))
    }
}