package net.streamroutes.sreamroutesapp.Screens.ProfileScreens

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.smallTopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.Colores.color_fondo_claro
import net.streamroutes.sreamroutesapp.Colores.color_fondo_textfield
import net.streamroutes.sreamroutesapp.Colores.color_fondo_topappbar_alterno
import net.streamroutes.sreamroutesapp.Colores.color_letra
import net.streamroutes.sreamroutesapp.Colores.color_letra_topappbar
import net.streamroutes.sreamroutesapp.Dialogs.DialogOcuInt
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

@SuppressLint("UnrememberedMutableState")
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileDataInfoScreen(navController: NavController){
    var ocupaciones = remember { mutableStateOf(false) }
    var intereses = remember { mutableStateOf(false) }

    val listaOcupaciones = listOf(
        remember { mutableStateOf(false) } to "Estudiante",
        remember { mutableStateOf(false) } to "Ama de Casa",
        remember { mutableStateOf(false) } to "Profesión",
        remember { mutableStateOf(false) } to "Obrero",
        remember { mutableStateOf(false) } to "Jubilado",
        remember { mutableStateOf(false) } to "Otro"
    )

    val listaIntereses = listOf(
        remember { mutableStateOf(false) } to "Entretenimiento",
        remember { mutableStateOf(false) } to "Comida",
        remember { mutableStateOf(false) } to "Ropa",
        remember { mutableStateOf(false) } to "Víveres",
        remember { mutableStateOf(false) } to "Salud"
    )



    var fecha by rememberSaveable {
        mutableStateOf("Mie, 27 Nov 2002")
    }

    val nCalendar = Calendar.getInstance()
    val year = nCalendar.get(Calendar.YEAR)
    val month = nCalendar.get(Calendar.MONTH)
    val day = nCalendar.get(Calendar.DAY_OF_MONTH)

    val nDatePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _, year: Int, month: Int, day: Int ->
            val selectedDate = LocalDate.of(year, month + 1, day)
            val formattedDate = selectedDate.format(DateTimeFormatter.ofPattern("EEE, d MMM yyyy", Locale("es", "ES")))
            fecha = formattedDate.replaceFirstChar { it.uppercase() }
            fecha = fecha.replace(".","")
        },
        year,
        month,
        day
    )

    if( ocupaciones.value ){
        DialogOcuInt(dialogo = ocupaciones, lista = listaOcupaciones, myViewModel = null)
    }

    if( intereses.value ){
        DialogOcuInt(dialogo = intereses, lista = listaIntereses, myViewModel = null)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Datos personales",
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(AppScreens.ProfileConfigureScreen.route)
                        }
                    ) {
                        androidx.compose.material.Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Te enviara a la ventana principal",
                            tint = Color.White
                        )
                    }
                },
                colors = smallTopAppBarColors(
                    containerColor = color_fondo_topappbar_alterno,
                    titleContentColor = color_letra_topappbar
                )
            )
        },
        containerColor = color_fondo_claro
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            OptionsWithSubOptions(text = "Informacion de contacto", sub_text = "Telefono, correo, etc.") {
                Row() {
                    Spacer(modifier = Modifier.size(20.dp))
                    GeneralOptions(
                        text = "Cambiar telefono",
                        sub_text = "445 141 1834",
                        onClick = { navController.navigate(AppScreens.ProfileChangePhoneScreen.route) }
                    )
                }
                Row() {
                    Spacer(modifier = Modifier.size(20.dp))
                    GeneralOptions(
                        text = "Cambiar correo",
                        sub_text = "s20120154@alumnos.itsur.edu.mx",
                        onClick = { navController.navigate(AppScreens.ProfileChangeEmailScreen.route) }
                    )
                }
            }

            OptionsWithSubOptions(text = "Informacion personal", sub_text = "Fecha nacimiento, ocupacion, intereses, etc.") {
                Row() {
                    Spacer(modifier = Modifier.size(20.dp))
                    GeneralOptions(
                        text = "Fecha nacimiento",
                        sub_text = fecha,
                        onClick = { nDatePickerDialog.show() }
                    )
                }
                Row() {
                    Spacer(modifier = Modifier.size(20.dp))
                    GeneralOptions(
                        text = "Ocupacion",
                        sub_text = "Estudiante",
                        onClick = {
                            ocupaciones.value = true
                        }
                    )
                }
                Row() {
                    Spacer(modifier = Modifier.size(20.dp))
                    GeneralOptions(
                        text = "Intereses",
                        sub_text = "Lista de intereses",
                        onClick = {
                            intereses.value = true
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ProfileDataInfoView(){

}

@Composable
private fun GeneralOptions(
    text: String,
    sub_text: String,
    onClick: () -> Unit,
    text_color: Color = color_letra
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(70.dp)
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.size(25.dp))
        // textos
        Column(
            modifier = Modifier
                .fillMaxWidth(0.75f)
        ) {
            Text(
                text = text, // texto
                color = text_color,
                fontFamily = FontFamily.SansSerif,
                fontSize = 20.sp
            )
            Text(
                text = sub_text, // texto
                color = text_color,
                fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
private fun Options(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(70.dp)
            .clickable(onClick = onClick)
            .background(
                if (isSelected) color_fondo_textfield else Color.Transparent
            ),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.size(width = 20.dp, height = 0.dp))
        Text(
            text = text, // texto
            modifier = Modifier
                .align(Alignment.CenterVertically),
            color = color_letra,
            fontFamily = FontFamily.SansSerif,
            fontSize = 20.sp
        )
    }
}


@Composable
private fun OptionsWithSubOptions(
    text: String,
    sub_text: String = "",
    subOptionsComposable: @Composable () -> Unit
) {
    var subOptionsVisibleState by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Options(text = text, isSelected = subOptionsVisibleState) {
            subOptionsVisibleState = !subOptionsVisibleState
        }

        if (subOptionsVisibleState) {
            subOptionsComposable()
        }
    }
}


// COMBOBOX O SPINNER PERSONALIZADO
@Composable
private fun ListOptions(items: List<String>){
    val selectedItem = remember { mutableStateOf("Selecciona una opción") }
    val expanded = remember { mutableStateOf(false) }

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