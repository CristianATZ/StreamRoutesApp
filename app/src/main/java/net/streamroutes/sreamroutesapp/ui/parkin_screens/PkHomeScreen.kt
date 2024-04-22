package net.streamroutes.sreamroutesapp.ui.parkin_screens

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.opencensus.trace.Span
import net.streamroutes.sreamroutesapp.R

@Composable
fun ParkingHomeScreen() {
    Column {
        Header()
    }
}

@Composable
private fun Spots() {
    TODO("Not yet implemented")
}

@Composable
private fun Vehicle() {
    TODO("Not yet implemented")
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
                color = colorScheme.onBackground,
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



