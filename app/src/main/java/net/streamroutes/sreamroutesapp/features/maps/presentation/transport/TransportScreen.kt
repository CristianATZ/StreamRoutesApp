package net.streamroutes.sreamroutesapp.features.maps.presentation.transport

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.core.data.repository.RouteWithPlaces
import net.streamroutes.sreamroutesapp.features.components.MapAllOptions
import net.streamroutes.sreamroutesapp.features.components.ShimmerMapAllOptions
import net.streamroutes.sreamroutesapp.features.maps.components.ElementOption
import net.streamroutes.sreamroutesapp.features.maps.components.ShimmerElementOption
import net.streamroutes.sreamroutesapp.features.maps.components.TransportModalBottomSheet

enum class TransportFilter {
    ALL, ONE_WAY, RETURN
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransportScreen(
    modifier: Modifier = Modifier,
    transportViewModel: TransportViewModel = hiltViewModel(),
    onSelectRoute: () -> Unit,
    onSelectMap: () -> Unit
) {
    val scope = rememberCoroutineScope()

    // Obtiene el controlador del teclado
    val routes by transportViewModel.routes.collectAsState()
    //var selectedRoute by remember { mutableStateOf<RouteWithPlaces?>(null) }
    val selectedRoute by transportViewModel.selectedRoute.collectAsState()


    var filterStatus by remember {
        mutableStateOf(TransportFilter.ALL)
    }

    fun updateFilter(filter: TransportFilter) {
        filterStatus = filter
    }

    var isOpen by remember {
        mutableStateOf(false)
    }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val closeSheet = { select: Boolean ->
        scope.launch {
            sheetState.hide()
        }.invokeOnCompletion {
            if(!sheetState.isVisible) {
                isOpen = false
            }
            if(select) {
                onSelectRoute()
            }
        }
    }

    if(isOpen) {
        TransportModalBottomSheet(
            sheetState = sheetState,
            onDismiss = {
                closeSheet(false)
            },
            onDownloadRoute = {

            },
            onSelectRoute = {
                closeSheet(true)
            },
            selectedRoute = selectedRoute
        )
    }

    if(routes.isNullOrEmpty()){
        ShimmerTransportScreen(modifier)
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // filtros
            item {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                ) {
                    FilterChip(
                        selected = filterStatus == TransportFilter.ALL,
                        onClick = {
                            updateFilter(TransportFilter.ALL)
                        },
                        label = {
                            Text(text = stringResource(id = R.string.lblFilterAll))
                        }
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    FilterChip(
                        selected = filterStatus == TransportFilter.ONE_WAY,
                        onClick = {
                            updateFilter(TransportFilter.ONE_WAY)
                        },
                        label = {
                            Text(text = stringResource(id = R.string.lblFilterOneWay))
                        }
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    FilterChip(
                        selected = filterStatus == TransportFilter.RETURN,
                        onClick = {
                            updateFilter(TransportFilter.RETURN)
                        },
                        label = {
                            Text(text = stringResource(id = R.string.lblFilterReturn))
                        }
                    )
                }

                Spacer(modifier = Modifier.size(16.dp))
            }

            // ver mapa
            item {
                // cambiar por imagen
                MapAllOptions(
                    onClick = onSelectMap
                )

                Spacer(modifier = Modifier.size(32.dp))
            }

            // Rutas de transporte
            items(routes ?: emptyList()) { route ->
                ElementOption(
                    onClick = {
                        //openBottomSheet
                        transportViewModel.selectRoute(route)
                        isOpen = !isOpen
                    },
                    title = route.route.name,
                    time = route.route.arriveTime
                )
                Spacer(Modifier.size(16.dp))
            }
        }
    }


}

@Composable
fun ShimmerTransportScreen(
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