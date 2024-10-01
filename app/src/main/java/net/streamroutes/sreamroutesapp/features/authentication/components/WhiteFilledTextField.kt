package net.streamroutes.sreamroutesapp.features.authentication.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R

@Preview
@Composable
fun WhiteFilledTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    iconClear: String = "",
    placeholder: String = "",
    isPasswordField: Boolean = false,
    imeAction: ImeAction = ImeAction.Next,
    onDone: () -> Unit = {}
) {
    // Obtener el controlador del teclado
    val keyboardController = LocalSoftwareKeyboardController.current

    // Varaible para controlar la visibilidad de la contrasenia
    var passwordVisible by remember { mutableStateOf(false) }

    // Visual transformation para mostrar u ocultar la contrasenia
    val visualTransformation = if (isPasswordField && !passwordVisible) {
        PasswordVisualTransformation() // Transformación visual para contraseña
    } else {
        VisualTransformation.None // No aplicar transformación visual
    }

    // tipo de teclado, cambia si es contrasenia o no
    val keyboardType = if (isPasswordField) {
        KeyboardType.Password // Tipo de teclado para contraseñas
    } else {
        KeyboardType.Text // Tipo de teclado para texto normal
    }

    // trailing icon. cambia si es contrasenia o no
    val trailingIcon = @Composable {
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

    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        placeholder = {
            Text(
                text = placeholder,
                fontWeight = FontWeight.Bold,
                color = Color.Black.copy(0.5f)
            )
        },
        shape = shapes.medium,
        singleLine = true,
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
        trailingIcon = {
            if(value.isNotEmpty()){
                trailingIcon()
            }
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = modifier
            .shadow(4.dp, shapes.small)
            .fillMaxWidth(0.9f)
    )
}

@Composable
fun PasswordTrailingIcon(
    passwordVisible: Boolean,
    onToggleVisibility: () -> Unit
) {
    IconButton(onClick = { onToggleVisibility() }) {
        Icon(
            imageVector = if (passwordVisible) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
            contentDescription = if (passwordVisible) {
                stringResource(id = R.string.iconHidePassword)
            } else {
                stringResource(id = R.string.iconShowPassword)
            },
            tint = Color.Black.copy(0.5f)
        )
    }
}