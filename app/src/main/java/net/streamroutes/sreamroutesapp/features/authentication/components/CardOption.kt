package net.streamroutes.sreamroutesapp.features.authentication.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun CardOption(
    modifier: Modifier = Modifier,
    text: String = "Texto prueba",
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .shadow(4.dp, shapes.large)
            .clickable {
                onClick()
            }
    ) {
        // remplazar por imagen
        Column(
            modifier = modifier
                .clip(shapes.large)
        ) {

        }

        Column(
            modifier = modifier
                .background(Color.Black.copy(0.25f), shapes.large),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = text,
                style = typography.titleLarge,
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
            )
        }
    }
}