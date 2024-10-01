package net.streamroutes.sreamroutesapp.features.authentication.presentation.password

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import net.streamroutes.sreamroutesapp.features.authentication.components.PrimaryOutlinedButton
import net.streamroutes.sreamroutesapp.features.authentication.components.WhiteFilledTextField

@Preview
@Composable
fun PasswordScreen(
    modifier: Modifier = Modifier
) {
    RumappAppTheme {
        val background = listOf(orange, yellow)

        var email by remember {
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

            // encabezado cambiar
            DisplayText(
                text = stringResource(id = R.string.lblChange)
            )
            // encabezado contrasenia
            DisplayText(
                text = stringResource(id = R.string.lblPassword)
            )

            Spacer(modifier = Modifier.size(64.dp))

            // textfield correo electronico
            WhiteFilledTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = stringResource(id = R.string.txtEmail),
                imeAction = ImeAction.Done,
                onDone = {
                    // ENVIAR CORREO DE FIREBASE
                }
            )

            Spacer(modifier = Modifier.size(32.dp))

            // boton enviar correo
            PrimaryFilledButton(
                text = stringResource(id = R.string.btnSendEmail),
                onClick =  {
                    // ENVIAR CORREO DE FIREBASE
                }
            )

            // boton regresar
            PrimaryOutlinedButton(
                text = stringResource(id = R.string.btnBack),
                onClick = {
                    // REGRESAR AL LOGIN
                }
            )
        }
    }
}