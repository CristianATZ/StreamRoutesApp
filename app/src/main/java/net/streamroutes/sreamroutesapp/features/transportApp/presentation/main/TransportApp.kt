package net.streamroutes.sreamroutesapp.features.transportApp.presentation.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.features.transportApp.components.DrawerContent
import net.streamroutes.sreamroutesapp.features.transportApp.components.TransportSmallTopAppBar
import net.streamroutes.sreamroutesapp.features.transportApp.presentation.home.HomeScreen

@Composable
fun TransportApp(
    modifier: Modifier = Modifier
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            // MENU, LLAMAR COMPONENTE DrawerContent
            DrawerContent(
                onLogOut = {
                    // SALIR DE FIREBASE
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                // TOPAPPBAR, LLAMAR COMPONENTE TopAppBar
                TransportSmallTopAppBar(
                    onNavigationPressed = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    }
                )
            }
        ) { innerPadding ->
            // LLAMAR COMPONENTE DE LA NAVEGACION
            HomeScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}