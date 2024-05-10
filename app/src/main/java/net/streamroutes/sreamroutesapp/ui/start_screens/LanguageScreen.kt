package net.streamroutes.sreamroutesapp.ui.start_screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.navigation.AppScreens
import net.streamroutes.sreamroutesapp.utils.MyViewModel

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

    val brush = Brush.verticalGradient(
        listOf(Color(0xFFE8AA42), Color(0xFFEACE43))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderLanguage()
        LanguageOptions(languague_items)
    }
}

@Composable
fun LanguageOptions(
    languague_items: List<LanguagueItem>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(languague_items.size){ index ->
            Languague(languague_items[index])
        }
    }
}

@Composable
fun Languague(
    item: LanguagueItem
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.background,
            contentColor = colorScheme.onBackground
        ),
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
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.size(16.dp))

            Column {
                Text(
                    text = item.es,
                    style = typography.titleMedium
                )
                Text(
                    text = item.en,
                    style = typography.bodyMedium
                )
            }
        }
    }
}

@Composable
private fun HeaderLanguage() {
    Spacer(modifier = Modifier.size(16.dp))

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.background,
            contentColor = colorScheme.onBackground
        ),
        modifier = Modifier
            .fillMaxWidth(0.92f)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.lblPreferencia),
                style = typography.bodyLarge,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.size(8.dp))

            Text(
                text = stringResource(id = R.string.lblPreferenciaEN),
                style = typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}