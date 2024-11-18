package net.streamroutes.sreamroutesapp.features.profile.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.orange
import kotlinx.coroutines.selects.whileSelect
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.core.data.repository.HistoricalParkingWithInfo
import net.streamroutes.sreamroutesapp.core.data.repository.ReservationWithInfo
import net.streamroutes.sreamroutesapp.core.domain.model.History
import net.streamroutes.sreamroutesapp.utils.DateUtils.fullDateFormat
import net.streamroutes.sreamroutesapp.utils.shimmerEffect
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Composable
fun HistoryItem(
    /*
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
    ),*/
    historical: HistoricalParkingWithInfo? = null,
    reservation: ReservationWithInfo? = null,
    onClick: () -> Unit = {}
) {
    val reference = when {
        historical != null -> historical.historicalParking.reference
        reservation != null -> reservation.reservation.reference
        else -> ""
    }

    val parkingDate = when {
        historical != null -> {
            val parsedDate = LocalDate.parse(historical.historicalParking.entranceDate)
            val parsedHour = LocalTime.parse(historical.historicalParking.entranceHour)
            fullDateFormat(postDateTime = LocalDateTime.of(parsedDate, parsedHour))
        }
        reservation != null -> {
            val parsedDate = LocalDate.parse(reservation.reservation.date)
            val parsedHour = LocalTime.parse(reservation.reservation.hour)
            fullDateFormat(postDateTime = LocalDateTime.of(parsedDate, parsedHour))
        }
        else -> "N/A"
    }

    val feePerHour = when {
        historical != null -> historical.historicalParking.feePerHour
        reservation != null -> reservation.parking.feePerHour
        else -> 0.0
    }

    val totalHours = when {
        historical != null -> historical.historicalParking.totalHours
        reservation != null -> reservation.reservation.reservationHours
        else -> 0
    }

    //Log.d("substring", reference.substring(0,3))
    val icon = if(reference.substring(0,3) == "RES") {
        Pair(Icons.Filled.Bookmark, stringResource(id = R.string.iconBooking))
    } else {
        Pair(Icons.Filled.QrCode, stringResource(id = R.string.iconQrCode))
    }


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
                    text = reference,
                    style = typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = parkingDate,
                    style = typography.labelLarge,
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
                    val total = feePerHour * totalHours
                    Text(
                        text = stringResource(id = R.string.lblPrice,  String.format("%.2f", total).toDouble()),
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

@Composable
fun ShimmerHistoryItem(

) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier
                .padding(end = 16.dp)
                .size(50.dp)
                .clip(CircleShape)
                .shimmerEffect()
        )

        Column {
            Spacer(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth(0.25f)
                    .height(25.dp)
                    .clip(shapes.small)
                    .shimmerEffect()
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(25.dp)
                    .clip(shapes.small)
                    .shimmerEffect()
            )
        }

        Spacer(Modifier.weight(1f))
        
        Spacer(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(40.dp)
                .clip(shapes.small)
                .shimmerEffect()
        )
    }
}