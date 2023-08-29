package net.streamroutes.sreamroutesapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import net.streamroutes.sreamroutesapp.Navigation.AppNavigation
import net.streamroutes.sreamroutesapp.Screens.Start.Splash
import net.streamroutes.sreamroutesapp.ui.theme.StreamRoutesAppTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val myViewModel = viewModel<MyViewModel>()
            StreamRoutesAppTheme (myViewModel.tema){
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AppNavigation(myViewModel)
                }
            }
        }
    }
}

