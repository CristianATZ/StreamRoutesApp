package net.streamroutes.sreamroutesapp.features.authentication.presentation.password

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose.orange
import com.example.compose.yellow
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.authentication.components.DisplayText
import net.streamroutes.sreamroutesapp.features.authentication.components.PrimaryFilledButton
import net.streamroutes.sreamroutesapp.features.authentication.components.PrimaryOutlinedButton
import net.streamroutes.sreamroutesapp.features.authentication.components.WhiteFilledTextField


@Composable
fun PasswordScreen(
    passwordViewModel: PasswordViewModel = hiltViewModel(),
    onBackSignIn: () -> Unit
) {
    val background = listOf(orange, yellow)
    val context = LocalContext.current
    val resetPasswordResult by passwordViewModel.resetPasswordResult.collectAsState()

    var email by remember {
        mutableStateOf("")
    }


    /**
     * Mensaje de resultado de autenticación en respuesta
     * al cambio de contraseña
     */
    resetPasswordResult?.let { success ->
        LaunchedEffect(success) {
            if (success) {
                Toast.makeText(context, "CORREO ENVIADO", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "NO SE VA A PODER CAMBIAR LA PASS GALLO", Toast.LENGTH_SHORT).show()
            }
        }
    }


    Column(
        modifier = Modifier
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
                passwordViewModel.resetPassword(email)
            },
            modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth()
        )

        // boton regresar
        PrimaryOutlinedButton(
            text = stringResource(id = R.string.btnBack),
            onClick = onBackSignIn
        )
    }
}