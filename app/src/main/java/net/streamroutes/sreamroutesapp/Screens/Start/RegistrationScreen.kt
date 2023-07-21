package net.streamroutes.sreamroutesapp.Screens.Start

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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.Colores.color_fondo_claro
import net.streamroutes.sreamroutesapp.Colores.color_fondo_topappbar_alterno
import net.streamroutes.sreamroutesapp.Colores.color_letra_alterno
import net.streamroutes.sreamroutesapp.Colores.color_letra_topappbar
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R

@Composable
fun RegistrationScreen (navController: NavController) {
    Registration(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Registration (navController: NavController) {
    var telefono by remember { mutableStateOf(TextFieldValue()) }
    var telefonoVisibility = remember { mutableStateOf(false) }
    var password by remember { mutableStateOf(TextFieldValue()) }
    var passwordVisibility = remember { mutableStateOf(true) }
    var confirm by remember { mutableStateOf(TextFieldValue()) }
    var confirmVisibility = remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color_fondo_claro)
    ) {
        // top app bar
        TopAppBar(
            title = {
                Text(text = "Registrate",
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.navigate(AppScreens.HelpScreen.route) }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Te enviara al login"
                    )
                }
            },
            colors = TopAppBarDefaults
                .smallTopAppBarColors(
                    containerColor = color_fondo_topappbar_alterno,
                    titleContentColor = color_letra_topappbar
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // logo
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(150.dp),
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "null")
            }

            // telefono
            PasswordTextfield(
                tittle = "Telefono",
                placeholder = "Telefono",
                readOnly = false,
                singleLine = false,
                size = 70,
                variable = telefono,
                onVariableChange = { newValue -> telefono = newValue },
                passwordVisibility = telefonoVisibility,
                roundedCornerShape = RoundedCornerShape(percent = 30),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                ),
                icono = false
            )

            Spacer(modifier = Modifier.size(15.dp))

            // contrasenia
            PasswordTextfield(
                tittle = "Contraseña",
                placeholder = "Contraseña",
                readOnly = false,
                singleLine = false,
                size = 70,
                variable = password,
                onVariableChange = { newValue -> password = newValue },
                passwordVisibility = passwordVisibility,
                roundedCornerShape = RoundedCornerShape(percent = 30),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.size(40.dp))

            // confirmar
            PasswordTextfield(
                tittle = "Contraseña",
                placeholder = "Contraseña",
                readOnly = false,
                singleLine = false,
                size = 70,
                variable = confirm,
                onVariableChange = { newValue -> confirm = newValue },
                passwordVisibility = confirmVisibility,
                roundedCornerShape = RoundedCornerShape(percent = 30),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )
            )

            // button registrarse
            val roundCornerShape = RoundedCornerShape(topEnd = 30.dp, bottomStart = 30.dp, topStart = 10.dp, bottomEnd = 10.dp)
            Button(
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF192833), // Cambiamos el color de fondo del botón aquí
                    contentColor = Color.White
                ),
                shape = roundCornerShape,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(15.dp)
            ) {
                Text(
                    text = "REGISTRARSE",
                    fontSize = 26.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun PasswordTextfield(
    tittle: String,
    placeholder: String,
    readOnly: Boolean,
    singleLine: Boolean = true,
    size: Int,
    variable: TextFieldValue,
    onVariableChange: (TextFieldValue) -> Unit,
    passwordVisibility: MutableState<Boolean>,
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(percent = 30),
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
        // forgot
        Text(
            text = tittle,
            modifier = Modifier
                .fillMaxWidth(),
            color = Color.DarkGray,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .background(
                Color(0xFFFFE5B4),
                roundedCornerShape
            )
    ){
        // caja de texto
        BasicTextField(
            value = variable,
            onValueChange = onVariableChange,
            singleLine = singleLine,
            readOnly = readOnly,
            modifier = Modifier
                .height(size.dp)
                .fillMaxWidth()
                .padding(4.dp),
            keyboardOptions = keyboardOptions,
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
                        .background(Color(0xFFFFE5B4), RoundedCornerShape(percent = 30))
                        .padding(16.dp)
                        .fillMaxWidth(0.8f)
                ){
                    if (variable.text.isEmpty()){
                        Text(
                            text = placeholder,
                            fontSize = 18.sp,
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

        if(icono){
            // icono
            Row(
                modifier = Modifier
                    .height(size.dp)
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
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
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground= true)
@Composable
fun RegistrationView(){

}