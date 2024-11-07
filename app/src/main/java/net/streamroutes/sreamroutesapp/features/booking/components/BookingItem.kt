package net.streamroutes.sreamroutesapp.features.booking.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.QrCode
import androidx.compose.material.icons.outlined.Route
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.compose.orange
import com.example.compose.primary
import com.example.compose.yellow
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.booking.presentation.booking.BookingItemClass
import net.streamroutes.sreamroutesapp.features.components.ParkingDescription
import net.streamroutes.sreamroutesapp.features.parking.components.InformationChip
import net.streamroutes.sreamroutesapp.features.profile.components.LineInformation
import net.streamroutes.sreamroutesapp.utils.QrUtils.generateQRCode
import net.streamroutes.sreamroutesapp.utils.brush

@Composable
fun BookingItem(
    item: BookingItemClass,
    onWatchRoute: () -> Unit
) {
    var openQr by remember {
        mutableStateOf(false)
    }

    if(openQr) {
        BookingDialogQr(
            item = item,
            onDissmiss = {
                openQr = !openQr
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(colorScheme.surfaceContainer)
    ) {
        // informacion del estacionamiento
        ParkingDescription(
            name = item.parkingName,
            price = null,
            address = item.parkingAddress
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.size(8.dp))

            InformationChip(
                text = stringResource(R.string.lblParkingPrice, item.price.toString()),
                color = CardDefaults.outlinedCardColors(
                    containerColor = colorScheme.surfaceVariant,
                    contentColor = colorScheme.onSurfaceVariant
                )
            )

            Spacer(modifier = Modifier.size(8.dp))

            InformationChip(
                text =
                if(item.isReserved)
                    stringResource(R.string.lblTimeLeft, "07:59")
                else
                    stringResource(R.string.lblCurrentTime, "03.00"),
                color = CardDefaults.outlinedCardColors(
                    containerColor = colorScheme.surfaceVariant,
                    contentColor = colorScheme.onSurfaceVariant
                )
            )

            Spacer(modifier = Modifier.size(8.dp))

            InformationChip(
                text = stringResource(R.string.lblTotalBooking, item.total.toString()),
                color = CardDefaults.outlinedCardColors(
                    containerColor = colorScheme.surfaceVariant,
                    contentColor = colorScheme.onSurfaceVariant
                )
            )

            Spacer(modifier = Modifier.size(8.dp))
        }

        Spacer(modifier = Modifier.size(16.dp))

        LineInformation(
            title = stringResource(R.string.lblReference),
            desc = item.reference,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )

        LineInformation(
            title = stringResource(R.string.lblCategory),
            desc = item.category,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )

        if(item.isReserved) {
            LineInformation(
                title = stringResource(R.string.lblTimeReserved),
                desc = item.timeReserverd,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            )
        }

        LineInformation(
            title = stringResource(R.string.lblDateIn),
            desc = item.enter,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )

        LineInformation(
            title = stringResource(R.string.lblDateOut),
            desc = item.exit,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )

        Spacer(modifier = Modifier.size(8.dp))

        if (item.isReserved) {
            Row {
                TextButton(
                    onClick = onWatchRoute,
                    shape = shapes.small,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Route,
                        contentDescription = stringResource(id = R.string.iconRoute)
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    Text(text = stringResource(id = R.string.btnWathcRoute))
                }

                TextButton(
                    onClick = {
                        openQr = !openQr
                    },
                    shape = shapes.small,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.QrCode,
                        contentDescription = stringResource(id = R.string.iconQrCode)
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    Text(text = stringResource(id = R.string.btnWatchQR))
                }
            }
        }
    }
}