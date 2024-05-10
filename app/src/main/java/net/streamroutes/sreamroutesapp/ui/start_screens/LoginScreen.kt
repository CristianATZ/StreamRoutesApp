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
import androidx.compose.ui.graphics.vector.ImageVector
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

    val brush = Brush.verticalGradient(
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
        LogoTitleLogin()

        Spacer(modifier = Modifier.size(32.dp))

        TextfieldsLogin(loginViewModel)

        // olvide mi contrasenia
        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(
                onClick = {
                    navController.navigate(AppScreens.VerificationScreen.route)
                }
            ) {
                Text(
                    text = stringResource(id = R.string.forgot_text_part1) + " " + stringResource(id = R.string.forgot_text_part2),
                    style = typography.titleSmall,
                    color = colorScheme.background
                )
            }
        }

        // boton ingresar
        Button(
            onClick = {
                navController.navigate(AppScreens.SelectOptionScreen.route)
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
                style = typography.titleSmall,
                color = colorScheme.background
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
                .background(colorScheme.background))

            Text(
                text = "o",
                style = typography.bodyLarge,
                color = colorScheme.background,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(colorScheme.background))
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

@Composable
fun TextfieldsLogin(
    loginViewModel: LoginViewModel
) {
    // telefono
    CustomOutlinedTextField(
        value = loginViewModel.telefono,
        onValueChange = { loginViewModel.updateTelefono(it) },
        placeholderText = stringResource(id = R.string.txtTelefono),
        leadingIcon = Icons.Filled.Phone,
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Next
    )

    Spacer(modifier = Modifier.size(16.dp))

    CustomOutlinedTextField(
        value = loginViewModel.password,
        onValueChange = { loginViewModel.updatePassword(it) },
        placeholderText = stringResource(id = R.string.txtPassword),
        leadingIcon = Icons.Filled.Lock,
        visualTransformation = if (loginViewModel.passwordVisibility) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            IconButton(
                onClick = {
                    loginViewModel.updatePasswordVisibility(!loginViewModel.passwordVisibility)
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
        keyboardType = KeyboardType.Password,
        imeAction = ImeAction.Done
    )
}

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    leadingIcon: ImageVector,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.None,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholderText,
                style = typography.titleMedium,
                color = colorScheme.primary.copy(0.5f)
            )
        },
        shape = RoundedCornerShape(16.dp),
        trailingIcon = trailingIcon,
        leadingIcon = {
            Icon(imageVector = leadingIcon, contentDescription = null)
        },
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = colorScheme.background,
            focusedTextColor = colorScheme.onBackground,
            focusedBorderColor = colorScheme.background,
            unfocusedContainerColor = colorScheme.background,
            unfocusedTextColor = colorScheme.onBackground,
            unfocusedBorderColor = colorScheme.background,
        ),
        modifier = modifier
            .fillMaxWidth(0.9f)
            .shadow(4.dp, CircleShape)
    )
}

@Composable
fun LogoTitleLogin() {
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
        fontWeight = FontWeight.Bold,
        color = colorScheme.background
    )
}
