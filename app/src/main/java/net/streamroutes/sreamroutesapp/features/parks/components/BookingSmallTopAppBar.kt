package net.streamroutes.sreamroutesapp.features.parks.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.components.CustomTopAppBar
import net.streamroutes.sreamroutesapp.features.components.NavigationButton

@Composable
fun BookingSmallTopAppBar(
    modifier: Modifier = Modifier,
    onNavigationPressed: () -> Unit
) {
    Column {
        CustomTopAppBar(
            title = {
                Text(text = stringResource(R.string.lblBookings))
            },
            navigationIcon = {
                NavigationButton(
                    onButtonPressed = onNavigationPressed,
                    icon = Icons.Outlined.ArrowBackIosNew
                )
            },
            actions = {

            },
            modifier = modifier
        )

        HorizontalDivider()
    }
}