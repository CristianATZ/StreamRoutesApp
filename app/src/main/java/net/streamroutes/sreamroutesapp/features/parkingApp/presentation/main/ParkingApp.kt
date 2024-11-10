package net.streamroutes.sreamroutesapp.features.parkingApp.presentation.main

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.compose.RumappAppTheme

@Composable
fun ParkingApp(
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var selectedRoute by remember {
        mutableIntStateOf(0)
    }

    RumappAppTheme() {

    }
}