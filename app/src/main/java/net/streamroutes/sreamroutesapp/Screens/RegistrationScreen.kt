package net.streamroutes.sreamroutesapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.R

@Composable
fun RegistrationScreen (navController: NavController) {
    Registration(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Registration (navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // topappbar
        TopAppBar(
            title = { Text("Registrarme") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Regresara al menu de ayuda"
                    )
                }
            }
        )

        // logo
        Row( modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_no_image),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
            )
        }

        // telefono
        Column(modifier = Modifier
            .fillMaxWidth()
            //.weight(0.2f)
            .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // telefono header
            Row (
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .size(48.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                // forgot
                Text(
                    text = "Telefono",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.Start),
                    color = Color(0xFF231955),
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            //textfield telefono
            var user by remember { mutableStateOf(TextFieldValue("")) }
            BasicTextField(
                value = user,
                onValueChange = {user = it},
                singleLine = true,
                modifier = Modifier
                    .height(70.dp)
                    .fillMaxWidth(0.85f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                decorationBox = { innerTextField ->
                    Row(
                        Modifier
                            .background(Color(0xFFFFE5B4), RoundedCornerShape(percent = 30))
                            .padding(16.dp)
                            .fillMaxWidth(0.8f)
                    ){
                        if (user.text.isEmpty()){
                            Text(
                                text = "Telefono",
                                color = Color(0xFFFFF7E7),
                                letterSpacing = 3.sp,
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                            )
                        }
                        innerTextField()
                    }
                }
            )
        }

        // password
        Column(modifier = Modifier
            .fillMaxWidth()
            //.weight(0.2f)
            .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // password header
            Row (
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .size(48.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                // forgot
                Text(
                    text = "Contraseña",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.Start),
                    color = Color(0xFF231955),
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            // textfield password
            var password by rememberSaveable { mutableStateOf("") }
            var passwordVisibility = remember { mutableStateOf(true) }
            BasicTextField(
                value = password,
                onValueChange = {password = it},
                singleLine = true,
                modifier = Modifier
                    .height(70.dp)
                    .fillMaxWidth(0.85f),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password
                ),
                decorationBox = { innerTextField ->
                    Row(
                        Modifier
                            .background(Color(0xFFFFE5B4), RoundedCornerShape(percent = 30))
                            .padding(16.dp)
                            .fillMaxWidth(0.8f)
                    ){
                        if (password.isEmpty()){
                            Text(
                                text = "Contraseña",
                                color = Color(0xFFFFF7E7),
                                letterSpacing = 3.sp,
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                            )
                        }
                        innerTextField()
                    }
                },
                visualTransformation = if (passwordVisibility.value)
                    PasswordVisualTransformation() else VisualTransformation.None
            )

            // ver contraseña
            Row(modifier = Modifier
                .fillMaxWidth(0.85f),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = {
                    passwordVisibility.value = !passwordVisibility.value
                }) {
                    Icon(
                        painter = if (passwordVisibility.value)
                            painterResource(id = R.drawable.visibility_off) else painterResource(id = R.drawable.visibility_on),
                        contentDescription = "visibilidad contraseña",
                        modifier = Modifier
                            .size(32.dp)
                    )
                }
            }
        }

        // confirmar password
        Column(modifier = Modifier
            .fillMaxWidth()
            //.weight(0.2f)
            .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // password header
            Row (
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .size(48.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                // forgot
                Text(
                    text = "Confirmar contraseña",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.Start),
                    color = Color(0xFF231955),
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            // textfield password confirmar
            var passwordConfirm by rememberSaveable { mutableStateOf("") }
            var passwordVisibilityConfirm = remember { mutableStateOf(true) }
            BasicTextField(
                value = passwordConfirm,
                onValueChange = {passwordConfirm = it},
                singleLine = true,
                modifier = Modifier
                    .height(70.dp)
                    .fillMaxWidth(0.85f),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password
                ),
                decorationBox = { innerTextField ->
                    Row(
                        Modifier
                            .background(Color(0xFFFFE5B4), RoundedCornerShape(percent = 30))
                            .padding(16.dp)
                            .fillMaxWidth(0.8f)
                    ){
                        if (passwordConfirm.isEmpty()){
                            Text(
                                text = "Confirmar Contraseña",
                                color = Color(0xFFFFF7E7),
                                letterSpacing = 3.sp,
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                            )
                        }
                        innerTextField()
                    }
                },
                visualTransformation = if (passwordVisibilityConfirm.value)
                    PasswordVisualTransformation() else VisualTransformation.None
            )

            // ver contraseña confirmada
            Row(modifier = Modifier
                .fillMaxWidth(0.85f),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = {
                    passwordVisibilityConfirm.value = !passwordVisibilityConfirm.value
                }) {
                    Icon(
                        painter = if (passwordVisibilityConfirm.value)
                            painterResource(id = R.drawable.visibility_off) else painterResource(id = R.drawable.visibility_on),
                        contentDescription = "visbilidad confirmar contraseña",
                        modifier = Modifier
                            .size(32.dp)
                    )
                }
            }
        }

        // boton registrar
        Column(modifier = Modifier
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            val gradientColors = listOf(Color(0xFF192833),Color(0xFF192833))
            val roundCornerShape = RoundedCornerShape(topEnd = 30.dp, bottomStart = 30.dp, topStart = 10.dp, bottomEnd = 10.dp)
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.horizontalGradient(colors = gradientColors),
                        shape = roundCornerShape
                    )
                    .clip(roundCornerShape)
                    .padding(PaddingValues(horizontal = 30.dp, vertical = 8.dp))
                    .clickable {
                        //navController.navigate(route = AppScreens.MainScreen.route)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "REGISTRARME",
                    fontSize = 26.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground= true)
@Composable
fun RegistrationView(){

}