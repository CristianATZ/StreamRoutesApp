package net.streamroutes.sreamroutesapp.features.profile.components

import android.app.DatePickerDialog
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar

@Composable
fun TrailingIconWithCalendar(
    iconDescription: String = "",
    onDateSelected: (String) -> Unit // El formato de la fecha seleccionada será enviado a esta lambda
) {
    // Estado para controlar la fecha seleccionada
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    // Estado para mostrar el diálogo de selección de fecha
    var showDatePicker by remember { mutableStateOf(false) }

    if (showDatePicker) {
        // Mostrar el DatePickerDialog
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${month + 1}/$year" // Formato DD/MM/YYYY
                onDateSelected(selectedDate) // Enviar la fecha seleccionada
                showDatePicker = false // Cerrar el diálogo
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    // El ícono del calendario que abre el diálogo
    IconButton(onClick = { showDatePicker = true }) {
        Icon(
            imageVector = Icons.Outlined.CalendarMonth, // Ícono de calendario
            contentDescription = iconDescription
        )
    }
}
