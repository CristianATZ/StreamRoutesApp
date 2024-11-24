package net.streamroutes.sreamroutesapp.features.profile.presentation.history

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.parkingApp.presentation.home.ParkingViewModel
import net.streamroutes.sreamroutesapp.features.profile.components.HistoryItem
import net.streamroutes.sreamroutesapp.features.profile.components.HistoryModalBottomSheet
import net.streamroutes.sreamroutesapp.features.profile.components.ProfileSmallTopAppBar
import net.streamroutes.sreamroutesapp.features.profile.components.ShimmerHistoryItem
import java.time.LocalDateTime
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    onBackPressed: () -> Unit,
    parkingViewModel: ParkingViewModel = hiltViewModel()
) {
    val historicalParking by parkingViewModel.historicalParking.collectAsState()
    val selectedHistorical by parkingViewModel.selectedHistorical.collectAsState()

    val reservations by parkingViewModel.reservations.collectAsState()
    val selectedReservation by parkingViewModel.selectedReservation.collectAsState()

    val coroutine = rememberCoroutineScope()

    var isLoading by remember {
        mutableStateOf(true)
    }

    var isReservation by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        coroutine.launch {
            parkingViewModel.getHistoricalParkingByUser()
            parkingViewModel.getReservationsByUser()
            isLoading = false
        }
    }


    val scope = rememberCoroutineScope()

    var isOpen by remember {
        mutableStateOf(false)
    }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val closeSheet = {
        scope.launch {
            sheetState.hide()
        }.invokeOnCompletion {
            if(!sheetState.isVisible) {
                isOpen = false
            }
        }
    }

    if(isOpen) {
        // bottom sheet para el item de historial
        if(isReservation){
            HistoryModalBottomSheet(
                /*
                    history = History(
                        idReference = "ASDF34",
                        idParking = "park001",
                        totalTime = 2.5,
                        totalPrice = 50.0,
                        parkingDate = LocalDateTime.of(2024, 10, 1, 10, 0),
                        timeIn = LocalTime.of(6,17,0),
                        timeOut = LocalTime.of(9,17,0),
                        isReserved = false,
                        parkingPrice = 39.00,
                        parkingName = "ITSUR",
                        parkingAddress = "Av. Educacion Superior, 38980"
                    ),*/
                reservation = selectedReservation,
                sheetState = sheetState,
                onDismiss = {
                    closeSheet()
                }
            )
        } else {
            HistoryModalBottomSheet(
                /*
                    history = History(
                        idReference = "ASDF34",
                        idParking = "park001",
                        totalTime = 2.5,
                        totalPrice = 50.0,
                        parkingDate = LocalDateTime.of(2024, 10, 1, 10, 0),
                        timeIn = LocalTime.of(6,17,0),
                        timeOut = LocalTime.of(9,17,0),
                        isReserved = false,
                        parkingPrice = 39.00,
                        parkingName = "ITSUR",
                        parkingAddress = "Av. Educacion Superior, 38980"
                    ),*/
                historical = selectedHistorical,
                sheetState = sheetState,
                onDismiss = {
                    closeSheet()
                }
            )
        }
    }

    Scaffold(
        topBar = {
            ProfileSmallTopAppBar(
                title = stringResource(id = R.string.lblHistory),
                onBackPressed = onBackPressed
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(!isLoading && reservations!=null){
                //Log.d("history_sceen", reservations.toString())
                items(reservations!!) { reservation ->
                    HistoryItem(
                        onClick = {
                            parkingViewModel.selectReservations(reservation)
                            isOpen = !isOpen
                            isReservation = true
                        },
                        reservation = reservation
                    )
                }
            }
            if(!isLoading && historicalParking!=null) {
                //Log.d("history_screen", historicalParking?.toString() ?: "nada")
                items(historicalParking!!) { historical ->
                    HistoryItem(
                        onClick = {
                            parkingViewModel.selectHistoricalParking(historical)
                            isOpen = !isOpen
                            isReservation = false
                        },
                        historical = historical
                    )
                }
            } else {
                items(5) {
                    ShimmerHistoryItem()
                }
            }
        }
    }
}