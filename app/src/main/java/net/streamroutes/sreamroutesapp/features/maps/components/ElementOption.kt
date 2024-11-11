package net.streamroutes.sreamroutesapp.features.maps.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.orange
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.utils.shimmerEffect

@Preview(showBackground = true)
@Composable
fun ElementOption(
    // imagen: aqui
    title: String = "Ruta 11 - El charco",
    description: String = "Siguiente parada en 7 minutos",
    time: Int = 0,
    price: String? = null,
    calification: String? = null,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clickable(
                indication = null,  // Eliminar efecto ripple
                interactionSource = remember { MutableInteractionSource() }  // Fuente de interacción requerida
            ) {
                onClick()
            }
    ) {
        // CAMBIAR POR IMAGEN
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(colorScheme.surfaceContainerHighest, shapes.large)
        ) {

        }

        Row {
            Column(
                modifier = Modifier.weight(0.8f)
            ) {
                Text(
                    text = title,
                    style = typography.titleLarge
                )
                Text(
                    text = "Siguiente parada en ${time} minutos",
                    style = typography.bodyMedium,
                    modifier = Modifier.graphicsLayer(alpha = 0.5f)
                )
                price?.let {
                    Text(
                        text = stringResource(R.string.lblParkingPrice, it),
                        style = typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = orange
                    )
                }
            }

            calification?.let {
                OutlinedCard(
                    shape = shapes.small,
                    modifier = Modifier
                        .weight(0.2f)
                        .height(50.dp)
                        .padding(top = 4.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = it,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.graphicsLayer(alpha = 0.5f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ShimmerElementOption() {
    Column {
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(150.dp)
                .clip(shapes.small)
                .shimmerEffect()
        )

        Spacer(Modifier.size(8.dp))

        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(40.dp)
                .clip(shapes.small)
                .shimmerEffect()
        )

        Spacer(Modifier.size(8.dp))

        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(20.dp)
                .clip(shapes.small)
                .shimmerEffect()
        )

        Spacer(Modifier.size(16.dp))
    }
}