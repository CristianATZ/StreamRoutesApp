package net.streamroutes.sreamroutesapp.features.parkingApp.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.components.CustomTopAppBar
import net.streamroutes.sreamroutesapp.features.components.NavigationButton

@Composable
fun ParkingSmallTopAppBar(
    modifier: Modifier = Modifier,
    onNavigationPressed: () -> Unit,
    onProfilePressed: () -> Unit,
    onSettingsPressed: () -> Unit
) {
    CustomTopAppBar(
        title = {
            Text(text = stringResource(R.string.lblHello, "USUARIO"))
        },
        navigationIcon = {
            NavigationButton(
                onButtonPressed = onNavigationPressed
            )
        },
        actions = {
            ParkingActionsTopBar(
                onProfilePressed = onProfilePressed,
                onSettingsPressed = onSettingsPressed,
                onFilterPressed = {

                }
            )
        },
        modifier = modifier
    )

    HorizontalDivider()
}

@Composable
private fun ParkingActionsTopBar(
    onProfilePressed: () -> Unit,
    onSettingsPressed: () -> Unit,
    onFilterPressed: () -> Unit
) {
    Row {
        IconButton(
            onClick = onFilterPressed
        ) {
            Icon(
                imageVector = Icons.Filled.FilterAlt,
                contentDescription = stringResource(id = R.string.iconFilter)
            )
        }

        IconButton(
            onClick = onProfilePressed
        ) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = stringResource(id = R.string.iconProfile)
            )
        }

        IconButton(
            onClick = onSettingsPressed
        ) {
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = stringResource(id = R.string.iconSettings)
            )
        }
    }
}