package net.streamroutes.sreamroutesapp.features.maps.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.core.domain.model.Destinations

@Composable
fun DestinationItem(
    destination: Destinations,
    onMoveItemUp: () -> Unit,
    onMoveItemDown: () -> Unit,
    onRemoveItem: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = destination.address,
                style = typography.labelLarge,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            )

            FilledIconButton(
                onClick = onMoveItemUp
            ) {
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowUp,
                    contentDescription = stringResource(id = R.string.iconUpItem)
                )
            }

            FilledIconButton(
                onClick = onMoveItemDown
            ) {
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowDown,
                    contentDescription = stringResource(id = R.string.iconDownItem)
                )
            }

            FilledIconButton(
                onClick = onRemoveItem,
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = colorScheme.error,
                    contentColor = colorScheme.onError
                )
            ) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = stringResource(id = R.string.iconDeleteItem)
                )
            }
        }
    }
}