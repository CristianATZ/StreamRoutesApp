package net.streamroutes.sreamroutesapp.features.settings.presentation.apparence

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.compose.RumappAppTheme
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.components.SwitchField
import net.streamroutes.sreamroutesapp.features.settings.components.SettingsSmallTopAppBar

enum class Theme {
    CLARO, OSCURO
}

@Composable
fun ApparenceScreen(
    modifier: Modifier = Modifier
) {
    val onBackPressed = {

    }

    var theme by remember {
        mutableStateOf(Theme.CLARO)
    }
    val onChangeTheme = { b: Theme ->
        theme = b
        // actualizar valor en el viewmodel
    }

    var dynamicTheme by remember {
        mutableStateOf(false)
    }
    val onChangeDynamicTheme = { b: Boolean ->
        dynamicTheme = b
        // actualizar valor en el viewmodel
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
                value =  theme == Theme.OSCURO,
                onValueChange = {
                    onChangeTheme(
                        if(it) Theme.OSCURO
                        else Theme.CLARO
                    )
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
                    onChangeDynamicTheme(it)
                }
            )
        }
    }
}