package net.streamroutes.sreamroutesapp.features.parks.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import net.streamroutes.sreamroutesapp.features.parks.presentation.parks.ParkItem
import net.streamroutes.sreamroutesapp.utils.QrUtils.generateQRCode

@Composable
fun BookingDialogQr(
    onDissmiss: () -> Unit,
    item: ParkItem
) {
    val background = listOf(orange, yellow)

    Dialog(
        onDismissRequest = onDissmiss
    ) {
        Card {
            Column(
                modifier = Modifier.background(
                    brush = Brush.verticalGradient(background)
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    bitmap = generateQRCode(item.reference).asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier.size(250.dp).padding(16.dp).clip(shapes.small)
                )

                Text(
                    text = item.reference,
                    style = typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = primary
                )

                Text(
                    text = stringResource(R.string.lblQrTimeFormat, item.from, item.until),
                    style = typography.headlineLarge,
                    color = primary
                )

                Spacer(Modifier.size(16.dp))
            }
        }
    }
}