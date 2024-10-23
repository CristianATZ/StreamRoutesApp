package net.streamroutes.sreamroutesapp.features.maps.presentation.fastest

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Accessible
import androidx.compose.material.icons.automirrored.outlined.DirectionsBike
import androidx.compose.material.icons.automirrored.outlined.DirectionsWalk
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.MyLocation
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.maps.components.RouteDetails
import net.streamroutes.sreamroutesapp.features.maps.components.RouteType

@Composable
fun FastestRoutesInformation(
    currentRoute: Int,
    onChangeRoute: (Int) -> Unit,
    onSelectRoute: () -> Unit,
    onMyLocation: () -> Unit
) {


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RouteDetails()

        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            RouteType(
                isSelected = currentRoute == 1,
                onClick = {
                    onChangeRoute(1)
                },
                icon = Icons.Outlined.DirectionsCar,
                iconDescription = stringResource(id = R.string.iconDirectionsCar)
            )

            RouteType(
                isSelected = currentRoute == 2,
                onClick = {
                    onChangeRoute(2)
                },
                icon = Icons.AutoMirrored.Outlined.DirectionsWalk,
                iconDescription = stringResource(id = R.string.iconFootWalking)
            )

            RouteType(
                isSelected = currentRoute == 3,
                onClick = {
                    onChangeRoute(3)
                },
                icon = Icons.AutoMirrored.Outlined.DirectionsBike,
                iconDescription = stringResource(id = R.string.icondirectionsBike)
            )

            RouteType(
                isSelected = currentRoute == 4,
                onClick = {
                    onChangeRoute(4)
                },
                icon = Icons.AutoMirrored.Outlined.Accessible,
                iconDescription = stringResource(id = R.string.iconAccesible)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            FloatingActionButton(
                onClick = onMyLocation,
                containerColor = colorScheme.tertiaryContainer,
                contentColor = colorScheme.onTertiaryContainer
            ) {
                Icon(
                    imageVector = Icons.Outlined.MyLocation,
                    contentDescription = stringResource(id = R.string.iconMyLocation)
                )
            }
            
            Spacer(modifier = Modifier.size(8.dp))

            ExtendedFloatingActionButton(
                onClick = onSelectRoute
            ) {
                Text(text = stringResource(id = R.string.btnSelect))
            }
        }
    }
}