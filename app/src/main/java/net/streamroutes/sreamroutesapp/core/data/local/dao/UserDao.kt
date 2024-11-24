package net.streamroutes.sreamroutesapp.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.streamroutes.sreamroutesapp.core.data.local.entity.UserEntity

@Dao
interface UserDao {
    /**
     * Método usado para obtener un usuario específico usando
     * el id del usuario como parámetro
     */
    @Query("SELECT * FROM users WHERE idUser = :idUser")
    suspend fun getUser(idUser: String): UserEntity

    /**
     * Método usado para obtener el total de usuarios existentes
     * con el id de usuario. Este método se usa para determinar
     * si existe o no X usuario en la base de datos de Room
     */
    @Query("SELECT COUNT(*) FROM users WHERE idUser = :idUser")
    suspend fun existsUser(idUser: String): Int

    /**
     * Método usado para guardar un usuario en la base de datos
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)
}