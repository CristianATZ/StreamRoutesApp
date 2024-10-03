package net.streamroutes.sreamroutesapp.features.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun NavigationButton(
    onButtonPressed: () -> Unit = {},
    icon: ImageVector = Icons.Outlined.Menu,
    iconDescription: String = "Icon Description"
) {
    IconButton(
        onClick = onButtonPressed
    ) {
        Icon(
            imageVector = icon,
            contentDescription = iconDescription
        )
    }
}