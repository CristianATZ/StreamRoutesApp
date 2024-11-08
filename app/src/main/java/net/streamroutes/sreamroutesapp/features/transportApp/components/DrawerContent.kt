package net.streamroutes.sreamroutesapp.features.transportApp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.Forum
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Navigation
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
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
fun DrawerContent(
    onLogOut: () -> Unit,
    navHostController: NavHostController
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
            selected = currentRoute == Destinations.HomeTransport.route,
            onClick = {
                navHostController.navigate(Destinations.HomeTransport.route) {
                    launchSingleTop = true
                    popUpTo(0) { inclusive = true }
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // paquetes
        DrawerItem(
            label = stringResource(id = R.string.lblPremium),
            icon = Icons.Outlined.ShoppingCart,
            iconDescription = stringResource(id = R.string.iconPremium),
            selected = currentRoute == Destinations.Premium.route,
            onClick = {
                navHostController.navigate(Destinations.Premium.route) {
                    launchSingleTop = true
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // Mapas
        DrawerItem(
            label = stringResource(id = R.string.lblMaps),
            icon = Icons.Outlined.Map,
            iconDescription = stringResource(id = R.string.iconMaps),
            selected = currentRoute == Destinations.Maps.route,
            onClick = {
                navHostController.navigate(Destinations.Maps.route) {
                    launchSingleTop = true
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // Turismo
        DrawerItem(
            label = stringResource(id = R.string.lblTourism),
            icon = Icons.Outlined.Navigation,
            iconDescription = stringResource(id = R.string.iconTourism),
            selected = currentRoute == Destinations.Tourism.route,
            onClick = {
                navHostController.navigate(Destinations.Tourism.route) {
                    launchSingleTop = true
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // Foro
        DrawerItem(
            label = stringResource(id = R.string.lblForum),
            icon = Icons.Outlined.Forum,
            iconDescription = stringResource(id = R.string.iconForum),
            selected = currentRoute == Destinations.Forum.route,
            onClick = {
                navHostController.navigate(Destinations.Forum.route) {
                    launchSingleTop = true
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // Configuracion
        DrawerItem(
            label = stringResource(id = R.string.lblSettings),
            icon = Icons.Outlined.Settings,
            iconDescription = stringResource(id = R.string.iconSettings),
            selected = currentRoute == Destinations.Settings.route,
            onClick = {
                navHostController.navigate(Destinations.Settings.route) {
                    launchSingleTop = true
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