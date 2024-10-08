package net.streamroutes.sreamroutesapp.features.maps.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import net.streamroutes.sreamroutesapp.features.maps.components.MapsSmallTopAppBar

@Preview(showBackground = true)
@Composable
fun MapsScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            MapsSmallTopAppBar()
        }
    ) {
        // IDEAS NAVEGACION PARA LAS 3 PANTALLAS

        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {

        }
    }
}