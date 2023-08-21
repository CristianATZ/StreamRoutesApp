@file:OptIn(ExperimentalMaterial3Api::class)

package net.streamroutes.sreamroutesapp.Screens.MenuScreens

import android.content.pm.PackageManager.PERMISSION_GRANTED
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import net.streamroutes.sreamroutesapp.Colores.color_fondo
import net.streamroutes.sreamroutesapp.Colores.color_fondo_claro
import net.streamroutes.sreamroutesapp.Colores.color_fondo_topbar
import net.streamroutes.sreamroutesapp.Colores.color_icon
import net.streamroutes.sreamroutesapp.Colores.color_letra
import net.streamroutes.sreamroutesapp.Colores.color_letra_botones
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R


@Composable
fun ResenaScreen(){
    Desing()
}

@Preview
@Composable
fun Desing(){

    Scaffold(
        topBar = { },
        containerColor = color_fondo
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            DesingTop()
            DesingAddPost()
            DesingPosts()
        }
    }
}



@Composable
fun DesingTop(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.08f)
            .background(color_fondo_topbar)
    ){
        Row(){
            Column{
                IconButton(onClick = {  }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Te enviara al menu de opciones",
                        tint = color_icon
                    )
                }
            }
            Column {
                Text(
                    text = "Reseñas",//myViewModel.languageType().get(29),
                    color = color_letra_botones,
                    fontSize = 35.sp,
                    modifier = Modifier.padding(start = 2.dp)
                )
            }
        }
    }
}

@Composable
fun DesingAddPost() {


    val context = LocalContext.current
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Permiso otorgado, abrir la cámara
            val intent = android.content.Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(context, intent, null)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.44f)
            .border(1.dp, Color.Black, shape = RoundedCornerShape(15.dp))
            .background(color_fondo_claro)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()  // Asegura que la altura del Column sea el contenido
                .padding(6.dp)

        ) {
            Text(
                text = "Comparte tu opinión",
                color = color_letra,
                fontSize = 19.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )

            IconButton(
                onClick = {
                    // Verificar y solicitar el permiso de la cámara
                    if (androidx.core.content.ContextCompat.checkSelfPermission(
                            context,
                            android.Manifest.permission.CAMERA
                        ) == PERMISSION_GRANTED
                    ) {
                        // Permiso ya otorgado, abrir la cámara
                        val intent = android.content.Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
                        startActivity(context, intent, null)
                    } else {
                        // Solicitar permiso de la cámara
                        cameraLauncher.launch(android.Manifest.permission.CAMERA)
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(170.dp)
                    .border(1.dp, Color.Black, shape = RoundedCornerShape(4.dp))

            ) {
                Image(
                    painter = painterResource(id = R.drawable.post),
                    contentDescription = "Imagen"
                )
            }

            Text(
                text = "Comenta tu viaje",
                color = color_letra,
                fontSize = 19.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )

            var textFieldValue by remember { mutableStateOf("") }

            val usadosCar = 0

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                BasicTextField(
                    value = textFieldValue,
                    onValueChange = { newValue ->
                        if (newValue.length <= 150) {
                            textFieldValue = newValue
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(.87f)
                        .fillMaxHeight()
                        .background(Color.White)
                        .align(Alignment.Center)
                        .border(1.dp, Color.Black, shape = RoundedCornerShape(4.dp))
                        .padding(12.dp)
                        ,
                    maxLines = 5
                )

                val remainingCharacters = usadosCar + textFieldValue.length
                Text(
                    text = "$remainingCharacters/150",
                    color = Color.Gray,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(top = 14.dp, end = 30.dp)
                )
            }

        }
    }
}



@Composable
fun DesingPosts(){

}


