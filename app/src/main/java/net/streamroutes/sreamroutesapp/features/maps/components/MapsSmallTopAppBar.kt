package net.streamroutes.sreamroutesapp.features.maps.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.DirectionsWalk
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.DirectionsBus
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.orange
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.components.NavigationButton

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MapsSmallTopAppBar(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
    onTransportPressed: () -> Unit = {},
    onPlannerPressed: () -> Unit = {},
    onFastestPressed: () -> Unit = {},
    onSettingsPressed: () -> Unit = {}
) {
    Column {
        TopAppBar(
            title = {

            },
            navigationIcon = {
                NavigationButton(
                    icon = Icons.Outlined.ArrowBackIosNew,
                    iconDescription = stringResource(id = R.string.iconBackMaps),
                    onButtonPressed = onBackPressed
                )
            },
            actions = {
                ActionsTopBar(
                    onSettingsPressed = onSettingsPressed
                )
            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(colorScheme.surface),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            // cambiar el valor de weight en base a si esta seleccionado o no
            ActionBox(
                onClick = onTransportPressed,
                icon = Icons.Outlined.DirectionsBus,
                isSelected = false,
                title = stringResource(id = R.string.lblTransport),
                contentDescription = stringResource(id = R.string.iconBus),
                modifier = Modifier.weight(1f)
            )
            ActionBox(
                onClick = onPlannerPressed,
                icon = Icons.Outlined.LocationOn,
                isSelected = false,
                title = stringResource(id = R.string.lblTrip),
                contentDescription = stringResource(id = R.string.iconLocation),
                modifier = Modifier.weight(1f)
            )
            ActionBox(
                onClick = onFastestPressed,
                icon = Icons.AutoMirrored.Outlined.DirectionsWalk,
                isSelected = true,
                title = stringResource(id = R.string.lblFastest),
                contentDescription = stringResource(id = R.string.iconFootWalking),
                modifier = Modifier.weight(2f)
            )
        }

        HorizontalDivider()
    }
}

@Preview
@Composable
private fun ActionsTopBar(
    onSettingsPressed: () -> Unit = {}
) {
    Row {
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

@Composable
fun ActionBox(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    title: String = "Transporte",
    icon: ImageVector,
    contentDescription: String,
    isSelected: Boolean = false
) {

    val rowColor = if(isSelected) orange else Color.Transparent

    val iconColor = if(isSelected) orange else colorScheme.onSurface

    Box(
        modifier = modifier
            .fillMaxHeight()
            .clickable(onClick = onClick),
    ) {
        Row(
            modifier = Modifier.align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = iconColor
            )

            if(isSelected) {
                Spacer(modifier = Modifier.size(8.dp))

                Text(
                    text = title,
                    style = typography.titleMedium,
                    color = rowColor
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(3.dp)
                .background(rowColor, RoundedCornerShape(topEnd = 12.dp, topStart = 12.dp))
                .align(Alignment.BottomCenter)
        )
    }
}