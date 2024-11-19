package net.streamroutes.sreamroutesapp.features.parks.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.QrCode
import androidx.compose.material.icons.outlined.Route
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.core.data.repository.ReservationWithInfo
import net.streamroutes.sreamroutesapp.features.parks.presentation.parks.ParkItem
import net.streamroutes.sreamroutesapp.features.components.ParkingDescription
import net.streamroutes.sreamroutesapp.features.parking.components.InformationChip
import net.streamroutes.sreamroutesapp.features.parkingApp.presentation.home.ParkingViewModel
import net.streamroutes.sreamroutesapp.features.profile.components.LineInformation
import net.streamroutes.sreamroutesapp.features.profile.components.ShimmerHistoryItem
import net.streamroutes.sreamroutesapp.utils.DateUtils.formatTime
import net.streamroutes.sreamroutesapp.utils.DateUtils.fullDateFormat
import net.streamroutes.sreamroutesapp.utils.shimmerEffect
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Composable
fun BookingItem(
    item: ReservationWithInfo,
    onWatchRoute: () -> Unit,
    parkingViewModel: ParkingViewModel
) {
    val currentHour = LocalTime.now()
    var expectedDepartureHour = LocalTime.parse(item.reservation.hour)
    expectedDepartureHour = expectedDepartureHour.plusHours(item.reservation.reservationHours.toLong())

    val duration = Duration.between(currentHour, expectedDepartureHour)
    val leftHours = duration.toHours()
    val leftMinutes = duration.toMinutes() % 60
    val timeDifference = String.format("%02d:%02d", leftHours, leftMinutes)

    // Hora de entrada
    var parsedHour = LocalTime.parse(item.reservation.hour)
    val parkingIn = formatTime(localTime = LocalTime.of(parsedHour.hour, parsedHour.minute))

    // Hora de salida
    parsedHour = LocalTime.parse(item.reservation.hour)
    parsedHour = parsedHour.plusHours(item.reservation.reservationHours.toLong())
    val parkinOut = formatTime(localTime = LocalTime.of(parsedHour.hour, parsedHour.minute))

    var openQr by remember {
        mutableStateOf(false)
    }

    if(openQr) {
        BookingDialogQr(
            item = item,
            onDissmiss = {
                openQr = !openQr
            },
            parkingIn = parkingIn,
            parkingOut = parkinOut
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(colorScheme.surfaceContainer)
    ) {
        // informacion del estacionamiento
        ParkingDescription(
            name = item.place.name,
            price = null,
            address = "${item.place.street}, ${item.place.suburb}, ${item.place.state}"
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.size(8.dp))

            InformationChip(
                text = stringResource(R.string.lblParkingPrice, item.parking.feePerHour),
                color = CardDefaults.outlinedCardColors(
                    containerColor = colorScheme.surfaceVariant,
                    contentColor = colorScheme.onSurfaceVariant
                )
            )

            Spacer(modifier = Modifier.size(8.dp))

            /*
            InformationChip(
                text =
                if(item.isReserved)
                    stringResource(R.string.lblTimeLeft, "07:59")
                else
                    stringResource(R.string.lblCurrentTime, "03.00"),
                color = CardDefaults.outlinedCardColors(
                    containerColor = colorScheme.surfaceVariant,
                    contentColor = colorScheme.onSurfaceVariant
                )
            )
             */

            InformationChip(
                text = stringResource(R.string.lblTimeLeft, timeDifference),
                color = CardDefaults.outlinedCardColors(
                    containerColor = colorScheme.surfaceVariant,
                    contentColor = colorScheme.onSurfaceVariant
                )
            )

            Spacer(modifier = Modifier.size(8.dp))

            InformationChip(
                text = stringResource(R.string.lblTotalBooking, item.parking.feePerHour * item.reservation.reservationHours),
                color = CardDefaults.outlinedCardColors(
                    containerColor = colorScheme.surfaceVariant,
                    contentColor = colorScheme.onSurfaceVariant
                )
            )

            Spacer(modifier = Modifier.size(8.dp))
        }

        Spacer(modifier = Modifier.size(16.dp))

        LineInformation(
            title = stringResource(R.string.lblReference),
            desc = item.reservation.reference,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )

        LineInformation(
            title = stringResource(R.string.lblCategory),
            desc = "Carro",
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )

        /*
        if(item.isReserved) {
            LineInformation(
                title = stringResource(R.string.lblTimeReserved),
                desc = item.timeReserverd,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            )
        }*/

        LineInformation(
            title = stringResource(R.string.lblTimeReserved),
            desc = item.reservation.reservationHours.toString(),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )


        // Mandar fecha completa
        LineInformation(
            title = stringResource(R.string.lblDateIn),
            desc = parkingIn,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )


        LineInformation(
            title = stringResource(R.string.lblDateOut),
            desc = parkinOut,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )


        Spacer(modifier = Modifier.size(8.dp))

        //if (item.isReserved) {
            Row {
                TextButton(
                    onClick = {
                        parkingViewModel.selectReservations(item)
                        Log.d("reservation", parkingViewModel.selectedReservation.value.toString())
                        onWatchRoute()
                    },
                    shape = shapes.small,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Route,
                        contentDescription = stringResource(id = R.string.iconRoute)
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    Text(text = stringResource(id = R.string.btnWathcRoute))
                }

                TextButton(
                    onClick = {
                        openQr = !openQr
                    },
                    shape = shapes.small,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.QrCode,
                        contentDescription = stringResource(id = R.string.iconQrCode)
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    Text(text = stringResource(id = R.string.btnWatchQR))
                }
            }
        //}
    }
}

@Composable
fun ShimmerBookingItem(

) {
    Column {
        ShimmerHistoryItem()

        Spacer(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth()
                .height(25.dp)
                .clip(shapes.small)
                .shimmerEffect()
        )

        Spacer(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth()
                .height(25.dp)
                .clip(shapes.small)
                .shimmerEffect()
        )

        Spacer(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth()
                .height(25.dp)
                .clip(shapes.small)
                .shimmerEffect()
        )
    }
}