package net.streamroutes.sreamroutesapp.features.profile.presentation.history

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.core.domain.model.History
import net.streamroutes.sreamroutesapp.features.profile.components.HistoryItem
import net.streamroutes.sreamroutesapp.features.profile.components.HistoryModalBottomSheet
import net.streamroutes.sreamroutesapp.features.profile.components.ProfileSmallTopAppBar
import java.time.LocalDateTime
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier
) {

    var isOpen by remember {
        mutableStateOf(false)
    }

    val openBottomSheet = {
        isOpen = !isOpen
    }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    if(isOpen) {
        // bottom sheet para el item de historial
        HistoryModalBottomSheet(
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
            ),
            sheetState = sheetState,
            onDismiss = openBottomSheet
        )
    }

    Scaffold(
        topBar = {
            ProfileSmallTopAppBar(
                title = stringResource(id = R.string.lblHistory),
                onBackPressed = {
                    // REGRESAR A EDITAR PERFIL
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(5) {
                HistoryItem(onClick = openBottomSheet)
            }
        }
    }
}