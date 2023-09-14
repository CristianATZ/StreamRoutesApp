@file:OptIn(ExperimentalMaterial3Api::class)

package net.streamroutes.sreamroutesapp.Screens.Start

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    var passwordVisibility = remember { mutableStateOf(true) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo Stream Routes",
            modifier = Modifier
                .size(200.dp)
        )

        // telefono
        HeaderTextField(
            tittle = "Telefono",
            placeholder = "Telefono",
            size = 70,
            variable = telefono,
            onValueChange = { newValue -> telefono = newValue},
            passwordVisibility = mutableStateOf(true),
            visualTransformation = VisualTransformation.None,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number
            ),
            icono = false
        )

        
        Spacer(modifier = Modifier.height(16.dp))

        // contraseña
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
                imeAction = ImeAction.Done
            ),
            icono = true
        )

        CustomText(
            firstString = myViewModel.languageType().get(111),
            secondString = myViewModel.languageType().get(112),
            horizontal = Arrangement.End,
            onClick = {
                navController.navigate(route = AppScreens.VerificationScreen.route)
            }
        )

        // button login
        Button(
            onClick = {
                navController.navigate(route = AppScreens.MainScreen.route)
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
                text = "Ingresar",
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.onTertiary
            )
        }

        // registration
        CustomText(
            firstString = myViewModel.languageType().get(114),
            secondString = myViewModel.languageType().get(115),
            horizontal = Arrangement.Center,
            onClick = {
                navController.navigate(route = AppScreens.RegistrationScreen.route)
            }
        )
    }
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
            Text(
                text = placeholder,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.5f))
        },
        textStyle = TextStyle(
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
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
            unfocusedBorderColor = MaterialTheme.colorScheme.surfaceVariant,
            cursorColor = MaterialTheme.colorScheme.onSurfaceVariant
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

@Composable
private fun CustomText(
    firstString: String,
    secondString: String,
    horizontal: Arrangement.Horizontal,
    onClick: () -> Unit
) {
    Row (
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .size(48.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = horizontal
    ){
        // forgot
        Text(
            text = buildAnnotatedString{
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.SansSerif)) {
                    append(firstString)
                }
                append(" ")
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.tertiary,
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.SansSerif)) {
                    append(secondString)
                }
            },
            modifier = Modifier
                .wrapContentWidth()
                .clickable(onClick = onClick)
        )
    }
}

@Preview (showBackground = true)
@Composable
fun hola(){
    //Login()
}