package net.streamroutes.sreamroutesapp.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.streamroutes.sreamroutesapp.core.data.local.entity.PostEntity

@Dao
interface PostDao {
    @Query("SELECT * FROM posts ORDER BY date DESC, hour DESC")
    suspend fun getAllPosts(): List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post: PostEntity)
}