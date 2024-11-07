package net.streamroutes.sreamroutesapp.features.booking.presentation.booking

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import net.streamroutes.sreamroutesapp.features.booking.components.BookingItem

@Composable
fun BookingList(
    bookingList: List<BookingItemClass>,
    onWatchRoute: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        bookingList.forEach { item ->
            BookingItem(
                item = item,
                onWatchRoute = onWatchRoute
            )
        }
    }
}