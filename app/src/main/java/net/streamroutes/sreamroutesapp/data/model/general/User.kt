package net.streamroutes.sreamroutesapp.data.model.general

data class User(
    val names: String = "",         // Nombres
    val lastName1: String = "",     // Apellido paterno
    val lastName2: String = "",     // Apellido materno
    val email: String = "",         // Correo electrónico
    val password: String = "",      // Contraseña
    val description: String = "",   // Descripción
    val phoneNumber: String = "",   // Número de teléfono
    val gender: String = "",        // Género
    val address: String = "",       // Dirección
    val country: String = "",       // País
    val state: String = "",         // Estado
    val birthday: String = ""       // Fecha de Nacimiento
)