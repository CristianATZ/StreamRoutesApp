@file:OptIn(ExperimentalMaterial3Api::class)

package net.streamroutes.sreamroutesapp.ui.routes_screens.menu.help

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen(myViewModel: MyViewModel = MyViewModel()){
    var nombre by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(16.dp))

        // nombre
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            placeholder = {
                Text(
                    text = myViewModel.languageType()[371],
                    style = typography.labelLarge
                )
            },
            singleLine = false,
            modifier = Modifier
                .fillMaxWidth(0.9f)
        )

        Spacer(modifier = Modifier.size(16.dp))

        // correo
        OutlinedTextField(
            value = "streamroutes2.0@gmail.com",
            onValueChange = {},
            enabled = false,
            modifier = Modifier
                .fillMaxWidth(0.9f)
        )

        Spacer(modifier = Modifier.size(16.dp))

        // mensjae
        OutlinedTextField(
            value = mensaje,
            onValueChange = { mensaje = it },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            placeholder = {
                Text(
                    text = myViewModel.languageType()[372],
                    style = MaterialTheme.typography.labelLarge
                )
            },
            singleLine = false,
            modifier = Modifier
                .height(350.dp)
                .fillMaxWidth(0.9f)
        )

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
                text = myViewModel.languageType()[373],
                style = typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        TextButton(
            onClick = { }
        ){
            Text(
                text = myViewModel.languageType().get(265) + " " + myViewModel.languageType().get(266),
                style = typography.titleMedium
            )
        }
    }
}