package net.streamroutes.sreamroutesapp.features.settings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RadioField(
    text: String,            // Texto a mostrar junto al RadioButton
    selected: Boolean,       // Estado del RadioButton (si está seleccionado o no)
    onSelect: () -> Unit     // Función a ejecutar cuando se selecciona el RadioButton
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clickable(
                indication = null,  // Elimina la animación de clic
                interactionSource = remember { MutableInteractionSource() }  // Previene el manejo de estados de interacción
            ) {
                onSelect()  // Ejecuta la acción al hacer clic en toda la fila
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = {
                onSelect()
            }  // Ejecuta la acción al hacer clic en el RadioButton
        )

        Text(
            text = text,
            style = typography.labelLarge,
            modifier = Modifier.padding(start = 8.dp)  // Espacio entre RadioButton y el texto
        )
    }
}