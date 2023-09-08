@file:OptIn(ExperimentalMaterial3Api::class)

package net.streamroutes.sreamroutesapp.Screens.HelpScreens

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.Colores.color_botones
import net.streamroutes.sreamroutesapp.Colores.color_fondo
import net.streamroutes.sreamroutesapp.Colores.color_fondo_textfield
import net.streamroutes.sreamroutesapp.Colores.color_fondo_topbar
import net.streamroutes.sreamroutesapp.Colores.color_icon
import net.streamroutes.sreamroutesapp.Colores.color_letra_botones
import net.streamroutes.sreamroutesapp.Colores.color_letra_topbar
import net.streamroutes.sreamroutesapp.Colores.color_letrain
import net.streamroutes.sreamroutesapp.Colores.color_letraout
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpContactScreen(myViewModel: MyViewModel, navController: NavController){
    var nombre by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopBarBody(myViewModel,navController) },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(30.dp))

            // nombre
            HeaderTextField(
                tittle = "Nomnre",
                placeholder = "Nombre",
                size = 70,
                variable = nombre,
                onValueChange = {newValue -> nombre = newValue},
                visualTransformation = VisualTransformation.None,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                enabled = true
            )

            Spacer(modifier = Modifier.size(30.dp))

            // destinatario
            HeaderTextField(
                tittle = "Nuestro correo",
                placeholder = "streamroutes2.0@gmail.com",
                size = 70,
                variable = "",
                onValueChange = {},
                visualTransformation = VisualTransformation.None,
                keyboardOptions = KeyboardOptions.Default,
                enabled = false
            )

            Spacer(modifier = Modifier.size(30.dp))

            // mensaje
            HeaderTextField(
                tittle = "Mensaje",
                placeholder = "Escribe tu inconveniente.",
                size = 200,
                variable = mensaje,
                onValueChange = {newValue -> mensaje = newValue},
                visualTransformation = VisualTransformation.None,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.None
                ),
                enabled = true,
                singleLine = false,
                shape = 10
            )


            //boton
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // button enviar
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
                        text = "Enviar",
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.onTertiary
                    )
                }

                // ubicacion
                Row(modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 15.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center
                ) {
                    // text ubicacion
                    CustomText(
                        firstString = myViewModel.languageType().get(104),
                        secondString = myViewModel.languageType().get(105),
                        horizontal = Arrangement.Center
                    ) {

                    }
                }
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
            Text(text = myViewModel.languageType().get(99),
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigate(AppScreens.HelpScreen.route) }) {
                Icon(
                    painterResource(id = R.drawable.back),
                    contentDescription = "Te enviara al menu de opciones",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        colors = TopAppBarDefaults
            .smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
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
    visualTransformation: VisualTransformation,
    keyboardOptions: KeyboardOptions,
    enabled: Boolean,
    shape: Int = 30
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
            Text(text = placeholder, fontSize = 18.sp, color = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.5f))
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
        shape = RoundedCornerShape(percent = shape),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unfocusedBorderColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        singleLine = singleLine,
        enabled = enabled
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
            .heightIn(min = 48.dp),
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
                .clickable(onClick = onClick),
            textAlign = TextAlign.Center
        )
    }
}