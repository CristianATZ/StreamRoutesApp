package net.streamroutes.sreamroutesapp.Screens.ProfileScreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.R

@Composable
fun ProfileVariedInfoScreen(navController: NavController){
    Column(
        modifier = Modifier.padding(top = 30.dp, start = 30.dp, end = 30.dp)
    ) {
        Text(
            modifier = Modifier,
            text = stringResource(id = R.string.birthday),
            color = Color(0xFF231955),
            fontSize = 20.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier,
            text = stringResource(id = R.string.ocupation),
            color = Color(0xFF231955),
            fontSize = 20.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier,
            text = stringResource(id = R.string.interests),
            color = Color(0xFF231955),
            fontSize = 20.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileVariedInformationView(){

}