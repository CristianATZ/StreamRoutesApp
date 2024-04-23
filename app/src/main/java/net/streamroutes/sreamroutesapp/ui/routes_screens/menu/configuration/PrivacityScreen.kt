package net.streamroutes.sreamroutesapp.ui.routes_screens.menu.configuration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.viewmodel.routes.ConfigurationViewModel

data class PrivacyItem(
    val name: String,
    val desc: String,
    val value: Boolean,
    val action: () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacityScreen(configurationViewModel: ConfigurationViewModel){

    /*
    // variables globales
    var localizacion by remember { mutableStateOf(true) }
    var anuncios by remember { mutableStateOf(true) }
    var rutas by remember { mutableStateOf(true) }
    var suscripcion by remember { mutableStateOf(true) }

     */

    val privacy_items = listOf(
        PrivacyItem(
            name = stringResource(id = R.string.lblLocaliz),
            desc = stringResource(id = R.string.lblLocalizDescription),
            value = configurationViewModel.current,
            action = {
                configurationViewModel.updateCurrent(!configurationViewModel.current)
            }
        ),
        PrivacyItem(
            name = stringResource(id = R.string.lblAnuncios),
            desc = stringResource(id = R.string.lblAnunciosDescription),
            value = configurationViewModel.ads,
            action = {
                configurationViewModel.updateAds(!configurationViewModel.ads)
            }
        ),
        PrivacyItem(
            name = stringResource(id = R.string.lblRutas),
            desc = stringResource(id = R.string.lblRutasDescription),
            value = configurationViewModel.routes,
            action = {
                configurationViewModel.updateRoutes(!configurationViewModel.routes)
            }
        ),
        PrivacyItem(
            name = stringResource(id = R.string.lblPagoSuscrip),
            desc = stringResource(id = R.string.lblPagoSuscripDescription),
            value = configurationViewModel.paymet,
            action = {
                configurationViewModel.updatePayment(!configurationViewModel.paymet)
            }
        )
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .verticalScroll(rememberScrollState())
    ) {
        privacy_items.forEach(){ item ->
            Options(item = item)
        }
    }
}

@Composable
private fun Options(
    item: PrivacyItem
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .toggleable(
                value = item.value,
                onValueChange = { item.action() }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Spacer(modifier = Modifier.size(16.dp))

        // textos
        Column(
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = item.name,
                style = typography.bodyLarge
            )

            Text(
                text = item.desc,
                style = typography.labelMedium
            )
        }

        Spacer(modifier = Modifier.size(16.dp))

        // switch
        Switch(
            checked = item.value,
            onCheckedChange = {
                item.action()
            }
        )
    }
}

@Preview (showBackground = true)
@Composable
fun view(){
    //priva()
}