@file:OptIn(ExperimentalMaterial3Api::class)

package net.streamroutes.sreamroutesapp.ui.start_screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockReset
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.utils.MyViewModel
import net.streamroutes.sreamroutesapp.navigation.AppScreens
import net.streamroutes.sreamroutesapp.R

@Composable
fun RegistrationScreen (myViewModel: MyViewModel, navController: NavController) {
    Registration(myViewModel,navController)
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Registration (myViewModel: MyViewModel, navController: NavController) {
    var telefono by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(true) }
    var confirmPass by remember { mutableStateOf("") }
    var confirmPassVisibility by remember { mutableStateOf(true) }

    val brush = Brush.verticalGradient(
        listOf(Color(0xFFE8AA42), Color(0xFFEACE43))
    )

    Column(
        modifier = Modifier
            .background(brush)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.size(64.dp))

        // logo
        Image(
            painter = painterResource(id = R.drawable.logo_navbar_2),
            contentDescription = "logo",
            Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.size(64.dp))

        // telefono
        CustomOutlinedTextField(
            value = telefono,
            onValueChange = {telefono = it},
            placeholderText = stringResource(id = R.string.txtTelefono),
            leadingIcon = Icons.Filled.Phone,
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        )

        Spacer(modifier = Modifier.size(16.dp))

        // contrasenia
        CustomOutlinedTextField(
            value = password,
            onValueChange = {password = it},
            placeholderText = stringResource(id = R.string.txtContra),
            leadingIcon = Icons.Filled.Lock,
            visualTransformation = if (passwordVisibility) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                IconButton(
                    onClick = {
                        passwordVisibility = !passwordVisibility
                    }
                ) {
                    Icon(
                        painter = if (!passwordVisibility)
                            painterResource(id = R.drawable.visibilityoff) else painterResource(id = R.drawable.visibilityon),
                        contentDescription = "visibilidad contraseña",
                        modifier = Modifier
                            .size(28.dp)
                    )
                }
            },
            imeAction = ImeAction.Next
        )

        Spacer(modifier = Modifier.size(32.dp))

        // confimar contrasenia
        CustomOutlinedTextField(
            value = confirmPass,
            onValueChange = {confirmPass = it},
            placeholderText = stringResource(id = R.string.txtConfrimarContra),
            leadingIcon = Icons.Filled.LockReset,
            visualTransformation = if (confirmPassVisibility) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                IconButton(
                    onClick = {
                        confirmPassVisibility = !confirmPassVisibility
                    }
                ) {
                    Icon(
                        painter = if (!confirmPassVisibility)
                            painterResource(id = R.drawable.visibilityoff) else painterResource(id = R.drawable.visibilityon),
                        contentDescription = "visibilidad contraseña",
                        modifier = Modifier
                            .size(28.dp)
                    )
                }
            },
            imeAction = ImeAction.Done
        )

        // boton registgrar
        Button(
            onClick = {
                navController.navigate(AppScreens.LoginScreen.route)
            },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorScheme.tertiaryContainer,
                contentColor = colorScheme.onTertiaryContainer
            ),
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 4.dp
            ),
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(top = 16.dp)
                .height(50.dp)
        ) {
            Text(
                text = stringResource(id = R.string.btnRegistrarme),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        // boton cancelar
        TextButton(
            onClick = {
                navController.navigate(AppScreens.LoginScreen.route)
            },
            shape = CircleShape,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(top = 8.dp)
                .height(50.dp)
        ) {
            Text(
                text = stringResource(id = R.string.btnCancelar),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}