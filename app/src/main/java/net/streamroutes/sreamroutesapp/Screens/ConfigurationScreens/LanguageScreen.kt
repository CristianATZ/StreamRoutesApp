package net.streamroutes.sreamroutesapp.Screens.ConfigurationScreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.viewmodel.ConfigurationViewModel
import net.streamroutes.sreamroutesapp.viewmodel.Idioma

@Composable
fun LanguageScreen(configurationViewModel: ConfigurationViewModel) {
    LanguageBody(configurationViewModel)
}

@Composable
fun LanguageBody(configurationViewModel: ConfigurationViewModel) {
    val options = listOf(
        Idioma.Español,
        Idioma.Ingles
    )

    Column {
        LazyColumn {
            item {
                options.forEach { option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Spacer(modifier = Modifier.size(16.dp))

                        Text(
                            text = option.name,
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        RadioButton(
                            selected = option.name.equals(configurationViewModel.idioma.name),
                            onClick = {
                                configurationViewModel.updateIdioma(option)
                            }
                        )
                    }
                }
            }
        }
    }
}
