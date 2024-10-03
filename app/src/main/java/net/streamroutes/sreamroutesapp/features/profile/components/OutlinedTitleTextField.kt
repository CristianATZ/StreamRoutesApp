package net.streamroutes.sreamroutesapp.features.profile.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.features.authentication.components.PasswordTrailingIcon

@Preview(showBackground = true)
@Composable
fun OutlinedTitleTextField(
    modifier: Modifier = Modifier,
    title: String = "Title",
    value: String = "",
    onValueChange: (String) -> Unit = {},
    iconClear: String = "",
    placeholder: String = "",
    prefix: String = "",
    singleLine: Boolean = true,
    isPasswordField: Boolean = false,
    readOnly: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onDone: () -> Unit = {},
    trailingIcon: @Composable (() -> Unit)? = null // Parametro para el icono configurable
) {
    // Obtener el controlador del teclado
    val keyboardController = LocalSoftwareKeyboardController.current

    // Variable para controlar la visibilidad de la contrasenia
    var passwordVisible by remember { mutableStateOf(false) }

    // Visual transformation para mostrar u ocultar la contrasenia
    val visualTransformation = if (isPasswordField && !passwordVisible) {
        PasswordVisualTransformation() // Transformación visual para contraseña
    } else {
        VisualTransformation.None // No aplicar transformación visual
    }

    // trailing icon. Cambia si es campo de contraseña o si se especifica un icono personalizado
    val defaultTrailingIcon = @Composable {
        if (isPasswordField) {
            PasswordTrailingIcon(passwordVisible) {
                passwordVisible = !passwordVisible
            }
        } else {
            IconButton(onClick = { onValueChange("") }) {
                Icon(
                    imageVector = Icons.Outlined.Clear,
                    contentDescription = iconClear,
                    tint = Color.Black.copy(0.5f)
                )
            }
        }
    }

    Column(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            style = typography.labelLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = value,
            onValueChange = { onValueChange(it) },
            placeholder = {
                Text(
                    text = placeholder
                )
            },
            shape = shapes.medium,
            singleLine = singleLine,
            readOnly = readOnly,
            visualTransformation = visualTransformation,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction // Acción del botón en el teclado
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    onDone()
                },
            ),
            prefix = {
                Text(text = prefix, modifier = Modifier.graphicsLayer(alpha = 0.5f))
            },
            trailingIcon = {
                if (isPasswordField || value.isNotEmpty() || trailingIcon != null ) {
                    trailingIcon?.invoke() ?: defaultTrailingIcon()
                }
            },
            modifier = modifier
                .fillMaxWidth()
        )
    }
}