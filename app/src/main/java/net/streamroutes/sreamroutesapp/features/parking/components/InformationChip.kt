package net.streamroutes.sreamroutesapp.features.parking.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun InformationChip(
    text: String,
    color: CardColors? = null
) {
    Row {
        OutlinedCard(
            colors = color ?: CardDefaults.outlinedCardColors()
        ) {
            Text(
                text = text,
                style = typography.labelLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(8.dp)
                    .graphicsLayer(alpha = 0.5f)
            )
        }

        Spacer(Modifier.size(8.dp))
    }
}