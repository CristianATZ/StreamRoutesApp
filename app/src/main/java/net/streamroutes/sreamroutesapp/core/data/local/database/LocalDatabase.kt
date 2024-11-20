package net.streamroutes.sreamroutesapp.core.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import net.streamroutes.sreamroutesapp.core.data.local.dao.PostDao
import net.streamroutes.sreamroutesapp.core.data.local.entity.PostEntity

@Database(entities = [PostEntity::class], version = 1)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}