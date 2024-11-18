package net.streamroutes.sreamroutesapp.features.settings.presentation.apparence

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.components.SwitchField
import net.streamroutes.sreamroutesapp.features.settings.components.SettingsSmallTopAppBar
import net.streamroutes.sreamroutesapp.features.settings.presentation.maps.MapSettingsViewModel

@Composable
fun ApparenceScreen(
    apparenceViewModel: ApparenceViewModel = hiltViewModel(),
    mapSettingsViewModel: MapSettingsViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    val theme by apparenceViewModel.theme.collectAsState()
    val onChangeTheme = { t: Boolean ->
        apparenceViewModel.changeTheme(t)
        mapSettingsViewModel.changeMapTheme(t)
    }

    val dynamicTheme by apparenceViewModel.dynaminc.collectAsState()
    val onEnableDynamic = { d: Boolean ->
        apparenceViewModel.enableDynamictheme(d)
    }


    Scaffold(
        topBar = {
            SettingsSmallTopAppBar(
                title = stringResource(R.string.lblApparence),
                onBackPressed = onBackPressed,
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            // tema oscuro o claro
            SwitchField(
                headerText = stringResource(R.string.lblTheme),
                descriptionText = stringResource(R.string.lblThemeDescription),
                iconTrue = Icons.Outlined.DarkMode,
                iconFalse = Icons.Outlined.LightMode,
                iconDescription = stringResource(R.string.iconThemeMode),
                value = theme,
                onValueChange = {
                    onChangeTheme(it)
                }
            )

            // tema dinamico
            SwitchField(
                headerText = stringResource(R.string.lblDynamicTheme),
                descriptionText = stringResource(R.string.lblDynamicThemeDescription),
                iconTrue = Icons.Outlined.Done,
                iconFalse = Icons.Outlined.Close,
                iconDescription = stringResource(R.string.iconDoneClose),
                value =  dynamicTheme,
                onValueChange = {
                    onEnableDynamic(it)
                }
            )
        }
    }
}