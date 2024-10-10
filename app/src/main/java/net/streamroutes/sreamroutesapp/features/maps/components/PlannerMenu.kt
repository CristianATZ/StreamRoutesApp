package net.streamroutes.sreamroutesapp.features.maps.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MyLocation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R

@Preview(showBackground = true)
@Composable
fun PlannerMenu(
    modifier: Modifier = Modifier,
    onSelectDestination: () -> Unit = {},
    onOpenList: () -> Unit = {},
    currentAddress: String = "Padre Luis Gaytan, San Isidro 38887"
) {
    ElevatedCard(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.MyLocation,
                contentDescription = stringResource(id = R.string.iconMyLocation),
                modifier = Modifier
                    .padding(start = 16.dp)
            )

            Text(
                text = currentAddress,
                style = typography.titleLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(weight = 1f)
                    .padding(16.dp)
            )
        }
    }
}