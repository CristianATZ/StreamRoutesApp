package net.streamroutes.sreamroutesapp.features.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColorField(
    headerText: String,
    descriptionText: String,
    colorTransport: Color,
    onOpenPickerColor: () -> Unit
) {
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

        // Caja con color para representar la línea de transporte
        Box(
            modifier = Modifier
                .width(100.dp)
                .height(50.dp)
                .background(colorTransport, shapes.medium)
                .clickable {
                    onOpenPickerColor()
                }
        )
    }
}