package net.streamroutes.sreamroutesapp.app

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.compose.RumappAppTheme
import dagger.hilt.android.AndroidEntryPoint
import net.streamroutes.sreamroutesapp.core.navigation.GeneralNavigation
import net.streamroutes.sreamroutesapp.features.settings.presentation.apparence.ApparenceViewModel

@AndroidEntryPoint
class GeneralActivity : ComponentActivity() {
    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val apparenceViewModel: ApparenceViewModel = hiltViewModel()

            val theme by apparenceViewModel.theme.collectAsState()
            val dynamicTheme by apparenceViewModel.dynaminc.collectAsState()

            RumappAppTheme(
                darkTheme = theme,
                dynamicColor = dynamicTheme
            ) {
                val navHostController = rememberNavController()

                GeneralNavigation(
                    navHostController = navHostController
                )
            }
        }
    }
}