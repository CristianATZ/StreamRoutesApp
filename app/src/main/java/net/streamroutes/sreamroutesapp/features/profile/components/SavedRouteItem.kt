package net.streamroutes.sreamroutesapp.features.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.core.domain.model.SavedRoute
import net.streamroutes.sreamroutesapp.utils.fullDateFormat
import java.time.LocalDateTime

@Preview(showBackground = true)
@Composable
fun SavedRouteItem(
    route: SavedRoute = SavedRoute(
        name = "Ruta 11 - El charco",
        start = "C. Pipila, Col. Ninos Heroes",
        end = "C. Francisco Marquez, Col. Zona Centro",
        officialSpotsCount = 16,
        saveDate = LocalDateTime.of(2023, 10, 1, 12, 0)
    )
) {
    val onDeletePressed = {

    }

    val saveDate = fullDateFormat(postDateTime = route.saveDate)

    Column(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .background(colorScheme.surfaceContainerHighest)
            .clickable {

            }
    ) {
        Spacer(modifier = Modifier.size(16.dp))

        // nombre, fecha de guardado e icono de eliminar
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = route.name, // Acceso directo
                    style = typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = saveDate,
                    style = typography.labelSmall,
                    modifier = Modifier.graphicsLayer(alpha = 0.5f)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = onDeletePressed) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = stringResource(id = R.string.iconDeletePost)
                )
            }
        }
        
        // informacion de la ruta
        Column(
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.lblOfficialStops, route.officialSpotsCount),
                style = typography.labelLarge,
                modifier = Modifier.padding(bottom = 8.dp).graphicsLayer(alpha = 0.5f)
            )

            Text(
                text = stringResource(id = R.string.lblStartRoute, route.start),
                style = typography.labelLarge,
                modifier = Modifier.padding(bottom = 8.dp).graphicsLayer(alpha = 0.5f)
            )

            Text(
                text = stringResource(id = R.string.lblEndRoute, route.end),
                style = typography.labelLarge,
                modifier = Modifier.padding(bottom = 8.dp).graphicsLayer(alpha = 0.5f)
            )
        }
    }
}