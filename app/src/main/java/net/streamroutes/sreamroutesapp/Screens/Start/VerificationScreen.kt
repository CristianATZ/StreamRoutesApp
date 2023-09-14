@file:OptIn(ExperimentalMaterial3Api::class)

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
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.text.TextStyle
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
import net.streamroutes.sreamroutesapp.Colores.color_botones
import net.streamroutes.sreamroutesapp.Colores.color_fondo
import net.streamroutes.sreamroutesapp.Colores.color_fondo_textfield
import net.streamroutes.sreamroutesapp.Colores.color_fondo_topbar
import net.streamroutes.sreamroutesapp.Colores.color_letra_botones
import net.streamroutes.sreamroutesapp.Colores.color_letra_topbar
import net.streamroutes.sreamroutesapp.Colores.color_letraout
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R
import java.util.Random

@Composable
fun VerificationScreen (myViewModel: MyViewModel,navController: NavController) {
    Verification(myViewModel,navController)
}

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

    var telefono by remember { mutableStateOf("") }
    var codigo by remember { mutableStateOf("") }
    var codigoGenerado by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            TopBarBody(
                navController = navController,
                myViewModel = myViewModel
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingvalues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingvalues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.size(32.dp))

            // imagen logo
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo Stream Routes",
                modifier = Modifier
                        .size(150.dp)
            )

            Spacer(modifier = Modifier.size(16.dp))

            // telefono
            HeaderTextField(
                tittle = "Telefono",
                placeholder = "Telefono",
                size = 70,
                variable = telefono,
                onValueChange = {newValue -> telefono = newValue},
                visualTransformation = VisualTransformation.None,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                )
            )

            Spacer(modifier = Modifier.size(5.dp))

            // boton enviar codigo de verificacion
            Button(
                onClick = {
                    if (!smsPermissionState.status.isGranted) {
                        smsPermissionState.launchPermissionRequest()
                    }

                    if(isPermissionsGranted(context)){
                        if(!telefono.isEmpty()){
                            codigoGenerado = generarCodigo()
                            val smsManager: SmsManager = SmsManager.getDefault()
                            smsManager.sendTextMessage(telefono, null, codigoGenerado, null, null)
                            Toast.makeText(context, myViewModel.languageType().get(123) + " $codigoGenerado", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, myViewModel.languageType().get(124), Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, "Por favor ve a la configuracion de la aplicacion y habilita los permisos de mensajeria.", Toast.LENGTH_LONG).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary
                ),
                shape = RoundedCornerShape(percent = 40),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 5.dp
                ),
                modifier = Modifier
                    .fillMaxWidth(0.65f)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Enviar",
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            }

            // codigo de verificacion
            HeaderTextField(
                tittle = "Codigo de verificacion",
                placeholder = "Codigo",
                size = 70,
                variable = codigo,
                onValueChange = {newValue -> codigo = newValue},
                visualTransformation = VisualTransformation.None,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                )
            )

            Spacer(modifier = Modifier.size(5.dp))

            // boton para verificar el codigo
            Button(
                onClick = {
                    navController.navigate(route = AppScreens.ChangeScreen.route)
                    if(codigoGenerado == codigo) navController.navigate(route = AppScreens.ChangeScreen.route)
                    else Toast.makeText(context, myViewModel.languageType().get(128), Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary
                ),
                shape = RoundedCornerShape(percent = 40),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 5.dp
                ),
                modifier = Modifier
                    .fillMaxWidth(0.65f)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Verificar",
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBarBody(
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
                    painterResource(id = R.drawable.back),
                    contentDescription = "Te enviara al login",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        colors = TopAppBarDefaults
            .smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
    )
}

@Composable
private fun HeaderTextField(
    tittle: String,
    placeholder: String,
    singleLine: Boolean = true,
    size: Int,
    variable: String,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation,
    keyboardOptions: KeyboardOptions
) {
    // nombre
    Row (
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .size(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        // header
        Text(
            text = tittle,
            modifier = Modifier
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.onBackground,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }

    OutlinedTextField(
        value = variable,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .heightIn(size.dp),
        // icono para mostrar la contraseña
        // trailingIcon =
        placeholder = {
            Text(text = placeholder, fontSize = 18.sp, color = MaterialTheme.colorScheme.onPrimaryContainer.copy(0.5f))
        },
        textStyle = TextStyle(
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
        ),
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        shape = RoundedCornerShape(percent = 30),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unfocusedBorderColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        singleLine = singleLine
    )
}

private fun generarCodigo(): String {
    val random = Random(System.currentTimeMillis())
    val codigo = StringBuilder()
    repeat(6) {
        codigo.append(random.nextInt(10)) // Genera un número aleatorio entre 0 y 9 (ambos inclusive)
    }
    return codigo.toString()
}