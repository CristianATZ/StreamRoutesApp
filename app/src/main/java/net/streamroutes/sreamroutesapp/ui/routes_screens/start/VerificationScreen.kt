@file:OptIn(ExperimentalMaterial3Api::class)

package net.streamroutes.sreamroutesapp.ui.routes_screens.start

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.telephony.SmsManager
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import net.streamroutes.sreamroutesapp.utils.MyViewModel
import net.streamroutes.sreamroutesapp.navigation.AppScreens
import net.streamroutes.sreamroutesapp.R
import java.util.Random

@Composable
fun VerificationScreen (myViewModel: MyViewModel, navController: NavController) {
    Verification(myViewModel,navController)
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun Verification(myViewModel: MyViewModel, navController: NavController) {
    var telefono by remember { mutableStateOf("") }
    var codigo by remember { mutableStateOf("") }
    var codigoGenerado by remember { mutableStateOf("") }
    var habilitar by remember {
        mutableStateOf(false)
    }
    val focusManager = LocalFocusManager.current

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

    fun generarCodigo(): String {
        val random = Random(System.currentTimeMillis())
        val codigo = StringBuilder()
        repeat(6) {
            codigo.append(random.nextInt(10)) // Genera un número aleatorio entre 0 y 9 (ambos inclusive)
        }
        return codigo.toString()
    }

    fun sendCode() {
        if(telefono.length == 10){
            habilitar = true

            if (!smsPermissionState.status.isGranted) {
                smsPermissionState.launchPermissionRequest()
            }

            if(isPermissionsGranted(context)){
                if(telefono.isNotEmpty()){
                    codigoGenerado = generarCodigo()
                    val smsManager: SmsManager = SmsManager.getDefault()
                    smsManager.sendTextMessage(telefono, null, codigoGenerado, null, null)
                } else {
                    Toast.makeText(context, myViewModel.languageType().get(285), Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Por favor ve a la configuración de la aplicación y habilita los permisos de mensajería.", Toast.LENGTH_LONG).show()
            }
            focusManager.moveFocus(FocusDirection.Down)
        } else {
            Toast.makeText(context, myViewModel.languageType().get(285), Toast.LENGTH_SHORT).show()
        }
    }


    Scaffold(
        topBar = { TopBar(navController,myViewModel) }
    ) { paddingValues ->  
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(64.dp))

            // logo
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo"
            )

            Spacer(modifier = Modifier.size(64.dp))

            if(!habilitar){
                // telefono
                OutlinedTextField(
                    value = telefono,
                    onValueChange = { telefono = it },
                    label = {
                        Text(
                            text = myViewModel.languageType().get(270),
                            style = typography.titleMedium
                        )
                    },
                    shape = RoundedCornerShape(16.dp),
                    trailingIcon = {
                        if( telefono.isNotEmpty() ){
                            IconButton(
                                onClick = {
                                    telefono = ""
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Clear,
                                    contentDescription = "borrar telefono",
                                    modifier = Modifier
                                        .size(32.dp)
                                )
                            }
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            sendCode()
                        }
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                )

                // enviar codigo
                Button(
                    onClick = {
                        sendCode()
                    },
                    shape = RoundedCornerShape(16),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(top = 16.dp)
                        .height(50.dp)
                ) {
                    Text(
                        text = myViewModel.languageType().get(286),
                        style = typography.bodyLarge
                    )
                }
            }

            //Spacer(modifier = Modifier.size(8.dp))

            if(habilitar){
                // codigo de verificacino
                OutlinedTextField(
                    value = codigo,
                    onValueChange = { codigo = it },
                    label = {
                        Text(
                            text = myViewModel.languageType().get(287),
                            style = typography.titleMedium
                        )
                    },
                    shape = RoundedCornerShape(16.dp),
                    trailingIcon = {
                        if( codigo.isNotEmpty() ){
                            IconButton(
                                onClick = {
                                    codigo = ""
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Clear,
                                    contentDescription = "borrar telefono",
                                    modifier = Modifier
                                        .size(32.dp)
                                )
                            }
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                )

                // verificar codigo
                Button(
                    onClick = {
                        navController.navigate(AppScreens.ChangeScreen.route)
                    },
                    shape = RoundedCornerShape(16),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(top = 16.dp)
                        .height(50.dp)
                ) {
                    Text(
                        text = myViewModel.languageType().get(290),
                        style = typography.bodyLarge
                    )
                }
            }
        }
    }

}

@Composable
private fun TopBar(
    navController: NavController,
    myViewModel: MyViewModel
) {
    CenterAlignedTopAppBar(
        title = { 
            Text(
                text = myViewModel.languageType().get(282),
                style = typography.titleLarge
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { 
                    navController.navigate(AppScreens.LoginScreen.route) 
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowLeft, 
                    contentDescription = "regresar al login"
                )
            }
        }
    )
}