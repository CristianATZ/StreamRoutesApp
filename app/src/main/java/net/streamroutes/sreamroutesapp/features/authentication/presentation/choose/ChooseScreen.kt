package net.streamroutes.sreamroutesapp.features.authentication.presentation.choose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.RumappAppTheme
import com.example.compose.orange
import com.example.compose.yellow
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.components.CardOption
import net.streamroutes.sreamroutesapp.features.authentication.components.DisplayText

@Preview
@Composable
fun ChooseScreen(
    modifier: Modifier = Modifier
) {
    RumappAppTheme {
        val background = listOf(orange, yellow)

        Column(
            modifier = modifier
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
                onClick = {
                    // ABRIR EL ACTIVITY DE TRANSPORTE
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.size(32.dp))

            CardOption(
                text = stringResource(id = R.string.lblParkings),
                onClick = {
                    // ABRIR EL ACTIVITY DE ESTACIONAMIENTOS
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(200.dp)
            )
        }
    }
}