package net.streamroutes.sreamroutesapp.features.settings.presentation.maps

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.components.ColorField
import net.streamroutes.sreamroutesapp.features.components.ColorPickerDialog
import net.streamroutes.sreamroutesapp.features.components.InfoRowField
import net.streamroutes.sreamroutesapp.features.components.SliderField
import net.streamroutes.sreamroutesapp.features.components.SwitchField
import net.streamroutes.sreamroutesapp.features.settings.components.RadioField
import net.streamroutes.sreamroutesapp.features.settings.components.SettingsSmallTopAppBar

@Composable
fun MapsSettingsScreen(
    modifier: Modifier = Modifier
) {
    val colorState = rememberColorPickerController()

    var colorTransport by remember {
        mutableStateOf(Color.Black)
    }
    var colorNearStop by remember {
        mutableStateOf(Color.Green)
    }
    var widthLine by remember {
        mutableFloatStateOf(3f)
    }
    var mapType by remember {
        mutableStateOf(false)
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

    val onChangeProgress = { progress: Float ->
        widthLine = progress
    }
    val onBackPressed = {

    }

    var showTransportColor by remember { mutableStateOf(false) }
    val openTransportColor = { showTransportColor = !showTransportColor }
    var showNearStopColor by remember { mutableStateOf(false) }
    val openNearStopColor = { showNearStopColor = !showNearStopColor }

    if(showTransportColor) {
        ColorPickerDialog(
            colorState = colorState,
            initialColor = colorTransport,
            onColorChange = { color: Color ->
                colorTransport = color
            },
            onDismiss = openTransportColor
        )
    }

    if(showNearStopColor) {
        ColorPickerDialog(
            colorState = colorState,
            initialColor = colorNearStop,
            onColorChange = { color: Color ->
                colorNearStop = color
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
            modifier = Modifier.padding(innerPadding)
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
                value = mapType,
                iconTrue = Icons.Outlined.DarkMode,
                iconFalse = Icons.Outlined.LightMode,
                iconDescription = stringResource(R.string.iconThemeMode),
                onValueChange = { type ->
                    mapType = type
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
                colorTransport = colorTransport,
                onOpenPickerColor = openTransportColor
            )

            ColorField(
                headerText = stringResource(id = R.string.lblNearStopLineColor),
                descriptionText = stringResource(id = R.string.lblNearStopLineColorDescription),
                colorTransport = colorNearStop,
                onOpenPickerColor = openNearStopColor
            )

            SliderField(
                headerText = stringResource(id = R.string.lblWidthLine),
                descriptionText = stringResource(id = R.string.lblWidthLineDescription),
                progress = widthLine,
                onChangeProgress = {
                    onChangeProgress(it)
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

@Composable
fun CheckBoxField(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    title: String,
    description: String
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
            .fillMaxWidth()
            .clickable(
                indication = null,  // Elimina la animación de clic
                interactionSource = remember { MutableInteractionSource() }  // Previene el manejo de estados de interacción
            ) {
                onCheckedChange(!checked)  // Cambia el estado cuando se hace clic en la fila
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )

        InfoRowField(
            title = title,
            descriptionText = description,
            modifier = Modifier.weight(1f)
        )
    }
}
