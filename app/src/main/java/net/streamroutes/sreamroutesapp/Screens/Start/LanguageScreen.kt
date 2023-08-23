package net.streamroutes.sreamroutesapp.Screens.Start

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.Colores.color_fondo
import net.streamroutes.sreamroutesapp.Colores.color_fondo_textfield
import net.streamroutes.sreamroutesapp.Colores.color_fondo_topbar
import net.streamroutes.sreamroutesapp.Colores.color_letra_textfield
import net.streamroutes.sreamroutesapp.Colores.color_letra_topbar
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R
import org.intellij.lang.annotations.Language

@Preview (showBackground = true)
@Composable
fun view() {
    //LanguageScreen()
}

@Composable
fun LanguageScreen(myViewModel: MyViewModel, navController: NavController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color_fondo),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color_fondo_topbar,
                        RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.size(25.dp))
                CustomText(
                    firstString = "Selecciona el idioma de tu preferencia.",
                    horizontal = Arrangement.Center,
                    color = color_letra_topbar
                )

                CustomText(
                    firstString = "Select your preferred language.",
                    horizontal = Arrangement.Center,
                    color = color_letra_topbar
                )
                Spacer(modifier = Modifier.size(25.dp))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp, start = 25.dp, end = 25.dp, bottom = 25.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OptionLanguage(
                    img = painterResource(id = R.drawable.mexico),
                    first = "Español",
                    second = "Spanish"
                ) {
                    sendToSignIn(0, navController, myViewModel)
                }

                OptionLanguage(
                    img = painterResource(id = R.drawable.estados),
                    first = "Inglés",
                    second = "English"
                ) {
                    sendToSignIn(1, navController, myViewModel)
                }
            }
        }
}

fun sendToSignIn(idioma: Int, navController: NavController, myViewModel: MyViewModel){
    myViewModel.idioma = idioma
    navController.navigate(route = AppScreens.LoginScreen.route)
}

@Composable
private fun CustomText(
    firstString: String,
    horizontal: Arrangement.Horizontal,
    color: Color
) {
    Row (
        modifier = Modifier
            .fillMaxWidth(0.95f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = horizontal
    ){
        // forgot
        Text(
            text = buildAnnotatedString{
                withStyle(style = SpanStyle(color = color,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.SansSerif)
                ) {
                    append(firstString)
                }
            },
            modifier = Modifier
                .wrapContentWidth()
        )
    }
}

@Composable
fun OptionLanguage(
    img: Painter,
    first: String,
    second: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(color_fondo_topbar, RoundedCornerShape(15.dp))
            .width(150.dp)
            .clickable (onClick = onClick),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(5.dp))
        CustomText(
            firstString = first,
            horizontal = Arrangement.Center,
            color = color_letra_topbar
        )
        Spacer(modifier = Modifier.size(5.dp))
        Image(
            painter = img,
            contentDescription = null,
            modifier = Modifier
                .size(width = 100.dp, height = 50.dp)
        )
        Spacer(modifier = Modifier.size(5.dp))
        CustomText(
            firstString = second,
            horizontal = Arrangement.Center,
            color = color_letra_topbar
        )
        Spacer(modifier = Modifier.size(5.dp))
    }
}