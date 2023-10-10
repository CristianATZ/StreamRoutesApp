package net.streamroutes.sreamroutesapp.Screens.MenuScreens

import android.widget.ImageView
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.Colores.color_botones
import net.streamroutes.sreamroutesapp.Colores.color_fondo
import net.streamroutes.sreamroutesapp.Colores.color_fondo_textfield
import net.streamroutes.sreamroutesapp.Colores.color_fondo_topbar
import net.streamroutes.sreamroutesapp.Colores.color_icon
import net.streamroutes.sreamroutesapp.Colores.color_letra_botones
import net.streamroutes.sreamroutesapp.Colores.color_letra_textfield
import net.streamroutes.sreamroutesapp.Colores.color_letra_topbar
import net.streamroutes.sreamroutesapp.Colores.color_letrain
import net.streamroutes.sreamroutesapp.Colores.color_letraout
import net.streamroutes.sreamroutesapp.Colores.cuatro
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R

@Preview (showBackground = true)
@Composable
fun suscripcionView() {
    //suscripcion()
}


data class ProfitItem(
    val name: String,
    val desc: String,
    val action: () -> Unit = {}
)

data class PremiumItem(
    val name: String,
    val selected: Boolean,
    val action: () -> Unit = {}
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuscripcionScreen(myViewModel: MyViewModel, navController: NavController) {
    val context = LocalContext.current

    var mensual by remember {
        mutableStateOf(false)
    }
    var anual by remember {
        mutableStateOf(false)
    }
    var estudiante by remember {
        mutableStateOf(false)
    }

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
            name = "Estudiante",
            selected = estudiante,
            action = {
                estudiante = !estudiante
                mensual = false
                anual = false
            }
        ),
        PremiumItem(
            name = myViewModel.languageType().get(194),
            selected = mensual,
            action = {
                mensual = !mensual
                anual = false
                estudiante = false
            }
        ),
        PremiumItem(
            name = myViewModel.languageType().get(195),
            selected = anual,
            action = {
                anual = !anual
                mensual = false
                estudiante = false
            }
        )
    )

    Scaffold(
        topBar = { TopBar(navController,myViewModel) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
            ) {
                item {
                    // logo
                    Image(
                        painter = painterResource(id = R.drawable.logonletran),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(16.dp)
                    )
                }

                // beneficios del premium
                items(profit_items.size){ index ->
                    val item = profit_items[index]
                    ProfiBody(item)
                }
            }


            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(premium_items.size){ index ->
                    val item = premium_items[index]
                    Option(item)
                }
            }

            // boton contratar premium
            Button(
                onClick = {
                    navController.navigate(AppScreens.MainScreen.route)
                },
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .padding(top = 16.dp)
                    .height(50.dp)
            ) {
                Text(
                    text = "Contratar",
                    style = typography.bodyLarge
                )
            }

            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
fun Option(
    item: PremiumItem
) {
    Card(
        modifier = Modifier
            .clickable { item.action() },
        colors = CardDefaults.cardColors(
            containerColor = if(item.selected) Color(0xFFFFCC66) else colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = item.name,
                style = typography.bodyMedium
            )
            if(item.selected){
                Icon(
                    imageVector = Icons.Outlined.Check,
                    contentDescription = null
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    navController: NavController,
    myViewModel: MyViewModel
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = myViewModel.languageType().get(185)
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigate(AppScreens.MainScreen.route) }) {
                Icon(
                    painterResource(id = R.drawable.back),
                    contentDescription = "Te enviara al menu de opciones",
                )
            }
        }
    )
}

@Composable
fun ProfiBody(
    item: ProfitItem
) {
    Card(
        modifier = Modifier
            .padding(PaddingValues(16.dp))
    ) {
        Row(
            modifier = Modifier
                .padding(PaddingValues(16.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .weight(0.7f)
            ) {
                Text(
                    text = item.name,
                    style = typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = item.desc,
                    style = typography.bodyLarge
                )
            }

            Column(
                modifier = Modifier
                    .weight(0.3f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null,
                    modifier = Modifier
                        .width(200.dp)
                )
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
    TopAppBar(
        title = {
            Text(text = myViewModel.languageType().get(185),
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigate(AppScreens.MainScreen.route) }) {
                Icon(
                    painterResource(id = R.drawable.back),
                    contentDescription = "Te enviara al menu de opciones",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        colors = TopAppBarDefaults
            .smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
    )
}

@Composable
private fun TextUser(
    text: String,
    fontSize: Int,
    fontWeight: FontWeight = FontWeight.Bold,
    color: Color,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text, // texto
        modifier = Modifier
            .fillMaxWidth(), // esto acapara el tamaño completo del Row=0.8f
        color = color,
        fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif,
        fontWeight = fontWeight,
        fontSize = fontSize.sp,
        textAlign = textAlign
    )
}

@Composable
private fun SuscripcionDatos(
    myViewModel: MyViewModel,
    titulo: String,
    descripcion: String,
    roundedCornerShape: RoundedCornerShape,
    painter: Painter,
    onClick: () -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .background(
                MaterialTheme.colorScheme.primaryContainer,
                roundedCornerShape
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        Spacer(modifier = Modifier.size(15.dp))
        Icon(
            painter = painter,
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ){
            TextUser(text = titulo, fontSize = 25, color = MaterialTheme.colorScheme.onPrimaryContainer)
            TextUser(text = descripcion, fontSize = 15, color = MaterialTheme.colorScheme.onPrimaryContainer, fontWeight = FontWeight.Normal)
            Spacer(modifier = Modifier.size(30.dp))
            ClickableText(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(
                        color = MaterialTheme.colorScheme.tertiary,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold)
                    ) {
                        append(myViewModel.languageType().get(198))
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                onClick = { onClick }
            )
        }
    }
}