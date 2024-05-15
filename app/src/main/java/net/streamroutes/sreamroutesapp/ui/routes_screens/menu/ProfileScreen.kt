package net.streamroutes.sreamroutesapp.ui.routes_screens.menu

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import net.streamroutes.sreamroutesapp.Dialogs.MembDialogEdit
import net.streamroutes.sreamroutesapp.Dialogs.SecuDialogEdit
import net.streamroutes.sreamroutesapp.Dialogs.UserDialogEdit
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.utils.brush
import net.streamroutes.sreamroutesapp.viewmodel.routes.ConfigurationViewModel
import net.streamroutes.sreamroutesapp.viewmodel.routes.ProfileViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = ProfileViewModel(),
    configurationViewModel: ConfigurationViewModel = ConfigurationViewModel(),
    onBack: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    LaunchedEffect(true) {
        systemUiController.setNavigationBarColor(Color.Black)
        systemUiController.setStatusBarColor(Color(0xFFE8AA42))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderProfile(onBack)

            InformationProfile(profileViewModel)
        }
    }
}

@Composable
fun InformationProfile(profileViewModel: ProfileViewModel) {

    var openMem by remember {
        mutableStateOf(false)
    }

    var openSec by remember {
        mutableStateOf(false)
    }

    var openUse by remember {
        mutableStateOf(false)
    }

    if(openMem){
        MembDialogEdit(
            onClose = { openMem = !openMem }
        )
    }

    if(openSec){
        SecuDialogEdit(
            onClose = { openSec = !openSec }
        )
    }

    if(openUse){
        UserDialogEdit(
            onClose = { openUse = !openUse },
            profileViewModel = profileViewModel
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {
                openUse = !openUse
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorScheme.inverseSurface,
                contentColor = colorScheme.inverseOnSurface
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .shadow(4.dp, RoundedCornerShape(8.dp), spotColor = colorScheme.onBackground)
        ) {
            Text(
                text = stringResource(id = R.string.lblEditarInformacion),
                style = typography.titleMedium
            )
        }
        
        Spacer(modifier = Modifier.size(16.dp))

        Button(
            onClick = {

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorScheme.inverseSurface,
                contentColor = colorScheme.tertiary
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .shadow(4.dp, RoundedCornerShape(8.dp), spotColor = colorScheme.onBackground)
        ) {
            Text(
                text = stringResource(id = R.string.lblCerrarSesion),
                style = typography.titleMedium
            )
        }
    }

    InfoCard(
        title = stringResource(id = R.string.lblCorreo),
        content = "s20120154@alumnos.itusr.edu.mx",
        editable = false
    )

    InfoCard(
        title = stringResource(id = R.string.lblTelefono),
        content = "4451411834",
        editable = false
    )

    InfoCard(
        title = stringResource(id = R.string.lblMembresia),
        content = "Estudiante"
    ){
        openMem = !openMem
    }

    InfoCard(
        title = stringResource(id = R.string.lblSeguridad),
        content = "Baja"
    ){
        openSec = !openSec
    }

}

@Composable
fun InfoCard(
    title: String,
    content: String,
    editable: Boolean = true,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        colors = CardDefaults.elevatedCardColors(
            containerColor = colorScheme.background,
            contentColor = colorScheme.onBackground,
        ),
        modifier = modifier
            .fillMaxWidth(0.92f)
            .padding(top = 16.dp)
            .shadow(4.dp, CardDefaults.elevatedShape, spotColor = colorScheme.onBackground)
    ) {
        Box(){
            Column(
                modifier = Modifier
                    .height(100.dp)
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    style = typography.bodySmall
                )

                Spacer(modifier = Modifier.size(16.dp))

                Text(text = content, style = typography.labelLarge, modifier = Modifier.fillMaxWidth(0.7f))
            }

            // boton editar
            if(editable){
                Row(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { onClick() },
                        modifier = Modifier
                            .size(50.dp)
                            .shadow(4.dp, CircleShape, spotColor = colorScheme.onBackground),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = colorScheme.background
                        )
                    ) {
                        Icon(imageVector = Icons.Filled.Edit, contentDescription = null)
                    }

                    Spacer(modifier = Modifier.size(16.dp))
                }
            }
        }
    }
}

@Composable
fun HeaderProfile(
    onBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Black.copy(alpha = 0.1f),
                        Color.Transparent,
                    )
                )
            )
            .shadow(8.dp, RoundedCornerShape(bottomEnd = 50.dp, bottomStart = 50.dp))
            .fillMaxWidth()
            .heightIn(200.dp),
    ){
        Column(
            modifier = Modifier
                .background(
                    brush = brush,
                    RoundedCornerShape(bottomEnd = 50.dp, bottomStart = 50.dp)
                )

                .fillMaxWidth()
                .heightIn(200.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TopBarBody(onBack)

            Box(

            ) {
                Image(
                    painter = painterResource(id = R.drawable.usuario_3),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(150.dp),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomEnd),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.End
                ) {
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .size(48.dp)
                            .background(Color.Black, CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Add,
                            contentDescription = "Agregar imagen de perfil",
                            tint = colorScheme.onPrimary
                        )
                    }
                }
            }
            Text(
                text = "Cristian Alexis Torres Zavala",
                style = typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF281800),
                modifier = Modifier
                    .padding(PaddingValues(16.dp))
            )
        }
    }
}

@Composable
private fun TopBarBody(
    onBack: () -> Unit
) {
    Row(
        modifier = Modifier
            .height(64.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { onBack() }
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowBackIosNew,
                contentDescription = "Regresar a la pantalla principal",
                tint = Color(0xFF281800)
            )
        }
    }
}