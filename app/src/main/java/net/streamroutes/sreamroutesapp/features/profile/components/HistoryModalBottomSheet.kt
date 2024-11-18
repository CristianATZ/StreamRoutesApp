package net.streamroutes.sreamroutesapp.features.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.core.data.repository.HistoricalParkingWithInfo
import net.streamroutes.sreamroutesapp.core.data.repository.ReservationWithInfo
import net.streamroutes.sreamroutesapp.core.domain.model.History
import net.streamroutes.sreamroutesapp.features.components.ParkingDescription
import net.streamroutes.sreamroutesapp.utils.DateUtils.formatTime
import net.streamroutes.sreamroutesapp.utils.DateUtils.fullDateFormat
import net.streamroutes.sreamroutesapp.utils.shimmerEffect
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryModalBottomSheet(
    sheetState: SheetState = rememberModalBottomSheetState(),
    onDismiss: () -> Unit = {},
    onBillClicked: () -> Unit = {},
    //history: History
    historical: HistoricalParkingWithInfo? = null,
    reservation: ReservationWithInfo? = null,
) {
    // CARGAR LA INFORMACION DEL ITEM
    // EN LUGAR DE PASAR EL HISTORIAL ITEM
    var isLoading by remember {
        mutableStateOf(false)
    }

    val reference = when {
        historical != null -> historical.historicalParking.reference
        reservation != null -> reservation.reservation.reference
        else -> ""
    }

    val parkingIn = when {
        historical != null -> {
            var parsedHour = LocalTime.parse(historical.historicalParking.entranceHour)
            formatTime(localTime = LocalTime.of(parsedHour.hour, parsedHour.minute))
        }
        reservation != null -> {
            var parsedHour = LocalTime.parse(reservation.reservation.hour)
            formatTime(localTime = LocalTime.of(parsedHour.hour, parsedHour.minute))
        }
        else -> "N/A"
    }

    val parkingOut = when {
        historical != null -> {
            var parsedHour = LocalTime.parse(historical.historicalParking.departureHour)
            formatTime(localTime = LocalTime.of(parsedHour.hour, parsedHour.minute))
        }
        reservation != null -> {
            var parsedHour = LocalTime.parse(reservation.reservation.hour)
            parsedHour = parsedHour.plusHours(reservation.reservation.reservationHours.toLong())
            formatTime(localTime = LocalTime.of(parsedHour.hour, parsedHour.minute)) + " (aprox)."
        }
        else -> "N/A"
    }

    val street = when {
        historical != null -> historical.place.street
        reservation != null -> reservation.place.street
        else -> ""
    }

    val suburb = when {
        historical != null -> historical.place.suburb
        reservation != null -> reservation.place.suburb
        else -> ""
    }

    val state = when {
        historical != null -> historical.place.state
        reservation != null -> reservation.place.state
        else -> ""
    }

    val name = when {
        historical != null -> historical.place.name
        reservation != null -> reservation.place.name
        else -> ""
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

    /*
    val parkingDate = when {
            val parsedDate = LocalDate.parse(historical.historicalParking.entranceDate)
            val parsedHour = LocalTime.parse(historical.historicalParking.entranceHour)
            val parkingDate = fullDateFormat(postDateTime = LocalDateTime.of(parsedDate, parsedHour))
    }
     */

    //var parsedHour = LocalTime.parse(historical.historicalParking.entranceHour)
    //val parkingIn = formatTime(localTime = LocalTime.of(parsedHour.hour, parsedHour.minute))

    //parsedHour = LocalTime.parse(historical.historicalParking.departureHour)
    //val parkingOut = formatTime(localTime = LocalTime.of(parsedHour.hour, parsedHour.minute))

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismiss,
        //modifier = Modifier.fillMaxHeight(0.8f)
    ) {
        Column(
            modifier = Modifier
                .navigationBarsPadding(),
            //.windowInsetsPadding(WindowInsets.navigationBars)
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // referencia de transaccion
            Text(
                text = reference,
                style = typography.headlineSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // CAMBIAR VALORES AL ABRIR
            if(!isLoading) {
                val address = "${street}, ${suburb}, ${state}"
                ParkingDescription(
                    name = name,
                    address = address,
                    price = feePerHour
                )
            } else {

                ShimmerHistoryItem()
            }

            // RESERVADO
            if(reservation != null) {
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .background(colorScheme.inverseSurface, shapes.small)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.lblReserved),
                        style = typography.displayMedium,
                        color = colorScheme.inverseOnSurface,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(vertical = 8.dp)
                    )
                }
            }

            // total y cantidad de horas
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = colorScheme.tertiaryContainer,
                    contentColor = colorScheme.onTertiaryContainer
                ),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    val total = feePerHour * totalHours
                    Text(
                        text = stringResource(id = R.string.lblPrice, String.format("%.2f", total).toDouble()),
                        style = typography.displayMedium
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    if(!isLoading) {
                        Text(
                            text = stringResource(id = R.string.lblTimeHours, totalHours),
                            style = typography.bodyLarge
                        )
                    } else {
                        Spacer(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .fillMaxWidth(0.5f)
                                .height(25.dp)
                                .clip(shapes.small)
                                .shimmerEffect()
                        )
                    }
                }
            }

            // fecha de transaccion, entrada y salida
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.lblParkinDetails),
                    style = typography.titleLarge,
                    modifier = Modifier.graphicsLayer(alpha = 0.5f)
                )

                /*
                val parsedDate = LocalDate.parse(historical.historicalParking.entranceDate)
                val parsedHour = LocalTime.parse(historical.historicalParking.entranceHour)
                val parkingDate = fullDateFormat(postDateTime = LocalDateTime.of(parsedDate, parsedHour))
                 */

                LineInformation(
                    title = stringResource(id = R.string.lblParkingDate),
                    desc = parkingDate,
                    modifier = Modifier.padding(bottom = 8.dp, top = 16.dp)
                )

                if(!isLoading) {
                    LineInformation(
                        title = stringResource(id = R.string.lblParkingIn),
                        desc = parkingIn,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    LineInformation(
                        title = stringResource(id = R.string.lblParkingOut),
                        desc = parkingOut,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                } else {
                    Spacer(
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                            .fillMaxWidth()
                            .height(25.dp)
                            .clip(shapes.small)
                            .shimmerEffect()
                    )

                    Spacer(
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                            .fillMaxWidth()
                            .height(25.dp)
                            .clip(shapes.small)
                            .shimmerEffect()
                    )
                }
            }

            // facturar transaccion
            OutlinedButton(
                onClick = onDismiss,
                shape = shapes.small,
                modifier = Modifier
                    .padding(horizontal = 16.dp,)
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.btnBack))
            }

            // regresar
            Button(
                onClick = onBillClicked,
                shape = shapes.small,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.btnBill))
            }

            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
fun LineInformation(
    modifier: Modifier = Modifier,
    title: String,
    desc: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(text = title, style = typography.labelLarge)

        Spacer(modifier = Modifier.weight(1f))

        Text(text = desc, style = typography.labelLarge, fontWeight = FontWeight.Bold)
    }
}
