package net.streamroutes.sreamroutesapp.features.authentication.presentation.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.compose.RumappAppTheme
import com.example.compose.orange
import com.example.compose.yellow
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.authentication.components.DisplayText
import net.streamroutes.sreamroutesapp.features.authentication.components.PrimaryFilledButton
import net.streamroutes.sreamroutesapp.features.authentication.components.PrimaryOutlinedButton
import net.streamroutes.sreamroutesapp.features.authentication.components.WhiteFilledTextField

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier
) {
    RumappAppTheme {
        val background = listOf(orange, yellow)

        var user by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }

        // Usar LazyColumn para permitir el desplazamiento
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(background)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.size(64.dp))

                // Encabezado crear
                DisplayText(text = stringResource(id = R.string.lblCreate))

                // Encabezado cuenta
                DisplayText(text = stringResource(id = R.string.lblAccount))

                Spacer(modifier = Modifier.size(64.dp))

                // TextField usuario
                WhiteFilledTextField(
                    value = user,
                    onValueChange = { user = it },
                    placeholder = stringResource(id = R.string.txtUser)
                )

                Spacer(modifier = Modifier.size(16.dp))

                // TextField correo electrónico
                WhiteFilledTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = stringResource(id = R.string.txtEmail)
                )

                Spacer(modifier = Modifier.size(16.dp))

                // TextField contraseña
                WhiteFilledTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = stringResource(id = R.string.txtPassword),
                    isPasswordField = true
                )

                Spacer(modifier = Modifier.size(16.dp))

                // TextField confirmar contraseña
                WhiteFilledTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    placeholder = stringResource(id = R.string.txtConfirmPassword),
                    isPasswordField = true,
                    imeAction = ImeAction.Done,
                    onDone = {
                        // REGISTRAR USUARIO
                    }
                )

                Spacer(modifier = Modifier.size(32.dp))

                // Botón registrar
                PrimaryFilledButton(
                    text = stringResource(id = R.string.btnRegister),
                    onClick = {
                        // REGISTRAR USUARIO
                    }
                )

                // Botón regresar
                PrimaryOutlinedButton(
                    text = stringResource(id = R.string.btnBack),
                    onClick = {
                        // REGRESAR AL LOGIN
                    }
                )
            }
        }
    }
}
