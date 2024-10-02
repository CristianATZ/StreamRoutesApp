package net.streamroutes.sreamroutesapp.features.transportApp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TransportAppTopAppBar(
    modifier: Modifier = Modifier,
    onOpenMenu: () -> Unit = {}
) {
    Column {
        TopAppBar(
            title = { /*TODO*/ },
            navigationIcon = {
                MenuButtonTransportApp(
                    onOpenMenu = onOpenMenu
                )
            },
            actions = {
                ActionsTopBarTransportApp()
            },
            modifier = Modifier.height(50.dp)
        )

        Divider(
            color = colorScheme.outline, // El color del borde
            thickness = 1.dp // El grosor del borde
        )
    }
}

@Preview
@Composable
fun MenuButtonTransportApp(
    modifier: Modifier = Modifier,
    onOpenMenu: () -> Unit = {}
) {
    IconButton(
        onClick = onOpenMenu
    ) {
        Icon(
            imageVector = Icons.Outlined.Menu,
            contentDescription = stringResource(id = R.string.iconMenu)
        )
    }
}

@Preview
@Composable
fun ActionsTopBarTransportApp(
    modifier: Modifier = Modifier
) {
    Row {
        IconButton(
            onClick = {
                // ACCION PARA ABRIR EL PERFIL
            }
        ) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = stringResource(id = R.string.iconProfile)
            )
        }

        IconButton(
            onClick = {
                // ACCION PARA ABRIR EL PERFIL
            }
        ) {
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = stringResource(id = R.string.iconOpenSettings)
            )
        }
    }
}