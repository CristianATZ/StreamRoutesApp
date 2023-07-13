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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.streamroutes.sreamroutesapp.R

@Composable
fun ChangeScreen(navController: NavController){
    Change(navController)
}

val color_fond = Color(0xFFFFF7E7)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Change( navController: NavController ){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color_fond)
    ){
        // top app bar
        TopAppBar(
            title = {
                Text(text = "Cambiar Contraseña",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            navigationIcon = {
                IconButton(onClick = {  }) {
                    Icon(
                        Icons.Filled.Menu,
                        contentDescription = "te regresara al login"
                    )
                }
            }
        )

        // todo
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.size(30.dp))

            // imagen logo
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .size(150.dp),
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "null")
            }

            Spacer(modifier = Modifier.size(15.dp))

            // contrasenia
            Column() {
                // header contrasenia
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(48.dp), // lo modifican dependiendo de su textfield
                    verticalAlignment = Alignment.CenterVertically, // alineamiento
                    horizontalArrangement = Arrangement.Center
                ){
                    // telefono
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.8f),
                        text = "Contraseña",
                        color = Color.DarkGray,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left
                    )
                }
                // textfield telefono
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    var variable by remember { mutableStateOf(TextFieldValue("")) }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .background(
                                Color(0xFFFFE5B4),
                                RoundedCornerShape(percent = 30)
                            )
                    ){
                        BasicTextField(
                            value = variable,
                            onValueChange = {variable = it},
                            singleLine = true,
                            modifier = Modifier
                                .height(70.dp)
                                .fillMaxWidth(0.85f)
                                .padding(4.dp),
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                                // aqui puede ser ImeAction.Next
                            ),
                            textStyle = LocalTextStyle.current.copy(
                                fontSize = 18.sp,
                                color = Color(0xFFE8AA42),
                                textAlign = TextAlign.Left,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 2.sp
                            ),
                            decorationBox = { innerTextField ->
                                Row(
                                    Modifier
                                        .background(
                                            Color(0xFFFFE5B4),
                                            RoundedCornerShape(percent = 30)
                                        )
                                        .padding(16.dp)
                                        .fillMaxWidth(0.8f)
                                ){
                                    if (variable.text.isEmpty()){
                                        Text(
                                            text = "Contraseña",
                                            fontSize = 18.sp,
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
            }

            Spacer(modifier = Modifier.size(15.dp))

            // confirmar contrasenia
            Column() {
                // header confirmar contrasenia
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(48.dp), // lo modifican dependiendo de su textfield
                    verticalAlignment = Alignment.CenterVertically, // alineamiento
                    horizontalArrangement = Arrangement.Center
                ){
                    // telefono
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.8f),
                        text = "Confirmar contraseña",
                        color = Color.DarkGray,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left
                    )
                }
                // textfield telefono
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    var variable by remember { mutableStateOf(TextFieldValue("")) }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .background(
                                Color(0xFFFFE5B4),
                                RoundedCornerShape(percent = 30)
                            )
                    ){
                        BasicTextField(
                            value = variable,
                            onValueChange = {variable = it},
                            singleLine = true,
                            modifier = Modifier
                                .height(70.dp)
                                .fillMaxWidth(0.85f)
                                .padding(4.dp),
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done
                                // aqui puede ser ImeAction.Next
                            ),
                            textStyle = LocalTextStyle.current.copy(
                                fontSize = 18.sp,
                                color = Color(0xFFE8AA42),
                                textAlign = TextAlign.Left,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 2.sp
                            ),
                            decorationBox = { innerTextField ->
                                Row(
                                    Modifier
                                        .background(
                                            Color(0xFFFFE5B4),
                                            RoundedCornerShape(percent = 30)
                                        )
                                        .padding(16.dp)
                                        .fillMaxWidth(0.8f)
                                ){
                                    if (variable.text.isEmpty()){
                                        Text(
                                            text = "Contraseña",
                                            fontSize = 18.sp,
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
            }

            // boton cambiar contrasenia
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                val roundCornerShape = RoundedCornerShape(topEnd = 30.dp, bottomStart = 30.dp, topStart = 10.dp, bottomEnd = 10.dp)
                Button(
                    onClick = {

                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF192833), // Cambiamos el color de fondo del botón aquí
                        contentColor = Color.White
                    ),
                    shape = roundCornerShape,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(15.dp)
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
}