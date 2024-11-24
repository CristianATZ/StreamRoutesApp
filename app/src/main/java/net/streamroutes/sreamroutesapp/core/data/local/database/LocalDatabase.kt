package net.streamroutes.sreamroutesapp.core.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import net.streamroutes.sreamroutesapp.core.data.local.dao.PlaceDao
import net.streamroutes.sreamroutesapp.core.data.local.dao.PostDao
import net.streamroutes.sreamroutesapp.core.data.local.dao.RouteDao
import net.streamroutes.sreamroutesapp.core.data.local.dao.UserDao
import net.streamroutes.sreamroutesapp.core.data.local.entity.PlaceEntity
import net.streamroutes.sreamroutesapp.core.data.local.entity.PostEntity
import net.streamroutes.sreamroutesapp.core.data.local.entity.RouteEntity
import net.streamroutes.sreamroutesapp.core.data.local.entity.UserEntity

/**
 * Definir la base de datos local con Room
 * Se le envían las siguientes entities:
 * - PostEntity para los posts o publicaciones
 * - UserEntity para los usuarios
 * - RouteEntity para las rutas de transporte público
 * - PlaceEntity para los lugares
 */
@Database(entities = [PostEntity::class, UserEntity::class, RouteEntity::class, PlaceEntity::class], version = 1)
abstract class LocalDatabase : RoomDatabase() {
    /**
     * Definición de los daos de cada entidad
     */
    abstract fun postDao(): PostDao
    abstract fun userDao(): UserDao
    abstract fun routeDao(): RouteDao
    abstract fun placeDao(): PlaceDao
}