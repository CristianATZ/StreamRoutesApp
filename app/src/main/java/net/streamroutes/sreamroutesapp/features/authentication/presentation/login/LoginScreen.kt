package net.streamroutes.sreamroutesapp.features.authentication.presentation.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import net.streamroutes.sreamroutesapp.features.authentication.components.PrimaryTextButton
import net.streamroutes.sreamroutesapp.features.authentication.components.WhiteFilledTextField


@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = hiltViewModel(),
    onSignIn: () -> Unit,
    onSignUp: () -> Unit,
    onChangePassword: () -> Unit
) {
    val background = listOf(orange, yellow)
    val context = LocalContext.current
    // collectAsState para que se tome como estado observable
    val loginResult by loginViewModel.loginResult.collectAsState()
    val userData by loginViewModel.userData.collectAsState()

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    /**
     * Mensaje de resultado de autenticación en respuesta
     * al cambio de estaod de loginResult
     */
    loginResult?.let { success ->
        LaunchedEffect(success) {
            if (success) {
                Toast.makeText(context, "USUARIO AUTENTICADO ORA VALEEEEE", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "NO SE PUDO INICIAR SESION VALE", Toast.LENGTH_SHORT).show()
            }
        }
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
            modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            PrimaryTextButton(
                text = stringResource(id = R.string.btnForgot),
                onClick =  onChangePassword
            )
        }

        Spacer(modifier = Modifier.size(32.dp))

        // boton ingresar
        PrimaryFilledButton(
            text = stringResource(id = R.string.btnLogin),
            onClick =  {
                // AUTENTICAR USUARIO CON FIREBASE
                //loginViewModel.loginUser(email, password)
                onSignIn()
            },
            modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth()
        )

        // registrarse
        PrimaryTextButton(
            text = stringResource(id = R.string.btnNoAccount),
            onClick = onSignUp,
            modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth()
        )


    }
}

