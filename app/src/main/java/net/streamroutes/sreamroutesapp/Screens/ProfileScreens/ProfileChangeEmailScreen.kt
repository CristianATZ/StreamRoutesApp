package net.streamroutes.sreamroutesapp.Screens.ProfileScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R

@Composable
fun ProfileChangeEmailScren(navController: NavController){
    val gradientColors = listOf(Color(0xFF192833), Color(0xFF192833))
    val roundCornerShape = RoundedCornerShape(topEnd = 30.dp, bottomStart = 30.dp, topStart = 10.dp, bottomEnd = 10.dp)

    Column(
        modifier = Modifier.padding(top = 30.dp, start = 30.dp, end = 30.dp)
    ) {
        // CORREO NUEVO
        Text(
            modifier = Modifier,
            text = stringResource(id = R.string.new_email),
            color = Color(0xFF231955),
            fontSize = 20.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold
        )
        var email by remember { mutableStateOf(TextFieldValue("")) }
        BasicTextField(
            value = email,
            onValueChange = {email = it},
            singleLine = true,
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth(0.85f),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            decorationBox = { innerTextField ->
                Row(
                    Modifier
                        .background(Color(0xFFFFE5B4), RoundedCornerShape(percent = 30))
                        .padding(20.dp)
                        .fillMaxWidth(0.8f)
                ){
                    if (email.text.isEmpty()){
                        Text(
                            text = "user@gmail.com",
                            color = Color(0xFFFFF7E7),
                            letterSpacing = 2.sp,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                        )
                    }
                    innerTextField()
                }
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(colors = gradientColors),
                    shape = roundCornerShape
                )
                .clip(roundCornerShape)
                .padding(PaddingValues(horizontal = 30.dp, vertical = 8.dp))
                .clickable {
                    //navController.navigate(route = )
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "ENVIAR CÓDIGO",
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(40.dp))


        // CODIGO SEGURIDAD
        Text(
            modifier = Modifier,
            text = stringResource(id = R.string.security_code),
            color = Color(0xFF231955),
            fontSize = 20.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold
        )
        var code by remember { mutableStateOf(TextFieldValue("")) }
        BasicTextField(
            value = code,
            onValueChange = {code = it},
            singleLine = true,
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth(0.85f),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            decorationBox = { innerTextField ->
                Row(
                    Modifier
                        .background(Color(0xFFFFE5B4), RoundedCornerShape(percent = 30))
                        .padding(20.dp)
                        .fillMaxWidth(0.8f)
                ){
                    if (code.text.isEmpty()){
                        Text(
                            text = "XXX-XXX",
                            color = Color(0xFFFFF7E7),
                            letterSpacing = 2.sp,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                        )
                    }
                    innerTextField()
                }
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(colors = gradientColors),
                    shape = roundCornerShape
                )
                .clip(roundCornerShape)
                .padding(PaddingValues(horizontal = 30.dp, vertical = 8.dp))
                .clickable {
                    //navController.navigate(route = )
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "VERIFICAR",
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileChangeEmailView(){

}
