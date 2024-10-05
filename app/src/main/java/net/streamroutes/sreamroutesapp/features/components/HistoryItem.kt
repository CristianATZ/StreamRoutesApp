package net.streamroutes.sreamroutesapp.features.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.example.compose.orange
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.core.domain.model.History
import net.streamroutes.sreamroutesapp.utils.fullDateFormat
import java.time.LocalDateTime
import java.time.LocalTime

@Preview(showBackground = true)
@Composable
fun HistoryItem(
    history: History = History(
        idReference = "ASDF34",
        idParking = "park001",
        totalTime = 2.5,
        totalPrice = 50.0,
        parkingDate = LocalDateTime.of(2024, 10, 1, 10, 0),
        timeIn = LocalTime.of(9,17,0),
        timeOut = LocalTime.of(16,17,0),
        isReserved = false,
        parkingPrice = 39.0,
        parkingName = "ITSUR",
        parkingAddress = "Av. Educacion Superior, 38980"
    ),
    onClick: () -> Unit = {}
) {
    val icon = if(history.isReserved) {
        Pair(Icons.Filled.Bookmark, stringResource(id = R.string.iconBooking))
    } else {
        Pair(Icons.Filled.QrCode, stringResource(id = R.string.iconQrCode))
    }

    val parkingDate = fullDateFormat(postDateTime = history.parkingDate)

    Column(
        modifier = Modifier
            .clickable {
                onClick()
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // icono de reservacion o de escaneo
            Box(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .background(orange.copy(0.25f), shapes.extraLarge)
            ) {
                Icon(
                    imageVector = icon.first,
                    contentDescription = icon.second,
                    tint = orange,
                    modifier = Modifier.padding(8.dp)
                )
            }

            // referencia y fecha
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = history.idReference,
                    style = typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = parkingDate,
                    style = typography.labelSmall,
                    modifier = Modifier.graphicsLayer(alpha = 0.5f)
                )
            }

            // precio total
            Card(
                shape = shapes.extraLarge,
                colors = CardDefaults.cardColors(
                    containerColor = colorScheme.surfaceContainerHighest,
                    contentColor = colorScheme.onSurface
                ),
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(0.4f)
                ) {
                    Text(
                        text = stringResource(id = R.string.lblPrice, history.totalPrice.toString()),
                        style = typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp,
                        modifier = Modifier
                            .padding(4.dp)
                            .align(Alignment.Center)
                    )
                }
            }
        }

        HorizontalDivider()
    }
}