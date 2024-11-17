package net.streamroutes.sreamroutesapp.core.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import net.streamroutes.sreamroutesapp.core.domain.model.Post
import net.streamroutes.sreamroutesapp.core.domain.model.User
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ForumRepository @Inject constructor(
    private val db: FirebaseFirestore
) {
    /**
     * Método usado paa obtener todos los posts de todos los usuarios
     */
    suspend fun getAllPosts(): List<PostWithInfo> {
        return try {
            val posts = mutableListOf<PostWithInfo>()
            val postsDocs = db.collection("posts").get().await()

            for(document in postsDocs){
                val postObj = document.toObject(Post::class.java)
                if(postObj != null){
                    val userInfo = db.collection("users").document(postObj.idUser).get().await()
                    val userInfoObj = userInfo.toObject(User::class.java)

                    if(userInfoObj != null){
                        posts.add(
                            PostWithInfo(userInfoObj, postObj)
                        )
                    }
                }
            }
            Log.d("post_repository", posts.toString())
            return posts
        } catch (e: Exception) {
            Log.d("user_repo", "Error: ${e.message}")
            println("Error: ${e.message}")
            emptyList()
        }
    }


    /**
     * Método usado para crear un post
     */
    suspend fun createPost(post: Post): Boolean {
        return try {
            val newPost = db.collection("posts").document()
            newPost.set(post).await()
            true
        } catch (e: Exception) {
            Log.d("forum_repo", "Error: ${e.message}")
            println("Error: ${e.message}")
            false
        }
    }
}

data class PostWithInfo(
    val user: User,
    val post: Post
)