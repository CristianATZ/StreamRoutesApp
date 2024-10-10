package net.streamroutes.sreamroutesapp.features.maps.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.MyLocation
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R

@Composable
fun PlannerFloatingButtons(
    onMyLocation: () -> Unit,
    onOpenList: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center,
    ) {
        // cambiar camara a mi ubicacion actual
        SmallFloatingActionButton(
            onClick = onMyLocation,
            shape = shapes.medium,
            containerColor = colorScheme.tertiaryContainer,
            contentColor = colorScheme.onTertiaryContainer
        ) {
            Icon(
                imageVector = Icons.Outlined.MyLocation,
                contentDescription = stringResource(id = R.string.iconMyLocation),
                modifier = Modifier.padding(12.dp)
            )
        }

        Spacer(modifier = Modifier.size(8.dp))

        // ver lista
        ExtendedFloatingActionButton(
            onClick = onOpenList,
            shape = shapes.medium,
        ) {
            Icon(
                imageVector = Icons.Outlined.Menu,
                contentDescription = stringResource(id = R.string.iconMenuPlanner)
            )

            Spacer(modifier = Modifier.size(8.dp))

            Text(text = stringResource(id = R.string.lblMyList))
        }
    }
}