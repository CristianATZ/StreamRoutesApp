package net.streamroutes.sreamroutesapp.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.streamroutes.sreamroutesapp.core.data.local.entity.PlaceEntity
import net.streamroutes.sreamroutesapp.core.domain.model.Place

/**
 * Dao de la entidad Places o Lugar (PlaceEntity.kt)
 * Todos se aplican sobre la base de datos local de Room
 */
@Dao
interface PlaceDao {
    /**
     * Método usado para obtener un lugar específico usando
     * el id del lugar como parámetro
     */
    @Query("SELECT * FROM places WHERE idPlace = :idPlace")
    suspend fun getPlace(idPlace: String): PlaceEntity

    /**
     * Método usado para obtener el total de lugares coincidentes con
     * el id del lugar. Este método se usa para determinar si existe o no
     * X lugar en la base de datos de Room.
     */
    @Query("SELECT COUNT(*) FROM places WHERE idPlace = :idPlace")
    suspend fun existsPlace(idPlace: String): Int

    /**
     * Método usado para guardar un lugar en la base de datos.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlace(place: PlaceEntity)
}