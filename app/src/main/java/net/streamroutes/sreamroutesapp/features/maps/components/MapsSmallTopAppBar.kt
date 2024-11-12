package net.streamroutes.sreamroutesapp.features.maps.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.DirectionsWalk
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.DirectionsBus
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.compose.orange
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.core.navigation.Destinations
import net.streamroutes.sreamroutesapp.features.components.CustomTopAppBar
import net.streamroutes.sreamroutesapp.features.components.NavigationButton

@Composable
fun MapsSmallTopAppBar(
    title: String,
    onBackPressed: () -> Unit = {},
    onSettingsPressed: () -> Unit = {},
    mapsNavHostController: NavHostController
) {
    val currentEntry = mapsNavHostController.currentBackStackEntryAsState()
    val currentRoute = currentEntry.value?.destination?.route

    val currentTab = when(currentRoute) {
        Destinations.Transport.route -> 0
        Destinations.Planner.route -> 1
        Destinations.Fastest.route -> 2
        else -> 0
    }

    Column {
        CustomTopAppBar(
            title = {
                Text(text = title)
            },
            navigationIcon = {
                NavigationButton(
                    icon = Icons.Outlined.ArrowBackIosNew,
                    iconDescription = stringResource(id = R.string.iconBackMaps),
                    onButtonPressed = onBackPressed
                )
            },
            actions = {
                ActionsTopBar(
                    onSettingsPressed = onSettingsPressed
                )
            }
        )

        TabRow(
            selectedTabIndex = currentTab,
            indicator = { tabPositions ->
                SecondaryIndicator(
                    color = orange,
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[currentTab]) // Para mover el indicador debajo del tab seleccionado
                        .clip(shapes.small) // Darle un estilo redondeado
                )
            },
        ) {
            // transporte
            Tab(
                selected = currentRoute == Destinations.Transport.route,
                text = {
                    AnimatedContent(
                        targetState = currentRoute == Destinations.Transport.route, label = ""
                    ) { isSelected ->
                        if(isSelected) {
                            Text(
                                text = stringResource(id = R.string.lblTransport),
                                style = typography.headlineSmall
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Outlined.DirectionsBus,
                                contentDescription = stringResource(R.string.iconBus)
                            )
                        }
                    }
                },
                selectedContentColor = orange,
                unselectedContentColor = colorScheme.outline,
                onClick = {
                    mapsNavHostController.navigate(Destinations.Transport.route) {
                        launchSingleTop = true
                        popUpTo(0) { inclusive = true }
                    }
                }
            )

            // planifica
            Tab(
                selected = currentRoute == Destinations.Planner.route,
                text = {
                    AnimatedContent(
                        targetState = currentRoute == Destinations.Planner.route, label = ""
                    ) { isSelected ->
                        if(isSelected) {
                            Text(
                                text = stringResource(id = R.string.lblPlanner),
                                style = typography.headlineSmall
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Outlined.LocationOn,
                                contentDescription = stringResource(R.string.iconLocation)
                            )
                        }
                    }
                },
                selectedContentColor = orange,
                unselectedContentColor = colorScheme.outline,
                onClick = {
                    mapsNavHostController.navigate(Destinations.Planner.route) {
                        launchSingleTop = true
                        popUpTo(Destinations.Transport.route) { inclusive = false }
                    }
                }
            )

            // camina
            Tab(
                selected = currentRoute == Destinations.Fastest.route,
                text = {
                    AnimatedContent(
                        targetState = currentRoute == Destinations.Fastest.route, label = ""
                    ) { isSelected ->
                        if(isSelected) {
                            Text(
                                text = stringResource(id = R.string.lblWalking),
                                style = typography.headlineSmall
                            )
                        } else {
                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.DirectionsWalk,
                                contentDescription = stringResource(R.string.iconFootWalking)
                            )
                        }
                    }
                },
                selectedContentColor = orange,
                unselectedContentColor = colorScheme.outline,
                onClick = {
                    mapsNavHostController.navigate(Destinations.Fastest.route) {
                        launchSingleTop = true
                        popUpTo(Destinations.Transport.route) { inclusive = false }
                    }
                }
            )
        }
    }
}

@Preview
@Composable
private fun ActionsTopBar(
    onSettingsPressed: () -> Unit = {}
) {
    Row {
        IconButton(
            onClick = onSettingsPressed
        ) {
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = stringResource(id = R.string.iconOpenSettings)
            )
        }
    }
}