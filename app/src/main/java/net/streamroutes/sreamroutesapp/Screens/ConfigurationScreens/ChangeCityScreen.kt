package net.streamroutes.sreamroutesapp.Screens.ConfigurationScreens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.Colores.color_fondo_claro
import net.streamroutes.sreamroutesapp.Colores.color_fondo_topappbar_alterno
import net.streamroutes.sreamroutesapp.Colores.color_letra_topappbar
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R

@Composable
fun ChangeCityScreen(myViewModel: MyViewModel,navController: NavController){
    CambiarCiudad(myViewModel,navController)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CambiarCiudad(myViewModel: MyViewModel,navController: NavController) {
    //Fondo
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color_fondo_claro)
    ){
        // top app bar
        TopAppBar(
            title = {
                Text(text = myViewModel.languageType().get(53),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.navigate(AppScreens.ConfigurationScreen.route) }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Te enviara al menu de configuraciones",
                        tint = Color.White

                    )
                }
            },
            colors = TopAppBarDefaults
                .smallTopAppBarColors(
                    containerColor = color_fondo_topappbar_alterno,
                    titleContentColor = color_letra_topappbar
                )
        )
        Options()
        // Espacio para empujar el contenido hacia abajo
        Spacer(modifier = Modifier.weight(1f))
    }
}


//Opciones
@Composable
fun Options(modifier: Modifier = Modifier) {
    val options = listOf(
        "LEÓN",
        "IRAPUATO",
        "CELAYA",
        "SALAMANCA",
        "GUANAJUATO",
        "SILAO",
        "ACÁMBARO",
        "SAN FRANCISCO DEL RINCÓN",
        "MOROLEON",
        "SALVATIERRA"
    )

    // Variable
    val selectedOption = remember { mutableStateOf("") }

    fun onOptionSelected(option: String) {
        selectedOption.value = option
    }

    fun isOptionSelected(option: String): Boolean {
        return selectedOption.value == option
    }

    options.forEach { option ->
        val topPadding = if (option == "LEÓN") 30.dp else 2.dp
        Row(modifier = Modifier.padding(top = topPadding)) {
            // Espacio entre los componentes
            Spacer(modifier = Modifier.width(13.dp))

            Box(
                modifier = Modifier
                    .height(45.dp)
                    .fillMaxWidth(0.95f)
                    .background(Color(android.graphics.Color.parseColor("#153040")))
            ) {
                Text(
                    text = option,
                    modifier = Modifier.align(Alignment.CenterStart)
                        .padding(start = 10.dp),
                    color = Color.White,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold
                )

                RadioButton(
                    selected = isOptionSelected(option),
                    onClick = { onOptionSelected(option) },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color(android.graphics.Color.parseColor("#E8AA42")),
                        unselectedColor = Color.DarkGray,
                        disabledSelectedColor = Color.LightGray
                    ),
                    modifier = Modifier.padding(16.dp).align(Alignment.CenterEnd)
                )
            }
        }
    }
}
