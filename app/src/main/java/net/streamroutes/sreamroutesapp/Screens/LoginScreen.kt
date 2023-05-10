package net.streamroutes.sreamroutesapp.Screens

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R

@Composable
fun LoginScreen(navController: NavController){
    Login(navController)
}

@Composable
fun Login(navController: NavController){
    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

        Image(
            painter = painterResource(id = R.drawable.streamrouteslogo),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
        )

        Row (
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .size(48.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            // forgot
            Text(
                text = stringResource(id = R.string.user_tittle_text),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.Start)
            )
        }

        var user by remember { mutableStateOf(TextFieldValue("")) }
        BasicTextField(
            value = user,
            onValueChange = {user = it},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
                ),
            decorationBox = { innerTextField ->
                Row(
                    Modifier
                        .background(Color(0xFFE8AA42), RoundedCornerShape(percent = 30))
                        .padding(16.dp)
                        .fillMaxWidth(0.8f)
                ){
                    if (user.text.isEmpty()){
                        Text(
                            text = "Telefono",
                            color = Color(0xFFFFE5B4),
                            letterSpacing = 3.sp)
                    }
                    innerTextField()
                }
            }
        )
        
        Spacer(modifier = Modifier.height(20.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .size(48.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            // forgot
            Text(
                text = stringResource(id = R.string.password_tittle_text),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.Start)
            )
        }

        var password by remember { mutableStateOf(TextFieldValue("")) }
        BasicTextField(
            value = password,
            onValueChange = {password = it},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            decorationBox = { innerTextField ->
                Row(
                    Modifier
                        .background(Color(0xFFE8AA42), RoundedCornerShape(percent = 30))
                        .padding(16.dp)
                        .fillMaxWidth(0.8f)
                ){
                    if (password.text.isEmpty()){
                        Text(
                            text = "Contraseña",
                            color = Color(0xFFFFE5B4),
                            letterSpacing = 3.sp)
                    }
                    innerTextField()
                }
            }
        )

        Row (
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .size(48.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            // forgot
            ClickableText(
                text = AnnotatedString(stringResource(id = R.string.forgot_text)),
                style = TextStyle(textDecoration = TextDecoration.Underline),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End),
                onClick = {
                    navController.navigate(route = AppScreens.ForgotScreen.route)
                })
        }

        // login
        Button(onClick = {
            navController.navigate(route = AppScreens.MainScreen.route)
        },
            modifier = Modifier
                .width(150.dp)
                .wrapContentHeight()
        ) {
            Text(
                text = "Ingresar",
                fontSize = 4.5.em,
                color = Color(0xFF231955),
                fontWeight = FontWeight.Bold
            )
        }

        // registration
        Row (
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .size(48.dp),
            verticalAlignment = Alignment.CenterVertically
                ) {
            ClickableText(
                text = AnnotatedString(stringResource(id = R.string.register_text)),
                style = TextStyle(textDecoration = TextDecoration.Underline),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally),
                onClick = {
                    navController.navigate(route = AppScreens.RegistrationScreen.route)
                })
        }
    }
}