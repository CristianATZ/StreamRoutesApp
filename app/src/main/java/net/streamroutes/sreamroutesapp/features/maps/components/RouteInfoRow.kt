package net.streamroutes.sreamroutesapp.features.maps.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun RouteInfowRow(
    description: String = "Ejemplo texto",
    color: Color = Color.Black
) {
    Row(
        modifier = Modifier.padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = description,
            style = typography.labelLarge,
            modifier = Modifier
                .weight(3f)
                .padding(start = 16.dp)
                .graphicsLayer(alpha = 0.5f)
        )

        Spacer(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .weight(1f)
                .height(10.dp)
                .background(color, shapes.extraLarge)
        )

        //Text(text = stringResource(id = R.string.lblBusTimeToStop, timeToStop))
    }
}