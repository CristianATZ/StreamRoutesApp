package net.streamroutes.sreamroutesapp.features.maps.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R

@Composable
fun RouteDetails(
    modifier: Modifier = Modifier,
    distance: String = "2.3 km",
    time: String = "19 min",
    currentStreet: String = "Padre Luis Gaytan",
    nextStreet: String = "Pedro Guzman"
) {
    ElevatedCard(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Row {
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f)
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.lblDistance),
                    style = typography.labelLarge
                )

                Text(
                    text = distance,
                    style = typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = time,
                    style = typography.labelLarge,
                    textAlign = TextAlign.Center
                )
            }

            VerticalDivider(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .height(50.dp)
                    .align(Alignment.CenterVertically)
            )

            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(2f)
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = currentStreet,
                    style = typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = stringResource(id = R.string.lblRouteDirection, nextStreet),
                    style = typography.labelLarge
                )
            }
        }
    }
}