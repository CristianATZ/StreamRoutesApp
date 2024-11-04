package net.streamroutes.sreamroutesapp.features.parking.presentation.qr

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.parking.components.QrScanner

@Composable
fun ScannerScreen(
    onSuccess: () -> Unit,
    onBackPressed: () -> Unit
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.padding(vertical = 16.dp).align(Alignment.TopCenter),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(Modifier.size(8.dp))

            IconButton(
                onClick = onBackPressed,
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = colorScheme.background,
                    contentColor = colorScheme.onBackground
                )
            ) {
                Icon(imageVector = Icons.Outlined.ArrowBackIosNew, contentDescription = null)
            }

            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .background(Color.Black.copy(0.5f), shapes.extraLarge),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.lblScanQR),
                    style = typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

        QrScanner(
            modifier = Modifier.align(Alignment.Center),
            onSuccess = onSuccess
        )
    }
}