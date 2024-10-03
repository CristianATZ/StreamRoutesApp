package net.streamroutes.sreamroutesapp.features.profile.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun TrailingIconWithDropdownMenu(
    menuItems: List<String>,
    icon: ImageVector = Icons.Outlined.ArrowDropDown,
    iconDescription: String = "",
    onMenuItemClick: (String) -> Unit
) {
    // Estado para mostrar u ocultar el menú
    var expanded by remember { mutableStateOf(false) }

    Box {
        IconButton(onClick = { expanded = true }) {
            Icon(
                imageVector = icon, // Cambia el ícono si es necesario
                contentDescription = iconDescription
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            menuItems.forEach { menuItem ->
                DropdownMenuItem(
                    text = { Text(menuItem) },
                    onClick = {
                        onMenuItemClick(menuItem)
                        expanded = false // Cerrar el menú al seleccionar
                    }
                )
            }
        }
    }
}