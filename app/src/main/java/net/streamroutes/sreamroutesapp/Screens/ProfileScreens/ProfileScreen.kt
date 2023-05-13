package net.streamroutes.sreamroutesapp.Screens.ProfileScreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R

@Composable
fun ProfileScreen(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        // ICONO Y NOMBRE DEL USUARIO
        Icon(
            painter = painterResource(id = R.drawable.circulo_de_usuario),
            contentDescription = null,
            modifier = Modifier
                .size(125.dp)
        )
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = stringResource(id = R.string.user_name),
            color = Color(0xFF231955),
            fontSize = 26.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold
        )

        // ICONO Y DATOS DEL TELEFONO
        Spacer(modifier = Modifier.height(30.dp))
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .wrapContentHeight()
        )
        {
            Icon(
                painter = painterResource(id = R.drawable.telefono),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 50.dp, top = 8.dp)
                    .size(60.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column() {
                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    text = stringResource(id = R.string.phone),
                    color = Color(0xFF231955),
                    fontSize = 20.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "XXX-XXX-XXXX",
                    color = Color(0xFF231955),
                    fontSize = 16.sp,
                    fontFamily = FontFamily.SansSerif
                )
            }
        }

        // ICONO Y DATOS DE LA FECHA DE NACIMIENTO
        Spacer(modifier = Modifier.height(2.dp))
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .wrapContentHeight()
        )
        {
            Icon(
                painter = painterResource(id = R.drawable.calendario),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 50.dp, top = 8.dp)
                    .size(60.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column() {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = stringResource(id = R.string.birthday),
                    color = Color(0xFF231955),
                    fontSize =20.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "DD/MM/AAAA",
                    color = Color(0xFF231955),
                    fontSize =16.sp,
                    fontFamily = FontFamily.SansSerif
                )
            }
        }

        // ICONO Y DATOS DEL CORREO
        Spacer(modifier = Modifier.height(2.dp))
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .wrapContentHeight()
        )
        {
            Icon(
                painter = painterResource(id = R.drawable.correo),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 50.dp, top = 8.dp)
                    .size(60.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column() {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = stringResource(id = R.string.email),
                    color = Color(0xFF231955),
                    fontSize =20.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "user@gmail.com",
                    color = Color(0xFF231955),
                    fontSize =16.sp,
                    fontFamily = FontFamily.SansSerif
                )
            }
        }

        // ICONO PARA CONFIGURAR PERFIL
        Spacer(modifier = Modifier.height(25.dp))
        Button(onClick = {
            navController.navigate(route = AppScreens.ProfileConfigureScreen.route)
        }) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = null,
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Configuración")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileView(){

}