package net.streamroutes.sreamroutesapp.features.profile.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.profile.components.OutlinedTitleTextField
import net.streamroutes.sreamroutesapp.features.profile.components.ProfileSmallTopAppBar

@Preview(showBackground = true)
@Composable
fun EditAccountScreen(
    modifier: Modifier = Modifier
) {
    // no pasar el modifier, solo en caso de que no se coloree
    // si no se colorea, usar scaffold para encapsular las cosas

    var user by remember {
        mutableStateOf("")
    }

    var phone by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var confirmPassword by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        ProfileSmallTopAppBar(
            title = stringResource(id = R.string.lblEditAccount),
            onBackPressed = {

            }
        )

        Spacer(modifier = Modifier.size(16.dp))

        // usuario
        OutlinedTitleTextField(
            title = stringResource(id = R.string.txtUser),
            placeholder = stringResource(id = R.string.lblPlacelholderUser),
            value = user,
            onValueChange = {
                user = it
            },
            iconClear = stringResource(id = R.string.iconClearUser),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .align(Alignment.CenterHorizontally)
        )

        // telefono
        OutlinedTitleTextField(
            title = stringResource(id = R.string.txtPhone),
            placeholder = stringResource(id = R.string.lblPlacelholderPhone),
            value = phone,
            onValueChange = {
                phone = it
            },
            keyboardType = KeyboardType.Number,
            iconClear = stringResource(id = R.string.iconClearPhone),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .align(Alignment.CenterHorizontally)
        )

        // correo electronico
        OutlinedTitleTextField(
            title = stringResource(id = R.string.txtEmail),
            placeholder = stringResource(id = R.string.lblPlaceholderEmail),
            value = email,
            onValueChange = {
                email = it
            },
            iconClear = stringResource(id = R.string.iconClearEmail),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .align(Alignment.CenterHorizontally)
        )

        // contrasenia
        OutlinedTitleTextField(
            title = stringResource(id = R.string.txtPassword),
            placeholder = stringResource(id = R.string.lblPlacelholderPassword),
            value = password,
            onValueChange = {
                password = it
            },
            iconClear = stringResource(id = R.string.iconHidePassword),
            keyboardType = KeyboardType.Password,
            isPasswordField = true,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .align(Alignment.CenterHorizontally)
        )

        // confirmar contrasenia
        OutlinedTitleTextField(
            title = stringResource(id = R.string.txtConfirmPassword),
            placeholder = stringResource(id = R.string.lblPlacelholderConfirmPassword),
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
            },
            iconClear = stringResource(id = R.string.iconHidePassword),
            keyboardType = KeyboardType.Password,
            isPasswordField = true,
            imeAction = ImeAction.Done,
            onDone = {
                // ACTUALIZAR DATOS EN FIREBASE
            },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.weight(1f))

        // regresar a reditar perfil
        OutlinedButton(
            onClick = {
                // REGRESAR A EDITAR PERFIL
            },
            shape = shapes.small,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(0.9f)
        ) {
            Text(text = stringResource(id = R.string.btnBack))
        }

        // guardar informacion
        Button(
            onClick = {
                // ACTUALIZAR DATOS EN FIREBASE
            },
            shape = shapes.small,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(0.9f)
        ) {
            Text(text = stringResource(id = R.string.btnSave))
        }
    }
}