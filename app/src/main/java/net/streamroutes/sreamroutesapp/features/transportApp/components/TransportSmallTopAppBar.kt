package net.streamroutes.sreamroutesapp.features.transportApp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.components.CustomTopAppBar
import net.streamroutes.sreamroutesapp.features.components.NavigationButton

@Preview
@Composable
fun TransportSmallTopAppBar(
    modifier: Modifier = Modifier,
    onNavigationPressed: () -> Unit = {}
) {
    Column {
        CustomTopAppBar(
            title = { /*TODO*/ },
            navigationIcon = {
                NavigationButton(
                    onButtonPressed = onNavigationPressed
                )
            },
            actions = {
                ActionsTopBar()
            },
            modifier = modifier
        )

        HorizontalDivider()
    }
}

@Preview
@Composable
private fun ActionsTopBar(
    onProfilePressed: () -> Unit = {},
    onSettingsPressed: () -> Unit = {}
) {
    Row {
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
                contentDescription = stringResource(id = R.string.iconOpenSettings)
            )
        }
    }
}