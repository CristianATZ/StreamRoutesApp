package net.streamroutes.sreamroutesapp.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun ForgotScreen (navController: NavController) {
    Forgot(navController)
}

@Composable
fun Forgot (navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Olvide mi contraseña")
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text(text = "Regresar a login")
        }
    }
}