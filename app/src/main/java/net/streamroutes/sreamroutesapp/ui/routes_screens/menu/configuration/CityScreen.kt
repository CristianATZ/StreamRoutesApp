
package net.streamroutes.sreamroutesapp.ui.routes_screens.menu.configuration

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.viewmodel.Ciudad
import net.streamroutes.sreamroutesapp.viewmodel.ConfigurationViewModel


@Composable
fun CityScreen(configurationViewModel: ConfigurationViewModel) {
    CityBody(configurationViewModel)
}

@Composable
fun CityBody(configurationViewModel: ConfigurationViewModel) {
    val options = listOf(
        Ciudad.Leon,
        Ciudad.Irapuato,
        Ciudad.Celaya,
        Ciudad.Salamanca
    )

    Column {
        LazyColumn(
            modifier = Modifier
                .height(200.dp)
        ){
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
                            selected = option.name.equals(configurationViewModel.ciudad.name),
                            onClick = {
                                configurationViewModel.updateCiudad(option)
                            }
                        )
                    }
                }
            }
        }
    }
}