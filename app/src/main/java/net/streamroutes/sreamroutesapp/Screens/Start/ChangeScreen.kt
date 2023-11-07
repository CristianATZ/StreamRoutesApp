@file:OptIn(ExperimentalMaterial3Api::class)

package net.streamroutes.sreamroutesapp.Screens.Start

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R

@Composable
fun ChangeScreen(myViewModel: MyViewModel,navController: NavController){
    Change(myViewModel,navController)
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun Change( myViewModel: MyViewModel,navController: NavController ){
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(true) }
    var confirmPass by remember { mutableStateOf("") }
    var confirmPassVisibility by remember { mutableStateOf(true) }

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current


    Scaffold(
        topBar = { TopBar(navController,myViewModel) }
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
                value = password,
                onValueChange = { password = it },
                label = {
                    Text(
                        text = myViewModel.languageType().get(292),
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                shape = RoundedCornerShape(16.dp),
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
                                .size(32.dp)
                        )
                    }
                },
                visualTransformation = if (passwordVisibility) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        passwordVisibility = true
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
            )

            Spacer(modifier = Modifier.size(16.dp))

            // confimar contrasenia
            OutlinedTextField(
                value = confirmPass,
                onValueChange = { confirmPass = it },
                label = {
                    Text(
                        text = myViewModel.languageType().get(293),
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                shape = RoundedCornerShape(16.dp),
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
                                .size(32.dp)
                        )
                    }
                },
                visualTransformation = if (confirmPassVisibility) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        confirmPassVisibility = true
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
                    text = myViewModel.languageType().get(295),
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
                    text = myViewModel.languageType()[301],
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    navController: NavController,
    myViewModel: MyViewModel
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = myViewModel.languageType().get(291),
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