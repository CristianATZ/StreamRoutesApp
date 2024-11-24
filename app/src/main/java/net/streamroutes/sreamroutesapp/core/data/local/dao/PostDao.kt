package net.streamroutes.sreamroutesapp.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.streamroutes.sreamroutesapp.core.data.local.entity.PostEntity

/**
 * Dao de la entidad Posts o Publicaciones (PostEntity.kt)
 * Todos se aplican sobre la base de datos local de Room
 */

@Dao
interface PostDao {
    /**
     * Método usado para obtener todas las publicaciones de la
     * base de datos de Room
     */
    @Query("SELECT * FROM posts ORDER BY date DESC, hour DESC")
    suspend fun getAllPosts(): List<PostEntity>

    /**
     * Método usado para obtener un post específico usando
     * el id del post como parámetro
     */
    @Query("SELECT * FROM posts WHERE idPost = :idPost")
    suspend fun getPost(idPost: String): PostEntity?

    /**
     * Método usado para obtener el total de posts coincidentes con
     * el id del post. Este método se usa para determinar si exsite o no
     * X post en la base de datos de Room.
     */
    @Query("SELECT COUNT(*) FROM posts WHERE idPost = :idPost")
    suspend fun existsPost(idPost: String): Int

    /**
     * Método usado para guardar un post en la base de datos
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post: PostEntity)
}