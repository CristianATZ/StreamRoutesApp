package net.streamroutes.sreamroutesapp.core.domain.model

import java.time.LocalDateTime

// Data class para representar un comentario
data class Comment(
    //val commentId: String,
    //val postId: String,
    val commenterName: String,      // Nombre de quien comentó
    val commentDate: LocalDateTime, // Fecha de publicación del comentario
    val description: String          // Descripción del comentario
)