package net.streamroutes.sreamroutesapp.ui.parking_screens

import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.ui.routes_screens.menu.BeneficioItem
import net.streamroutes.sreamroutesapp.viewmodel.parking.ApartarPkViewModel
import net.streamroutes.sreamroutesapp.viewmodel.parking.ApartarUiState
import net.streamroutes.sreamroutesapp.viewmodel.parking.ParkingPkViewModel
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

@Composable
fun ApartarScreen(
    apartarPkViewModel: ApartarPkViewModel,
    parkingPkViewModel: ParkingPkViewModel,
    navHostController: NavHostController
) {
    val uiState by apartarPkViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        HeaderApartarLugar(uiState, navHostController)

        CircularProgress(
            apartarPkViewModel = apartarPkViewModel
        )

        InformationUser(
            uiState = uiState
        )

        AceptarApartar(uiState, parkingPkViewModel, navHostController)
    }
}

@Composable
fun AceptarApartar(
    uiState: ApartarUiState,
    parkingPkViewModel: ParkingPkViewModel,
    navHostController: NavHostController
) {
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                scope.launch {
                    parkingPkViewModel.apartarLugar(
                        a = true,
                        v = uiState.vehiculo!!,
                        e = uiState.estacionamiento!!,
                        hi = uiState.horaInicio,
                        mi = uiState.minutoInicio,
                        hf = uiState.horaFinal,
                        t = uiState.total
                    )
                }

                navHostController.popBackStack()
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonColors(
                containerColor = colorScheme.tertiary,
                contentColor = colorScheme.onTertiary,
                disabledContentColor = Color.White,
                disabledContainerColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .height(50.dp)
        ) {
            Text(text = stringResource(id = R.string.btnApartarLugar), style = typography.titleMedium, fontWeight = FontWeight.SemiBold)
        }
        
        Spacer(modifier = Modifier.size(16.dp))
    }
}

@Composable
fun InformationUser(uiState: ApartarUiState) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.9f)
    ) {
        
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // placa, hora y precio
            Column {
                BeneficioItem(
                    icon = painterResource(id = R.drawable.car),
                    title = stringResource(id = R.string.lblVehiculo),
                    subtitle = uiState.vehiculo!!.matricula
                )

                BeneficioItem(
                    icon = painterResource(id = R.drawable.hora_exacta),
                    title = stringResource(id = R.string.lblHoraInicio),
                    subtitle = "${uiState.horaInicio}:${uiState.minutoInicio}"
                )

                BeneficioItem(
                    icon = painterResource(id = R.drawable.hora_exacta),
                    title = stringResource(id = R.string.lblHoraFin),
                    subtitle = "${uiState.horaFinal}:${uiState.minutoInicio}"
                )

                BeneficioItem(
                    icon = painterResource(id = R.drawable.precio),
                    title = stringResource(id = R.string.lblPrecioPorHora),
                    subtitle = "$${uiState.estacionamiento!!.price}"
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // total
            Card(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .width(150.dp)
                        .height(200.dp)
                        .background(colorScheme.inverseSurface),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "TOTAL",
                        style = typography.headlineLarge,
                        fontWeight = FontWeight.W900,
                        color = colorScheme.inverseOnSurface.copy(0.5f)
                    )
                    
                    Spacer(modifier = Modifier.size(16.dp))

                    Text(
                        text = "$${uiState.total}",
                        style = typography.headlineMedium,
                        color = colorScheme.tertiary,
                        fontWeight = FontWeight.W500
                    )
                }
            }
        }
    }
}

@Composable
fun CircularProgress(apartarPkViewModel: ApartarPkViewModel) {
    val uiState by apartarPkViewModel.uiState.collectAsState()

    var horas by remember {
        mutableIntStateOf(1)
    }

    Spacer(modifier = Modifier.size(16.dp))

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(colorScheme.background)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.size(16.dp))

            Text(
                text = stringResource(id = R.string.lblCuantoTiempo),
                style = typography.displaySmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(id = R.string.lblVasAQuedarte),
                style = typography.headlineLarge
            )

            Box {
                MuneerCircularProgressBar(){
                    horas = (it/0.1).toInt() + if(it/0.1 == 10.0) 0 else 1
                    apartarPkViewModel.updateTiempos(horas)
                }

                Column(
                    modifier = Modifier.size(300.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "$horas",
                        style = typography.displayLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = stringResource(id = R.string.lblHrs),
                        style = typography.bodyLarge,
                        color = colorScheme.primary.copy(0.5f)
                    )
                }
            }
        }
    }
}

@Composable
fun HeaderQuestion() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = stringResource(id = R.string.lblCuantoTiempo),
            style = typography.displaySmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(id = R.string.lblVasAQuedarte),
            style = typography.headlineLarge
        )
    }
}

@Composable
fun HeaderApartarLugar(uiState: ApartarUiState, navHostController: NavHostController) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorScheme.primary,
                RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
            )
    ){
        Row(
            modifier = Modifier.padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.size(16.dp))

            Column(
                modifier = Modifier
                    .background(colorScheme.tertiary, RoundedCornerShape(16.dp))
                    .clickable {
                        navHostController.popBackStack()
                    }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    tint = colorScheme.onTertiary,
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = uiState.estacionamiento!!.name,
                style = typography.titleLarge,
                color = colorScheme.onPrimary
            )

            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalComposeUiApi::class)
@Composable
fun MuneerCircularProgressBar(
    modifier: Modifier = Modifier,
    padding: Float = 50f,
    stroke: Float = 35f,
    cap: StrokeCap = StrokeCap.Round,
    initialAngle : Double = 36.0,
    onProgressChanged: (progress: Double) -> Unit,
) {
    var width by remember { mutableStateOf(0) }
    var height by remember { mutableStateOf(0) }
    var radius by remember { mutableStateOf(0f) }
    var center by remember { mutableStateOf(Offset.Zero) }

    var appliedAngle by remember {
        mutableStateOf(initialAngle)
    }
    var lastAngle by remember {
        mutableStateOf(60.0)
    }

    val selectProgress = colorScheme.tertiary
    val unselectProgress = colorScheme.inverseSurface.copy(0.2f)

    val stiackBallColor = colorScheme.onPrimary
    val ballColor = colorScheme.primary
    val outBallColor = colorScheme.primary.copy(0.5f)

    Canvas(
        modifier = modifier
            .size(300.dp)
            .onGloballyPositioned {
                width = it.size.width
                height = it.size.height
                center = Offset(width / 2f, height / 2f)
                radius = min(width.toFloat(), height.toFloat()) / 2f - padding - stroke / 2f
            }
            .pointerInteropFilter {

                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {

                    }

                    MotionEvent.ACTION_MOVE -> {
                        appliedAngle = if (center.x > it.x && center.y > it.y) {
                            270 + deltaAngle(center.x - it.x, center.y - it.y)
                        } else {
                            90 - deltaAngle(it.x - center.x, center.y - it.y)
                        }

                        val diff = abs(lastAngle - appliedAngle)
                        if (diff > 180) {
                            appliedAngle = if (appliedAngle < 180) {
                                360.0
                            } else {
                                0.0
                            }
                        }

                        onProgressChanged(appliedAngle / 360.0)
                        lastAngle = appliedAngle

                    }

                    MotionEvent.ACTION_UP -> {

                    }

                    else -> return@pointerInteropFilter false
                }

                return@pointerInteropFilter true
            }
    ) {

        // controlador
        drawArc(
            color = unselectProgress,
            startAngle = -90f,
            sweepAngle = 360f,
            useCenter = false,
            topLeft = center - Offset(radius, radius),
            size = Size(radius * 2, radius * 2),
            style = Stroke(
                width = stroke,
                cap = cap
            )
        )


        // circulo superior
        drawArc(
            color = selectProgress,
            startAngle = -90f,
            sweepAngle = abs(appliedAngle.toFloat()),
            topLeft = center - Offset(radius, radius),
            size = Size(radius * 2, radius * 2),
            useCenter = false,
            style = Stroke(
                width = stroke,
                cap = cap
            )
        )

        // circulo inferior
        drawCircle(
            color = outBallColor,
            radius = stroke,
            center = center + Offset(
                radius * cos((-90 + abs(appliedAngle)) * PI / 180f).toFloat(),
                radius * sin((-90 + abs(appliedAngle)) * PI / 180f).toFloat()
            )
        )

        // bola
        drawCircle(
            color = ballColor,
            radius = ((stroke*2.0)/3.0).toFloat(),
            center = center + Offset(
                radius * cos((-90 + abs(appliedAngle)) * PI / 180f).toFloat(),
                radius * sin((-90 + abs(appliedAngle)) * PI / 180f).toFloat()
            )
        )

        // linea de la bola
        drawLine(
            color = stiackBallColor,
            start = center + Offset(
                (radius-10) * cos((-90 + abs(appliedAngle)) * PI / 180f).toFloat(),
                (radius-10) * sin((-90 + abs(appliedAngle)) * PI / 180f).toFloat()
            ),
            end = center + Offset(
                (radius+10) * cos((-90 + abs(appliedAngle)) * PI / 180f).toFloat(),
                (radius+10) * sin((-90 + abs(appliedAngle)) * PI / 180f).toFloat()
            )

        )

    }

}

fun deltaAngle(x: Float, y: Float): Double {
    return Math.toDegrees(atan2(y.toDouble(), x.toDouble()))
}