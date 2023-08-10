package net.streamroutes.sreamroutesapp.Screens.Start

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.telephony.SmsManager
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import net.streamroutes.sreamroutesapp.Colores.color_fondo_claro
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R
import java.util.Random

@Composable
fun VerificationScreen (myViewModel: MyViewModel,navController: NavController) {
    Verification(myViewModel,navController)
}

val color_fondo_ = Color(0xFFFFF7E7)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun Verification(myViewModel: MyViewModel,navController: NavController) {
    val context = LocalContext.current

    val smsPermissionState = rememberPermissionState(
        Manifest.permission.SEND_SMS
    )

    fun isPermissionsGranted(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.SEND_SMS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            // No es necesario verificar permisos en versiones anteriores a Marshmallow
            true
        }
    }

    var telefono by remember { mutableStateOf(TextFieldValue()) }
    var codigo by remember { mutableStateOf(TextFieldValue("")) }
    var codigoGenerado by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            TopBarBody(
                navController = navController,
                myViewModel = myViewModel
            )
        },
        containerColor = color_fondo_claro
    ) { paddingvalues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingvalues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.size(30.dp))

            // imagen logo
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .size(150.dp),
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "null")
            }

            Spacer(modifier = Modifier.size(15.dp))

            // telefono
            PasswordTextfield(
                tittle = myViewModel.languageType().get(122),
                placeholder = myViewModel.languageType().get(122),
                readOnly = false,
                size = 70,
                variable = telefono,
                onVariableChange = {newValue -> telefono = newValue},
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                )
            )

            Spacer(modifier = Modifier.size(5.dp))

            // boton enviar codigo de verificacion
            val roundCornerShape = RoundedCornerShape(topEnd = 30.dp, bottomStart = 30.dp, topStart = 10.dp, bottomEnd = 10.dp)
            Button(
                onClick = {
                    if (!smsPermissionState.status.isGranted) {
                        smsPermissionState.launchPermissionRequest()
                    }

                    if(isPermissionsGranted(context)){
                        if(!telefono.text.isEmpty()){
                            codigoGenerado = generarCodigo()
                            val smsManager: SmsManager = SmsManager.getDefault()
                            smsManager.sendTextMessage(telefono.text, null, codigoGenerado, null, null)
                            Toast.makeText(context, myViewModel.languageType().get(123) + " $codigoGenerado", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, myViewModel.languageType().get(124), Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, "Por favor ve a la configuracion de la aplicacion y habilita los permisos de mensajeria.", Toast.LENGTH_LONG).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF192833), // Cambiamos el color de fondo del botón aquí
                    contentColor = Color.White
                ),
                shape = roundCornerShape,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(10.dp)
            ) {
                Text(
                    text = myViewModel.languageType().get(125),
                    fontSize = 26.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            // codigo de verificacion
            PasswordTextfield(
                tittle = myViewModel.languageType().get(126),
                placeholder = myViewModel.languageType().get(127),
                readOnly = false,
                size = 70,
                variable = codigo,
                onVariableChange = {newValue -> codigo = newValue},
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                )
            )

            Spacer(modifier = Modifier.size(5.dp))

            // boton para verificar el codigo
            Button(
                onClick = {
                    if(codigoGenerado.equals(codigo.text)) navController.navigate(route = AppScreens.ChangeScreen.route)
                    else Toast.makeText(context, myViewModel.languageType().get(128), Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF192833), // Cambiamos el color de fondo del botón aquí
                    contentColor = Color.White
                ),
                shape = roundCornerShape,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(10.dp)
            ) {
                Text(
                    text = myViewModel.languageType().get(129),
                    fontSize = 26.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarBody(
    navController: NavController,
    myViewModel: MyViewModel
) {
    TopAppBar(
        title = {
            Text(text = myViewModel.languageType().get(121),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "Te enviara al login"
                )
            }
        }
    )
}

@Composable
private fun PasswordTextfield(
    tittle: String,
    placeholder: String,
    readOnly: Boolean,
    singleLine: Boolean = true,
    size: Int,
    variable: TextFieldValue,
    onVariableChange: (TextFieldValue) -> Unit,
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(percent = 30),
    keyboardOptions: KeyboardOptions
) {
    // nombre
    Row (
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .size(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        // forgot
        Text(
            text = tittle,
            modifier = Modifier
                .fillMaxWidth(),
            color = Color.DarkGray,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .background(
                Color(0xFFFFE5B4),
                roundedCornerShape
            )
    ){
        // caja de texto
        BasicTextField(
            value = variable,
            onValueChange = onVariableChange,
            singleLine = singleLine,
            readOnly = readOnly,
            modifier = Modifier
                .height(size.dp)
                .fillMaxWidth()
                .padding(4.dp),
            keyboardOptions = keyboardOptions,
            textStyle = LocalTextStyle.current.copy(
                fontSize = 18.sp,
                color = Color(0xFFE8AA42),
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp
            ),
            decorationBox = { innerTextField ->
                Row(
                    Modifier
                        .background(Color(0xFFFFE5B4), RoundedCornerShape(percent = 30))
                        .padding(16.dp)
                        .fillMaxWidth(0.8f)
                ){
                    if (variable.text.isEmpty()){
                        Text(
                            text = placeholder,
                            fontSize = 18.sp,
                            color = Color(0xFFFFF7E7),
                            letterSpacing = 3.sp,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)

                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}

private fun generarCodigo(): String {
    val random = Random(System.currentTimeMillis())
    val codigo = StringBuilder()
    repeat(6) {
        codigo.append(random.nextInt(10)) // Genera un número aleatorio entre 0 y 9 (ambos inclusive)
    }
    return codigo.toString()
}