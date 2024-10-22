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
import net.streamroutes.sreamroutesapp.data.RetrofitOpenRouteService
import net.streamroutes.sreamroutesapp.data.RetrofitParkingService
import net.streamroutes.sreamroutesapp.data.repository.FirebaseRepository
import net.streamroutes.sreamroutesapp.data.repository.NetworkRemoteReposiroty
import net.streamroutes.sreamroutesapp.features.forum.presentation.ForumScreen
import net.streamroutes.sreamroutesapp.features.maps.presentation.MapsScreen
import net.streamroutes.sreamroutesapp.features.settings.presentation.apparence.ApparenceScreen
import net.streamroutes.sreamroutesapp.features.settings.presentation.maps.MapsSettingsScreen
import net.streamroutes.sreamroutesapp.features.settings.presentation.notifications.NotificationsScreen
import net.streamroutes.sreamroutesapp.features.settings.presentation.privacity.PrivacityScreen
import net.streamroutes.sreamroutesapp.features.settings.presentation.settings.SettingsScreen
import net.streamroutes.sreamroutesapp.features.turism.presentation.TurismScreen
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

            // viewmodel de rutas
            val routesViewModel: RoutesViewModel by viewModels { RoutesViewModelFactory(firebaseRepository) }

            // viewmodels de esatcionamiento
            val parkingPkViewModel: ParkingPkViewModel by viewModels { ParkingPkViewModelFactory(repository) }
            val homePkViewModel: HomePkViewModel by viewModels { HomePkViewModelFactory(repository) }
            val viajePkViewModel : ViajePkViewModel by viewModels { ViajePkViewModelFactory(repository) }
            val apartarPkViewModel: ApartarPkViewModel by viewModels { ApartarPkViewModelFactory(repository) }

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
                MapsSettingsScreen()
            }
        }
    }
}
