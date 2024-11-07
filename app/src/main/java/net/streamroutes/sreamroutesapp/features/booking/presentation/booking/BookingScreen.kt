package net.streamroutes.sreamroutesapp.features.booking.presentation.booking

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
import net.streamroutes.sreamroutesapp.features.booking.components.BookinSmallTopAppBar

data class BookingItemClass(
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
fun BookingScreen(
    modifier: Modifier = Modifier
) {
    // ontener lista
    val bookingList = listOf(
        BookingItemClass(
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
        BookingItemClass(
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

    val onBackPressed = {

    }

    val onWatchRoute = {
        onViewRoute = true
    }

    Scaffold(
        topBar = {
            BookinSmallTopAppBar(
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
                NoBooking()
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
                        ParkingRouteBookingScreen(
                            onBackPressed = {
                                onViewRoute = false
                            }
                        )
                    } else {
                        BookingList(
                            bookingList = bookingList,
                            onWatchRoute = onWatchRoute
                        )
                    }
                }

            }
        }
    }
}
