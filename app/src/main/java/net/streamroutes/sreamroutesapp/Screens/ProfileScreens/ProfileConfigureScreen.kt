package net.streamroutes.sreamroutesapp.Screens.ProfileScreens

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R
import kotlin.random.Random

@Composable
fun ProfileConfigureScreen(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 35.dp, start = 30.dp)
    )
    {
        // DATOS PERSONALES
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .wrapContentHeight()
        )
        {
            Icon(
                painter = painterResource(id = R.drawable.editar_usuario),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .size(60.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column() {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = stringResource(id = R.string.personal_info),
                    color = Color(0xFF231955),
                    fontSize = 20.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Sexo, Fecha Nac, etc.",
                    color = Color(0xFF231955),
                    fontSize = 16.sp,
                    fontFamily = FontFamily.SansSerif
                )
            }
            Spacer(modifier = Modifier.width(5.dp))
            Button(
                onClick = { },
                modifier = Modifier.padding(top = 15.dp, start = 15.dp)
            )
            {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            }
        }

        // CONTRASEÑA
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .wrapContentHeight()
        )
        {
            Icon(
                painter = painterResource(id = R.drawable.candado),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .size(60.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column() {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = stringResource(id = R.string.password_profile),
                    color = Color(0xFF231955),
                    fontSize =20.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Seguridad",
                    color = Color(0xFF231955),
                    fontSize =16.sp,
                    fontFamily = FontFamily.SansSerif
                )
            }
            Spacer(modifier = Modifier.width(5.dp))
            Button(
                onClick = { },
                modifier = Modifier.padding(top = 15.dp, start = 75.dp)
            )
            {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileConfigureView(){

}