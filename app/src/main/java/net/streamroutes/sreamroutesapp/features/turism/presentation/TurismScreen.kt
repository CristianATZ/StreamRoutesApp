package net.streamroutes.sreamroutesapp.features.turism.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.maps.presentation.transport.MapRouteScreen
import net.streamroutes.sreamroutesapp.features.turism.components.TurismSmallTopAppBar
import net.streamroutes.sreamroutesapp.features.turism.presentation.turismRoute.TurismRouteScreen

@Preview(showBackground = true)
@Composable
fun TurismScreen(
    modifier: Modifier = Modifier
) {

    Scaffold(
        topBar = {
            TurismSmallTopAppBar(
                title = stringResource(id = R.string.lblTurism)
            )
        }
    ) { innerPadding ->
        /*TurismList(
            modifier = Modifier.padding(innerPadding)
        )*/
        /*TurismMapScreen(
            modifier = Modifier.padding(innerPadding),
            onBackPressed = {

            }
        )*/

        // ELEGIR UNOO Y BORRAR EL OTRO

/*        MapRouteScreen(
            modifier = Modifier.padding(innerPadding),
            onBackPressed = {

            },
            onShareLocation = {

            }
        )*/

        TurismRouteScreen(
            modifier = Modifier.padding(innerPadding),
            onBackPressed = {

            },
            onShareLocation = {

            }
        )
    }
}
