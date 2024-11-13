package net.streamroutes.sreamroutesapp.features.turism.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.utils.shimmerEffect

@Composable
fun TurismBottomSheet(
    totalTurism: Int? = null,
    onBackPressed: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.lblTurismTotal),
            style = typography.headlineSmall
        )

        if(totalTurism != null) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = colorScheme.tertiaryContainer,
                    contentColor = colorScheme.onTertiaryContainer
                ),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = totalTurism.toString(),
                        style = typography.displayMedium
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    Text(
                        text = stringResource(id = R.string.lblTurismCounts),
                        style = typography.bodyLarge
                    )
                }
            }
        } else {
            Spacer(Modifier.size(16.dp))

            Spacer(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(shapes.small)
                    .shimmerEffect()
            )

            Spacer(Modifier.size(16.dp))
        }

        Button(
            onClick = onBackPressed,
            shape = shapes.small,
            modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.btnBack)
            )
        }

        Spacer(Modifier.size(16.dp))
    }
}