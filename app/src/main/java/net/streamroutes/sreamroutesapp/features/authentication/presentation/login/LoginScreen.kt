package net.streamroutes.sreamroutesapp.features.authentication.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.RumappAppTheme
import com.example.compose.orange
import com.example.compose.yellow
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.authentication.components.DisplayText
import net.streamroutes.sreamroutesapp.features.authentication.components.PrimaryFilledButton
import net.streamroutes.sreamroutesapp.features.authentication.components.PrimaryTextButton
import net.streamroutes.sreamroutesapp.features.authentication.components.WhiteFilledTextField

@Preview
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {
    RumappAppTheme {
        val background = listOf(orange, yellow)

        var email by remember {
            mutableStateOf("")
        }

        var password by remember {
            mutableStateOf("")
        }

        Column(
            modifier = modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(background)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(64.dp))
            
            // encabezado iniciar
            DisplayText(
                text = stringResource(id = R.string.lblLog)
            )
            // encabezado sesion
            DisplayText(
                text = stringResource(id = R.string.lblIn)
            )

            Spacer(modifier = Modifier.size(64.dp))

            // textfield correo
            WhiteFilledTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = stringResource(id = R.string.txtEmail),
                imeAction = ImeAction.Next
            )

            Spacer(modifier = Modifier.size(16.dp))

            // texfield password
            WhiteFilledTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = stringResource(id = R.string.txtPassword),
                isPasswordField = true,
                imeAction = ImeAction.Done,
                onDone = {
                    // AUTENTICAR USUARIO CON FIREBASE
                }
            )

            // olvide mi contrasenia
            Row(
                modifier = Modifier.fillMaxWidth(0.9f),
                horizontalArrangement = Arrangement.End
            ) {
                PrimaryTextButton(
                    text = stringResource(id = R.string.btnForgot),
                )
            }

            Spacer(modifier = Modifier.size(32.dp))

            // boton ingresar
            PrimaryFilledButton(
                text = stringResource(id = R.string.btnLogin),
                onClick =  {
                    // AUTENTICAR USUARIO CON FIREBASE
                }
            )

            // registrarse
            PrimaryTextButton(
                text = stringResource(id = R.string.btnNoAccount),
                modifier = Modifier.fillMaxWidth(0.9f)
            )
        }
    }
}