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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
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
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisibility = remember { mutableStateOf(true) }
    var confirmPass by remember { mutableStateOf(TextFieldValue("")) }
    var confirmPassVisibility = remember { mutableStateOf(true) }

    Scaffold(
        topBar = { TopBarBody(myViewModel,navController) },
        containerColor = color_fondo
    ) { paddingValues ->
        // todo
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
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
            PasswordTextfield(
                tittle = myViewModel.languageType().get(131),
                placeholder = myViewModel.languageType().get(131),
                readOnly = false,
                size = 70,
                variable = password,
                onVariableChange = {newValue -> password = newValue},
                passwordVisibility = passwordVisibility,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                visualTransformation = if (passwordVisibility.value)
                    PasswordVisualTransformation() else VisualTransformation.None
            )

            Spacer(modifier = Modifier.size(15.dp))

            // confirmar contrasenia
            PasswordTextfield(
                tittle = myViewModel.languageType().get(132),
                placeholder = myViewModel.languageType().get(132),
                readOnly = false,
                size = 70,
                variable = confirmPass,
                onVariableChange = {newValue -> confirmPass = newValue},
                passwordVisibility = confirmPassVisibility,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                visualTransformation = if (passwordVisibility.value)
                    PasswordVisualTransformation() else VisualTransformation.None
            )

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
                        containerColor = color_botones, // Cambiamos el color de fondo del botón aquí
                        contentColor = color_letra_botones
                    ),
                    shape = roundCornerShape,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(15.dp)
                ) {
                    Text(
                        text = myViewModel.languageType().get(134),
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBarBody(myViewModel: MyViewModel, navController: NavController) {
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
                    Icons.Filled.ArrowBack,
                    contentDescription = "te regresara al login"
                )
            }
        },
        colors = TopAppBarDefaults
            .smallTopAppBarColors(
                containerColor = color_fondo_topbar,
                titleContentColor = color_letra_topbar
            )
    )
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
            color = color_letraout,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .background(
                color_fondo_textfield,
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
            textStyle = androidx.compose.material.LocalTextStyle.current.copy(
                fontSize = 18.sp,
                color = color_letrain,
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp
            ),
            decorationBox = { innerTextField ->
                Row(
                    Modifier
                        .background(color_fondo_textfield, RoundedCornerShape(percent = 30))
                        .padding(16.dp)
                        .fillMaxWidth(0.8f)
                ){
                    if (variable.text.isEmpty()){
                        Text(
                            text = placeholder,
                            fontSize = 18.sp,
                            color = color_letraout.copy(0.5f),
                            letterSpacing = 3.sp,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)

                        )
                    }
                    innerTextField()
                }
            },
            visualTransformation = visualTransformation
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
                            .size(32.dp),
                        tint = color_letraout
                    )
                }
            }
        }
    }
}