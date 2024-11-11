package net.streamroutes.sreamroutesapp.features.parkingApp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.core.navigation.Destinations
import net.streamroutes.sreamroutesapp.features.components.DrawerItem

@Composable
fun ParkingDrawerContent(
    navHostController: NavHostController,
    onLogOut: () -> Unit
) {
    val currentbBackStackEntry = navHostController.currentBackStackEntryAsState()
    val currentRoute = currentbBackStackEntry.value?.destination?.route

    ModalDrawerSheet(
        drawerShape = RectangleShape,
        modifier = Modifier
            .fillMaxWidth(0.8f),
    ) {
        Text(
            text = stringResource(id = R.string.lblRumapp),
            style = typography.titleLarge,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.Start)
        )

        HorizontalDivider(modifier = Modifier.padding(bottom = 16.dp))

        // perfil
        DrawerItem(
            label = stringResource(id = R.string.lblProfile),
            icon = Icons.Outlined.Person,
            iconDescription = stringResource(id = R.string.iconProfile),
            selected = currentRoute == Destinations.Profile.route,
            onClick = {
                navHostController.navigate(Destinations.Profile.route) {
                    launchSingleTop = true
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // inicio
        DrawerItem(
            label = stringResource(id = R.string.lblHome),
            icon = Icons.Outlined.Home,
            iconDescription = stringResource(id = R.string.iconHome),
            selected = currentRoute == Destinations.HomeParking.route,
            onClick = {
                navHostController.navigate(Destinations.HomeParking.route) {
                    launchSingleTop = true
                    popUpTo(Destinations.HomeParking.route) { inclusive = false }
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // parqueos
        DrawerItem(
            label = stringResource(id = R.string.lblParks),
            icon = Icons.Outlined.BookmarkBorder,
            iconDescription = stringResource(id = R.string.iconBooking),
            selected = currentRoute == Destinations.Parks.route,
            onClick = {
                navHostController.navigate(Destinations.Parks.route) {
                    launchSingleTop = true
                    popUpTo(Destinations.HomeParking.route) { inclusive = false }
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // ajustes
        DrawerItem(
            label = stringResource(id = R.string.lblSettings),
            icon = Icons.Outlined.Settings,
            iconDescription = stringResource(id = R.string.iconSettings),
            selected = currentRoute == Destinations.Settings.route,
            onClick = {
                navHostController.navigate(Destinations.Settings.route) {
                    launchSingleTop = true
                    popUpTo(Destinations.HomeParking.route) { inclusive = false }
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        HorizontalDivider(modifier = Modifier.padding(bottom = 16.dp))

        // Cerrar sesion
        DrawerItem(
            label = stringResource(id = R.string.lblLogout),
            icon = Icons.AutoMirrored.Outlined.Logout,
            iconDescription = stringResource(id = R.string.iconLogout),
            selected = true,
            logout = true,
            onClick = onLogOut,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}