package net.streamroutes.sreamroutesapp.Screens.HelpScreens

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
import net.streamroutes.sreamroutesapp.Colores.color_fondo_claro
import net.streamroutes.sreamroutesapp.Colores.color_fondo_topappbar_alterno
import net.streamroutes.sreamroutesapp.Colores.color_letra_topappbar
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpContactScreen(myViewModel: MyViewModel, navController: NavController){
    var nombre by remember { mutableStateOf(TextFieldValue()) }
    var mensaje by remember { mutableStateOf(TextFieldValue()) }
    var mail by remember { mutableStateOf(TextFieldValue("streamroutes2.0@gmail.com")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color_fondo_claro)
    ) {
        // top app bar
        TopAppBar(
            title = {
                Text(text = myViewModel.languageType().get(99),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.navigate(AppScreens.HelpScreen.route) }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Te enviara al menu de opciones"
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
            Spacer(modifier = Modifier.size(30.dp))

            // nombre
            TextWTittle(
                tittle = myViewModel.languageType().get(100),
                placeholder = myViewModel.languageType().get(100),
                readOnly = false,
                size = 70,
                variable = nombre,
                onVariableChange = { newValue -> nombre = newValue }
            )

            Spacer(modifier = Modifier.size(30.dp))

            // destinatario
            TextWTittle(
                tittle = myViewModel.languageType().get(101),
                placeholder = "",
                readOnly = true,
                size = 70,
                variable = mail,
                onVariableChange = { newValue -> mail = newValue }
            )

            Spacer(modifier = Modifier.size(30.dp))

            // mensaje
            TextWTittle(
                tittle = myViewModel.languageType().get(102),
                placeholder = "",
                readOnly = false,
                singleLine = false,
                size = 150,
                variable = mensaje,
                onVariableChange = { newValue -> mensaje = newValue },
                roundedCornerShape = RoundedCornerShape(percent = 5)
            )


            //boton
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // button enviar
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
                        .padding(16.dp)
                ) {
                    Text(
                        text = myViewModel.languageType().get(103),
                        fontSize = 26.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
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

@Composable
private fun TextWTittle(
    tittle: String,
    placeholder: String,
    readOnly: Boolean,
    singleLine: Boolean = true,
    size: Int,
    variable: TextFieldValue,
    onVariableChange: (TextFieldValue) -> Unit,
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(percent = 30)
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
        BasicTextField(
            value = variable,
            onValueChange = onVariableChange,
            singleLine = singleLine,
            readOnly = readOnly,
            modifier = Modifier
                .height(size.dp)
                .fillMaxWidth()
                .padding(4.dp),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
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
            }
        )
    }
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
                withStyle(style = SpanStyle(color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.SansSerif)) {
                    append(firstString)
                }
                append(" ")
                withStyle(style = SpanStyle(color = Color(0xFFE8AA42),
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