package net.streamroutes.sreamroutesapp.core.domain.model

data class User(
    var idUser: String = "",
    val names: String = "",         // Nombres
    val username: String = "",      // Nombre de usuario
    val lastName1: String = "",     // Apellido paterno
    val lastName2: String = "",     // Apellido materno
    val email: String = "",         // Correo electrónico
    val description: String = "",   // Descripción
    val phoneNumber: String = "",   // Número de teléfono
    val gender: String = "",        // Género
    val address: String = "",       // Dirección
    val neighborhood: String = "",  // Colonia
    val numberAddress: String = "", // No. Exterior
    val country: String = "",       // País
    val state: String = "",         // Estado
    val birthday: String = "",      // Fecha de Nacimiento
    val createdAt: String = ""      // Fecha de creación del usuario
)