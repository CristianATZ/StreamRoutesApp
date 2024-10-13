package net.streamroutes.sreamroutesapp.features.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.core.domain.model.History
import net.streamroutes.sreamroutesapp.features.components.ParkingDescription
import net.streamroutes.sreamroutesapp.utils.formatTime
import net.streamroutes.sreamroutesapp.utils.fullDateFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryModalBottomSheet(
    sheetState: SheetState = rememberModalBottomSheetState(),
    onDismiss: () -> Unit = {},
    onBillClicked: () -> Unit = {},
    history: History
) {
    // CARGAR LA INFORMACION DEL ITEM
    // EN LUGAR DE PASAR EL HISTORIAL ITEM

    val historyDate = fullDateFormat(postDateTime = history.parkingDate)

    val parkingIn = formatTime(localTime = history.timeIn)
    val parkingOut = formatTime(localTime = history.timeOut)

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismiss,
        //modifier = Modifier.fillMaxHeight(0.8f)
    ) {
        Column(
            modifier = Modifier
                .navigationBarsPadding(),
            //.windowInsetsPadding(WindowInsets.navigationBars)
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // referencia de transaccion
            Text(
                text = history.idReference,
                style = typography.headlineSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // CAMBIAR VALORES AL ABRIR
            ParkingDescription(
                name = history.parkingName,
                address = history.parkingAddress,
                price = history.parkingPrice
            )

            // RESERVADO
            if(history.isReserved) {
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .background(colorScheme.inverseSurface, shapes.small)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.lblBooking),
                        style = typography.displayMedium,
                        color = colorScheme.inverseOnSurface,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(vertical = 8.dp)
                    )
                }
            }

            // total y cantidad de horas
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = colorScheme.surfaceContainerHighest,
                    contentColor = colorScheme.onSurface
                ),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.lblPrice, history.totalPrice),
                        style = typography.displayMedium
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    Text(
                        text = stringResource(id = R.string.lblTimeHours, history.totalTime),
                        style = typography.bodyLarge
                    )
                }
            }

            // fecha de transaccion, entrada y salida
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.lblParkinDetails),
                    style = typography.titleLarge,
                    modifier = Modifier.graphicsLayer(alpha = 0.5f)
                )

                LineInformation(
                    title = stringResource(id = R.string.lblParkingDate),
                    desc = historyDate,
                    modifier = Modifier.padding(bottom = 8.dp, top = 16.dp)
                )

                LineInformation(
                    title = stringResource(id = R.string.lblParkingIn),
                    desc = parkingIn,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                LineInformation(
                    title = stringResource(id = R.string.lblParkingOut),
                    desc = parkingOut,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            // facturar transaccion
            OutlinedButton(
                onClick = onBillClicked,
                shape = shapes.small,
                modifier = Modifier
                    .padding(horizontal = 16.dp,)
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.btnBill))
            }

            // regresar
            Button(
                onClick = onDismiss,
                shape = shapes.small,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.btnBack))
            }

            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
fun LineInformation(
    modifier: Modifier = Modifier,
    title: String,
    desc: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(text = title)

        Spacer(modifier = Modifier.weight(1f))

        Text(text = desc)
    }
}
