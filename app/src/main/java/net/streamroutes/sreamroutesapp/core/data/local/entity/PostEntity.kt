package net.streamroutes.sreamroutesapp.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey val idPost: String,
    val idUser: String,
    val description: String,
    val date: String,
    val hour: String,
    val totalComments: Int,
    val likes: Int
)

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val idUser: String,
    val name: String,
    val email: String
)