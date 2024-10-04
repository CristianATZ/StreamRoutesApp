package net.streamroutes.sreamroutesapp.core.domain.model

import java.time.LocalDateTime

// Data class para representar una publicación
data class Post(
    val postId: String,               // Id de la publicación
    val authorName: String,           // Nombre de quien publicó
    val publicationDate: LocalDateTime,// Fecha de publicación
    val description: String,           // Descripción de la publicación
    val likes: Int = 0,               // Número de "me gusta" (0 o N)
    val comments: List<Comment> = emptyList() // Lista de comentarios (0 o N)
)