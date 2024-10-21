package net.streamroutes.sreamroutesapp.features.settings.presentation.privacity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.components.SwitchField
import net.streamroutes.sreamroutesapp.features.settings.components.SettingsSmallTopAppBar

@Composable
fun PrivacityScreen(
    modifier: Modifier = Modifier
) {
    val onBackPressed = {

    }

    var ads by remember {
        mutableStateOf(true)
    }
    val adsPressed = {
        // actualizar valor en viewmodel
    }

    var payment by remember {
        mutableStateOf(true)
    }
    val paymentPressed = {
        // actualizar valor en viewmodel
    }

    var links by remember {
        mutableStateOf(true)
    }
    val linkPressed = {
        // actualizar valor en viewmodel
    }

    Scaffold(
        topBar = {
            SettingsSmallTopAppBar(
                title = stringResource(R.string.lblPrivacity),
                onBackPressed = onBackPressed,
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            // publicidad
            SwitchField(
                headerText = stringResource(R.string.lblCustomAds),
                descriptionText = stringResource(R.string.lblCustomAdsDescription),
                value = ads,
                iconTrue = Icons.Outlined.Done,
                iconFalse = Icons.Outlined.Close,
                iconDescription = stringResource(R.string.iconDoneClose),
                onValueChange = {
                    ads = it
                    adsPressed()
                }
            )

            // pago de suscrpicion
            SwitchField(
                headerText = stringResource(R.string.lblPayment),
                descriptionText = stringResource(R.string.lblPaymentDescription),
                value = payment,
                iconTrue = Icons.Outlined.Done,
                iconFalse = Icons.Outlined.Close,
                iconDescription = stringResource(R.string.iconDoneClose),
                onValueChange = {
                    payment = it
                    paymentPressed()
                }
            )

            // enlances
            SwitchField(
                headerText = stringResource(R.string.lblLinks),
                descriptionText = stringResource(R.string.lblLinksDescription),
                value = links,
                iconTrue = Icons.Outlined.Done,
                iconFalse = Icons.Outlined.Close,
                iconDescription = stringResource(R.string.iconDoneClose),
                onValueChange = {
                    links = it
                    linkPressed()
                }
            )
        }
    }
}