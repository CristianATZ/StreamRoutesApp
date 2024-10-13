package net.streamroutes.sreamroutesapp.features.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.ColorPickerController
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import net.streamroutes.sreamroutesapp.R

@Composable
fun ColorPickerDialog(
    colorState: ColorPickerController,
    initialColor: Color,
    onColorChange: (Color) -> Unit,
    onDismiss: () -> Unit,
) {

    var currentColor by remember {
        mutableStateOf(initialColor)
    }

    Dialog(
        onDismissRequest = onDismiss
    ) {
        Card {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.size(16.dp))

                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.lblChoiceColor),
                        style = typography.headlineSmall,
                        modifier = Modifier.align(Alignment.Center)
                    )

                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = stringResource(id = R.string.iconClose)
                        )
                    }
                }

                HsvColorPicker(
                    controller = colorState,
                    initialColor = currentColor,
                    onColorChanged = {
                        currentColor = it.color
                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .height(250.dp)
                        .width(250.dp)
                )

                Spacer(modifier = Modifier.size(8.dp))

                BrightnessSlider(
                    controller = colorState,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .height(50.dp)
                )

                Row(
                    modifier = Modifier.padding(16.dp)
                ) {
                    InfoRowField(
                        title = stringResource(id = R.string.lblSelectedColor),
                        descriptionText = stringResource(id = R.string.lblSelectedColorDescription),
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .height(50.dp)
                            .background(currentColor, shapes.medium)
                    )
                }

                Button(
                    onClick = {
                        onColorChange(currentColor)
                        onDismiss()
                    },
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
}