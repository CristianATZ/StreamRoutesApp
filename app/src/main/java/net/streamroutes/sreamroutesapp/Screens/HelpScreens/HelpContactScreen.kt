package net.streamroutes.sreamroutesapp.Screens.HelpScreens

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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpContactScreen(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            title = { Text(stringResource(id = R.string.tittle_contact_screen)) },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Regresara al menu de ayuda"
                    )
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // nombre
            Column(modifier = Modifier
                .fillMaxWidth()
                .weight(0.4f)
                .padding(10.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = stringResource(id = R.string.contact_head_name),
                    modifier = Modifier
                        .padding(10.dp,0.dp)
                        .fillMaxWidth()
                )
                // textfield name
                var name by remember { mutableStateOf(TextFieldValue("")) }
                BasicTextField(
                    value = name,
                    onValueChange = {name = it},
                    singleLine = true,
                    modifier = Modifier
                        .height(85.dp)
                        .fillMaxWidth()
                        .padding(10.dp),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    decorationBox = { innerTextField ->
                        Row(
                            Modifier
                                .background(Color(0xFFFFE5B4), RoundedCornerShape(percent = 5))
                                .padding(16.dp)
                                .fillMaxWidth(0.8f)
                        ){
                            if (name.text.isEmpty()){
                                Text(
                                    text = stringResource(id = R.string.contact_body_name),
                                    color = Color(0xFFFFF7E7),
                                    letterSpacing = 3.sp
                                )
                            }
                            innerTextField()
                        }
                    }
                )
            }

            // destinatario
            Column(modifier = Modifier
                .fillMaxWidth()
                .weight(0.4f)
                .padding(10.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.contact_head_mail),
                    modifier = Modifier
                        .padding(10.dp,0.dp)
                )
                // textfield destinatario
                val mail = "streamroutes2.0@gmail.com"
                BasicTextField(
                    value = mail,
                    onValueChange = { },
                    singleLine = true,
                    readOnly = true,
                    modifier = Modifier
                        .height(85.dp)
                        .fillMaxWidth()
                        .padding(10.dp),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    decorationBox = {
                        Row(
                            Modifier
                                .background(Color(0xFFFFE5B4), RoundedCornerShape(percent = 5))
                                .padding(16.dp)
                                .fillMaxWidth(0.8f)
                        ){
                            Text(
                                text = "streamroutes2.0@gmail.com",
                                color = Color(0xFF192833),
                                letterSpacing = 3.sp
                            )
                        }
                    }
                )
            }

            // mensaje
            Column(modifier = Modifier
                .fillMaxWidth()
                .weight(0.6f)
                .padding(10.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.contact_head_message),
                    modifier = Modifier
                        .padding(10.dp,0.dp)
                )
                // textfield mensaje
                var message by remember { mutableStateOf(TextFieldValue("")) }
                BasicTextField(
                    value = message,
                    onValueChange = {message = it},
                    singleLine = false,
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                        .padding(10.dp),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    decorationBox = { innerTextField ->
                        Row(
                            Modifier
                                .background(Color(0xFFFFE5B4), RoundedCornerShape(percent = 5))
                                .padding(16.dp)
                                .fillMaxWidth(0.8f)
                        ){
                            if (message.text.isEmpty()){
                                Text(
                                    text = stringResource(id = R.string.contact_body_message),
                                    color = Color(0xFFFFF7E7),
                                    letterSpacing = 3.sp
                                )
                            }
                            innerTextField()
                        }
                    }
                )
            }

            //boton
            Column(modifier = Modifier
                .fillMaxWidth()
                .weight(0.4f)
                .padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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

                // ubicacion
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2f)
                    .padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // text ubicacion
                    Text(
                        text = buildAnnotatedString{
                            withStyle(style = SpanStyle(color = Color.DarkGray,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.SansSerif)
                            ) {
                                append(stringResource(id = R.string.contact_bottom_text1))
                            }
                            append(" ")
                            withStyle(style = SpanStyle(color = Color(0xFFE8AA42),
                                textDecoration = TextDecoration.Underline,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.SansSerif)
                            ) {
                                append(stringResource(id = R.string.contact_bottom_text2))
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {  },
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun HelpContactView(){

}