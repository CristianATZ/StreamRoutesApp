package net.streamroutes.sreamroutesapp.Screens.HelpScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpScreen(navController: NavController) {
    Column() {
        TopAppBar(
            title = { Text(stringResource(id = R.string.tittle_help_screen)) },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack()  }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Regresara al menu principal"
                    )
                }
            })
        // comments button
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .wrapContentHeight()
                .clickable {
                    navController.navigate(route = AppScreens.HelpCommentsScreen.route)
                }
        ){
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                painter = painterResource(id = R.drawable.comentarios),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .padding(top = 15.dp)
            )
            Spacer(modifier = Modifier.width(25.dp))
            Column(
                modifier = Modifier
                    .height(80.dp)
                    .width(230.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.help_comment_tittle),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.Start),
                    color = Color(0xFF231955),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    fontFamily = FontFamily.Monospace
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = stringResource(id = R.string.help_comment_subtittle),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.Start),
                    color = Color(0xFF231955),
                    fontSize = 12.sp,
                    fontFamily = FontFamily.SansSerif
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.siguiente),
                contentDescription = null,
                modifier = Modifier
                    .size(55.dp)
                    .wrapContentWidth(Alignment.End)
                    .padding(top = 25.dp)
            )
        }

        // contact button
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .wrapContentHeight()
                .clickable {
                    navController.navigate(route = AppScreens.HelpContactScreen.route)
                }
        ){
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                painter = painterResource(id = R.drawable.usuarios),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .padding(top = 15.dp)
            )
            Spacer(modifier = Modifier.width(25.dp))
            Column(
                modifier = Modifier
                    .height(80.dp)
                    .width(230.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.help_contact_tittle),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.Start),
                    color = Color(0xFF231955),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    fontFamily = FontFamily.Monospace
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = stringResource(id = R.string.help_contact_subtittle),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.Start),
                    color = Color(0xFF231955),
                    fontSize = 15.sp,
                    fontFamily = FontFamily.SansSerif
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.siguiente),
                contentDescription = null,
                modifier = Modifier
                    .size(55.dp)
                    .wrapContentWidth(Alignment.End)
                    .padding(top = 25.dp)
            )
        }

        // About app button
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .wrapContentHeight()
                .clickable {
                    navController.navigate(route = AppScreens.HelpAboutAppScreen.route)
                }
        ){
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                painter = painterResource(id = R.drawable.informacion),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .padding(top = 15.dp)
            )
            Spacer(modifier = Modifier.width(25.dp))
            Column(
                modifier = Modifier
                    .height(80.dp)
                    .width(230.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.help_information_tittle),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.Start),
                    color = Color(0xFF231955),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    fontFamily = FontFamily.Monospace
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = stringResource(id = R.string.help_information_subtittle),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.Start),
                    color = Color(0xFF231955),
                    fontSize = 12.sp,
                    fontFamily = FontFamily.SansSerif
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.siguiente),
                contentDescription = null,
                modifier = Modifier
                    .size(55.dp)
                    .wrapContentWidth(Alignment.End)
                    .padding(top = 25.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HelpView(){

}