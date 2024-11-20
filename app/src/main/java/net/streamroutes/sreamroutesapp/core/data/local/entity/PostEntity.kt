package net.streamroutes.sreamroutesapp.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

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

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val idUser: String,
    val name: String,
    val email: String
)