package net.streamroutes.sreamroutesapp.features.authentication.presentation.choose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.compose.orange
import com.example.compose.yellow
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.components.CardOption
import net.streamroutes.sreamroutesapp.features.authentication.components.DisplayText

@Composable
fun ChooseScreen(
    onTransport: () -> Unit,
    onParking: () -> Unit
) {
    val background = listOf(orange, yellow)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(background)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(64.dp))

        // encabezado explora
        DisplayText(
            text = stringResource(id = R.string.lblExplore)
        )
        // encabezado selecciona
        DisplayText(
            text = stringResource(id = R.string.lblSelect)
        )

        Spacer(modifier = Modifier.size(64.dp))

        CardOption(
            text = stringResource(id = R.string.lblPublictransport),
            onClick = onTransport,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.size(32.dp))

        CardOption(
            text = stringResource(id = R.string.lblParkings),
            onClick = onParking,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(200.dp)
        )
    }
}