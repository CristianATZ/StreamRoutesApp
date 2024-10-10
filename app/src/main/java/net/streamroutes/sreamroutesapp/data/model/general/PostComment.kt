package net.streamroutes.sreamroutesapp.data.model.general

data class PostComment(
    val idPost: String = "",        // ID del post donde se hizo el comentario
    val idUser: String = "",        // ID del usuario que comentó
    val description: String = "",   // Texto o descripción del comentario
    val date: String = "",          // Fecha
    val hour: String = ""           // Hora
)