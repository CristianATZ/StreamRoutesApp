package net.streamroutes.sreamroutesapp.Screens.HelpScreens

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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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

@Composable
fun HelpScreen(navController: NavController){
    Column(
        modifier = Modifier
            .padding(top = 50.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
    ) {
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
            Icon(
                painter = painterResource(id = R.drawable.streamrouteslogo),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
            )
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
                painter = painterResource(id = R.drawable.streamrouteslogo),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .wrapContentWidth(Alignment.End)
            )
        }

        // about app button
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .wrapContentHeight()
                .clickable {
                    navController.navigate(route = AppScreens.HelpAboutAppScreen.route)
                }
        ){
            Icon(
                painter = painterResource(id = R.drawable.streamrouteslogo),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
            )
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
                painter = painterResource(id = R.drawable.streamrouteslogo),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .wrapContentWidth(Alignment.End)
            )
        }

        // Contact button
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .wrapContentHeight()
                .clickable {
                    navController.navigate(route = AppScreens.HelpContactScreen.route)
                }
        ){
            Icon(
                painter = painterResource(id = R.drawable.streamrouteslogo),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
            )
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
                painter = painterResource(id = R.drawable.streamrouteslogo),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .wrapContentWidth(Alignment.End)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HelpView(){

}