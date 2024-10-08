package net.streamroutes.sreamroutesapp.features.premium.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.orange
import com.example.compose.primary
import com.example.compose.yellow
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.authentication.components.PrimaryFilledButton
import net.streamroutes.sreamroutesapp.features.premium.presentation.Plan

@Preview
@Composable
fun PremiumPackage(
    plan: Plan = Plan(
        title = "Paquete",
        time = "7 dias gratis",
        description = "Despues paga $20 por mes",
        action = {}
    ),
    isFreeTrial: Boolean = false
) {

    val color = if(isFreeTrial) {
        Brush.verticalGradient(listOf(orange, yellow))
    } else {
        Brush.verticalGradient(listOf(colorScheme.surfaceContainerHighest, colorScheme.surfaceContainerHighest))
    }

    val textColor = if(isFreeTrial) primary else colorScheme.onSurface

    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(brush = color, shapes.medium)
            .fillMaxHeight()
            .width(300.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(32.dp))

        Text(
            text = plan.title,
            style = typography.displayLarge,
            color = textColor,
            modifier = Modifier.fillMaxWidth(0.9f)
        )

        Text(
            text = plan.time,
            style = typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = textColor,
            modifier = Modifier.fillMaxWidth(0.9f)
        )

        Text(
            text = plan.description ?: "",
            style = typography.bodyMedium,
            color = textColor,
            modifier = Modifier.fillMaxWidth(0.9f)
        )

        Spacer(modifier = Modifier.weight(1f))

        // contratar

        if(isFreeTrial) {
            PrimaryFilledButton(
                text = stringResource(id = R.string.btnSubscribe),
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth(0.9f)
            )
        } else {
            Button(
                onClick = plan.action,
                shape = shapes.small,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.9f)
            ) {
                Text(text = stringResource(id = R.string.btnSubscribe))
            }
        }
    }
}