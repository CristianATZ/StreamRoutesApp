package net.streamroutes.sreamroutesapp.features.premium.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.premium.components.PremiumAdvantage
import net.streamroutes.sreamroutesapp.features.premium.components.PremiumPackage
import net.streamroutes.sreamroutesapp.features.premium.components.PremiumSmallTopAppBar

data class Advantage(
    val title: String,
    val description: String
)

data class Plan(
    val title: String,
    val time: String,
    val description: String?,
    val action: () -> Unit
)

@Preview(showBackground = true)
@Composable
fun PremiumScreen(
    modifier: Modifier = Modifier
) {
    val plans = listOf(
        Plan(
            title = stringResource(id = R.string.lblFree),
            time = stringResource(id = R.string.lblFreeTime),
            description = stringResource(id = R.string.lblFreeDescription, 20),
            action = {
                //
            }
        ),
        Plan(
            title = stringResource(id = R.string.lblStandar),
            time = stringResource(id = R.string.lblCommonTime),
            description = stringResource(id = R.string.lblCommonPrice, 20),
            action = {
                //
            }
        ),
        Plan(
            title = stringResource(id = R.string.lblStudent),
            time = stringResource(id = R.string.lblCommonTime),
            description = stringResource(id = R.string.lblCommonPrice, 15),
            action = {
                //
            }
        ),
        Plan(
            title = stringResource(id = R.string.lblTurist),
            time = stringResource(id = R.string.lblTuristTime),
            description = stringResource(id = R.string.lblCommonPrice, 15),
            action = {
                //
            }
        ),
        Plan(
            title = stringResource(id = R.string.lblAnual),
            time = stringResource(id = R.string.lblAnualTime),
            description = stringResource(id = R.string.lblAnualDescription, 15),
            action = {
                //
            }
        ),
    )

    Scaffold(
        topBar = {
            PremiumSmallTopAppBar(
                title = "",
                onBackPressed = {
                    // REGRESAR A INICIO
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.size(16.dp))

            Text(
                text = "Mejora",
                style = typography.displayLarge,
                modifier = Modifier.padding(start = 16.dp)
            )
            Text(
                text = "tu plan",
                style = typography.displayLarge,
                modifier = Modifier.padding(start = 16.dp)
            )

            Spacer(modifier = Modifier.size(16.dp))

            // sin publicidad
            PremiumAdvantage(
                advantage = Advantage(
                    title = stringResource(id = R.string.lblAdvantage_NoADS),
                    description = stringResource(id = R.string.lblAdvantage_NoADS_description)
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            // tiempo exacto
            PremiumAdvantage(
                advantage = Advantage(
                    title = stringResource(id = R.string.lblAdvantage_exactTime),
                    description = stringResource(id = R.string.lblAdvantage_exactTime_description)
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            // foro
            PremiumAdvantage(
                advantage = Advantage(
                    title = stringResource(id = R.string.lblAdvantage_forum),
                    description = stringResource(id = R.string.lblAdvantage_forum_description)
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            // disponibilidad
            PremiumAdvantage(
                advantage = Advantage(
                    title = stringResource(id = R.string.lblAdvantage_availability),
                    description = stringResource(id = R.string.lblAdvantage_availability_description)
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            LazyRow {
                items(plans.size) { index ->
                    PremiumPackage(
                        plan = plans[index],
                        isFreeTrial = index == 0
                    )
                }
            }
        }
    }
}