package net.streamroutes.sreamroutesapp.ui.start_screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.navigation.AppScreens
import net.streamroutes.sreamroutesapp.utils.brush

@Composable
fun SelectOptionScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(64.dp))

        Text(
            text = stringResource(id = R.string.lblQueQuieresHacer),
            style = typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.size(64.dp))

        CustomCard(
            imageId = R.drawable.autobus,
            title = stringResource(id = R.string.lblTransportePublico),
            backgroundColor = colorScheme.background,
            contentColor = colorScheme.onBackground
        ){
            navController.navigate(AppScreens.MainScreen.route)
        }
        
        Spacer(modifier = Modifier.size(16.dp))

        CustomCard(
            imageId = R.drawable.estacionamiento,
            title = stringResource(id = R.string.lblEstacionamiento),
            backgroundColor = colorScheme.background,
            contentColor = colorScheme.onBackground
        ){
            navController.navigate(AppScreens.MainParking.route)
        }

    }
}


@Composable
fun CustomCard(
    imageId: Int,
    title: String,
    backgroundColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        modifier = modifier
            .fillMaxWidth(0.92f)
            .height(125.dp)
            .clickable { onClick() }
    ) {
        Row {
            Column(
                modifier = Modifier
                    .background(
                        color = colorScheme.inverseSurface,
                        shape = RoundedCornerShape(topEnd = 50.dp)
                    )
                    .width(125.dp)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = null,
                    modifier = Modifier.size(75.dp)
                )
            }

            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start

            ) {
                Spacer(modifier = Modifier.size(16.dp))

                Text(
                    text = title,
                    style = typography.titleLarge,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.size(8.dp))
            }
        }
    }
}
