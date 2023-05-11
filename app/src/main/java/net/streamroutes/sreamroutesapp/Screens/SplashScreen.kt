package net.streamroutes.sreamroutesapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R

@Composable
fun SplashScreen(navController: NavHostController) {
    LaunchedEffect(key1 = true) {
        delay(3000)
        //navController.popBackStack()
        navController.navigate(AppScreens.LoginScreen.route)
    }
    Splash()
}

@Composable
fun Splash() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.streamrouteslogowimage),
                contentDescription = "Logo Stream Routes",
                modifier = Modifier
                    .size(300.dp)
            )

            CustomLinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
            )
            Text(text = "Cargando...",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color(0xFF231955)
            )
        }

        Spacer(modifier = Modifier.height(300.dp))

        Text(text = "Version 2.0",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color(0xFF231955)
        )
    }
}

// custom progress bar
@Composable
fun CustomLinearProgressIndicator(
    modifier: Modifier = Modifier,
    progressColor: Color = Color(0xFFFFFFFF),
    backgroundColor: Color = Color(0xFF231955),
    clipShape: Shape = RoundedCornerShape(16.dp)
) {
    var progress by remember { mutableStateOf(0.0f) }

    LaunchedEffect(key1 = true){
        for(i in 10..100 step 10){
            delay(300)
            progress = i/100f
        }
    }

    Box(
        modifier = modifier
            .clip(clipShape)
            .background(backgroundColor)
            .height(8.dp)
    ) {
        Box(
            modifier = Modifier
                .background(progressColor)
                .fillMaxHeight()
                .fillMaxWidth(progress)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SplashScreenView() {
    Splash()
}