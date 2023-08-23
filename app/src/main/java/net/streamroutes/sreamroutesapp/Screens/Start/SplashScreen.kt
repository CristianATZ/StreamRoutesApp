package net.streamroutes.sreamroutesapp.Screens.Start

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import net.streamroutes.sreamroutesapp.Colores.color_fondo
import net.streamroutes.sreamroutesapp.Colores.color_letraout
import net.streamroutes.sreamroutesapp.Navigation.AppScreens

@Composable
fun SplashScreen(navController: NavHostController) {
    LaunchedEffect(key1 = true) {
        delay(3000)
        navController.navigate(AppScreens.LanguageScreen.route)
    }
    Splash()
}

@Composable
fun Splash() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color_fondo),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "STREAM",
            fontSize = 40.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.SansSerif,
            letterSpacing = 5.sp,
            textAlign = TextAlign.Center,
            color = color_letraout
        )
        Text(
            text = "ROUTES",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            letterSpacing = 3.sp,
            textAlign = TextAlign.Center,
            color = color_letraout
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SplashScreenView() {
    Splash()
}