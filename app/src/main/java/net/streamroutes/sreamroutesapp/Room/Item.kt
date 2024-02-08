package net.streamroutes.sreamroutesapp.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Items")
data class Item (
    @PrimaryKey
    val id: Int
)