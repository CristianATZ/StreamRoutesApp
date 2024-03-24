package net.streamroutes.sreamroutesapp.Screens.HelpScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.viewmodel.MyViewModel
import net.streamroutes.sreamroutesapp.navigation.AppScreens
import net.streamroutes.sreamroutesapp.R

@Preview(showBackground = true)
@Composable
fun HelpView(){

}


data class HelpItem(
    val name: String,
    val desc: String,
    val action: () -> Unit
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpScreen(myViewModel: MyViewModel = MyViewModel()) {
    val help_items = listOf(
        HelpItem(
            name = myViewModel.languageType().get(255),
            desc = myViewModel.languageType().get(256),
            action =  {

            }
        ),
        HelpItem(
            name = myViewModel.languageType().get(257),
            desc = myViewModel.languageType().get(258),
            action =  {

            }
        ),
        HelpItem(
            name = myViewModel.languageType().get(259),
            desc = myViewModel.languageType().get(260),
            action =  {

            }
        )
    )

    Scaffold(
        topBar = { TopBarBody(myViewModel) },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            help_items.forEach(){ item ->
                Options(item)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBarBody(
    myViewModel: MyViewModel,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Ayuda y soporte",
                style = typography.titleLarge
            )
        },
        navigationIcon = {
            IconButton(onClick = {  }) {
                Icon(
                    painterResource(id = R.drawable.back),
                    contentDescription = "Te enviara al menu de opciones"
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
private fun Options(
    item: HelpItem
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(70.dp)
            .clickable {
                item.action()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.size(16.dp))

        // textos
        Column {
            Text(
                text = item.name, // texto
                style = typography.bodyLarge
            )
            Text(
                text = item.desc, // texto
                style = typography.labelMedium
            )
        }
    }
}