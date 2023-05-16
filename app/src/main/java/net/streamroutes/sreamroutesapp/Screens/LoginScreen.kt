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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
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
            painter = painterResource(id = R.drawable.logo_no_image),
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
                    .wrapContentWidth(Alignment.Start),
                color = Color(0xFF231955),
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold
            )
        }

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
                    .wrapContentWidth(Alignment.Start),
                color = Color(0xFF231955),
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold
            )
        }

        var password by remember { mutableStateOf(TextFieldValue("")) }
        BasicTextField(
            value = password,
            onValueChange = {password = it},
            singleLine = true,
            modifier = Modifier
                .height(70.dp)
                .fillMaxWidth(0.85f),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            decorationBox = { innerTextField ->
                Row(
                    Modifier
                        .background(Color(0xFFFFE5B4), RoundedCornerShape(percent = 30))
                        .padding(16.dp)
                        .fillMaxWidth(0.8f)
                ){
                    if (password.text.isEmpty()){
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
            }
        )

        Row (
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .size(48.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            // forgot
            Text(
                text = buildAnnotatedString{
                    withStyle(style = SpanStyle(color = Color.DarkGray,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif)) {
                        append(stringResource(id = R.string.forgot_text_part1))
                    }
                    append(" ")
                    withStyle(style = SpanStyle(color = Color(0xFFE8AA42),
                        textDecoration = TextDecoration.Underline,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif)) {
                        append(stringResource(id = R.string.forgot_text_part2))
                    }
                },
                style = TextStyle(textDecoration = TextDecoration.Underline),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End)
                    .clickable {
                        navController.navigate(route = AppScreens.ForgotScreen.route)
                    },
                color = Color(0xFF192833)
            )
        }

        // button login
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
                    navController.navigate(route = AppScreens.MainScreen.route)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "INGRESAR",
                fontSize = 26.sp,
                color = Color.White,
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
            Text(
                text = buildAnnotatedString{
                    withStyle(style = SpanStyle(color = Color.DarkGray,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif)) {
                        append(stringResource(id = R.string.register_text_part1))
                    }
                    append(" ")
                    withStyle(style = SpanStyle(color = Color(0xFFE8AA42),
                        textDecoration = TextDecoration.Underline,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif)) {
                        append(stringResource(id = R.string.register_text_part2))
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .clickable {
                        navController.navigate(route = AppScreens.RegistrationScreen.route)
                    },
                color = Color(0xFF192833)
            )
        }
    }
}