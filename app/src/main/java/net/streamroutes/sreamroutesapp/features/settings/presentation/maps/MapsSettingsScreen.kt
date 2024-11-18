package net.streamroutes.sreamroutesapp.features.settings.presentation.maps

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.components.ColorField
import net.streamroutes.sreamroutesapp.features.components.ColorPickerDialog
import net.streamroutes.sreamroutesapp.features.components.SliderField
import net.streamroutes.sreamroutesapp.features.components.SwitchField
import net.streamroutes.sreamroutesapp.features.settings.components.CheckBoxField
import net.streamroutes.sreamroutesapp.features.settings.components.SettingsSmallTopAppBar

@Composable
fun MapsSettingsScreen(
    mapSettingsViewModel: MapSettingsViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    val colorState = rememberColorPickerController()

    val mapTheme by mapSettingsViewModel.mapTheme.collectAsState()
    val changeMapTheme = { m: Boolean ->
        mapSettingsViewModel.changeMapTheme(m)
    }

    val routeColor by mapSettingsViewModel.routeColor.collectAsState()
    val changeRouteColor = { c: Color ->
        mapSettingsViewModel.changeRouteColor(c)
    }

    val stopColor by mapSettingsViewModel.stopColor.collectAsState()
    val changeStopColor = { s: Color ->
        mapSettingsViewModel.changeStopColor(s)
    }

    val lineSize by mapSettingsViewModel.lineSize.collectAsState()
    val changeLineSize = { l: Int->
        mapSettingsViewModel.changeLineSize(l)
    }

    var terminal by remember {
        mutableStateOf(false)
    }

    var food by remember {
        mutableStateOf(false)
    }

    var health by remember {
        mutableStateOf(false)
    }

    var showTransportColor by remember { mutableStateOf(false) }
    val openTransportColor = { showTransportColor = !showTransportColor }
    var showNearStopColor by remember { mutableStateOf(false) }
    val openNearStopColor = { showNearStopColor = !showNearStopColor }

    if(showTransportColor) {
        ColorPickerDialog(
            colorState = colorState,
            initialColor = Color(routeColor),
            onColorChange = { color: Color ->
                changeRouteColor(color)
            },
            onDismiss = openTransportColor
        )
    }

    if(showNearStopColor) {
        ColorPickerDialog(
            colorState = colorState,
            initialColor = Color(stopColor),
            onColorChange = { color: Color ->
                changeStopColor(color)
            },
            onDismiss = openNearStopColor
        )
    }

    Scaffold(
        topBar = {
            SettingsSmallTopAppBar(
                title = stringResource(R.string.lblMaps),
                onBackPressed = onBackPressed,
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).verticalScroll(rememberScrollState())
        ) {


            Text(
                text = stringResource(R.string.lblMap),
                style = typography.labelMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 8.dp, top = 16.dp)
                    .graphicsLayer(alpha = 0.5f)
            )

            SwitchField(
                headerText = stringResource(id = R.string.lblMapType),
                descriptionText = stringResource(id = R.string.lblMapTypeDescription),
                value = mapTheme,
                iconTrue = Icons.Outlined.DarkMode,
                iconFalse = Icons.Outlined.LightMode,
                iconDescription = stringResource(R.string.iconThemeMode),
                onValueChange = {
                    changeMapTheme(it)
                }
            )

            HorizontalDivider(modifier = Modifier.graphicsLayer(alpha = 0.5f))

            Text(
                text = stringResource(R.string.lblLineSettings),
                style = typography.labelMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 8.dp, top = 16.dp)
                    .graphicsLayer(alpha = 0.5f)
            )

            ColorField(
                headerText = stringResource(id = R.string.lblTransportLineColor),
                descriptionText = stringResource(id = R.string.lblTrasnportLineColorDescription),
                colorTransport = Color(routeColor),
                onOpenPickerColor = openTransportColor
            )

            ColorField(
                headerText = stringResource(id = R.string.lblNearStopLineColor),
                descriptionText = stringResource(id = R.string.lblNearStopLineColorDescription),
                colorTransport = Color(stopColor),
                onOpenPickerColor = openNearStopColor
            )

            SliderField(
                headerText = stringResource(id = R.string.lblWidthLine),
                descriptionText = stringResource(id = R.string.lblWidthLineDescription),
                progress = lineSize.toFloat(),
                onChangeProgress = {
                    changeLineSize(it.toInt())
                }
            )

            HorizontalDivider(modifier = Modifier.graphicsLayer(alpha = 0.5f))

            Text(
                text = stringResource(R.string.lblMapsMarker),
                style = typography.labelMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 8.dp, top = 16.dp)
                    .graphicsLayer(alpha = 0.5f)
            )

            CheckBoxField(
                checked = terminal,
                onCheckedChange = { isChecked ->
                    terminal = isChecked
                },
                title = stringResource(R.string.lblBusTerminals),
                description = stringResource(R.string.lblBusTerminalsDescription)
            )

            CheckBoxField(
                checked = food,
                onCheckedChange = { isChecked ->
                    food = isChecked
                },
                title = stringResource(R.string.lblFood),
                description = stringResource(R.string.lblFoodDescription)
            )

            CheckBoxField(
                checked = health,
                onCheckedChange = { isChecked ->
                    health = isChecked
                },
                title = stringResource(R.string.lblHealthy),
                description = stringResource(R.string.lblHealthyDescription)
            )
        }
    }
}