package net.streamroutes.sreamroutesapp.ui.parking_screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
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
                color = colorScheme.onBackground,
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
            style = typography.displaySmall,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun VehiculoEstacionado(parkingPkViewModel: ParkingPkViewModel) {

    Column {
        // label de VER HISTORIAL
        /*Row {
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = stringResource(id = if(parkingPkViewModel.verHistorial) R.string.lblRegresar else R.string.lblVerHistorial),
                style = typography.titleMedium,
                color = colorScheme.tertiary,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        parkingPkViewModel.updateVerHistorial(!parkingPkViewModel.verHistorial)
                    }
            )
        }*/
        Spacer(modifier = Modifier.size(16.dp))

        Estacionados(parkingPkViewModel)
       /* // coches estacionados
        if(!parkingPkViewModel.verHistorial){
            Estacionados(parkingPkViewModel)
        } else {
            Historial(parkingPkViewModel)
        }*/
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
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.background,
            contentColor = colorScheme.onBackground
        ),
        modifier = Modifier
            .fillMaxWidth(0.9f)
    ) {

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Spacer(modifier = Modifier.size(8.dp))

                Image(
                    painter = painterResource(id = R.drawable.car),
                    contentDescription = null,
                    modifier = Modifier.size(50.dp)
                )

                Spacer(modifier = Modifier.size(16.dp))

                Column {
                    Text(text = stringResource(id = R.string.lblVehiculo), style = typography.titleMedium)
                    Text(text = historialItem.vehiculo.matricula, style = typography.bodyMedium)
                }
            }

            Spacer(modifier = Modifier.size(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.size(8.dp))

                Image(
                    painter = painterResource(id = R.drawable.marker_parking),
                    contentDescription = null,
                    modifier = Modifier.size(50.dp)
                )

                Spacer(modifier = Modifier.size(16.dp))

                Column {
                    Text(text = stringResource(id = R.string.lblEstacionamiento), style = typography.titleMedium)
                    Text(text = historialItem.estacionamiento.nombre, style = typography.bodyMedium)
                    Text(text = historialItem.estacionamiento.calle, style = typography.bodyMedium)
                }
            }

            Spacer(modifier = Modifier.size(32.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.size(16.dp))
                Image(
                    painter = painterResource(id = R.drawable.hora_exacta),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(text = "1 minuto", style = typography.titleMedium)

                Spacer(modifier = Modifier.weight(1f))

                Image(
                    painter = painterResource(id = R.drawable.precio),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(text = "$0", style = typography.headlineSmall, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }
}

@Composable
private fun Header(parkingPkViewModel: ParkingPkViewModel) {

    val headerText1 = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = colorScheme.background,
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
        SpanStyle(fontWeight = FontWeight.Bold, color = colorScheme.background),
        SpanStyle(fontWeight = FontWeight.Bold, color = colorScheme.tertiary),
    )

    val headerText3 = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = colorScheme.tertiary,
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
                color = colorScheme.primary,
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
            Text(text = headerText1, style = typography.displaySmall)
            Text(text = headerText2, style = typography.displaySmall)
            Text(text = headerText3, style = typography.displaySmall)
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