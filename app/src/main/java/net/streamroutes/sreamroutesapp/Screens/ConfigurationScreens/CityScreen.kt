
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


@Composable
fun CityScreen(configurationViewModel: ConfigurationViewModel) {
    CityBody(configurationViewModel)
}

@Composable
fun CityBody(configurationViewModel: ConfigurationViewModel) {
    val options = listOf(
        "Leon",
        "Irapuato",
        "Celaya",
        "Salamanca",
        "Guanajuato",
        "Silao",
        "Acambaro",
        "San francisco del rincon",
        "Moroleon",
        "Salvatierra",
        "Uriangato"
    )

    // Variable
    val selectedOption = remember { mutableStateOf("") }

    fun onOptionSelected(option: String) {
        selectedOption.value = option
    }

    fun isOptionSelected(option: String): Boolean {
        return selectedOption.value == option
    }

    Column {
        LazyColumn(
            modifier = Modifier
                .height(200.dp)
        ){
            item {
                options.forEach { option ->
                    Row(
                        modifier = Modifier
                            .clickable {
                                onOptionSelected(option)
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Spacer(modifier = Modifier.size(16.dp))

                        Text(
                            text = option,
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        RadioButton(
                            selected = isOptionSelected(option),
                            onClick = {
                                onOptionSelected(option)
                            }
                        )
                    }
                }
            }
        }
    }
}