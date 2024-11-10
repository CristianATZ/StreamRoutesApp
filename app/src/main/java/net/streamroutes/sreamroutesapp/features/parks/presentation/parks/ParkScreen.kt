package net.streamroutes.sreamroutesapp.features.parks.presentation.parks

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import net.streamroutes.sreamroutesapp.features.parks.components.BookingSmallTopAppBar

data class ParkItem(
    val parkingName: String,
    val parkingAddress: String,
    val price: Double,
    val total: Double,
    val isReserved: Boolean,
    val from: String,
    val until: String,
    val reference: String,
    val category: String,
    val timeReserverd: String,
    val enter: String,
    val exit: String
)

@Composable
fun ParkScreen(
    onBackPressed: () -> Unit
) {
    // ontener lista
    val bookingList = listOf(
        ParkItem(
            parkingName = "Estacionamiento Central",
            parkingAddress = "Calle Principal 123, Guanajuato",
            price = 20.0,
            total = 100.0,
            isReserved = true,
            reference = "REF123456",
            from = "08:00",
            until = "14:00",
            category = "Carro",
            timeReserverd = "3",
            enter = "11:00:40",
            exit = "14:00:20"
        ),
        ParkItem(
            parkingName = "Estacionamiento Norte",
            parkingAddress = "Avenida Hidalgo 456, Guanajuato",
            price = 15.0,
            total = 75.0,
            isReserved = false,
            reference = "REF789012",
            from = "",
            until = ")",
            category = "Privado",
            timeReserverd = "",
            enter = "09:30:12",
            exit = "12:30:20"
        )
    )
    
    var onViewRoute by remember { mutableStateOf(false) }

    val onWatchRoute = {
        onViewRoute = true
    }

    Scaffold(
        topBar = {
            BookingSmallTopAppBar(
                onNavigationPressed = onBackPressed,
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            if(bookingList.isEmpty()) {
                NoParks()
            } else {
                AnimatedContent(
                    targetState = onViewRoute,
                    transitionSpec = {
                        if(onViewRoute) {
                            slideInHorizontally(
                                initialOffsetX = { fullWidth -> fullWidth } // Comienza desde la izquierda
                            ) togetherWith slideOutHorizontally(
                                targetOffsetX = { fullWidth -> -fullWidth } // Desliza hacia la derecha al salir
                            ) using SizeTransform(clip = false)
                        } else {
                            slideInHorizontally(
                                initialOffsetX = { fullWidth -> -fullWidth } // Comienza desde la izquierda
                            ) togetherWith slideOutHorizontally(
                                targetOffsetX = { fullWidth -> fullWidth } // Desliza hacia la derecha al salir
                            ) using SizeTransform(clip = false)
                        }
                    },
                    label = ""
                ) { isRoute ->
                    if (isRoute) {
                        ParkRouteScreen(
                            onBackPressed = {
                                onViewRoute = false
                            }
                        )
                    } else {
                        ParksList(
                            bookingList = bookingList,
                            onWatchRoute = onWatchRoute
                        )
                    }
                }

            }
        }
    }
}
