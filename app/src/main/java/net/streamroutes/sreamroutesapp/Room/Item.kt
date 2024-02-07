package net.streamroutes.sreamroutesapp.Room

import androidx.room.PrimaryKey
import com.google.android.filament.Entity

@Entity(tablename = "Items")
data class Item (
    @PrimaryKey
    val id: Int
)