package net.streamroutes.sreamroutesapp.Screens.Start

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.navigation.AppScreens
import net.streamroutes.sreamroutesapp.R


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
