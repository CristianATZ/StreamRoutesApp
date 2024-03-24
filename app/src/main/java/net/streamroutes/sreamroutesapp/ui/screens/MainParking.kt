package net.streamroutes.sreamroutesapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainParking() {
    Text(text = "Estacionamiento apartado")

    Scaffold(
        bottomBar = { ParkingBottomBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {

        }
    }
}

@Composable
fun ParkingBottomBar() {
    BottomAppBar(

    ) {

    }
}
