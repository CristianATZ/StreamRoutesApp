package net.streamroutes.sreamroutesapp.features.maps.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.RestartAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.components.ColorField
import net.streamroutes.sreamroutesapp.features.components.ColorPickerDialog
import net.streamroutes.sreamroutesapp.features.components.SliderField
import net.streamroutes.sreamroutesapp.features.components.SwitchField
import net.streamroutes.sreamroutesapp.features.settings.presentation.maps.MapSettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapsModalBottomSheet(
    sheetState: SheetState = rememberModalBottomSheetState(),
    mapSettingsViewModel: MapSettingsViewModel = hiltViewModel(),
    onDismiss: () -> Unit = {},
    onSave: () -> Unit = {}
) {
    val colorState = rememberColorPickerController()

    val r by mapSettingsViewModel.routeColor.collectAsState()
    var routeColor by remember {
        mutableStateOf(Color(r))
    }
    val s by mapSettingsViewModel.stopColor.collectAsState()
    var stopColor by remember {
        mutableStateOf(Color(s))
    }
    val l by mapSettingsViewModel.lineSize.collectAsState()
    var lineSize by remember {
        mutableFloatStateOf(l.toFloat())
    }
    val m by mapSettingsViewModel.mapTheme.collectAsState()
    var mapTheme by remember {
        mutableStateOf(mapSettingsViewModel.mapTheme.value)
    }

    val onRestartMapSettings = {
        mapTheme = mapSettingsViewModel.mapTheme.value
        routeColor = Color(mapSettingsViewModel.routeColor.value)
        stopColor = Color(mapSettingsViewModel.stopColor.value)
        lineSize = mapSettingsViewModel.lineSize.value.toFloat()
    }

    val onChangeProgress = { progress: Float ->
        lineSize = progress
    }

    val onSaveMapSettings = {
        mapSettingsViewModel.changeMapTheme(mapTheme)
        mapSettingsViewModel.changeStopColor(stopColor)
        mapSettingsViewModel.changeRouteColor(routeColor)
        mapSettingsViewModel.changeLineSize(lineSize.toInt())
        onSave()
    }

    var showTransportColor by remember { mutableStateOf(false) }
    val openTransportColor = { showTransportColor = !showTransportColor }
    var showNearStopColor by remember { mutableStateOf(false) }
    val openNearStopColor = { showNearStopColor = !showNearStopColor }

    if(showTransportColor) {
        ColorPickerDialog(
            colorState = colorState,
            initialColor = routeColor,
            onColorChange = { color: Color ->
                routeColor = color
            },
            onDismiss = openTransportColor
        )
    }

    if(showNearStopColor) {
        ColorPickerDialog(
            colorState = colorState,
            initialColor = stopColor,
            onColorChange = { color: Color ->
                stopColor = color
            },
            onDismiss = openNearStopColor
        )
    }

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.lblMapSettings),
                    style = typography.headlineSmall,
                    modifier = Modifier.align(Alignment.Center)
                )

                FilledTonalIconButton(
                    onClick = onRestartMapSettings,
                    colors = IconButtonDefaults.filledTonalIconButtonColors(
                        containerColor = colorScheme.tertiaryContainer,
                        contentColor = colorScheme.onTertiaryContainer
                    ),
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.RestartAlt,
                        contentDescription = stringResource(id = R.string.iconRestart)
                    )
                }
            }

            Spacer(modifier = Modifier.size(16.dp))

            ColorField(
                headerText = stringResource(id = R.string.lblTransportLineColor),
                descriptionText = stringResource(id = R.string.lblTrasnportLineColorDescription),
                colorTransport = routeColor,
                onOpenPickerColor = openTransportColor
            )

            ColorField(
                headerText = stringResource(id = R.string.lblNearStopLineColor),
                descriptionText = stringResource(id = R.string.lblNearStopLineColorDescription),
                colorTransport = stopColor,
                onOpenPickerColor = openNearStopColor
            )

            SliderField(
                headerText = stringResource(id = R.string.lblWidthLine),
                descriptionText = stringResource(id = R.string.lblWidthLineDescription),
                progress = lineSize,
                onChangeProgress = {
                    onChangeProgress(it)
                }
            )

            SwitchField(
                headerText = stringResource(id = R.string.lblMapType),
                descriptionText = stringResource(id = R.string.lblMapTypeDescription),
                value = mapTheme,
                iconTrue = Icons.Outlined.DarkMode,
                iconFalse = Icons.Outlined.LightMode,
                iconDescription = stringResource(R.string.iconThemeMode),
                onValueChange = { type ->
                    mapTheme = type
                }
            )

            Button(
                onClick = onSaveMapSettings,
                shape = shapes.small,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.btnSave))
            }

            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}