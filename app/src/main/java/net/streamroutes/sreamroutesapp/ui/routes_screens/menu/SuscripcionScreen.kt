package net.streamroutes.sreamroutesapp.ui.routes_screens.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.LocalParking
import androidx.compose.material.icons.filled.Timelapse
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.navigation.AppScreens
import net.streamroutes.sreamroutesapp.utils.brush

@Composable
fun SuscripcionScreen(
    onBack: () -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(onBack) }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
        ) {
            Beneficios()

            Spacer(modifier = Modifier.size(32.dp))

            Paquetes()

            Spacer(modifier = Modifier.weight(1f))
            
            Iniciar()
            
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
fun Iniciar() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.lblIniciarPrueba),
            style = typography.bodyMedium
        )

        Button(
            onClick = {

            },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorScheme.tertiaryContainer,
                contentColor = colorScheme.onTertiaryContainer
            ),
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 4.dp
            ),
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(vertical = 16.dp)
                .height(50.dp)
        ) {
            Text(
                text = stringResource(id = R.string.btnEmpezarAhora),
                style = typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.size(16.dp))
        
        Text(
            text = stringResource(id = R.string.lblCondiciones),
            style = typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Paquetes() {
    LazyRow {
        item {
            PaqueteItem(
                stringResource(id = R.string.lblFree),
                stringResource(id = R.string.lblFreePrecio),
                stringResource(id = R.string.lblFreeDesc),
                Modifier.background(brush)
            )

            PaqueteItem(
                stringResource(id = R.string.lblEstandar),
                stringResource(id = R.string.lblEstandarPrecio),
                stringResource(id = R.string.lblEstandarDesc)
            )

            PaqueteItem(
                stringResource(id = R.string.lblEstudiante),
                stringResource(id = R.string.lblEstudiantePrecio),
                stringResource(id = R.string.lblEstudianteDesc)
            )

            PaqueteItem(
                stringResource(id = R.string.lblTurista),
                stringResource(id = R.string.lblTuristaPrecio),
                stringResource(id = R.string.lblTuristaDesc)
            )

            PaqueteItem(
                stringResource(id = R.string.lblAnual),
                stringResource(id = R.string.lblAnualPrecio),
                stringResource(id = R.string.lblAnualDesc)
            )
        }
    }
}

@Composable
fun PaqueteItem(
    title: String,
    precio: String,
    desc: String,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.background,
            contentColor = colorScheme.onBackground
        ),
        modifier = Modifier
            .size(250.dp)
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = modifier
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(text = title, style = typography.headlineLarge, fontWeight = FontWeight.ExtraBold)
                Text(text = precio, style = typography.labelLarge, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.size(32.dp))
                Text(text = desc, style = typography.labelMedium, fontWeight = FontWeight.Normal)
            }
        }
    }
}

@Composable
fun Beneficios() {
    BeneficioItem(
        painterResource(id = R.drawable.ads),
        stringResource(id = R.string.lblSinPublicidad),
        stringResource(id = R.string.lblSinPublicidad1)
    )

    BeneficioItem(
        painterResource(id = R.drawable.hora_exacta),
        stringResource(id = R.string.lblTiempoExato),
        stringResource(id = R.string.lblTiempoExato1)
    )

    BeneficioItem(
        painterResource(id = R.drawable.planifica),
        stringResource(id = R.string.lblPlanifica),
        stringResource(id = R.string.lblPlanifica1)
    )

    BeneficioItem(
        painterResource(id = R.drawable.parking),
        stringResource(id = R.string.lblDisponibilidad),
        stringResource(id = R.string.lblDisponibilidad1)
    )
}

@Composable
fun BeneficioItem(
    icon: Painter,
    title: String,
    subtitle: String
) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.size(16.dp))

        Image(painter = icon, contentDescription = null, modifier = Modifier.size(25.dp))

        Spacer(modifier = Modifier.size(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = title, style = typography.titleMedium)
            Text(text = subtitle, style = typography.bodyMedium)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar(
    onBack: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.lblBeneficios)
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBack() }) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBackIosNew,
                    contentDescription = "Te enviara al menu de opciones",
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorScheme.background,
            titleContentColor = colorScheme.onBackground
        )
    )
}