package net.streamroutes.sreamroutesapp.features.profile.presentation.routes

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.core.domain.model.SavedRoute
import net.streamroutes.sreamroutesapp.features.profile.components.ProfileSmallTopAppBar
import net.streamroutes.sreamroutesapp.features.profile.components.SavedRouteItem
import java.time.LocalDateTime

@Preview(showBackground = true)
@Composable
fun SavedRouteScreen(
    modifier: Modifier = Modifier
) {
    // no pasar el modifier, solo en caso de que no se coloree
    // si no se colorea, usar scaffold para encapsular las cosas

    // ELIMINAR LISTA
    val savedRoutes = listOf(
        SavedRoute(
            name = "Ruta 1",
            start = "Estación Central",
            end = "Parque de la Ciudad",
            officialSpotsCount = 12,
            saveDate = LocalDateTime.of(2024, 9, 29, 14, 30)
        ),
        SavedRoute(
            name = "Ruta 2",
            start = "Plaza Principal",
            end = "Terminal de Autobuses",
            officialSpotsCount = 8,
            saveDate = LocalDateTime.of(2024, 10, 1, 9, 15)
        ),
        SavedRoute(
            name = "Ruta 3",
            start = "Universidad",
            end = "Centro Histórico",
            officialSpotsCount = 10,
            saveDate = LocalDateTime.of(2024, 10, 2, 16, 45)
        ),
        SavedRoute(
            name = "Ruta 4",
            start = "Mercado Municipal",
            end = "Hospital General",
            officialSpotsCount = 14,
            saveDate = LocalDateTime.of(2024, 9, 27, 8, 0)
        ),
        SavedRoute(
            name = "Ruta 5",
            start = "Aeropuerto",
            end = "Estación de Tren",
            officialSpotsCount = 7,
            saveDate = LocalDateTime.of(2024, 9, 30, 18, 20)
        )
    )

    Scaffold(
        topBar = {
            ProfileSmallTopAppBar(
                title = stringResource(id = R.string.lblRoutes),
                onBackPressed = {
                    // REGRESAR A EDITAR PERFIL
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = stringResource(id = R.string.lblRoutesSavedAmount, savedRoutes.size),
                    style = typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .graphicsLayer(alpha = 0.5f)
                )
            }

            items(savedRoutes) { route ->
                SavedRouteItem(route = route)
            }
        }
    }
}