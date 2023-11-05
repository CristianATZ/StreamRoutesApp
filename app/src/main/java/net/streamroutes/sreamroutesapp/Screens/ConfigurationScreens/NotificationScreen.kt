package net.streamroutes.sreamroutesapp.Screens.ConfigurationScreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.Dialogs.DialogPush
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R


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
fun NotificationsScreen(myViewModel: MyViewModel,navController: NavController){
    var noticias by remember { mutableStateOf(true) }
    var alertas by remember { mutableStateOf(true) }
    var suscripcion by remember { mutableStateOf(true) }
    var dialogo by remember { mutableStateOf(false) }

    val notification_items = listOf(
        NotificationItem(
            name = myViewModel.languageType().get(230),
            desc = myViewModel.languageType().get(231),
            value = noticias,
            action = { noticias = !noticias }
        ),
        NotificationItem(
            name = myViewModel.languageType().get(232),
            desc = myViewModel.languageType().get(233),
            value = alertas,
            action = { alertas = !alertas }
        ),
        NotificationItem(
            name = myViewModel.languageType().get(234),
            desc = myViewModel.languageType().get(235),
            value = suscripcion,
            action = { suscripcion = !suscripcion }
        ),
    )

    // presentacion del dialog
    if (dialogo) {
        DialogPush {
            dialogo = !dialogo
        }
    }

    Scaffold(
        topBar = { TopBarBody(myViewModel,navController) }
    ) { paddingValues ->
        // cuerpo
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
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
                        text = myViewModel.languageType().get(222), // texto
                        style = typography.bodyLarge
                    )
                    Text(
                        text = myViewModel.languageType().get(223), // texto
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBarBody(
    myViewModel: MyViewModel,
    navController: NavController
) {
    // top app bar
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = myViewModel.languageType().get(222),
                style = typography.titleLarge
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigate(AppScreens.ConfigurationScreen.route) }) {
                Icon(
                    painterResource(id = R.drawable.back),
                    contentDescription = "Te enviara al menu de configuraciones"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
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