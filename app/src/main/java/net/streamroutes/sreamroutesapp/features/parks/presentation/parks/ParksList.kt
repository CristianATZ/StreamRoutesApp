package net.streamroutes.sreamroutesapp.features.parks.presentation.parks

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import net.streamroutes.sreamroutesapp.core.data.repository.ReservationWithInfo
import net.streamroutes.sreamroutesapp.features.parkingApp.presentation.home.ParkingViewModel
import net.streamroutes.sreamroutesapp.features.parks.components.BookingItem

@Composable
fun ParksList(
    reservations: List<ReservationWithInfo>,
    onWatchRoute: () -> Unit,
    parkingViewModel: ParkingViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        reservations.forEach { item ->
            BookingItem(
                item = item,
                onWatchRoute = onWatchRoute,
                parkingViewModel = parkingViewModel
            )
        }
    }
}