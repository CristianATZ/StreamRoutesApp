package net.streamroutes.sreamroutesapp.ui.routes_screens.profile

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getString
import net.streamroutes.sreamroutesapp.Dialogs.MembDialogEdit
import net.streamroutes.sreamroutesapp.Dialogs.UserDialogEdit
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.viewmodel.ProfileViewModel

data class DataInfoItem(val title: String, val inf: String)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = ProfileViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderProfile()
            FeaturedProfile(profileViewModel)
            FooterProfile(profileViewModel)
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FooterProfile(profileViewModel: ProfileViewModel) {
    val context = LocalContext.current

    var user by remember {
        mutableStateOf(false)
    }

    var member by remember {
        mutableStateOf(false)
    }

    var seguridad by remember {
        mutableStateOf(false)
    }

    var userInfo by remember {
        mutableStateOf(
            listOf(
                DataInfoItem(getString(context, R.string.birthday),profileViewModel.birthday.toString()),
                DataInfoItem(getString(context, R.string.phone),profileViewModel.phone),
                DataInfoItem(getString(context, R.string.country),profileViewModel.country),
                DataInfoItem(getString(context, R.string.city),profileViewModel.address)
            )
        )
    }

    var secInfo by remember {
        mutableStateOf(
            listOf(
                DataInfoItem(getString(context, R.string.password_profile), profileViewModel.pass),
                DataInfoItem(getString(context, R.string.verification), if(profileViewModel.verification) "Activada" else "Desactivada"),
                DataInfoItem(getString(context, R.string.email), profileViewModel.email),
                DataInfoItem(getString(context, R.string.phone), profileViewModel.phone)
            )
        )
    }

    var memberInfo by remember {
        mutableStateOf(
            listOf(
                DataInfoItem(getString(context, R.string.type), profileViewModel.type.name),
                DataInfoItem(getString(context, R.string.duration), profileViewModel.badge),
                DataInfoItem(getString(context, R.string.expiration_date), profileViewModel.end.toString()),
            )
        )
    }



    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var openUse by remember {
            mutableStateOf(false)
        }


        var openMem by remember {
            mutableStateOf(false)
        }

        var openSec by remember {
            mutableStateOf(false)
        }

        if(openUse){
            UserDialogEdit(
                onClose = { openUse = !openUse },
                profileViewModel = profileViewModel
            )
        }


        if(openMem){
            MembDialogEdit(
                onClose = { openMem = !openMem }
            )
        }

        /*
        if(openSec){
            SecuDialogEdit(
                onClose = { openSec = !openSec }
            )
        }*/


        ProfileItems(
            title = stringResource(id = R.string.personal_info),
            description = stringResource(id = R.string.personal_information_desc),
            open = user,
            items = userInfo,
            onClick = {
                user = !user
            },
            onEdit = { openUse = !openUse }
        )

        Spacer(modifier = Modifier.size(8.dp))

        ProfileItems(
            title = stringResource(id = R.string.membership),
            description = stringResource(id = R.string.home),
            open = member,
            items = memberInfo,
            onClick = { member = !member },
            onEdit = { openMem = !openMem }
        )

        Spacer(modifier = Modifier.size(8.dp))

        ProfileItems(
            title = stringResource(id = R.string.security),
            description = stringResource(id = R.string.security_desc),
            open = seguridad,
            items = secInfo,
            onClick = { seguridad = !seguridad },
            onEdit = { openSec = !openSec }
        )

        Spacer(modifier = Modifier.size(16.dp))
    }
}

data class FeaturedItem(
    val name: String,
    val value: String
)

@SuppressLint("StateFlowValueCalledInComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FeaturedProfile(profileViewModel: ProfileViewModel) {
    val featured_items = listOf(
        FeaturedItem(
            name = stringResource(id = R.string.favorite_routes),
            value = profileViewModel.uiState.value.fav.toString()
        ),
        FeaturedItem(
            name = stringResource(id = R.string.suscription),
            value = profileViewModel.uiState.value.payment.name
        ),
        FeaturedItem(
            name = stringResource(id = R.string.verification),
            value = "Icono"
        ),
        FeaturedItem(
            name = stringResource(id = R.string.interestings),
            value = profileViewModel.uiState.value.intereses.toString()
        ),
        FeaturedItem(
            name = stringResource(id = R.string.log_out),
            value = "Icono"
        ),
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorScheme.surface),
        verticalArrangement = Arrangement.Center,
    ) {

        Spacer(modifier = Modifier.size(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            FeaturedInformation(
                inf = profileViewModel.fav.toString(),
                title = stringResource(id = R.string.favorite_routes)
            )
            FeaturedInformation(
                inf = profileViewModel.payment.name,
                title = stringResource(id = R.string.suscription)
            )
            FeaturedInformation(
                icon = Icons.Outlined.Check,
                title = stringResource(id = R.string.verification)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            FeaturedInformation(
                inf = profileViewModel.intereses.toString(),
                title = stringResource(id = R.string.interestings),
                large = true
            )
            FeaturedInformation(
                icon = Icons.Outlined.ExitToApp,
                title = stringResource(id = R.string.log_out)
            )
        }

    }
}

@Composable
fun FeaturedInformation(
    inf: String = "",
    icon: ImageVector = Icons.Outlined.Refresh,
    title: String,
    large: Boolean = false
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .padding(8.dp)
            .width(if (!large) 125.dp else 250.dp)
            .height(100.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if( !inf.isEmpty() ){
                Text(
                    text = inf,
                    style = typography.bodyMedium,
                    color =  Color(0xFFE8AA42),
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = if(large) 2 else 1,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            } else {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color(0xFFE8AA42)
                )
            }

            Text(
                text = title,
                style = typography.titleSmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ProfileItems(
    title: String,
    description: String,
    open: Boolean,
    items: List<DataInfoItem>,
    onClick: () -> Unit,
    onEdit: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(80.dp)
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 5.dp
        )
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessVeryLow
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .heightIn(80.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = title,
                        style = typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                    )
                    Text(
                        text = description,
                        style = typography.bodySmall
                    )
                }

                Spacer(Modifier.weight(1f))

                IconButton(
                    onClick = { onClick() }
                ) {
                    Icon(
                        imageVector = if (open) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = stringResource(id = R.string.more_information),
                    )
                }
            }
            if(open){
                ProfileInfo(
                    list = items,
                    onClick = { onEdit() }
                )
            }

        }
    }
}

@Composable
fun ProfileInfo(
    list: List<DataInfoItem>,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .fillMaxWidth()
    ) {
        list.forEach() { item ->
            InfoItem(
                title = item.title,
                inf = item.inf
            )
        }

        TextButton(
            onClick = { onClick() },
            modifier = Modifier
                .align(Alignment.End)
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.edit),
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}

@Composable
fun InfoItem(
    title: String,
    inf: String
) {
    Row(
        modifier = Modifier
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier
                .padding(end = 16.dp)
        )
        Text(
            text = inf,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            fontSize = 14.sp,
        )
    }
}

@Composable
fun HeaderProfile() {
    Column(
        modifier = Modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFFE8CF41), Color(0xFFE8AA42))
                ),
                RoundedCornerShape(bottomEnd = 15.dp, bottomStart = 15.dp)
            )
            .fillMaxWidth()
            .heightIn(200.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TopBarBody()

        Box(

        ) {
            Image(
                painter = painterResource(id = R.drawable.usuario_2),
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

@Composable
private fun TopBarBody() {
    Row(
        modifier = Modifier
            .height(64.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {  }
        ) {
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowLeft,
                contentDescription = "Regresar a la pantalla principal",
                tint = Color(0xFF281800)
            )
        }
    }
}