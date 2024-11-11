package net.streamroutes.sreamroutesapp.features.maps.presentation.transport

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.features.components.ShimmerMapAllOptions
import net.streamroutes.sreamroutesapp.features.maps.components.ShimmerElementOption
import net.streamroutes.sreamroutesapp.utils.shimmerEffect

@Composable
fun MapsTransportMainScreen(
    modifier: Modifier = Modifier
) {
    var currentTab by remember {
        mutableIntStateOf(0)
    }

    val onChangeTab = { index: Int ->
        currentTab = index
    }

    // Entrada desde la parte superior
    val slideInFromTop = slideInVertically(
        initialOffsetY = { -it } // Entra desde la parte superior
    )

    // Salida hacia la parte superior
    val slideOutToTop = slideOutVertically(
        targetOffsetY = { -it } // Sale hacia la parte superior
    )

    // Entrada desde la parte inferior
    val slideInFromBottom = slideInVertically(
        initialOffsetY = { it } // Entra desde la parte inferior
    )

    // Salida hacia la parte inferior
    val slideOutToBottom = slideOutVertically(
        targetOffsetY = { it } // Sale hacia la parte inferior
    )


    AnimatedVisibility(
        visible = currentTab == 0,
        enter = fadeIn() + slideInFromTop,
        exit = fadeOut() + slideOutToTop
    ) {
        var isLoading by remember {
            mutableStateOf(true)
        }

        // cambiar "Cargando..." por este elemento
        /*ShimmerTransportScreen(
            modifier = modifier
        )*/

        TransportScreen(
            onSelectRoute = {
                onChangeTab(2)
            },
            onSelectMap = {
                onChangeTab(1)
            },
            modifier = modifier
        )
    }

    AnimatedVisibility(
        visible = currentTab == 1,
        enter = fadeIn() + slideInFromBottom,
        exit = fadeOut() + slideOutToBottom
    ) {
        MapStopScreen(
            onBackPressed = {
                onChangeTab(0)
            },
            modifier = modifier
        )
    }

    AnimatedVisibility(
        visible = currentTab == 2,
        enter = fadeIn() + slideInFromBottom,
        exit = fadeOut() + slideOutToBottom
    ) {
        MapRouteScreen(
            onBackPressed = {
                onChangeTab(0)
            },
            onShareLocation = {

            },
            modifier = modifier
        )
    }
}