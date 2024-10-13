package net.streamroutes.sreamroutesapp.features.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SliderField(
    headerText: String,
    descriptionText: String,
    progress: Float,
    onChangeProgress: (Float) -> Unit
) {
    Column {
        InfoRowField(
            title = headerText,
            descriptionText = descriptionText,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(8.dp))

        Slider(
            value = progress,
            onValueChange = { p ->
                onChangeProgress(p)
            },
            steps = 8,
            valueRange = 1f..10f,
            thumb = {
                Box(
                    modifier = Modifier
                        .background(colorScheme.primary, shape = shapes.extraLarge) // Color y bordes redondeados
                ) {
                    Text(
                        text = progress.toInt().toString(),
                        color = colorScheme.onPrimary,
                        style = typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            },
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}