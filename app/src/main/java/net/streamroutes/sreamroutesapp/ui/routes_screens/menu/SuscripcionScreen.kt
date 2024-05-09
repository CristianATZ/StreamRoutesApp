package net.streamroutes.sreamroutesapp.ui.routes_screens.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.utils.MyViewModel
import net.streamroutes.sreamroutesapp.R

data class ProfitItem(
    val name: String,
    val desc: String,
    val action: () -> Unit = {}
)

data class PremiumItem(
    val name: String,
    val price: String,
    val time: String,
    val carac: List<String>
)

@Composable
fun SuscripcionScreen(myViewModel: MyViewModel = MyViewModel(), onBack: () -> Unit) {
    val profit_items = listOf(
        ProfitItem(
            name = myViewModel.languageType().get(186),
            desc = myViewModel.languageType().get(187)
        ),
        ProfitItem(
            name = myViewModel.languageType().get(188),
            desc = myViewModel.languageType().get(189)
        ),
        ProfitItem(
            name = myViewModel.languageType().get(190),
            desc = myViewModel.languageType().get(191)
        ),
        ProfitItem(
            name = myViewModel.languageType().get(192),
            desc = myViewModel.languageType().get(193)
        )
    )

    val premium_items = listOf(
        PremiumItem(
            name = myViewModel.languageType()[309],
            price = "15",
            time = "MXN",
            carac = listOf(
                myViewModel.languageType()[310],
                myViewModel.languageType()[311],
                myViewModel.languageType()[312],
                myViewModel.languageType()[313]
            )
        ),
        PremiumItem(
            name = myViewModel.languageType()[314],
            price = "20",
            time = "MXN",
            carac = listOf(
                myViewModel.languageType()[310],
                myViewModel.languageType()[315],
                myViewModel.languageType()[313]
            )
        ),
        PremiumItem(
            name = myViewModel.languageType()[316],
            price = "15",
            time = "MXN",
            carac = listOf(
                myViewModel.languageType()[317],
                myViewModel.languageType()[313]
            )
        ),
        PremiumItem(
            name = myViewModel.languageType().get(195),
            price = "180",
            time = "MXN",
            carac = listOf(
                myViewModel.languageType()[319],
                myViewModel.languageType()[313]
            )
        ),
    )

    Scaffold(
        topBar = { TopBar(myViewModel, onBack) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LazyColumn(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    profit_items.forEach() { item ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .padding(vertical = 8.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(PaddingValues(16.dp))
                            ) {
                                Text(
                                    text = item.name,
                                    style = typography.headlineSmall,
                                    fontWeight = FontWeight.ExtraBold
                                )
                                Text(
                                    text = item.desc,
                                    style = typography.bodyLarge,
                                    textAlign = TextAlign.Justify
                                )
                            }
                        }
                    }
                }
                
                item {
                    Spacer(modifier = Modifier.size(16.dp))

                    /*
                    Text(
                        text = myViewModel.languageType()[308],
                        style = typography.titleLarge,
                        textAlign = TextAlign.Center
                    )
                     */

                    premium_items.forEach(){ item ->
                        Paquete(
                            profit_items,
                            item,
                            myViewModel
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun Paquete(
    profit_items: List<ProfitItem>,
    premiumItem: PremiumItem,
    myViewModel: MyViewModel
) {
    Column(
        Modifier.padding(vertical = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                //.clip(RoundedCornerShape(16.dp))
                .background(
                    colorScheme.primaryContainer,
                    RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = premiumItem.name,
                style = typography.bodyLarge,
                fontWeight = FontWeight.ExtraBold,
                color = colorScheme.onPrimaryContainer,
                modifier = Modifier
                    .padding(PaddingValues(16.dp))
            )

            premiumItem.carac.forEach(){ item ->
                Row(
                    Modifier.fillMaxWidth(0.9f)
                ) {
                    Text(
                        text = "* ",
                        fontWeight = FontWeight.Bold,
                        color = colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = item,
                        textAlign = TextAlign.Justify,
                        color = colorScheme.onPrimaryContainer
                    )
                }
            }

            Spacer(modifier = Modifier.size(16.dp))
        }

        Row(
            Modifier
                .fillMaxWidth(0.9f)
                .padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // boton contratar
            Button(
                onClick = {
                    //navController.navigate(AppScreens.MainScreen.route)
                },
                shape = RoundedCornerShape(bottomStart = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorScheme.tertiary,
                    contentColor = colorScheme.onTertiary
                ),
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
            ) {
                Text(
                    text = myViewModel.languageType()[320],
                    style = typography.bodyLarge
                )
            }

            Spacer(modifier = Modifier.size(8.dp))


            // precio del paquete
            Column(
                Modifier
                    .weight(0.2f)
                    .height(60.dp)
                    .background(
                        colorScheme.secondary,
                        RoundedCornerShape(bottomEnd = 16.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = premiumItem.price,
                    style = typography.headlineSmall,
                    fontWeight = FontWeight.ExtraBold,
                    color = colorScheme.onSecondary
                )
                Text(
                    text = premiumItem.time,
                    style = typography.bodySmall,
                    color = colorScheme.onSecondary
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    myViewModel: MyViewModel,
    onBack: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.lblBeneficios)
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBack() }) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBackIosNew,
                    contentDescription = "Te enviara al menu de opciones",
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorScheme.primary,
            titleContentColor = colorScheme.onPrimary,
            navigationIconContentColor = colorScheme.onPrimary
        )
    )
}