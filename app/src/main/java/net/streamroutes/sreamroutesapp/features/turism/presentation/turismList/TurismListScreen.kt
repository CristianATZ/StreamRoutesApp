package net.streamroutes.sreamroutesapp.features.turism.presentation.turismList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.core.data.repository.TuristicPointWithInfo
import net.streamroutes.sreamroutesapp.features.components.MapAllOptions
import net.streamroutes.sreamroutesapp.features.components.ShimmerMapAllOptions
import net.streamroutes.sreamroutesapp.features.maps.components.ElementOption
import net.streamroutes.sreamroutesapp.features.maps.components.ShimmerElementOption
import net.streamroutes.sreamroutesapp.features.turism.components.TurismModalBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TurismListScreen(
    modifier: Modifier = Modifier,
    onViewMap: () -> Unit,
    onSelectPoint: () -> Unit,
    turismViewModel: TurismListViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val turisticPoints by turismViewModel.turisticPoints.collectAsState()
    var selectedTp by remember { mutableStateOf<TuristicPointWithInfo?>(null) }

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
        TurismModalBottomSheet(
            sheetState = sheetState,
            onDismiss = {
                closeSheet()
            },
            onSelectRoute = {
                closeSheet()
                onSelectPoint()
            },
            onMore = {

            },
            turisticPoint = selectedTp
        )
    }


    if(turisticPoints.isNullOrEmpty()){
        ShimmerTurismScreen(modifier)
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // frase de alfredo
            item {
                OutlinedCard(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.lblJoseAlfredo),
                            style = typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.size(8.dp))

                        Text(
                            text = stringResource(id = R.string.lblJoseAlfredoName),
                            style = typography.labelMedium,
                            modifier = Modifier.align(Alignment.End)
                        )
                    }
                }
            }

            // opcion para el mapa
            item {
                MapAllOptions(
                    onClick = onViewMap
                )

                Spacer(modifier = Modifier.size(16.dp))
            }


            items(turisticPoints ?: emptyList()) { turisticPoint ->
                ElementOption(
                    title = turisticPoint.place.name,
                    description = stringResource(id = R.string.lblTimeNextStop, 7),
                    //onClick = openBottomSheet
                    onClick = {
                        selectedTp = turisticPoint
                        isOpen = !isOpen
                    }
                )

                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }


}



@Composable
fun ShimmerTurismScreen(
    modifier: Modifier
) {
    Column(
        modifier = modifier
    ) {
        ShimmerMapAllOptions()

        repeat(3) {
            ShimmerElementOption()
        }
    }
}