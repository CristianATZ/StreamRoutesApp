package net.streamroutes.sreamroutesapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
fun ForgotScreen (navController: NavController) {
    Forgot(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Forgot (navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // variables necesarias para los botones
        var enabledVerificar by remember { mutableStateOf(false) }
        var enabledCambiar by remember { mutableStateOf(false) }

        // topappbar
        TopAppBar(
            title = { Text("Olvide mi contraseña") },
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
            .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // telefono header
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .size(35.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ){
                Spacer(modifier = Modifier.width(15.dp))

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

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                //textfield telefono
                var user by remember { mutableStateOf(TextFieldValue("")) }
                BasicTextField(
                    value = user,
                    onValueChange = {user = it},
                    singleLine = true,
                    modifier = Modifier
                        .height(65.dp)
                        .fillMaxWidth(0.55f),
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

                Spacer(modifier = Modifier.width(10.dp))

                // boton enviar codigo
                val gradientColors = listOf(Color(0xFF192833), Color(0xFF192833))
                val roundCornerShape = RoundedCornerShape(topEnd = 30.dp, bottomStart = 30.dp, topStart = 10.dp, bottomEnd = 10.dp)
                Box(
                    modifier = Modifier
                        .wrapContentWidth()
                        .background(
                            brush = Brush.horizontalGradient(colors = gradientColors),
                            shape = roundCornerShape
                        )
                        .clip(roundCornerShape)
                        .padding(PaddingValues(horizontal = 30.dp, vertical = 8.dp))
                        .clickable {
                            enabledVerificar = true
                            //navController.navigate(route = AppScreens.MainScreen.route)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "ENVIAR",
                        fontSize = 26.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // codigo de verificacion
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // codigo verificacion header
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .size(35.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Spacer(modifier = Modifier.width(15.dp))

                // codigo text
                Text(
                    text = "Codigo de verificación",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.Start),
                    color = Color(0xFF231955),
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
            ) {
                var digit1 by remember { mutableStateOf(TextFieldValue("")) }
                var digit2 by remember { mutableStateOf(TextFieldValue("")) }
                var digit3 by remember { mutableStateOf(TextFieldValue("")) }
                var digit4 by remember { mutableStateOf(TextFieldValue("")) }
                var digit5 by remember { mutableStateOf(TextFieldValue("")) }
                var digit6 by remember { mutableStateOf(TextFieldValue("")) }

                // digit 1 codigo
                BasicTextField(
                    value = digit1,
                    onValueChange = {digit1 = it},
                    singleLine = true,
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .weight(0.15f),
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
                            if (digit1.text.isEmpty()){
                                Text(
                                    text = "X",
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

                Spacer(modifier = Modifier.width(10.dp))

                // digit 2 codigo
                BasicTextField(
                    value = digit2,
                    onValueChange = {digit2 = it},
                    singleLine = true,
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .weight(0.15f),
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
                            if (digit2.text.isEmpty()){
                                Text(
                                    text = "X",
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

                Spacer(modifier = Modifier.width(10.dp))

                // digit 3 codigo
                BasicTextField(
                    value = digit3,
                    onValueChange = {digit3 = it},
                    singleLine = true,
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .weight(0.15f),
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
                            if (digit3.text.isEmpty()){
                                Text(
                                    text = "X",
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

                Spacer(modifier = Modifier.width(10.dp))

                // digit 4 codigo
                BasicTextField(
                    value = digit4,
                    onValueChange = {digit4 = it},
                    singleLine = true,
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .weight(0.15f),
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
                            if (digit4.text.isEmpty()){
                                Text(
                                    text = "X",
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

                Spacer(modifier = Modifier.width(10.dp))

                // digit 5 codigo
                BasicTextField(
                    value = digit5,
                    onValueChange = {digit5 = it},
                    singleLine = true,
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .weight(0.15f),
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
                            if (digit5.text.isEmpty()){
                                Text(
                                    text = "X",
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

                Spacer(modifier = Modifier.width(10.dp))

                // digit 6 codigo
                BasicTextField(
                    value = digit6,
                    onValueChange = {digit6 = it},
                    singleLine = true,
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .weight(0.15f),
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
                            if (digit6.text.isEmpty()){
                                Text(
                                    text = "X",
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
        }

        // boton verificar codigo
        Column(modifier = Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            val gradientColors = listOf(Color(0xFF192833), Color(0xFF192833))
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
                        if( enabledVerificar ){
                            enabledVerificar = !enabledVerificar
                            enabledCambiar = !enabledCambiar
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "VERIFICAR",
                    fontSize = 26.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // password
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )  {
            // password header
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .size(35.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Spacer(modifier = Modifier.width(15.dp))

                // contraseña text
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

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                // textfield password
                var password by rememberSaveable { mutableStateOf("") }
                var passwordVisibility = remember { mutableStateOf(true) }
                BasicTextField(
                    value = password,
                    onValueChange = {password = it},
                    singleLine = true,
                    modifier = Modifier
                        .height(65.dp)
                        .fillMaxWidth(0.85f),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Password
                    ),
                    decorationBox = { innerTextField ->
                        Row(
                            Modifier
                                .background(Color(0xFFFFE5B4), RoundedCornerShape(percent = 30))
                                .padding(16.dp)
                                .fillMaxWidth(0.85f)
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

                // icon visibilidad contraseña
                IconButton(
                    onClick = { passwordVisibility.value = !passwordVisibility.value }
                ) {
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
            // confirmar password header
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .size(35.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Spacer(modifier = Modifier.width(15.dp))
                // confirmar contraseña text
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

            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                // textfield password confirmar
                var passwordConfirm by rememberSaveable { mutableStateOf("") }
                var passwordVisibilityConfirm = remember { mutableStateOf(true) }
                BasicTextField(
                    value = passwordConfirm,
                    onValueChange = {passwordConfirm = it},
                    singleLine = true,
                    modifier = Modifier
                        .height(65.dp)
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

        // boton cambiar
        Column(modifier = Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            val gradientColors = listOf(Color(0xFF192833), Color(0xFF192833))
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
                        if(enabledCambiar) {
                            enabledCambiar = !enabledCambiar
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "CAMBIAR",
                    fontSize = 26.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ForgotView(){

}