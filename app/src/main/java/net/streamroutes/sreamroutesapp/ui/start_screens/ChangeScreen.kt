@file:OptIn(ExperimentalMaterial3Api::class)

package net.streamroutes.sreamroutesapp.ui.start_screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.navigation.AppScreens
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.viewmodel.routes.ChangeViewModel

@Composable
fun ChangeScreen(changeViewModel: ChangeViewModel, navController: NavController){
    Change(changeViewModel, navController)
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun Change(changeViewModel: ChangeViewModel, navController: NavController ){


    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current


    Scaffold(
        topBar = { TopAppBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(64.dp))

            // logo
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo"
            )

            Spacer(modifier = Modifier.size(64.dp))

            // contrasenia
            OutlinedTextField(
                value = changeViewModel.password,
                onValueChange = { changeViewModel.updatePassword(it) },
                label = {
                    Text(
                        text = stringResource(id = R.string.password_profile),
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                shape = RoundedCornerShape(16.dp),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            changeViewModel.updatePasswordVisibility(!changeViewModel.passwordVisibility)
                        }
                    ) {
                        Icon(
                            painter = if (!changeViewModel.passwordVisibility)
                                painterResource(id = R.drawable.visibilityoff) else painterResource(id = R.drawable.visibilityon),
                            contentDescription = "visibilidad contraseña",
                            modifier = Modifier
                                .size(32.dp)
                        )
                    }
                },
                visualTransformation = if (changeViewModel.passwordVisibility) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        changeViewModel.updatePasswordVisibility(true)
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
            )

            Spacer(modifier = Modifier.size(16.dp))

            // confimar contrasenia
            OutlinedTextField(
                value = changeViewModel.confirmPass,
                onValueChange = { changeViewModel.updateConfirmPass(it) },
                label = {
                    Text(
                        text = stringResource(id = R.string.confirm_password),
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                shape = RoundedCornerShape(16.dp),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            changeViewModel.updateConfirmPassVisibility(!changeViewModel.confirmPassVisibility)
                        }
                    ) {
                        Icon(
                            painter = if (!changeViewModel.confirmPassVisibility)
                                painterResource(id = R.drawable.visibilityoff) else painterResource(id = R.drawable.visibilityon),
                            contentDescription = "visibilidad contraseña",
                            modifier = Modifier
                                .size(32.dp)
                        )
                    }
                },
                visualTransformation = if (changeViewModel.confirmPassVisibility) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        changeViewModel.updateConfirmPassVisibility(true)
                        keyboardController?.hide()
                        navController.navigate(AppScreens.LoginScreen.route)
                    }
                ),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
            )

            // boton cambiar
            Button(
                onClick = {
                    navController.navigate(AppScreens.LoginScreen.route)
                },
                shape = RoundedCornerShape(16),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary
                ),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(top = 16.dp)
                    .height(50.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.change),
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            // boton cancelar
            TextButton(
                onClick = {
                    navController.navigate(AppScreens.LoginScreen.route)
                },
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(50.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.cancel),
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar(
    navController: NavController
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.change_password),
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.navigate(AppScreens.LoginScreen.route)
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowLeft,
                    contentDescription = "regresar al login"
                )
            }
        }
    )
}