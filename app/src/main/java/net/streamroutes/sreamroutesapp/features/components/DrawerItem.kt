package net.streamroutes.sreamroutesapp.features.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.compose.orange

@Composable
fun DrawerItem(
    modifier: Modifier = Modifier,
    label: String = "Ejemplo",
    icon: ImageVector = Icons.Outlined.Home,
    iconDescription: String = "Ejemplo",
    selected: Boolean = false,
    logout: Boolean = false,
    onClick: () -> Unit = {}
) {

    val colors = if(!logout) {
        NavigationDrawerItemDefaults.colors(
            selectedContainerColor = orange.copy(0.25f),
            selectedTextColor = orange,
            selectedIconColor = orange,
        )
    } else {
        NavigationDrawerItemDefaults.colors(
            selectedContainerColor = colorScheme.errorContainer,
            selectedTextColor = colorScheme.onErrorContainer,
            selectedIconColor = colorScheme.onErrorContainer,
        )
    }

    NavigationDrawerItem(
        label = {
            Text(
                text = label,
                fontWeight = FontWeight.Bold
            )
        },
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = iconDescription
            )
        },
        selected = selected,
        shape = shapes.small,
        onClick = onClick,
        colors = colors,
        modifier = modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth(0.9f)
            .height(50.dp)
    )
}