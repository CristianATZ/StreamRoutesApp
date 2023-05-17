package net.streamroutes.sreamroutesapp.Screens.ProfileScreens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileDataInfoScreen(navController: NavController){
    TopAppBar(
        title = { Text(stringResource(id = R.string.title_profile_data_info_screen)) },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack()  }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "Regresara a la ventana de configuracion del perfil"
                )
            }
        })
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp, start = 20.dp, end = 20.dp)
    ) {

            // INFORMACIÓN DE CONTACTO
            Text(
                text = stringResource(id = R.string.contact_info),
                color = Color(0xFF231955),
                fontSize =20.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold
            )
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(color = Color(0xFFFFE5B4))
            )
            {
                Column(
                    modifier = Modifier.padding(start = 5.dp, top = 5.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.phone) + ": XXX-XXX-XXXX",
                        color = Color(0xFF231955),
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                    Text(
                        text = stringResource(id = R.string.email) + ": user@gmail.com",
                        color = Color(0xFF231955),
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                }
                Spacer(modifier = Modifier.width(70.dp))
                Button(
                    modifier = Modifier.padding(top = 5.dp),
                    onClick = {
                        navController.navigate(route = AppScreens.ProfileContactInfoScreen.route)
                    }
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


        Spacer(modifier = Modifier.height(10.dp))


            // INFORMACIÓN DE CONTACTO
            Text(
                text = stringResource(id = R.string.varied_info),
                color = Color(0xFF231955),
                fontSize =20.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold
            )
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .background(color = Color(0xFFFFE5B4))
            )
            {
                Column(
                    modifier = Modifier.padding(start = 5.dp, top = 5.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.birthday) + ": DD/MM/AAAA",
                        color = Color(0xFF231955),
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                    Text(
                        text = stringResource(id = R.string.ocupation) + ": ocupación",
                        color = Color(0xFF231955),
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                    Text(
                        text = stringResource(id = R.string.interests) + ": intereses",
                        color = Color(0xFF231955),
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                }
                Spacer(modifier = Modifier.width(55.dp))
                Button(
                    modifier = Modifier.padding(top = 20.dp),
                    onClick = {
                        navController.navigate(route = AppScreens.ProfileVariedInfoScreen.route)
                    }
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
fun ProfileDataInfoView(){

}