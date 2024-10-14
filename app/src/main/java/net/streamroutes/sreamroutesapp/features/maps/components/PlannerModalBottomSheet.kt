package net.streamroutes.sreamroutesapp.features.maps.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.LatLng
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.core.domain.model.Destinations

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlannerModalBottomSheet(
    destinationsList: List<Destinations> = listOf(
        Destinations(
            address = "C. Padre Luis Gaytan, Col. San Isidro",
            coords = LatLng(20.126856880277188, -101.19127471960047)
        )
    ),
    sheetState: SheetState = rememberModalBottomSheetState(),
    onDismiss: () -> Unit = {},
    onCalculateRoute: () -> Unit = {},
    onRemoveItem: (Int) -> Unit,
    onMoveItemUp: (Int) -> Unit,
    onMoveItemDown: (Int) -> Unit
) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier
                .animateContentSize()
                .navigationBarsPadding()
        ) {

            AnimatedVisibility(
                visible = destinationsList.isEmpty(),
                enter = slideInVertically(
                    initialOffsetY = { -it } // animacion desde arriba
                )
            ) {
                Column {
                    Text(
                        text = stringResource(id = R.string.lblNoDestinations),
                        style = typography.displayLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.graphicsLayer(alpha = 0.5f)
                    )
                }
            }

            AnimatedVisibility(
                visible = destinationsList.isNotEmpty(),
                exit = slideOutVertically(
                    targetOffsetY = { it } // animacion hacia abajo
                )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.lblDestinationsSelected),
                        style = typography.headlineSmall
                    )

                    Spacer(modifier = Modifier.size(16.dp))

                    LazyColumn(
                        modifier = Modifier.fillMaxWidth().animateContentSize(),
                        contentPadding = PaddingValues(horizontal = 16.dp)
                    ) {
                        items(destinationsList.size) { index ->
                            DestinationItem(
                                destination = destinationsList[index],
                                onMoveItemUp = {
                                    onMoveItemUp(index)
                                },
                                onMoveItemDown = {
                                    onMoveItemDown(index)
                                },
                                onRemoveItem = {
                                    onRemoveItem(index)
                                }
                            )

                            Spacer(modifier = Modifier.size(16.dp))
                        }
                    }

                    // cerrar
                    Button(
                        onClick = onCalculateRoute,
                        shape = shapes.small,
                        modifier = Modifier
                            .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth()
                    ) {
                        Text(text = stringResource(id = R.string.btnCalculate))
                    }
                }
            }
        }
    }
}
