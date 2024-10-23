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
import com.example.compose.orange
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.components.CustomTopAppBar
import net.streamroutes.sreamroutesapp.features.components.NavigationButton

@Preview
@Composable
fun MapsSmallTopAppBar(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
    currentTab: Int = 1,
    onChangeTab: (Int) -> Unit = {},
    onSettingsPressed: () -> Unit = {}
) {

    val tabs = listOf(
        Triple(stringResource(id = R.string.lblTransport), Icons.Outlined.DirectionsBus, stringResource(id = R.string.iconBus)),
        Triple(stringResource(id = R.string.lblPlanner), Icons.Outlined.LocationOn, stringResource(id = R.string.iconLocation)),
        Triple(stringResource(id = R.string.lblWalking), Icons.AutoMirrored.Outlined.DirectionsWalk, stringResource(id = R.string.iconFootWalking))
    )

    Column {
        CustomTopAppBar(
            title = {

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
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = index == currentTab,
                    text = {
                        AnimatedContent(
                            targetState = index == currentTab, label = ""
                        ) { isSelected ->
                            if(isSelected) {
                                Text(
                                    text = tab.first,
                                    style = typography.headlineSmall
                                )
                            } else {
                                Icon(
                                    imageVector = tab.second,
                                    contentDescription = tab.third
                                )
                            }
                        }
                    },
                    selectedContentColor = orange,
                    unselectedContentColor = colorScheme.outline,
                    onClick = {
                        onChangeTab(index)
                    }
                )
            }
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