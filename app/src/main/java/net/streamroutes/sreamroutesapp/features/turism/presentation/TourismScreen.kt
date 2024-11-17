package net.streamroutes.sreamroutesapp.features.turism.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.core.navigation.TourismNavigation
import net.streamroutes.sreamroutesapp.features.maps.presentation.transport.TransportViewModel
import net.streamroutes.sreamroutesapp.features.turism.components.TurismSmallTopAppBar
import net.streamroutes.sreamroutesapp.features.turism.presentation.turismList.TourismViewModel

@Composable
fun TourismScreen(
    tourismViewModel: TourismViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    val tourismNavHostController = rememberNavController()

    Scaffold(
        topBar = {
            TurismSmallTopAppBar(
                title = stringResource(id = R.string.lblTourism),
                onBackPressed = onBackPressed
            )
        }
    ) { innerPadding ->
        TourismNavigation(
            navHostController = tourismNavHostController,
            modifier = Modifier.padding(innerPadding),
            tourismViewModel = tourismViewModel
        )
    }
}
