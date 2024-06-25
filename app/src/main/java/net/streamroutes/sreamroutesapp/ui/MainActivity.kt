package net.streamroutes.sreamroutesapp.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import net.streamroutes.sreamroutesapp.data.RetrofitOpenRouteService
import net.streamroutes.sreamroutesapp.data.RetrofitParkingService
import net.streamroutes.sreamroutesapp.data.navigation.AppNavigation
import net.streamroutes.sreamroutesapp.data.repository.NetworkRemoteReposiroty
import net.streamroutes.sreamroutesapp.ui.Theme.RumaAppTheme
import net.streamroutes.sreamroutesapp.utils.MyViewModel
import net.streamroutes.sreamroutesapp.viewmodel.parking.HomePkViewModel
import net.streamroutes.sreamroutesapp.viewmodel.parking.HomePkViewModelFactory
import net.streamroutes.sreamroutesapp.viewmodel.parking.ParkingPkViewModel
import net.streamroutes.sreamroutesapp.viewmodel.parking.ParkingPkViewModelFactory
import net.streamroutes.sreamroutesapp.viewmodel.parking.ViajePkViewModel
import net.streamroutes.sreamroutesapp.viewmodel.parking.ViajePkViewModelFactory

class MainActivity : ComponentActivity() {
    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()

            val myViewModel : MyViewModel = viewModel()

            val parkingService = RetrofitParkingService.retrofitService
            val routesService = RetrofitOpenRouteService.retrofitService
            val repository by lazy { NetworkRemoteReposiroty(parkingService, routesService) }
            val parkingPkViewModel: ParkingPkViewModel by viewModels { ParkingPkViewModelFactory(repository) }
            val homePkViewModel: HomePkViewModel by viewModels { HomePkViewModelFactory(repository) }
            val viajePkViewModel : ViajePkViewModel by viewModels { ViajePkViewModelFactory(repository) }

            RumaAppTheme (false){
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AppNavigation(
                        navHostController = navHostController,
                        myViewModel = myViewModel,
                        parkingPkViewModel = parkingPkViewModel,
                        homePkViewModel = homePkViewModel,
                        viajePkViewModel = viajePkViewModel
                    )
                }
            }
        }
    }
}
