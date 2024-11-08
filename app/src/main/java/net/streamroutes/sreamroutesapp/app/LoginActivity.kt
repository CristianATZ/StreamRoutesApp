package net.streamroutes.sreamroutesapp.app

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.compose.RumappAppTheme
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint
import net.streamroutes.sreamroutesapp.core.data.repository.ParkingRepository
import net.streamroutes.sreamroutesapp.core.data.repository.RouteRepository
import net.streamroutes.sreamroutesapp.core.navigation.LoginNavigation
import net.streamroutes.sreamroutesapp.data.RetrofitOpenRouteService
import net.streamroutes.sreamroutesapp.data.RetrofitParkingService
import net.streamroutes.sreamroutesapp.data.repository.FirebaseRepository
import net.streamroutes.sreamroutesapp.data.repository.NetworkRemoteReposiroty
import net.streamroutes.sreamroutesapp.features.maps.presentation.transport.TransportViewModel
import net.streamroutes.sreamroutesapp.features.maps.presentation.transport.TransportViewModelFactory
import net.streamroutes.sreamroutesapp.utils.MyViewModel
import net.streamroutes.sreamroutesapp.viewmodel.OrsViewModel
import net.streamroutes.sreamroutesapp.viewmodel.OrsViewModelFactory
import net.streamroutes.sreamroutesapp.viewmodel.parking.ApartarPkViewModel
import net.streamroutes.sreamroutesapp.viewmodel.parking.ApartarPkViewModelFactory
import net.streamroutes.sreamroutesapp.viewmodel.parking.HomePkViewModel
import net.streamroutes.sreamroutesapp.viewmodel.parking.HomePkViewModelFactory
import net.streamroutes.sreamroutesapp.viewmodel.parking.ParkingPkViewModel
import net.streamroutes.sreamroutesapp.viewmodel.parking.ParkingPkViewModelFactory
import net.streamroutes.sreamroutesapp.viewmodel.parking.ViajePkViewModel
import net.streamroutes.sreamroutesapp.viewmodel.parking.ViajePkViewModelFactory
import net.streamroutes.sreamroutesapp.viewmodel.routes.RoutesViewModel
import net.streamroutes.sreamroutesapp.viewmodel.routes.RoutesViewModelFactory


@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicialización de Firebase

        setContent {
            val navHostController = rememberNavController()

            val myViewModel : MyViewModel = viewModel()

            val parkingService = RetrofitParkingService.retrofitService
            val routesService = RetrofitOpenRouteService.retrofitService
            val repository by lazy { NetworkRemoteReposiroty(parkingService, routesService) }
            val firebaseRepository by lazy { FirebaseRepository() }
            val parkingRepository by lazy { ParkingRepository() }

            // viewmodel de rutas

            // viewmodels de esatcionamiento
            val parkingPkViewModel: ParkingPkViewModel by viewModels { ParkingPkViewModelFactory(repository) }
            val homePkViewModel: HomePkViewModel by viewModels { HomePkViewModelFactory(repository) }
            val viajePkViewModel : ViajePkViewModel by viewModels { ViajePkViewModelFactory(repository) }
            val apartarPkViewModel: ApartarPkViewModel by viewModels { ApartarPkViewModelFactory(repository) }



            // ------------------------------------------------------------------
            // viewModels de usuarios (ViewModels Chidos)

            // viewmodel encargado de manejar acerca de la peticion a ORS
            val orsViewModel: OrsViewModel by viewModels { OrsViewModelFactory(repository) }

            RumappAppTheme (false){
                LoginNavigation(
                    navHostController = navHostController
                )
                // A surface container using the 'background' color from the theme
                /*Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AppNavigation(
                        navHostController = navHostController,
                        myViewModel = myViewModel,
                        parkingPkViewModel = parkingPkViewModel,
                        homePkViewModel = homePkViewModel,
                        viajePkViewModel = viajePkViewModel,
                        apartarPkViewModel = apartarPkViewModel,
                        orsViewModel = orsViewModel,
                        routesViewModel = routesViewModel
                    )
                }*/
                // EditProfileScreen()
                //RoutesScreen(orsViewModel, routesViewModel)
                //EditInformation()
                //EditAccountScreen()
                //EditProfileScreen()
                //SavedPostScreen()
                //SavedRouteScreen()
                //HistoryScreen()
                //PremiumScreen()
                //MapsScreen()
                //TurismScreen()
                //ForumScreen()
                //SettingsScreen()
                //NotificationsScreen()
                //MapsSettingsScreen()
                //PrivacityScreen()
                //ApparenceScreen()
                //MapsSettingsScreen()
                //StorageScreen()
                //TransportApp()
                //ParkingApp()
                //TransportScreen()
                //ParkingInformationScreen()
                //ParkingRouteScreen()
                //ParkingQrScreen()
                //ParkingBookingScreen()
                //BookingScreen()


                /**
                 * PANTALLAS VINCULADAS CON BACK
                 */
                //RegisterScreen(registerViewModel)
                //LoginScreen(loginViewModel)
                //ProfileScreen(profileViewModel)
                //EditAccountScreen(profileViewModel)
                //EditInformation(profileViewModel)
                //PasswordScreen(passwordViewModel)
                //TransportApp(loginViewModel, profileViewModel)

                //TransportScreen(transportViewModel)
            }
        }
    }
}