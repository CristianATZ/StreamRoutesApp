package net.streamroutes.sreamroutesapp.ui.parking_screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.Audiotrack
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Motorcycle
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.viewmodel.parking.AccountPkViewModel
import net.streamroutes.sreamroutesapp.viewmodel.parking.TipoVehiculo
import net.streamroutes.sreamroutesapp.viewmodel.parking.Vehiculo

@Composable
fun ParkingAccountScreen(
    accountPkViewModel: AccountPkViewModel
) {
    Column {
        Header(accountPkViewModel)
        Vehicles(accountPkViewModel)
        Payment()
    }
}

@Composable
private fun Header(
    accountPkViewModel: AccountPkViewModel
){
    Column(
        modifier = Modifier
            .background(
                color = colorScheme.primary
            )
            .fillMaxWidth()
            .heightIn(200.dp)
            .padding(15.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.usuario_3),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(125.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = accountPkViewModel.user.name,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = colorScheme.background,
            modifier = Modifier
                .padding(PaddingValues(16.dp)),
        )
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 15.dp)
        ) {
            InfoField(texto = accountPkViewModel.user.phone, icono = Icons.Filled.Phone)
            InfoField(texto = accountPkViewModel.user.email, icono = Icons.Filled.AlternateEmail)
            InfoField(texto = accountPkViewModel.user.location, icono = Icons.Filled.LocationOn)
        }
    }
}

@Composable
private fun Vehicles(
    accountPkViewModel: AccountPkViewModel
){
    val vehiculos = accountPkViewModel.vehicles

    Column {
        Row {
            Text(
                text = stringResource(id = R.string.lblMisVehiculos),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth(0.98f)
        ) {
            items( vehiculos.size ){ index ->
                VehicleItem(index, vehiculos[index], accountPkViewModel.selectedVehicle.matricula){
                    accountPkViewModel.updateSelectedVehicle(vehiculos[index])
                }
            }
            item {
                Card(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    ),
                    colors = CardColors(
                        containerColor = colorScheme.inverseOnSurface,
                        contentColor = colorScheme.inverseSurface,
                        disabledContainerColor = colorScheme.inverseOnSurface,
                        disabledContentColor = colorScheme.inverseSurface
                    ),
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clickable {
                            /* TODO */
                        }
                ) {
                    Column(
                        modifier = Modifier
                            .size(100.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp)
                        )
                        Text(
                            text = stringResource(id = R.string.lblAgregar),
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }

        VehicleInfo(accountPkViewModel.selectedVehicle)
    }
}

@Composable
private fun Payment(){
    Text(text = "PAGO")
}

@Composable
private fun InfoField(texto: String, icono: ImageVector){
    Row {
        Icon(
            icono,
            contentDescription = null,
            tint = colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.size(5.dp))
        Text(
            text = texto,
            color = colorScheme.onPrimary
        )
    }
}


@Composable
private fun VehicleInfo(
    vehiculo: Vehiculo
){
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardColors(
            containerColor = colorScheme.inverseOnSurface,
            contentColor = colorScheme.inverseSurface,
            disabledContainerColor = colorScheme.inverseOnSurface,
            disabledContentColor = colorScheme.inverseSurface
        ),
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 25.dp)
            .clickable {
                /* TODO */
            }
            .fillMaxWidth(),
        border = BorderStroke(1.dp, colorScheme.tertiary)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(10.dp)
        ) {
            val icono = when(vehiculo.tipo) {
                TipoVehiculo.CARRO -> Icons.Filled.DirectionsCar
                TipoVehiculo.MOTO -> Icons.Filled.Motorcycle
                TipoVehiculo.TRACTOR -> Icons.Filled.Audiotrack
                TipoVehiculo.NINGUNO -> TODO()
            }

            Icon(
                imageVector = icono,
                contentDescription = null,
                modifier = Modifier
                    .size(75.dp)
            )
            Column(
                modifier = Modifier
                    .padding(start = 5.dp)
            ) {
                Text(
                    text = vehiculo.marca + " " + vehiculo.modelo + " " + vehiculo.year,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Row{
                    Text(
                        text = stringResource(id = R.string.lblMatricula) + " ",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = vehiculo.matricula,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Row{
                    Text(
                        text = stringResource(id = R.string.lblTipo) + " ",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = vehiculo.tipo.name.toLowerCase().capitalize(),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Row{
                    Text(
                        text = stringResource(id = R.string.lblColor) + " ",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = vehiculo.color.name.toLowerCase().capitalize(),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}