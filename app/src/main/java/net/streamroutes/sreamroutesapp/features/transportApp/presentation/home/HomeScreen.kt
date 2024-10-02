package net.streamroutes.sreamroutesapp.features.transportApp.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.components.CardOption

@Preview(showBackground = true)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(vertical = 16.dp, horizontal = 8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.lblHello) + "usuario !",
                style = typography.displaySmall
            )
            Text(
                text = stringResource(id = R.string.lblGreet),
                style = typography.bodyMedium,
                modifier = Modifier.graphicsLayer(alpha = 0.5f)
            )
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            CardOption(
                text = stringResource(id = R.string.lblPublicTransportRoutes),
                onClick = {
                    // ENVIAR A MAPAS
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(200.dp)
            )

            CardOption(
                text = stringResource(id = R.string.lblPlan),
                onClick = {
                    // ENVIAR A PLANIFICAR TU VIAJE
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(200.dp)
            )

            CardOption(
                text = stringResource(id = R.string.lblForum),
                onClick = {
                    // ENVIAR A FORO
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(200.dp)
            )
        }
    }
}