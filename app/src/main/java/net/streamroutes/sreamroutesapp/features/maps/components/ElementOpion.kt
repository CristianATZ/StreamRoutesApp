package net.streamroutes.sreamroutesapp.features.maps.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun ElementOpion(
    // imagen: aqui
    title: String = "Ruta 11 - El charco",
    description: String = "Siguiente parada en 7 minutos",
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clickable(
                indication = null,  // Eliminar efecto ripple
                interactionSource = remember { MutableInteractionSource() }  // Fuente de interacción requerida
            ) {
                onClick()
            }
    ) {
        // CAMBIAR POR IMAGEN
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(colorScheme.surfaceContainerHighest, shapes.large)
        ) {

        }

        Text(
            text = title,
            style = typography.titleLarge
        )
        Text(
            text = description,
            style = typography.bodyMedium,
            modifier = Modifier.graphicsLayer(alpha = 0.5f)
        )
    }
}
