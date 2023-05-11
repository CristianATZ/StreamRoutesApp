package net.streamroutes.sreamroutesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import net.streamroutes.sreamroutesapp.Navigation.AppNavigation
import net.streamroutes.sreamroutesapp.ui.theme.StreamRoutesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StreamRoutesAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFFFF7E7)
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

