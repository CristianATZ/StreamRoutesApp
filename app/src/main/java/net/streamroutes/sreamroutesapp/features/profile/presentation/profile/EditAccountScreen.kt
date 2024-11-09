package net.streamroutes.sreamroutesapp.features.profile.presentation.profile

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.core.domain.model.User
import net.streamroutes.sreamroutesapp.features.profile.components.OutlinedTitleTextField
import net.streamroutes.sreamroutesapp.features.profile.components.ProfileSmallTopAppBar

@Composable
fun EditAccountScreen(
    profileViewModel: ProfileViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    // no pasar el modifier, solo en caso de que no se coloree
    // si no se colorea, usar scaffold para encapsular las cosas

    val context = LocalContext.current
    val updateResult by profileViewModel.updateResult.collectAsState()
    val userData by profileViewModel.userData.collectAsState()

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


    /**
     * Cambiar valor de las variables en base a userData
     */
    LaunchedEffect(userData) {
        userData?.let {
            user = it.username
            phone = it.phoneNumber
            email = it.email
        }
    }


    /**
     * Cambio de estado de la vriable updateResult para indicar si se pudo o
     * no realizar el cambio de información del usuario
     */
    updateResult?.let { success ->
        LaunchedEffect(success) {
            if(success){
                Toast.makeText(context, "Información actualizada", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "No fue posible actualizar los datos del usuario", Toast.LENGTH_SHORT).show()
            }
        }
    }


    Scaffold(
        topBar = {
            ProfileSmallTopAppBar(
                title = stringResource(id = R.string.lblEditAccount),
                onBackPressed = onBackPressed
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.size(16.dp))

            // usuario
            OutlinedTitleTextField(
                title = stringResource(id = R.string.txtUser),
                placeholder = stringResource(id = R.string.lblPlacelholderUser),
                value = user,
                onValueChange = { u ->
                    user = u
                },
                iconClear = stringResource(id = R.string.iconClearUser),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )

            // telefono
            OutlinedTitleTextField(
                title = stringResource(id = R.string.txtPhone),
                placeholder = stringResource(id = R.string.lblPlacelholderPhone),
                value = phone,
                onValueChange = { p ->
                    phone = p
                },
                keyboardType = KeyboardType.Number,
                iconClear = stringResource(id = R.string.iconClearPhone),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )

            // correo electronico
            OutlinedTitleTextField(
                title = stringResource(id = R.string.txtEmail),
                placeholder = stringResource(id = R.string.lblPlaceholderEmail),
                value = email,
                onValueChange = { e ->
                    email = e
                },
                iconClear = stringResource(id = R.string.iconClearEmail),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )

            // contrasenia
            OutlinedTitleTextField(
                title = stringResource(id = R.string.txtPassword),
                placeholder = stringResource(id = R.string.lblPlacelholderPassword),
                value = password,
                onValueChange = { p ->
                    password = p
                },
                iconClear = stringResource(id = R.string.iconHidePassword),
                keyboardType = KeyboardType.Password,
                isPasswordField = true,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )

            // confirmar contrasenia
            OutlinedTitleTextField(
                title = stringResource(id = R.string.txtConfirmPassword),
                placeholder = stringResource(id = R.string.lblPlacelholderConfirmPassword),
                value = confirmPassword,
                onValueChange = { c ->
                    confirmPassword = c
                },
                iconClear = stringResource(id = R.string.iconHidePassword),
                keyboardType = KeyboardType.Password,
                isPasswordField = true,
                imeAction = ImeAction.Done,
                onDone = {
                    // ACTUALIZAR DATOS EN FIREBASE
                },
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.weight(1f))

            // regresar a reditar perfil
            OutlinedButton(
                onClick = onBackPressed,
                shape = shapes.small,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.btnBack))
            }

            // guardar informacion
            Button(
                onClick = {
                    // ACTUALIZAR DATOS EN FIREBASE
                    val updUser = User(
                        username = user,
                        phoneNumber = phone,
                        email = email
                    )
                    profileViewModel.updateUserData(updUser, password)
                },
                shape = shapes.small,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.btnSave))
            }

            Spacer(Modifier.size(16.dp))
        }
    }
}