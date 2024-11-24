package net.streamroutes.sreamroutesapp.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad de Room usada para las publicaciones del foro
 * Similar al modelo Post.kt
 */
@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey val idPost: String = "",
    val idUser: String = "",
    val date: String = "",
    val hour: String = "",
    val description: String = "",
    val likes: Int = 0,
    val totalComments: Int = 0
)

