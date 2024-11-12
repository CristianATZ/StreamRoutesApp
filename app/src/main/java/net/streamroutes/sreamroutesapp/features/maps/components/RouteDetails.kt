package net.streamroutes.sreamroutesapp.features.maps.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.utils.shimmerEffect

@Composable
fun RouteDetails(
    distance: String = "2.3 km",
    time: String = "19 min",
    currentStreet: String = "Padre Luis Gaytan",
    nextStreet: String = "Pedro Guzman"
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.surface,
            contentColor = colorScheme.onSurface
        ),
        modifier = Modifier
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShimmerRouteDetails(

) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.surface,
            contentColor = colorScheme.onSurface
        ),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                repeat(2) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(25.dp)
                            .clip(shapes.small)
                            .shimmerEffect()
                    )
                    
                    if(it < 1) Spacer(Modifier.size(8.dp))
                }
            }

            VerticalDivider(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .height(50.dp)
                    .align(Alignment.CenterVertically)
            )

            Column(
                modifier = Modifier
                    .weight(2f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .clip(shapes.small)
                        .shimmerEffect()
                )
            }
        }
    }
}