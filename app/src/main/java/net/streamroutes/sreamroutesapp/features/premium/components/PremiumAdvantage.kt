package net.streamroutes.sreamroutesapp.features.premium.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.features.premium.presentation.Advantage

@Preview(showBackground = true)
@Composable
fun PremiumAdvantage(
    modifier: Modifier = Modifier,
    advantage: Advantage = Advantage("Titulo", "Texto descriptivo")
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {

        Text(
            text = advantage.title,
            style = typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = advantage.description,
            style = typography.labelMedium,
            modifier = Modifier.graphicsLayer(alpha = 0.5f)
        )

        Spacer(modifier = Modifier.size(16.dp))
    }
}