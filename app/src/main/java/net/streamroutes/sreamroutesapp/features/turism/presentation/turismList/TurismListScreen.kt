package net.streamroutes.sreamroutesapp.features.turism.presentation.turismList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
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
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.components.MapAllOptions
import net.streamroutes.sreamroutesapp.features.maps.components.ElementOpion
import net.streamroutes.sreamroutesapp.features.turism.components.TurismModalBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TurismListScreen(
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()

    val onMapPressed = {
        // mostrar mapa con todas las opciones
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

    val closeSheet = {
        scope.launch {
            sheetState.hide()
        }.invokeOnCompletion { isOpen = false }
    }

    if(isOpen) {
        TurismModalBottomSheet(
            sheetState = sheetState,
            onDismiss = {
                closeSheet()
            },
            onSelectRoute = {
                closeSheet()
                // cambiar pagina o lo que sea
            },
            onMore = {

            }
        )
    }

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
                onClick = onMapPressed
            )

            Spacer(modifier = Modifier.size(16.dp))
        }

        items(10) {
            ElementOpion(
                title = "Alhondiga de granaditas",
                description = stringResource(id = R.string.lblTimeNextStop, 7),
                onClick = openBottomSheet
            )

            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}