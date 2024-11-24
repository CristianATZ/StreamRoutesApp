package net.streamroutes.sreamroutesapp.features.maps.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.maps.presentation.transport.TransportViewModel
import net.streamroutes.sreamroutesapp.utils.shimmerEffect

@Composable
fun RouteBottomSheet(
    colorNearStop: Color = Color.Red, // este dato probablemente obtener del viewmodel
    colorRoute: Color = Color.Black, // este dato tambien del viewmodel
    colorStop: Color = Color.Green,
    onBackPressed: () -> Unit = {},
    onShareLocation: () -> Unit = {},
    //selectedRoute: RouteWithPlaces?
    name: String,
    transportViewModel: TransportViewModel
) {
    val routeData by transportViewModel.orsRouteData.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // nombre de la ruta
        if(name == null) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .align(Alignment.CenterHorizontally)
                    .height(25.dp)
                    .clip(shapes.small)
                    .shimmerEffect()
            )
        } else {
            Text(
                text = name,
                style = typography.headlineSmall,
                textAlign = TextAlign.Center
            )
        }

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


        if(routeData?.get("duration") != null){
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
                        text = "${routeData?.get("duration")} minutos",
                        style = typography.displayMedium
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    Text(
                        text = stringResource(id = R.string.lblNextStop),
                        style = typography.bodyLarge
                    )
                }
            }
        } else {
            Spacer(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(shapes.small)
                    .shimmerEffect()
            )
        }

        Spacer(modifier = Modifier.size(16.dp))

        TextButton(
            onClick = onShareLocation,
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
            onClick = onBackPressed,
            shape = shapes.small,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.btnCancelRoute))
        }

        Spacer(Modifier.navigationBarsPadding())
    }
}