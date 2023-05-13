@file:OptIn(ExperimentalMaterial3Api::class)

package net.streamroutes.sreamroutesapp.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.Navigation.AppScreens

@Composable
fun MainScreen(navController: NavController) {
    Main(navController)
}

@Composable
fun Main(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Pantalla principal")
        // regresar
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text(text = "Regresar al login")
        }
        Button(onClick = {
            navController.navigate(route = AppScreens.HelpScreen.route)
        }) {
            Text(text = "Ayuda")
        }
        Button(onClick = {
            navController.navigate(route = AppScreens.ProfileScreen.route)
        }) {
            Text(text = "Perfil")
        }
    }
}