package net.streamroutes.sreamroutesapp.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.streamroutes.sreamroutesapp.core.data.local.entity.RouteEntity

@Dao
interface RouteDao {
    @Query("SELECT * FROM routes ORDER BY name DESC")
    suspend fun getAllRoutes(): List<RouteEntity>

    @Query("SELECT * FROM routes WHERE idRoute = :idRoute")
    suspend fun getRoute(idRoute: String): RouteEntity

    @Query("SELECT COUNT(*) FROM routes WHERE idRoute = :idRoute")
    suspend fun existsRoute(idRoute: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoute(route: RouteEntity)
}