package net.streamroutes.sreamroutesapp.ui.parking_screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import net.streamroutes.sreamroutesapp.R

data class Vehiculo (
    val matricula: String,
    val icono: ImageVector
)

@Composable
fun ParkingHomeScreen() {
    var vehiculos = listOf(
        Vehiculo("58ECP3",Icons.Filled.MonetizationOn),
        Vehiculo("31EHP3",Icons.Filled.Star),
        Vehiculo("HMN123",Icons.Filled.MonetizationOn)
    )

    Column {
        Header()
        Vehicle(vehiculos)
        Spots()
    }
}

@Composable
private fun Spots() {
    Column {
        // encabezado
        Row {
            Text(
                text = stringResource(id = R.string.lblEspaciosCercanos),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )

            Spacer(modifier = Modifier.weight(1f))
            
            Text(
                text = stringResource(id = R.string.lblVerTodo),
                style = MaterialTheme.typography.titleMedium,
                color = colorScheme.tertiary,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(16.dp)
            )
        }

        // espacios cercanos
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(5){
                SpotItem("Nombre del lugar", "Calle #N, Colonia, Codigo Postal", "5.0", "30", Icons.Filled.MonetizationOn)
            }
        }
    }
}

@Composable
private fun SpotItem(
    name: String,
    informacion: String,
    calificacion: String,
    precio: String,
    foto: ImageVector
) {

    var openDialog by remember {
        mutableStateOf(false)
    }

    if(openDialog){
        DialogIniciarRecorrido()
    }

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(0.9f)
            .clickable {
                openDialog = !openDialog
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Icon(imageVector = foto, contentDescription = null, modifier = Modifier.size(100.dp))

            Column(
                verticalArrangement = Arrangement.Center,
            ) {
                Spacer(modifier = Modifier.size(8.dp))

                Text(
                    text = name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                )
                Text(
                    text = informacion,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp,
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = colorScheme.tertiary,
                        modifier = Modifier
                            .size(25.dp)
                    )
                    Text(
                        text = calificacion,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.weight(1f))
                    
                    Text(
                        text = "$$precio",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.size(16.dp))
                }
            }
        }
    }
}

@Composable
fun DialogIniciarRecorrido() {
    Dialog(onDismissRequest = { /*TODO*/ }) {
        Card {
            Text(text = "Quieres iniciar el recorrido?")
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Si")
            }
        }
    }
}

@Composable
private fun Vehicle(vehiculos: List<Vehiculo>) {
    var selection by remember {
        mutableIntStateOf(0)
    }

    Column {
        // encabezado
        Row {
            Text(
                text = stringResource(id = R.string.lblMisVehiculos),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
        }

        // vehiculos del usuario
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(0.95f)
        ) {
            if( true ){
                items( vehiculos.size/*Aqui agregar el viewmodel*/){ index ->
                    VehicleItem(index, vehiculos[index], selection){
                        selection = index
                    }
                }
            } else {

            }
        }
    }
}

@Composable
private fun VehicleItem(
    index: Int,
    item: Vehiculo,
    selection: Int,
    onClick: () -> Unit
) {
    val cont = if(index == selection) colorScheme.tertiary else colorScheme.primaryContainer
    val int = if(index == selection) colorScheme.onTertiary else colorScheme.onPrimaryContainer

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardColors(
            containerColor = cont,
            contentColor = int,
            disabledContainerColor = cont,
            disabledContentColor = int
        ),
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clickable {
                onClick()
            }
    ) {
        Column(
            modifier = Modifier
                .size(100.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = item.icono,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
            )
            Text(
                text = item.matricula,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun Header() {

    val headerText1 = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = colorScheme.background,
                fontWeight = FontWeight.Bold
            )
        ){
            append(stringResource(id = R.string.lblHeader1) + " ")
        }
    }

    val headerText2 = generaHeaderText(
        stringResource(id = R.string.lblHeader2),
        stringResource(id = R.string.lblHeader3),
        stringResource(id = R.string.lblHeader4),
        SpanStyle(fontWeight = FontWeight.Bold, color = colorScheme.background),
        SpanStyle(fontWeight = FontWeight.Bold, color = colorScheme.tertiary),
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .background(
                color = colorScheme.primary,
                RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // texto e imagen
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = headerText1, style = MaterialTheme.typography.displaySmall)
                Text(text = headerText2, style = MaterialTheme.typography.displaySmall)
            }
            Image(
                painter = painterResource(id = R.drawable.logo_icono),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
            )
        }
        
        Spacer(modifier = Modifier.size(8.dp))

        // barra de busqueda
        OutlinedTextField(
            value = "",
            onValueChange = {  },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.lblBarraBusquedaInicio),
                    style = MaterialTheme.typography.titleMedium
                )
            },
            shape = RoundedCornerShape(16.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = colorScheme.background,
                unfocusedBorderColor = colorScheme.background,
                focusedContainerColor = colorScheme.background,
                focusedBorderColor = colorScheme.background,
            ),
            modifier = Modifier
                .fillMaxWidth(0.85f)
        )
    }
}

fun generaHeaderText(
    lblHeader1: String,
    lblHeader2: String,
    lblHeader3: String,
    spanStyleNormal: SpanStyle,
    spanStyleBold: SpanStyle
): AnnotatedString {
    return buildAnnotatedString {
        withStyle(
            style = spanStyleBold
        ){
            append("$lblHeader1 ")
        }

        withStyle(
            style = spanStyleNormal
        ){
            append("$lblHeader2 ")
        }

        withStyle(
            style = spanStyleBold
        ){
            append(lblHeader3)
        }
    }
}



