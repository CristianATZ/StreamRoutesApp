package net.streamroutes.sreamroutesapp.Screens.MenuScreens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.Colores.color_fondo_claro
import net.streamroutes.sreamroutesapp.Colores.color_fondo_oscuro
import net.streamroutes.sreamroutesapp.Colores.color_fondo_topappbar_alterno
import net.streamroutes.sreamroutesapp.Colores.color_letra_textfield
import net.streamroutes.sreamroutesapp.Colores.color_letra_topappbar
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R

@Preview (showBackground = true)
@Composable
fun suscripcionView() {
    //suscripcion()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuscripcionScreen(myViewModel: MyViewModel, navController: NavController) {
    val context = LocalContext.current

    var mensual = remember { mutableStateOf(true) }
    var anual = remember { mutableStateOf(false) }

    mensual.value = !anual.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color_fondo_claro)
    ) {
        // top app bar
        TopAppBar(
            title = {
                Text(text = myViewModel.languageType().get(15),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.navigate(AppScreens.MenuScreen.route) }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Te enviara al menu de opciones",
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults
                .smallTopAppBarColors(
                    containerColor = color_fondo_topappbar_alterno,
                    titleContentColor = color_letra_topappbar
                )
        )

        // cuerpo para las opciones inmovibles
        Column(
            modifier = Modifier
                .fillMaxSize()
        ){
            // cuerpo de las ventajas de la suscripcion
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.70f)
                    .verticalScroll(rememberScrollState())
            ) {
                SuscripcionDatos(
                    myViewModel,
                    titulo = myViewModel.languageType().get(16),
                    descripcion = myViewModel.languageType().get(17),
                    roundedCornerShape = RoundedCornerShape(topEnd = 5.dp,bottomStart = 5.dp,topStart = 5.dp,bottomEnd = 5.dp),
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    onClick = { }
                )


                SuscripcionDatos(
                    myViewModel,
                    titulo = myViewModel.languageType().get(18),
                    descripcion = myViewModel.languageType().get(19),
                    roundedCornerShape = RoundedCornerShape(topEnd = 5.dp,bottomStart = 5.dp,topStart = 5.dp,bottomEnd = 5.dp),
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    onClick = { }
                )

                SuscripcionDatos(
                    myViewModel,
                    titulo = myViewModel.languageType().get(20),
                    descripcion = myViewModel.languageType().get(21),
                    roundedCornerShape = RoundedCornerShape(topEnd = 5.dp,bottomStart = 5.dp,topStart = 5.dp,bottomEnd = 5.dp),
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    onClick = { }
                )

                SuscripcionDatos(
                    myViewModel,
                    titulo = myViewModel.languageType().get(22),
                    descripcion = myViewModel.languageType().get(23),
                    roundedCornerShape = RoundedCornerShape(topEnd = 5.dp,bottomStart = 5.dp,topStart = 5.dp,bottomEnd = 5.dp),
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    onClick = { }
                )
            }

            // cuerpo para el tipo de suscripcion
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color_fondo_oscuro,
                        RoundedCornerShape(
                            topEnd = 15.dp,
                            bottomStart = 0.dp,
                            topStart = 15.dp,
                            bottomEnd = 0.dp
                        )
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // cuerpo para las opciones de suscripcion
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    // caja para la suscripcion mensual
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.45f)
                            .fillMaxHeight(0.6f)
                            .background(
                                color_letra_textfield,
                                RoundedCornerShape(
                                    topEnd = 10.dp,
                                    bottomStart = 10.dp,
                                    topStart = 10.dp,
                                    bottomEnd = 10.dp
                                )
                            )
                            .toggleable(
                                value = mensual.value,
                                onValueChange = { mensual.value = it },
                                role = Role.RadioButton
                            ),
                    ){
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(3.dp)
                                .background(
                                    color_fondo_oscuro,
                                    RoundedCornerShape(
                                        topEnd = 10.dp,
                                        bottomStart = 10.dp,
                                        topStart = 10.dp,
                                        bottomEnd = 10.dp
                                    )
                                )
                        ){
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                TextUser(text = myViewModel.languageType().get(24), fontSize = 25, color = color_letra_topappbar, textAlign = TextAlign.Center)
                                Spacer(modifier = Modifier.size(10.dp))
                                TextUser(text = "$ 15.00", fontSize = 30, color = color_letra_textfield, textAlign = TextAlign.Center)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.size(10.dp))

                    // caja para la suscripcion anual
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.90f)
                            .fillMaxHeight(0.6f)
                            .background(
                                color_letra_textfield,
                                RoundedCornerShape(
                                    topEnd = 10.dp,
                                    bottomStart = 10.dp,
                                    topStart = 10.dp,
                                    bottomEnd = 10.dp
                                )
                            )
                            .toggleable(
                                value = anual.value,
                                onValueChange = { anual.value = it },
                                role = Role.RadioButton,
                            ),
                    ){
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(3.dp)
                                .background(
                                    color_fondo_oscuro,
                                    RoundedCornerShape(
                                        topEnd = 10.dp,
                                        bottomStart = 10.dp,
                                        topStart = 10.dp,
                                        bottomEnd = 10.dp
                                    )
                                )
                        ){
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                TextUser(text = myViewModel.languageType().get(25), fontSize = 25, color = color_letra_topappbar, textAlign = TextAlign.Center)
                                Spacer(modifier = Modifier.size(10.dp))
                                TextUser(text = "$ 120.00", fontSize = 30, color = color_letra_textfield, textAlign = TextAlign.Center)
                                TextUser(text = "$ 10.00/" + myViewModel.languageType().get(26), fontSize = 15, color = color_letra_textfield, textAlign = TextAlign.Center)
                            }
                        }
                    }
                }

                // cuerpo para el boton de suscripcion
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    val roundCornerShape = RoundedCornerShape(topEnd = 30.dp, bottomStart = 30.dp, topStart = 10.dp, bottomEnd = 10.dp)
                    Button(
                        onClick = {

                            Toast.makeText(
                                context,
                                "Seleccionaste ${mensual.value} y ${anual.value}",
                                Toast.LENGTH_LONG
                            ).show()

                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = color_letra_topappbar, // Cambiamos el color de fondo del botón aquí
                        ),
                        shape = roundCornerShape,
                        modifier = Modifier
                            .wrapContentSize()
                    ) {
                        Text(
                            text = myViewModel.languageType().get(27),
                            fontSize = 26.sp,
                            color = color_fondo_topappbar_alterno,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TextUser(
    text: String,
    fontSize: Int,
    fontWeight: FontWeight = FontWeight.Bold,
    color: Color,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text, // texto
        modifier = Modifier
            .fillMaxWidth(), // esto acapara el tamaño completo del Row=0.8f
        color = color,
        fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif,
        fontWeight = fontWeight,
        fontSize = fontSize.sp,
        textAlign = textAlign
    )
}

@Composable
private fun SuscripcionDatos(
    myViewModel: MyViewModel,
    titulo: String,
    descripcion: String,
    roundedCornerShape: RoundedCornerShape,
    painter: Painter,
    onClick: () -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .background(
                color_fondo_oscuro,
                roundedCornerShape
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        Spacer(modifier = Modifier.size(15.dp))
        Icon(
            painter = painter,
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ){
            TextUser(text = titulo, fontSize = 25, color = color_letra_topappbar)
            TextUser(text = descripcion, fontSize = 15, color = color_letra_topappbar, fontWeight = FontWeight.Normal)
            Spacer(modifier = Modifier.size(30.dp))
            ClickableText(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(
                        color = color_letra_textfield,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold)
                    ) {
                        append(myViewModel.languageType().get(28))
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                onClick = { onClick }
            )
        }
    }
}