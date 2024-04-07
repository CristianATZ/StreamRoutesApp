package net.streamroutes.sreamroutesapp.Screens.ConfigurationScreens

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.viewmodel.ConfigurationViewModel

data class ConfItem(
    @StringRes val name: Int,
    val configuration: ConfigurationSelection,
    val composable: @Composable () -> Unit
)

enum class ConfigurationSelection{
    NONE,
    CITY,
    NOTIFICATIONS,
    MAP,
    PRIVACY,
    LANGUAGE,
    THEME
}

@Composable
fun ConfigurationScreen(configurationViewModel: ConfigurationViewModel){
    var selection by remember {
        mutableStateOf(ConfigurationSelection.NONE)
    }

    val confItems = listOf(
        ConfItem(name = R.string.lblCiudad, configuration = ConfigurationSelection.CITY) { CityOptions(configurationViewModel) },
        ConfItem(name = R.string.lblNoti, configuration = ConfigurationSelection.NOTIFICATIONS) { NotificationOptions(configurationViewModel) },
        ConfItem(name = R.string.lblMapa, configuration = ConfigurationSelection.MAP) { MapOptions(configurationViewModel) },
        ConfItem(name = R.string.lblPrivacidad, configuration = ConfigurationSelection.PRIVACY) { PrivacyOptions(configurationViewModel) },
        ConfItem(name = R.string.lblIdioma, configuration = ConfigurationSelection.LANGUAGE) { LanguageOptions(configurationViewModel) },
        ConfItem(name = R.string.lblTema, configuration = ConfigurationSelection.THEME) { ThemeOptions(configurationViewModel) }
    )


    Scaffold(
        topBar = { TopBarConfiguration() },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            confItems.forEach { item ->
                Options(name = item.name, isSelected = selection == item.configuration) {
                    selection = if(selection == item.configuration)
                        ConfigurationSelection.NONE
                    else
                        item.configuration
                }

                if(selection == item.configuration){
                    item.composable()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarConfiguration() {
    CenterAlignedTopAppBar(title = { Text(text = "Configuracion") })
}

@Composable
private fun Options(
    @StringRes name: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {


    val background = if(isSelected){
        MaterialTheme.colorScheme.surfaceVariant
    } else {
        MaterialTheme.colorScheme.background
    }

    val textColor = if(isSelected){
        MaterialTheme.colorScheme.onSurfaceVariant
    } else {
        MaterialTheme.colorScheme.onBackground
    }

    val fontWeight = if(isSelected){
        FontWeight.Bold
    } else {
        FontWeight.Normal
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background( background )
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = stringResource(id = name), // texto
            style = typography.bodyLarge,
            color = textColor,
            fontWeight = fontWeight
        )
    }
}

@Composable
private fun CityOptions(configurationViewModel: ConfigurationViewModel) {
    CityScreen(configurationViewModel)
}

@Composable
private fun NotificationOptions(configurationViewModel: ConfigurationViewModel) {
    //Text(text = "Elegir los ajustes de las notificaciones")
    NotificationsScreen(configurationViewModel)
}

@Composable
private fun MapOptions(configurationViewModel: ConfigurationViewModel) {
    //Text(text = "Elegir los ajustes del mapa")
    MapOptionsScreen(configurationViewModel)
}

@Composable
private fun PrivacyOptions(configurationViewModel: ConfigurationViewModel) {
    //Text(text = "Elegir los ajustes de privacidad")
    PrivacityScreen(configurationViewModel)
}

@Composable
private fun LanguageOptions(configurationViewModel: ConfigurationViewModel) {
    LanguageScreen(configurationViewModel)
}

@Composable
fun ThemeOptions(configurationViewModel: ConfigurationViewModel) {
    ThemeScreen(configurationViewModel)
}