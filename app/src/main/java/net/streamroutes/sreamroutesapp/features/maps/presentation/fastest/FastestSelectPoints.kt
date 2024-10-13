package net.streamroutes.sreamroutesapp.features.maps.presentation.fastest

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.MyLocation
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.maps.components.CardCurrentLocationWithIcon

@Composable
fun FastestSelectPoints(
    modifier: Modifier = Modifier,
    current: String? = null,
    dest: String? = null,
    onRestartLocation: () -> Unit,
    onCalculateRoute: () -> Unit,
    onMyLocation: () -> Unit
) {
    val destination = if(dest.isNullOrEmpty()) stringResource(id = R.string.lblDestination) else dest
    val currentAddress = if(current.isNullOrEmpty()) stringResource(id = R.string.lblEmptyCurrentAddress) else current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // ubicacion actual
        // siguiente ubicacion
        Spacer(modifier = Modifier.size(16.dp))

        CardCurrentLocationWithIcon(
            icon = Icons.Outlined.MyLocation,
            iconDescription = stringResource(id = R.string.iconDestination),
            currentAddress = currentAddress
        )

        Spacer(modifier = Modifier.size(16.dp))

        CardCurrentLocationWithIcon(
            icon = Icons.Outlined.Place,
            iconDescription = stringResource(id = R.string.iconDestination),
            currentAddress = destination,
            modifier = Modifier.graphicsLayer(
                alpha = if(dest.isNullOrEmpty()) 0.75f else 1f
            )
        )

        Spacer(modifier = Modifier.weight(1f))

        // cancelar seleccion
        AnimatedVisibility(
            visible = !dest.isNullOrEmpty(),
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.End)
        ) {
            Column(
                modifier = modifier.padding(16.dp),
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

                FloatingActionButton(
                    onClick = onRestartLocation,
                    containerColor = colorScheme.errorContainer,
                    contentColor = colorScheme.onErrorContainer
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Cancel,
                        contentDescription = stringResource(id = R.string.iconCancelDestination)
                    )
                }

                Spacer(modifier = Modifier.size(8.dp))

                ExtendedFloatingActionButton(
                    onClick = onCalculateRoute
                ) {
                    Text(text = stringResource(id = R.string.btnCalculateRoute))
                }
            }
        }
    }
}