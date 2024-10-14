package net.streamroutes.sreamroutesapp.features.maps.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.RumappAppTheme
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.features.maps.components.MapsModalBottomSheet
import net.streamroutes.sreamroutesapp.features.maps.components.MapsSmallTopAppBar
import net.streamroutes.sreamroutesapp.features.maps.presentation.fastest.FastestScreen
import net.streamroutes.sreamroutesapp.features.maps.presentation.planner.PlannerScreen
import net.streamroutes.sreamroutesapp.features.maps.presentation.transport.MapRouteScreen
import net.streamroutes.sreamroutesapp.features.maps.presentation.transport.MapStopScreen
import net.streamroutes.sreamroutesapp.features.maps.presentation.transport.TransportScreen

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MapsScreen(
    modifier: Modifier = Modifier
) {
    var currentTab by remember {
        mutableIntStateOf(0)
    }

    val onChangeTab = { index: Int ->
        currentTab = index
    }

    val coroutine = rememberCoroutineScope()

    var isOpen by remember {
        mutableStateOf(false)
    }

    val openBottomSheet = {
        isOpen = !isOpen
    }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    val onSaveMapSettings = {
        coroutine.launch {
            sheetState.hide()
        }.invokeOnCompletion {
            if(!sheetState.isVisible) {
                isOpen = false
            }
        }
    }

    if(isOpen) {
        MapsModalBottomSheet(
            sheetState = sheetState,
            onDismiss = openBottomSheet,
            onSave = {
                onSaveMapSettings()
            }
        )
    }

    Scaffold(
        topBar = {
            MapsSmallTopAppBar(
                onSettingsPressed = openBottomSheet,
                currentTab = currentTab,
                onChangeTab = { index ->
                    onChangeTab(index)
                }
            )
        }
    ) { innerPadding ->
        // UTILIZAR UN NAVHOST EN ESTE SCAFFOLD
        // PARA MANEJAR TRANSPORTE, PLANIFICA Y CAMINA
        // DENTRO DE TRANSPORTE MANEJAR OTRO NAVHOST
        // PARA MANEJAR LA RUTA Y EL MAPA COMPLETO

        when(currentTab) {
            0 -> TransportScreen(modifier = Modifier.padding(innerPadding))
            1 -> PlannerScreen(modifier = Modifier.padding(innerPadding))
            2 -> FastestScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}