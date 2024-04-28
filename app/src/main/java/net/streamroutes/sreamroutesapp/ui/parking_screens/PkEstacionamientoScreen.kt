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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.viewmodel.parking.HistorialItem
import net.streamroutes.sreamroutesapp.viewmodel.parking.ParkingPkViewModel

@Composable
fun ParkingEstacionamientoScreen(parkingPkViewModel: ParkingPkViewModel) {
    Column {
        if(parkingPkViewModel.estacionado){
            Header(parkingPkViewModel)
            VehiculoEstacionado(parkingPkViewModel)
        } else {
            VehiculoNoEstacionado()
        }
    }
}

@Composable
private fun Historial(parkingPkViewModel: ParkingPkViewModel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        parkingPkViewModel.historial.forEach { item ->
            item {
                VehiculoItem(item)
            }
        }
    }
}

@Composable
fun VehiculoNoEstacionado() {
    val label = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            )
        ){
            //append(stringResource(id = R.string.lblHeader1) + " ")
            append("No hay coches estacionados.")
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.estacionamiento),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
        )

        Spacer(modifier = Modifier.size(16.dp))

        Text(
            text = label,
            style = MaterialTheme.typography.displaySmall,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun VehiculoEstacionado(parkingPkViewModel: ParkingPkViewModel) {

    Column {
        // label de VER HISTORIAL
        Row {
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = stringResource(id = if(parkingPkViewModel.verHistorial) R.string.lblRegresar else R.string.lblVerHistorial),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.tertiary,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        parkingPkViewModel.updateVerHistorial(!parkingPkViewModel.verHistorial)
                    }
            )
        }
        // coches estacionados
        if(!parkingPkViewModel.verHistorial){
            Estacionados(parkingPkViewModel)
        } else {
            Historial(parkingPkViewModel)
        }
    }
}

@Composable
private fun Estacionados(parkingPkViewModel: ParkingPkViewModel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            VehiculoItem(
                HistorialItem(
                    parkingPkViewModel.vehiculo,
                    parkingPkViewModel.estacionamiento,
                    parkingPkViewModel.tiempo,
                    parkingPkViewModel.total
                )
            )
        }
    }
}

@Composable
private fun VehiculoItem(historialItem: HistorialItem) {
    Card {
        Row {
            Image(
                painter = painterResource(id = R.drawable.coche_izq),
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )

            Column {
                Text(text = historialItem.vehiculo.matricula)
                Text(text = historialItem.estacionamiento.nombre)

                Row {
                    Icon(
                        imageVector = Icons.Filled.MonetizationOn,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier
                            .size(25.dp)
                    )
                    Text(text = "${historialItem.tiempo}")

                    Spacer(modifier = Modifier.weight(1f))

                    Text(text = "$${historialItem.total}")
                }

            }
        }
    }
}

@Composable
private fun Header(parkingPkViewModel: ParkingPkViewModel) {

    val headerText1 = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.background,
                fontWeight = FontWeight.Bold
            )
        ){
            //append(stringResource(id = R.string.lblHeader1) + " ")
            append("Relajate, Disfruta.")
        }
    }

    val headerText2 = generaHeaderText(
        "Tu",
        "Vehiculo",
        "Esta",
        SpanStyle(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.background),
        SpanStyle(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.tertiary),
    )

    val headerText3 = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.tertiary,
                fontWeight = FontWeight.Bold
            )
        ){
            //append(stringResource(id = R.string.lblHeader1) + " ")
            append("Aqui.")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            //.height(250.dp)
            .background(
                color = MaterialTheme.colorScheme.primary,
                RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // texto
        Column(
            modifier = Modifier
                .padding(vertical = 16.dp)
        ) {
            Text(text = headerText1, style = MaterialTheme.typography.displaySmall)
            Text(text = headerText2, style = MaterialTheme.typography.displaySmall)
            Text(text = headerText3, style = MaterialTheme.typography.displaySmall)
        }
    }
}

private fun generaHeaderText(
    lblHeader1: String,
    lblHeader2: String,
    lblHeader3: String,
    spanStyleNormal: SpanStyle,
    spanStyleBold: SpanStyle
): AnnotatedString {
    return buildAnnotatedString {
        withStyle(
            style = spanStyleNormal
        ){
            append("$lblHeader1 ")
        }

        withStyle(
            style = spanStyleBold
        ){
            append("$lblHeader2 ")
        }

        withStyle(
            style = spanStyleNormal
        ){
            append(lblHeader3)
        }
    }
}