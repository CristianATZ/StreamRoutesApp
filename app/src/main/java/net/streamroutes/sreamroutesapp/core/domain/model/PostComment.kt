package net.streamroutes.sreamroutesapp.core.domain.model

/**
 * Modelo usado para los comentarios de un post
 */
data class PostComment(
    var idPost: String = "",        // ID del post donde se hizo el comentario
    var idUser: String = "",        // ID del usuario que comentó
    val description: String = "",   // Texto o descripción del comentario
    val date: String = "",          // Fecha
    val hour: String = ""           // Hora
)