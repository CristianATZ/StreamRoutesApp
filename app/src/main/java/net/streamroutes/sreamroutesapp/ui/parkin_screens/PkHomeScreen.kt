package net.streamroutes.sreamroutesapp.ui.parkin_screens

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Motorcycle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.streamroutes.sreamroutesapp.R

@Composable
fun ParkingHomeScreen() {
    Column {
        Header()
        Vehicle()
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
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(0.9f)
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
private fun Vehicle() {
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
                items( 5/*Aqui agregar el viewmodel*/){
                    VehicleItem("58ECP3", Icons.Filled.Motorcycle)
                }
            } else {

            }
        }
    }
}

@Composable
private fun VehicleItem(
    name: String,
    icono: ImageVector
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        modifier = Modifier
            .padding(horizontal = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .size(100.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icono,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
            )
            Text(
                text = name,
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



