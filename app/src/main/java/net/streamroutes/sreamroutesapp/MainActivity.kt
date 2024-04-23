package net.streamroutes.sreamroutesapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import net.streamroutes.sreamroutesapp.navigation.AppNavigation
import net.streamroutes.sreamroutesapp.ui.Theme.RumaAppTheme
import net.streamroutes.sreamroutesapp.utils.MyViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val myViewModel : MyViewModel = viewModel()
            RumaAppTheme (myViewModel.tema){
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
