package net.streamroutes.sreamroutesapp.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.compose.RumappAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.core.navigation.ParkingNavigation
import net.streamroutes.sreamroutesapp.features.authentication.presentation.login.LoginViewModel
import net.streamroutes.sreamroutesapp.features.parkingApp.components.ParkingDrawerContent

@AndroidEntryPoint
class ParkingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navHostController = rememberNavController()

            val loginViewModel: LoginViewModel = hiltViewModel()

            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val coroutineScope = rememberCoroutineScope()

            val openDrawer = {
                coroutineScope.launch {
                    drawerState.open()
                }
            }

            RumappAppTheme {
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        ParkingDrawerContent(
                            navHostController = navHostController,
                            onLogOut = {
                                loginViewModel.signOut()
                            }
                        )
                    }
                ) {
                    ParkingNavigation(
                        navHostController = navHostController,
                        onOpenMenu = {
                            openDrawer()
                        }
                    )
                }
            }
        }
    }
}