@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package net.streamroutes.sreamroutesapp.ui.routes_screens.menu.help

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.utils.MyViewModel

@Composable
fun CommentScreen(myViewModel: MyViewModel = MyViewModel()){

    var comment by remember { mutableStateOf("") }
    val checkedState = remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(15.dp))

        OutlinedTextField(
            value = comment,
            onValueChange = { comment = it },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.None
            ),
            placeholder = {
                Text(
                    text = myViewModel.languageType().get(262),
                    style = typography.labelLarge
                )
            },
            singleLine = false,
            modifier = Modifier
                .height(350.dp)
                .fillMaxWidth(0.9f)
        )

        // SUGERENCIA Y CHECKBOX
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
                .clickable { checkedState.value = !checkedState.value },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Text(
                text = myViewModel.languageType().get(263) + ". " + myViewModel.languageType().get(264),
                style = typography.bodyLarge
            )

            Checkbox(
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it }
            )
        }

        // boton y subtitulo
        Button(
            onClick = {

            },
            shape = RoundedCornerShape(16),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.onTertiary
            ),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(top = 16.dp)
                .height(50.dp)
        ) {
            Text(
                text = myViewModel.languageType()[369],
                style = typography.bodyLarge
            )
        }
    }
}