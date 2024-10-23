package net.streamroutes.sreamroutesapp.features.parkingApp.presentation.main

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.features.parkingApp.components.ParkingDrawerContent
import net.streamroutes.sreamroutesapp.features.parkingApp.components.ParkingSmallTopAppBar

@Composable
fun ParkingApp(
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var selectedRoute by remember {
        mutableIntStateOf(0)
    }

    /*Scaffold(
        bottomBar = {
            BottomContent(
                selectedRoute = selectedRoute,
                onChangeRoute = { index ->
                    selectedRoute = index
                }
            )
        }
    ) { innerPadding ->
        // home
        // parks
        // account
        ProfileScreen()
    }*/

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ParkingDrawerContent(
                selectedRoute = selectedRoute,
                onChangeRoute = {
                    selectedRoute = it
                },
                onLogOut = {
                    // SALIR DE FIREBASE
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                ParkingSmallTopAppBar(
                    onNavigationPressed = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    },
                    onSettingsPressed = {

                    },
                    onProfilePressed = {

                    }
                )
            }
        ) { innerPadding ->
            // home
            // parks
            // account
        }
    }
}