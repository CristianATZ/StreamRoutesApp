package net.streamroutes.sreamroutesapp.features.maps.presentation.transport

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.authentication.components.MapAllOptions
import net.streamroutes.sreamroutesapp.features.maps.components.TransportModalBottomSheet
import net.streamroutes.sreamroutesapp.features.maps.components.TransportOption

enum class TransportFilter {
    ALL, ONE_WAY, RETURN
}

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransportScreen(
    modifier: Modifier = Modifier
) {
    // Obtiene el controlador del teclado
    val keyboardController = LocalSoftwareKeyboardController.current

    var query by remember {
        mutableStateOf("")
    }

    val onSearch = {
        keyboardController?.hide()
    }


    var filterStatus by remember {
        mutableStateOf(TransportFilter.ALL)
    }

    fun updateFilter(filter: TransportFilter) {
        filterStatus = filter
    }


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
        TransportModalBottomSheet(
            sheetState = sheetState,
            onDismiss = openBottomSheet,
            onDownloadRoute = {},
            onSelectRoute = {}
        )
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // barra de busqueda
        item {
            // CAMBIAR COLORES
            SearchBar(
                query = query,
                onQueryChange = { query = it },
                onSearch = {
                    query = it
                    onSearch()
                },
                active = false,
                onActiveChange = {

                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Menu,
                        contentDescription = stringResource(id = R.string.iconMenu)
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            onSearch()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = stringResource(id = R.string.iconSearch)
                        )
                    }
                },
                placeholder = {
                    Text(text = stringResource(id = R.string.lblFilterRoute))
                },
                shadowElevation = 4.dp,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(0.9f)
            ) {

            }
        }

        // filtros
        item {
            Row(
                modifier = Modifier.fillMaxWidth(0.9f)
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
            MapAllOptions()

            Spacer(modifier = Modifier.size(32.dp))
        }

        // estacionamientos
        items(10) {
            TransportOption(
                onClick = openBottomSheet,
            )
        }
    }
}
