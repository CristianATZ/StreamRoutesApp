package net.streamroutes.sreamroutesapp.features.parkingApp.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.compose.orange
import net.streamroutes.sreamroutesapp.R

@Composable
fun BottomContent(
    selectedRoute: Int,
    onChangeRoute: (Int) -> Unit
) {
    val navigationColors = NavigationBarItemDefaults.colors(
        selectedIconColor = orange,
        indicatorColor = orange.copy(0.25f)
    )

    NavigationBar {
        NavigationBarItem(
            selected = selectedRoute == 0,
            onClick = {
                onChangeRoute(0)
            },
            icon = {
                Icon(
                    imageVector = if(selectedRoute == 0 ) Icons.Filled.Home else Icons.Outlined.Home,
                    contentDescription = stringResource(R.string.iconHome)
                )
            },
            label = {
                Text(text = stringResource(R.string.lblHome))
            },
            alwaysShowLabel = false,
            colors = navigationColors
        )

        NavigationBarItem(
            selected = selectedRoute == 1,
            onClick = {
                onChangeRoute(1)
            },
            icon = {
                Icon(
                    imageVector = if(selectedRoute == 1) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                    contentDescription = stringResource(R.string.iconBooking)
                )
            },
            label = {
                Text(text = stringResource(R.string.lblParks))
            },
            alwaysShowLabel = false,
            colors = navigationColors
        )

        NavigationBarItem(
            selected = selectedRoute == 2,
            onClick = {
                onChangeRoute(2)
            },
            icon = {
                Icon(
                    imageVector = if(selectedRoute == 2) Icons.Filled.Person else Icons.Outlined.Person,
                    contentDescription = stringResource(R.string.iconPerson)
                )
            },
            label = {
                Text(text = stringResource(R.string.lblAccount))
            },
            alwaysShowLabel = false,
            colors = navigationColors
        )
    }
}