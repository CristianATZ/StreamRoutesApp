package net.streamroutes.sreamroutesapp.features.components

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R

@Composable
fun SwitchField(
    headerText: String,
    descriptionText: String,
    value: Boolean,
    onValueChange: (Boolean) -> Unit
) {
    val icon = if(!value) Icons.Outlined.LightMode else Icons.Outlined.DarkMode

    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
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
                    contentDescription = stringResource(id = R.string.iconThemeMode)
                )
            },
            colors = SwitchDefaults.colors(
                checkedIconColor = colorScheme.primary
            )
        )
    }
}