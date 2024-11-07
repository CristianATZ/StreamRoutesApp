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
import net.streamroutes.sreamroutesapp.core.data.repository.ParkingRepository
import net.streamroutes.sreamroutesapp.core.data.repository.RouteRepository
import net.streamroutes.sreamroutesapp.core.data.repository.UserRepository
import net.streamroutes.sreamroutesapp.data.RetrofitOpenRouteService
import net.streamroutes.sreamroutesapp.data.RetrofitParkingService
import net.streamroutes.sreamroutesapp.data.repository.FirebaseRepository
import net.streamroutes.sreamroutesapp.data.repository.NetworkRemoteReposiroty
import net.streamroutes.sreamroutesapp.features.authentication.presentation.login.LoginViewModel
import net.streamroutes.sreamroutesapp.features.authentication.presentation.login.LoginViewModelFactory
import net.streamroutes.sreamroutesapp.features.authentication.presentation.password.PasswordViewModel
import net.streamroutes.sreamroutesapp.features.authentication.presentation.password.PasswordViewModelFactory
import net.streamroutes.sreamroutesapp.features.authentication.presentation.register.RegisterViewModel
import net.streamroutes.sreamroutesapp.features.authentication.presentation.register.RegisterViewModelFactory
import net.streamroutes.sreamroutesapp.features.booking.presentation.booking.BookingScreen
import net.streamroutes.sreamroutesapp.features.maps.presentation.transport.TransportViewModel
import net.streamroutes.sreamroutesapp.features.maps.presentation.transport.TransportViewModelFactory
import net.streamroutes.sreamroutesapp.features.parking.presentation.booking.ParkingBookingScreen
import net.streamroutes.sreamroutesapp.features.parking.presentation.information.ParkingInformationScreen
import net.streamroutes.sreamroutesapp.features.parking.presentation.route.ParkingRouteScreen
import net.streamroutes.sreamroutesapp.features.profile.presentation.history.HistoryScreen
import net.streamroutes.sreamroutesapp.features.profile.presentation.profile.ProfileViewModel
import net.streamroutes.sreamroutesapp.features.profile.presentation.profile.ProfileViewModelFactory
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

class LoginActivity : ComponentActivity() {
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicialización de Firebase
        FirebaseApp.initializeApp(this)
        FirebaseFirestore.getInstance()
        setContent {
            val navHostController = rememberNavController()

            val myViewModel : MyViewModel = viewModel()

            val parkingService = RetrofitParkingService.retrofitService
            val routesService = RetrofitOpenRouteService.retrofitService
            val repository by lazy { NetworkRemoteReposiroty(parkingService, routesService) }
            val firebaseRepository by lazy { FirebaseRepository() }
            val routesRepository by lazy { RouteRepository() }
            val parkingRepository by lazy { ParkingRepository() }
            val userRepository by lazy { UserRepository() }

            // viewmodel de rutas
            val routesViewModel: RoutesViewModel by viewModels { RoutesViewModelFactory(routesRepository) }

            // viewmodels de esatcionamiento
            val parkingPkViewModel: ParkingPkViewModel by viewModels { ParkingPkViewModelFactory(repository) }
            val homePkViewModel: HomePkViewModel by viewModels { HomePkViewModelFactory(repository) }
            val viajePkViewModel : ViajePkViewModel by viewModels { ViajePkViewModelFactory(repository) }
            val apartarPkViewModel: ApartarPkViewModel by viewModels { ApartarPkViewModelFactory(repository) }



            // ------------------------------------------------------------------
            // viewModels de usuarios (ViewModels Chidos)
            val registerViewModel: RegisterViewModel by viewModels { RegisterViewModelFactory(userRepository) }
            val loginViewModel: LoginViewModel by viewModels { LoginViewModelFactory(userRepository) }
            val profileViewModel: ProfileViewModel by viewModels { ProfileViewModelFactory(userRepository) }
            val passwordViewModel: PasswordViewModel by viewModels { PasswordViewModelFactory(userRepository) }
            val transportViewModel: TransportViewModel by viewModels { TransportViewModelFactory(routesRepository) }

            // viewmodel encargado de manejar acerca de la peticion a ORS
            val orsViewModel: OrsViewModel by viewModels { OrsViewModelFactory(repository) }

            RumappAppTheme (false){
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
                BookingScreen()


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