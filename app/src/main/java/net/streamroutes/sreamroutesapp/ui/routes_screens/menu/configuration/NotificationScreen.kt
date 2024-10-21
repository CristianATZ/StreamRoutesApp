package net.streamroutes.sreamroutesapp.ui.routes_screens.menu.configuration

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.Dialogs.DialogPush
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.viewmodel.routes.ConfigurationViewModel


@Preview (showBackground = true)
@Composable
fun notificaciones(){
    //notificacionesView()
}
data class NotificationItem(
    val name: String,
    val desc: String,
    val value: Boolean,
    val action: () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsScreen(configurationViewModel: ConfigurationViewModel){
    var dialogo by remember { mutableStateOf(false) }

    val notification_items = listOf(
        NotificationItem(
            name = stringResource(id = R.string.lblAlerts),
            desc = stringResource(id = R.string.lblAlertDescription),
            value = configurationViewModel.alerts,
            action = { configurationViewModel.updateAlerts(!configurationViewModel.alerts) }
        ),
        NotificationItem(
            name = stringResource(id = R.string.lblSuscription),
            desc = stringResource(id = R.string.lblSuscriptionDescription),
            value = configurationViewModel.suscription,
            action = { configurationViewModel.updateSuscription(!configurationViewModel.suscription) }
        ),
        NotificationItem(
            name = stringResource(id = R.string.lblMap),
            desc = stringResource(id = R.string.lblMapDescription),
            value = configurationViewModel.map,
            action = { configurationViewModel.updateMap(!configurationViewModel.map) }
        ),
    )

    // presentacion del dialog
    if (dialogo) {
        DialogPush(
            configurationViewModel
        ) {
            dialogo = !dialogo
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .verticalScroll(rememberScrollState())
    ) {

        // notificaciones push
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .clickable {
                    dialogo = !dialogo
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.size(16.dp))
            // textos
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.75f)
            ) {
                Text(
                    text = stringResource(id = R.string.lblNoti), // texto
                    style = typography.bodyLarge
                )
                Text(
                    text = stringResource(id = R.string.lblNotiDescription), // texto
                    style = typography.labelMedium
                )
            }
        }

        notification_items.forEach(){ item ->
            OptionSwitch(item = item, value = item.value) {
                item.action()
            }
        }
    }
}

@Composable
private fun OptionSwitch(
    item: NotificationItem,
    value: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {

        Spacer(modifier = Modifier.size(16.dp))
        // textos
        Column(
            modifier = Modifier
                .fillMaxWidth(0.75f)
        ) {
            Text(
                text = item.name, // texto
                style = typography.bodyLarge
            )
            Text(
                text = item.desc, // texto
                style = typography.labelMedium
            )
        }

        // switch
        Switch(
            checked = value,
            onCheckedChange = {
                onClick()
            }
        )
    }
}