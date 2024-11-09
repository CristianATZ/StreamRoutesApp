package net.streamroutes.sreamroutesapp.features.maps.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.maps.components.MapsModalBottomSheet
import net.streamroutes.sreamroutesapp.features.maps.components.MapsSmallTopAppBar
import net.streamroutes.sreamroutesapp.features.maps.presentation.fastest.FastestScreen
import net.streamroutes.sreamroutesapp.features.maps.presentation.planner.PlannerScreen
import net.streamroutes.sreamroutesapp.features.maps.presentation.transport.MapsTransportMainScreen
import net.streamroutes.sreamroutesapp.features.maps.presentation.transport.TransportViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapsScreen(
    transportViewModel: TransportViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    // namas pa usar ya luego tu lo modificas xd
    val transport by transportViewModel.routes.collectAsState()

    var currentTab by remember {
        mutableIntStateOf(0)
    }

    val onChangeTab = { index: Int ->
        currentTab = index
    }

    val scope = rememberCoroutineScope()

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
        scope.launch {
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
                title = stringResource(R.string.lblMaps),
                onSettingsPressed = openBottomSheet,
                currentTab = currentTab,
                onChangeTab = { index ->
                    onChangeTab(index)
                },
                onBackPressed = onBackPressed
            )
        }
    ) { innerPadding ->
        when(currentTab) {
            0 -> {
                MapsTransportMainScreen(
                    modifier = Modifier.padding(innerPadding)
                )
            }
            1 -> {
                PlannerScreen(modifier = Modifier.padding(innerPadding))
            }
            2 -> {
                FastestScreen(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}
