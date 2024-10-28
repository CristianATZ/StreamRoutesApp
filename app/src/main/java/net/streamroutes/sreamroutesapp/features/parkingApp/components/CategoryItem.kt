package net.streamroutes.sreamroutesapp.features.parkingApp.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.compose.orange
import com.example.compose.primary

@Composable
fun CategoryItem(
    icon: ImageVector,
    selected: Boolean,
    label: String,
    onSelectCategory: () -> Unit
) {
    val containerColor by animateColorAsState(
        targetValue = if (selected) orange else colorScheme.secondary,
        animationSpec = tween(500),
        label = "" // Cambia el color según isSelected
    )
    val iconColor by animateColorAsState(
        targetValue = if (selected) primary else colorScheme.onSecondary,
        animationSpec = tween(500),
        label = "" // Cambia el color según isSelected
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(75.dp)
                .background(containerColor, shapes.large)
                .clickable {
                    onSelectCategory()
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor
            )
        }

        Text(text = label, style = typography.labelLarge)
    }
}