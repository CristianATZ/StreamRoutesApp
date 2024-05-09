@file:OptIn(ExperimentalMaterial3Api::class)

package net.streamroutes.sreamroutesapp.ui.start_screens

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.navigation.AppScreens
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.viewmodel.routes.LoginViewModel

@Composable
fun LoginScreen(loginViewModel: LoginViewModel, navController: NavController){
    Login(loginViewModel, navController)
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun Login(
    loginViewModel: LoginViewModel,
    navController: NavController
){

    val brush = Brush.linearGradient(
        listOf(Color(0xFFE8AA42),Color(0xFFEACE43))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // logo
        Image(
            painter = painterResource(id = R.drawable.logo_blanco),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
        )

        Spacer(modifier = Modifier.size(16.dp))

        Text(
            text = stringResource(id = R.string.log_in),
            style = typography.displaySmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.size(32.dp))

        // telefono
        OutlinedTextField(
            value = loginViewModel.telefono,
            onValueChange = { loginViewModel.updateTelefono(it) },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.txtTelefono),
                    style = typography.titleMedium,
                    color = colorScheme.primary.copy(0.5f)
                )
            },
            shape = RoundedCornerShape(16.dp),
            trailingIcon = {
                if( loginViewModel.telefono.isNotEmpty() ){
                    IconButton(
                        onClick = {
                            loginViewModel.updateTelefono("")
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
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Phone, contentDescription = null)
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                focusedBorderColor = Color.White,
                unfocusedContainerColor = Color.White,
                unfocusedTextColor = Color.Black,
                unfocusedBorderColor = Color.White,
            ),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .shadow(4.dp, CircleShape)
        )

        Spacer(modifier = Modifier.size(16.dp))

        // contrasenia
        OutlinedTextField(
            value = loginViewModel.password,
            onValueChange = { loginViewModel.updatePassword(it) },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.txtPassword),
                    style = typography.titleMedium,
                    color = colorScheme.primary.copy(0.5f)
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        loginViewModel.updatePasswordVisibility( !loginViewModel.passwordVisibility )
                    }
                ) {
                    Icon(
                        painter = if (!loginViewModel.passwordVisibility)
                            painterResource(id = R.drawable.visibilityoff) else painterResource(id = R.drawable.visibilityon),
                        contentDescription = "visibilidad contraseña",
                        modifier = Modifier
                            .size(28.dp)
                    )
                }
            },
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Lock, contentDescription = null)
            },
            visualTransformation = if (loginViewModel.passwordVisibility) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                focusedBorderColor = Color.White,
                unfocusedContainerColor = Color.White,
                unfocusedTextColor = Color.Black,
                unfocusedBorderColor = Color.White,
            ),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .shadow(4.dp, CircleShape)
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
                    text = stringResource(id = R.string.forgot_text_part1) + " " + stringResource(id = R.string.forgot_text_part2),
                    style = typography.titleSmall
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
        }

        // boton ingresar
        Button(
            onClick = {
                navController.navigate(AppScreens.SelectOptionScreen.route)
            },
            shape = CircleShape,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(top = 16.dp)
                .height(50.dp)
        ) {
            Text(
                text = stringResource(id = R.string.sign_in),
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
                text = stringResource(id = R.string.register_text_part1) + " " + stringResource(id = R.string.register_text_part2),
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
                    contentDescription = "facebook"
                )
            }

            // twitter
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.twitter),
                    contentDescription = "equis"
                )
            }
        }
    }
}