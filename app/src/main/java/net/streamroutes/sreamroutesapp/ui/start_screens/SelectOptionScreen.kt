package net.streamroutes.sreamroutesapp.ui.start_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.navigation.AppScreens
import net.streamroutes.sreamroutesapp.utils.brush

@Composable
fun SelectOptionScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { navController.navigate(AppScreens.MainScreen.route) }) {
            Text(text = stringResource(id = R.string.lblTransporteTitle))
        }

        Button(onClick = { navController.navigate(AppScreens.MainParking.route) }) {
            Text(text = stringResource(id = R.string.lblEstacionamientoTitle))
        }
    }
}