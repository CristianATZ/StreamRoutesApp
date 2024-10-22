package net.streamroutes.sreamroutesapp.features.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun InfoRowField(
    modifier: Modifier = Modifier,
    title: String,
    descriptionText: String
) {
    Column(
        modifier = modifier
    ) {
        // Texto de encabezado
        Text(
            text = title,
            style = typography.labelLarge
        )

        // Texto de descripción
        Text(
            text = descriptionText,
            style = typography.labelMedium,
            modifier = Modifier.graphicsLayer(alpha = 0.5f)
        )
    }
}