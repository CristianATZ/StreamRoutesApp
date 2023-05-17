package net.streamroutes.sreamroutesapp.Screens.ProfileScreens

import android.app.appsearch.AppSearchSchema
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContactInfoScreen(navController: NavController){
    TopAppBar(
        title = { Text(stringResource(id = R.string.title_profile_contact_info_screen)) },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack()  }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "Regresara a la ventana de datos personales del perfil"
                )
            }
        })
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 60.dp, start = 30.dp)
    )
    {
        // CAMBIAR TELEFONO
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
                    .padding(top = 8.dp)
                    .size(60.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column() {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = stringResource(id = R.string.change_phone_number),
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
            Spacer(modifier = Modifier.width(5.dp))
            Button(
                onClick = {
                    navController.navigate(route = AppScreens.ProfileChangePhoneScreen.route)
                },
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

        // CAMBIAR CORREO
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
                    .padding(top = 8.dp)
                    .size(60.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column() {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = stringResource(id = R.string.change_email),
                    color = Color(0xFF231955),
                    fontSize = 20.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "user@gmail.com",
                    color = Color(0xFF231955),
                    fontSize = 16.sp,
                    fontFamily = FontFamily.SansSerif
                )
            }
            Spacer(modifier = Modifier.width(25.dp))
            Button(
                onClick = {
                    navController.navigate(route = AppScreens.ProfileChangeEmailScreen.route)
                },
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

    }
}

@Preview(showBackground = true)
@Composable
fun ProfileContactInfoView(){

}