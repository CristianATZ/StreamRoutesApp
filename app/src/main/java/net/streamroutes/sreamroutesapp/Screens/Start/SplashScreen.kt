package net.streamroutes.sreamroutesapp.Screens.Start

import android.content.Context
import android.content.res.loader.ResourcesLoader
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.ui.theme.StreamRoutesAppTheme


@Composable
fun SplashScreen(navController: NavHostController, myViewModel: MyViewModel) {
    LaunchedEffect(key1 = true) {
        delay(3000)
        navController.navigate(AppScreens.LanguageScreen.route)
    }
    Splash(myViewModel)
}

@Composable
fun Splash(myViewModel: MyViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = if(myViewModel.tema) painterResource(id = R.drawable.letrablanca) else painterResource(id = R.drawable.letranegra),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.85f)
        )
        /*Text(
            text = "STREAM",
            fontSize = 40.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.SansSerif,
            letterSpacing = 5.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "ROUTES",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            letterSpacing = 3.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        )*/
    }
}


/*
@Preview
@Composable
fun SplashScreenDark() {
    StreamRoutesAppTheme(true) {
        Splash()
    }
}

@Preview
@Composable
fun SplashScreenLight() {
    StreamRoutesAppTheme(false) {
        Splash()
    }
}*/
