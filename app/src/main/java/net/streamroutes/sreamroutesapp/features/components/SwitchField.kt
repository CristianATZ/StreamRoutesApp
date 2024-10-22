package net.streamroutes.sreamroutesapp.features.components

import android.media.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R

@Composable
fun SwitchField(
    headerText: String,
    descriptionText: String,
    value: Boolean,
    iconTrue: ImageVector,
    iconFalse: ImageVector,
    iconDescription: String,
    onValueChange: (Boolean) -> Unit
) {
    val icon = if(value) iconTrue else iconFalse

    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
            .fillMaxWidth()
            .clickable(
                indication = null,  // Elimina la animación de clic
                interactionSource = remember { MutableInteractionSource() }  // Previene el manejo de estados de interacción
            ) {
                onValueChange(!value)  // Ejecuta la acción al hacer clic en toda la fila
            },
    ) {
        InfoRowField(
            title = headerText,
            descriptionText = descriptionText,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.size(8.dp))

        Switch(
            checked = value,
            onCheckedChange = {
                onValueChange(it)
            },
            thumbContent = {
                Icon(
                    imageVector = icon,
                    contentDescription = iconDescription
                )
            },
            colors = SwitchDefaults.colors(
                checkedIconColor = colorScheme.primary
            )
        )
    }
}