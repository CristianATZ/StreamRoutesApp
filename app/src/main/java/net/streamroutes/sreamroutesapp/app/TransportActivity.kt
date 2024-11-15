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
import net.streamroutes.sreamroutesapp.core.navigation.TransportNavigation
import net.streamroutes.sreamroutesapp.features.authentication.presentation.login.LoginViewModel
import net.streamroutes.sreamroutesapp.features.maps.presentation.transport.TransportViewModel
import net.streamroutes.sreamroutesapp.features.transportApp.components.TransportDrawerContent

@AndroidEntryPoint
class TransportActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RumappAppTheme {
                val navHostController = rememberNavController()

                val loginViewModel: LoginViewModel = hiltViewModel()
                val transportViewModel: TransportViewModel = hiltViewModel()

                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val coroutineScope = rememberCoroutineScope()

                val openDrawer = {
                    coroutineScope.launch {
                        drawerState.open()
                    }
                }

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        // MENU, LLAMAR COMPONENTE DrawerContent
                        TransportDrawerContent(
                            navHostController = navHostController,
                            onLogOut = {
                                loginViewModel.signOut()
                            }
                        )
                    }
                ) {
                    TransportNavigation(
                        navHostController = navHostController,
                        onOpenMenu = {
                            openDrawer()
                        },
                        transportViewModel = transportViewModel
                    )
                }
            }
        }
    }
}