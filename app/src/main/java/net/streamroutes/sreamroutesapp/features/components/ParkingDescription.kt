package net.streamroutes.sreamroutesapp.features.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.green_scheme
import com.example.compose.orange
import net.streamroutes.sreamroutesapp.R

@Preview(showBackground = true)
@Composable
fun ParkingDescription(
    name: String = "Itsur",
    address: String = "Av. Educacion Superior, 38980",
    price: Double = 39.0,

) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // icono de QR
        Box(
            modifier = Modifier
                .padding(16.dp)
                .background(orange.copy(0.25f), shapes.extraLarge)
        ) {
            Icon(
                imageVector = Icons.Filled.QrCode,
                contentDescription = stringResource(id = R.string.iconQrCode),
                tint = orange,
                modifier = Modifier.padding(8.dp)
            )
        }

        // referencia y fecha
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = name,
                style = typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = address,
                style = typography.labelSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.graphicsLayer(alpha = 0.5f)
            )
        }

        // precio por hora
        Card(
            shape = shapes.extraLarge,
            colors = CardDefaults.cardColors(
                containerColor = green_scheme.color,
                contentColor = green_scheme.onColor
            ),
            modifier = Modifier.padding(end = 16.dp)
        ) {
            Box {
                Text(
                    text = stringResource(id = R.string.lblPricePerHour, price.toString()),
                    style = typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}