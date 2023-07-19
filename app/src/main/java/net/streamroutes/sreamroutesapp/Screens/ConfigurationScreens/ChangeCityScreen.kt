package net.streamroutes.sreamroutesapp.Screens.ConfigurationScreens


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import net.streamroutes.sreamroutesapp.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            desing()
        }
    }
}

@Preview
@Composable
fun desing(){
    CambiarCiudad(modifier = Modifier
        .fillMaxSize()
        .background(Color(android.graphics.Color.parseColor("#FFF7E7")))
        .wrapContentSize(Alignment.Center))

}

@Composable
fun CambiarCiudad(modifier: Modifier = Modifier) {
    //Fondo
    Column(modifier = modifier){
        Row {
            Box(
                modifier = Modifier
                    .height(70.dp)
                    .fillMaxWidth()
                    .background(Color(android.graphics.Color.parseColor("#153040")))
            ){
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth(0.13f)
                        .padding(start = 15.dp)
                        .align(Alignment.CenterStart)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = stringResource(id = R.string.back),
                        modifier = Modifier.fillMaxSize()
                            .background(Color(android.graphics.Color.parseColor("#153040"))),
                        contentScale = ContentScale.FillBounds
                    )
                }
                Text(
                    text = "Configuracion",
                    fontSize = 30.sp,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                        .padding(end = 65.dp)
                )
            }
        }
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
                    fontSize = 21.sp,
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
