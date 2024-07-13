package net.ivanvega.milocationymapascompose

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import net.ivanvega.milocationymapascompose.network.NetworkRemoteReposiroty
import net.ivanvega.milocationymapascompose.network.RetrofitOpenRouteService
import net.ivanvega.milocationymapascompose.ui.location.CurrentLocationScreen
import net.ivanvega.milocationymapascompose.ui.theme.MiLocationYMapasComposeTheme

import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MiLocationYMapasComposeTheme {

                val apiService = RetrofitOpenRouteService.retrofitService
                val repo by lazy { NetworkRemoteReposiroty(apiService) }


                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                    //LocationPermissionScreen()
                    CurrentLocationScreen(repo)
                    //LocationUpdatesScreen()
                    //MiMapa() //Google
                    //MiMapaOSMDroidCompose() //Open Street Map
                }
            }
        }

        //startLocationWorker()
    }

    /*private fun startLocationWorker() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val locationWorkRequest = OneTimeWorkRequestBuilder<LocationWorker>()
            //.setConstraints(constraints)
            .addTag("LocationWork")
            .build()

        WorkManager.getInstance(applicationContext).beginUniqueWork(
            "LocationWork",
            ExistingWorkPolicy.REPLACE,
            locationWorkRequest
        ).enqueue()
    }*/
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MiLocationYMapasComposeTheme {
        Greeting("Android")
    }
}