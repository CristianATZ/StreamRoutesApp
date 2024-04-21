package net.streamroutes.sreamroutesapp.ui.routes_screens.menu.help

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.viewmodel.MyViewModel

@Composable
fun AboutScreen(myViewModel: MyViewModel = MyViewModel()){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.logo_navbar_2),
            contentDescription = "Logo Rumapp",
            modifier = Modifier
                .size(200.dp)
        )
        Image(
            painter = if(myViewModel.tema) painterResource(id = R.drawable.letrablanca) else painterResource(id = R.drawable.letranegra),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.85f)
        )

        Spacer(modifier = Modifier.size(16.dp))

        Text(
            text = myViewModel.languageType().get(268) + " 3.2.1",
            style = typography.titleMedium
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = myViewModel.languageType().get(269),
            style = typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(bottom = 16.dp)
        )
    }
}