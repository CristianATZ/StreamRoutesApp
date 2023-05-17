package net.streamroutes.sreamroutesapp.Screens.ProfileScreens

import android.app.DatePickerDialog
import android.graphics.drawable.Icon
import android.widget.Spinner
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileVariedInfoScreen(navController: NavController){
    val ocupaciones = listOf("Estudiante","Ama de Casa","Profesión","Obrero","Jubilado","Otro")
    val intereses = listOf("Entretenimiento","Comida","Ropa","Víveres","Salud")

    TopAppBar(
        title = { Text(stringResource(id = R.string.title_profile_varied_info_screen)) },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack()  }) {
                androidx.compose.material.Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "Regresara a la ventana de datos personales"
                )
            }
        })
    Column(
        modifier = Modifier.padding(top = 80.dp, start = 30.dp, end = 30.dp)
    ) {
        Text(
            modifier = Modifier,
            text = stringResource(id = R.string.birthday),
            color = Color(0xFF231955),
            fontSize = 20.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(5.dp))
        DatePicker()
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            modifier = Modifier,
            text = stringResource(id = R.string.ocupation),
            color = Color(0xFF231955),
            fontSize = 20.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(5.dp))
        comboBox(ocupaciones)
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            modifier = Modifier,
            text = stringResource(id = R.string.interests),
            color = Color(0xFF231955),
            fontSize = 20.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(5.dp))
        comboBox(intereses)
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileVariedInformationView(){

}

// DATE TIME PICKER PERSONALIZADO
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(){
    var fecha by rememberSaveable {
        mutableStateOf("")
    }
    val year: Int
    val month: Int
    val day: Int
    val nCalendar = Calendar.getInstance()
    year = nCalendar.get(Calendar.YEAR)
    month = nCalendar.get(Calendar.MONTH)
    day = nCalendar.get(Calendar.DAY_OF_MONTH)

    val nDatePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _, year:Int, month:Int, day:Int ->
            fecha = "$day/${month+1}/$year"
        }, year, month, day
    )
    Box(
        modifier = Modifier.fillMaxWidth()
    ){
        Row(
            modifier = Modifier.align(Alignment.Center)
        ){
            OutlinedTextField(
                value = fecha,
                onValueChange = {fecha = it},
                readOnly = true,
                label = {Text(text = "Selecciona fecha")}
            )
            androidx.compose.material.Icon(
                imageVector = Icons.Filled.DateRange,
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .padding(4.dp)
                    .clickable {
                        nDatePickerDialog.show()
                    }
            )
        }
    }
}


// COMBOBOX O SPINNER PERSONALIZADO
@Composable
fun comboBox(items: List<String>){
    val selectedItem = remember { mutableStateOf("Selecciona una opción") }
    val expanded = remember { mutableStateOf(false) }

    Column {
        // Botón del spinner
        OutlinedButton(
            onClick = { expanded.value = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(selectedItem.value)
            androidx.compose.material.Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Drop-down"
            )
        }

        // Menú desplegable
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        selectedItem.value = item
                        expanded.value = false
                    }
                ) {
                    Text(item)
                }
            }
        }
    }
}