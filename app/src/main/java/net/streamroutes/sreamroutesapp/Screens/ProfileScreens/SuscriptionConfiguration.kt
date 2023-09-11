package net.streamroutes.sreamroutesapp.Screens.ProfileScreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import net.streamroutes.sreamroutesapp.Colores.color_fondo

// aqui agregar algo como para poder cancelar la suscripcion o cambiar el pago automatico a manual
@Composable
fun SuscriptionConfigurationScreen(){
    Suscription()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Suscription(){
    Scaffold(
        topBar = {},
        containerColor = color_fondo
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

        }
    }
}