package net.streamroutes.sreamroutesapp.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.streamroutes.sreamroutesapp.core.data.local.entity.RouteEntity

/**
 * Dao de la entidad Route o Ruta (RouteEntity.kt)
 * Todos se aplican sobre la base de datos local de Room
 */

@Dao
interface RouteDao {
    /**
     * Método usado para obtener todas las rutas de transporte
     * público de la base de datos de Room
     */
    @Query("SELECT * FROM routes ORDER BY name DESC")
    suspend fun getAllRoutes(): List<RouteEntity>

    /**
     * Método usado para obtener una ruta e transporte público específica
     * usando el id de la ruta como parámetro
     */
    @Query("SELECT * FROM routes WHERE idRoute = :idRoute")
    suspend fun getRoute(idRoute: String): RouteEntity

    /**
     * Método usado para obtener el total de rutas coincidentes con
     * el id de ruta mandado como parámetro. Este método se usa para
     * determinar si existe o no X ruta en la base de datos de Room
     */
    @Query("SELECT COUNT(*) FROM routes WHERE idRoute = :idRoute")
    suspend fun existsRoute(idRoute: String): Int

    /**
     * Método usado para guardar una ruta en la base de datos
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoute(route: RouteEntity)
}