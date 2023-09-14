@file:OptIn(ExperimentalMaterial3Api::class)

package net.streamroutes.sreamroutesapp.Screens.Start

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.streamroutes.sreamroutesapp.Colores.color_botones
import net.streamroutes.sreamroutesapp.Colores.color_fondo
import net.streamroutes.sreamroutesapp.Colores.color_fondo_textfield
import net.streamroutes.sreamroutesapp.Colores.color_fondo_topbar
import net.streamroutes.sreamroutesapp.Colores.color_letra_botones
import net.streamroutes.sreamroutesapp.Colores.color_letra_textfield
import net.streamroutes.sreamroutesapp.Colores.color_letra_topbar
import net.streamroutes.sreamroutesapp.Colores.color_letrain
import net.streamroutes.sreamroutesapp.Colores.color_letraout
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R

@Composable
fun ChangeScreen(myViewModel: MyViewModel,navController: NavController){
    Change(myViewModel,navController)
}

val color_fond = Color(0xFFFFF7E7)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Change( myViewModel: MyViewModel,navController: NavController ){
    var password by remember { mutableStateOf("") }
    var passwordVisibility = remember { mutableStateOf(true) }
    var confirmPass by remember { mutableStateOf("") }
    var confirmPassVisibility = remember { mutableStateOf(true) }

    Scaffold(
        topBar = { TopBarBody(myViewModel,navController) },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        // todo
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(32.dp))

            // imagen logo
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo Stream Routes",
                modifier = Modifier
                    .size(150.dp)
            )

            Spacer(modifier = Modifier.size(16.dp))

            // contrasenia
            HeaderTextField(
                tittle = "Contraseña",
                placeholder = "Contraseña",
                size = 70,
                variable = password,
                onValueChange = {newValue -> password = newValue},
                passwordVisibility = passwordVisibility,
                visualTransformation = if (passwordVisibility.value)
                    PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.size(15.dp))

            // confirmar contrasenia
            HeaderTextField(
                tittle = "Confirmar contraseña",
                placeholder = "Confirmar contraseña",
                size = 70,
                variable = confirmPass,
                onValueChange = {newValue -> confirmPass = newValue},
                passwordVisibility = confirmPassVisibility,
                visualTransformation = if (confirmPassVisibility.value)
                    PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )
            )

            // boton cambiar contrasenia
            Button(
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary
                ),
                shape = RoundedCornerShape(percent = 40),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 5.dp
                ),
                modifier = Modifier
                    .fillMaxWidth(0.65f)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Cambiar",
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBarBody(
    myViewModel: MyViewModel,
    navController: NavController
) {
    TopAppBar(
        title = {
            Text(text = myViewModel.languageType().get(130),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigate(AppScreens.LoginScreen.route) }) {
                Icon(
                    painterResource(id = R.drawable.back),
                    contentDescription = "te regresara al login",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        colors = TopAppBarDefaults
            .smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
    )
}


@Composable
private fun HeaderTextField(
    tittle: String,
    placeholder: String,
    singleLine: Boolean = true,
    size: Int,
    variable: String,
    onValueChange: (String) -> Unit,
    passwordVisibility: MutableState<Boolean>,
    visualTransformation: VisualTransformation,
    keyboardOptions: KeyboardOptions,
    icono: Boolean = true
) {
    // nombre
    Row (
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .size(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        // header
        Text(
            text = tittle,
            modifier = Modifier
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.onBackground,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }

    OutlinedTextField(
        value = variable,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .heightIn(size.dp),
        // icono para mostrar la contraseña
        // trailingIcon =
        placeholder = {
            Text(text = placeholder, fontSize = 18.sp, color = MaterialTheme.colorScheme.onPrimaryContainer.copy(0.5f))
        },
        textStyle = TextStyle(
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
        ),
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        shape = RoundedCornerShape(percent = 30),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unfocusedBorderColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        singleLine = singleLine,
        trailingIcon = {
            if(icono){
                IconButton(
                    onClick = {
                        passwordVisibility.value = !passwordVisibility.value
                    },
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                ) {
                    Icon(
                        painter = if (passwordVisibility.value)
                            painterResource(id = R.drawable.visibilityoff) else painterResource(id = R.drawable.visibilityon),
                        contentDescription = "visibilidad contraseña",
                        modifier = Modifier
                            .size(32.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    )
}