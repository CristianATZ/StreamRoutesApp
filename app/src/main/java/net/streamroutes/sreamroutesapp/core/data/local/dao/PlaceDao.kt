package net.streamroutes.sreamroutesapp.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.streamroutes.sreamroutesapp.core.data.local.entity.PlaceEntity
import net.streamroutes.sreamroutesapp.core.domain.model.Place

@Dao
interface PlaceDao {
    @Query("SELECT * FROM places ORDER BY name DESC")
    suspend fun getAllPlaces(): List<PlaceEntity>

    @Query("SELECT * FROM places WHERE idPlace = :idPlace")
    suspend fun getPlace(idPlace: String): PlaceEntity

    @Query("SELECT COUNT(*) FROM places WHERE idPlace = :idPlace")
    suspend fun existsPlace(idPlace: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlace(place: PlaceEntity)
}