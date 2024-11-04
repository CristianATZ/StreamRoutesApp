package net.streamroutes.sreamroutesapp.features.parking.presentation.qr

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.compose.orange
import net.streamroutes.sreamroutesapp.R

@Composable
fun SuccessfullScanScreen(
    onAcceptPressed: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(0.75f)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.lblConfirmation),
            style = typography.displayLarge,

            color = Color.White
        )

        Spacer(Modifier.size(16.dp))

        Text(
            text = stringResource(R.string.lblConfirmationDescription),
            style = typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.fillMaxWidth(0.6f)
        )

        Spacer(Modifier.size(64.dp))

        Text(
            text = "#asd123",
            style = typography.headlineSmall,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )

        Spacer(Modifier.size(64.dp))

        // escanear codigo qr
        Button(
            onClick = onAcceptPressed,
            shape = shapes.small,
            colors = ButtonDefaults.buttonColors(
                containerColor = orange,
                contentColor = Color.Black
            ),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.btnAccept))
        }
    }
}