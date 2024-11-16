package net.streamroutes.sreamroutesapp.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.compose.RumappAppTheme
import dagger.hilt.android.AndroidEntryPoint
import net.streamroutes.sreamroutesapp.core.navigation.GeneralNavigation

@AndroidEntryPoint
class GeneralActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RumappAppTheme {
                val navHostController = rememberNavController()

                GeneralNavigation(
                    navHostController = navHostController
                )
            }
        }
    }
}