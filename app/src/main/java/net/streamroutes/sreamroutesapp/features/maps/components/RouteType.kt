package net.streamroutes.sreamroutesapp.features.maps.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.compose.orange
import com.example.compose.primary

@Composable
fun RouteType(
    isSelected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector,
    iconDescription: String,
    modifier: Modifier = Modifier,
) {
    // Animar el color de fondo
    val containerColor by animateColorAsState(
        targetValue = if (isSelected) orange else colorScheme.surface,
        animationSpec = tween(1000),
        label = "" // Cambia el color según isSelected
    )

    // Animar el color del contenido
    val contentColor by animateColorAsState(
        targetValue = if (isSelected) primary else colorScheme.onSurface,
        animationSpec = tween(1000),
        label = ""
    )

    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        modifier = modifier
    ) {
        Box(
            modifier = Modifier.padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = icon, contentDescription = iconDescription)
        }
    }
}