@file:OptIn(ExperimentalMaterial3Api::class)

package net.streamroutes.sreamroutesapp.Screens.Start

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R

@Composable
fun LoginScreen(myViewModel: MyViewModel,navController: NavController){
    Login(myViewModel,navController)
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun Login(myViewModel: MyViewModel,navController: NavController){
    var telefono by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // logo
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null
        )

        Spacer(modifier = Modifier.size(16.dp))

        Text(
            text = "Iniciar sesion",
            style = typography.titleLarge
        )

        Spacer(modifier = Modifier.size(32.dp))

        // telefono
        OutlinedTextField(
            value = telefono,
            onValueChange = { telefono = it },
            label = {
                Text(
                    text = myViewModel.languageType().get(270),
                    style = typography.titleMedium
                )
            },
            shape = RoundedCornerShape(16.dp),
            trailingIcon = {
                if( telefono.isNotEmpty() ){
                    IconButton(
                        onClick = {
                            telefono = ""
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Clear,
                            contentDescription = "borrar telefono",
                            modifier = Modifier
                                .size(32.dp)
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier
                .fillMaxWidth(0.9f)
        )

        Spacer(modifier = Modifier.size(16.dp))

        // contrasenia
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = {
                Text(
                    text = myViewModel.languageType().get(271),
                    style = typography.titleMedium
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
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth(0.9f)
        )

        // olvide mi contrasenia
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(
                onClick = {
                    navController.navigate(AppScreens.VerificationScreen.route)
                }
            ) {
                Text(
                    text = myViewModel.languageType().get(272) + " " + myViewModel.languageType().get(273),
                    style = typography.titleSmall
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
        }

        // boton ingresar
        Button(
            onClick = {
                navController.navigate(AppScreens.MainScreen.route)
            },
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .padding(top = 16.dp)
                .height(50.dp)
        ) {
            Text(
                text = myViewModel.languageType().get(274),
                style = typography.bodyLarge
            )
        }

        // no tienes cuenta
        TextButton(
            onClick = {
                navController.navigate(AppScreens.RegistrationScreen.route)
            }
        ) {
            Text(
                text = myViewModel.languageType().get(275) + " " + myViewModel.languageType().get(276),
                style = typography.titleSmall
            )
        }

        // otras opciones de login
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(colorScheme.onSurface))

            Text(
                text = "o",
                style = typography.bodyLarge,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(colorScheme.onSurface))
        }

        // otros metodos (fb, twt, gm)
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // gmail
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.gmail),
                    contentDescription = "gmail"
                )
            }

            // facebook
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.facebook),
                    contentDescription = "gmail"
                )
            }

            // twitter
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.twitter),
                    contentDescription = "gmail"
                )
            }
        }
    }
}