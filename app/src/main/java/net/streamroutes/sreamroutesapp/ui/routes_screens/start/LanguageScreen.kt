package net.streamroutes.sreamroutesapp.ui.routes_screens.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.utils.MyViewModel
import net.streamroutes.sreamroutesapp.navigation.AppScreens
import net.streamroutes.sreamroutesapp.R


data class LanguagueItem(
    val es: String,
    val en: String,
    val image: Painter,
    val action: () -> Unit = {}
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageScreen(myViewModel: MyViewModel, navController: NavController) {
    val languague_items = listOf(
        LanguagueItem(
            es = "Español",
            en = "Spanish",
            image = painterResource(id = R.drawable.mexico),
            action = {
                myViewModel.idioma = 0
                navController.navigate(route = AppScreens.LoginScreen.route)
            }
        ),
        LanguagueItem(
            es = "Inglés",
            en = "English",
            image = painterResource(id = R.drawable.estados),
            action = {
                myViewModel.idioma = 1
                navController.navigate(route = AppScreens.LoginScreen.route)
            }
        )
    )

    Scaffold(
        topBar = { TopBar(myViewModel) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            Divider()

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(languague_items.size){ index ->
                    val item = languague_items[index]
                    Languague(item)
                }
            }
        }
    }
}

@Composable
fun Languague(
    item: LanguagueItem
) {
    Card(
        modifier = Modifier
            .clickable { item.action() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PaddingValues(16.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = item.image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(100))
            )

            Spacer(modifier = Modifier.size(32.dp))

            Column {
                Text(
                    text = item.es,
                    style = typography.titleMedium
                )
                Text(
                    text = item.en,
                    style = typography.labelLarge
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    myViewModel: MyViewModel
) {
    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Selecciona el idioma de tu preferencia.",
            style = typography.titleLarge,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.size(16.dp))
        
        Text(
            text = "Select your preferred language.",
            style = typography.titleMedium,
            textAlign = TextAlign.Center
        )
    }
}