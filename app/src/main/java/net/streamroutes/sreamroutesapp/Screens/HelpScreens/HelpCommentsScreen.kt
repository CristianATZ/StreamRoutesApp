package net.streamroutes.sreamroutesapp.Screens.HelpScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.Colores.color_fondo_claro
import net.streamroutes.sreamroutesapp.Colores.color_fondo_topappbar_alterno
import net.streamroutes.sreamroutesapp.Colores.color_letra
import net.streamroutes.sreamroutesapp.Colores.color_letra_topappbar
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpCommentsScreen(myViewModel: MyViewModel,navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color_fondo_claro)
    ) {
        // top app bar
        TopAppBar(
            title = {
                Text(
                    text = myViewModel.languageType().get(93),
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
                .fillMaxSize()
                .padding(20.dp)
        ) {
            // textfield comment
            var comment by remember { mutableStateOf(TextFieldValue("")) }
            BasicTextField(
                value = comment,
                onValueChange = {comment = it},
                singleLine = false,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(1f)
                    .padding(4.dp),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 18.sp,
                    color = Color( 0xFFE8AA42),
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                ),
                decorationBox = { innerTextField ->
                    Row(
                        Modifier
                            .background(Color(0xFFFFE5B4), RoundedCornerShape(percent = 5))
                            .padding(16.dp)
                            .fillMaxWidth(0.8f)
                    ){
                        if (comment.text.isEmpty()){
                            Text(
                                text = myViewModel.languageType().get(94),
                                fontSize = 18.sp,
                                color = Color(0xFFFFF7E7),
                                letterSpacing = 3.sp
                            )
                        }
                        innerTextField()
                    }
                }
            )

            val checkedState = remember { mutableStateOf(true) }
            // SUGERENCIA Y CHECKBOX
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(4.dp)
                    .clickable { checkedState.value = !checkedState.value },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Column(

                ) {
                    Text(
                        text = myViewModel.languageType().get(95),
                        color = color_letra,
                        fontFamily = FontFamily.SansSerif,
                    )
                    Text(
                        text = myViewModel.languageType().get(96),
                        color = color_letra,
                        fontFamily = FontFamily.SansSerif,
                    )
                }
                Checkbox(
                    checked = checkedState.value,
                    modifier = Modifier
                        .padding(16.dp),
                    onCheckedChange = { checkedState.value = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFFE8AA42),
                        checkmarkColor = Color(0xFFFFE5B4),
                        uncheckedColor = Color.Gray
                    )
                )
            }

            // boton y subtitulo
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
                        text = myViewModel.languageType().get(97),
                        fontSize = 26.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = myViewModel.languageType().get(98),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun HelpCommmentsView(){

}