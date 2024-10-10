package net.streamroutes.sreamroutesapp.data.model.general

data class Post(
    val idUser: String = "",        // ID del usuario que posteo
    val date: String = "",          // Fecha
    val hour: String = "",          // Hora
    val description: String = "",   // Texto o descripción del post
    val likes: Int = 0,             // Cantidad de likes
    val totalComments: Int = 0      // Cantidad de comentarios
)