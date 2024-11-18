package net.streamroutes.sreamroutesapp.core.data.repository

import android.util.Log
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import net.streamroutes.sreamroutesapp.core.domain.model.Post
import net.streamroutes.sreamroutesapp.core.domain.model.PostComment
import net.streamroutes.sreamroutesapp.core.domain.model.User
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
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

            //Log.d("post_repository", posts.toString())
            return posts.sortedByDescending {
                LocalDateTime.of(LocalDate.parse(it.post.date), LocalTime.parse(it.post.hour))
            }
        } catch (e: Exception) {
            //Log.d("user_repo", "Error: ${e.message}")
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
            post.idPost = newPost.id
            newPost.set(post).await()
            true
        } catch (e: Exception) {
            //Log.d("forum_repo", "Error: ${e.message}")
            println("Error: ${e.message}")
            false
        }
    }


    /**
     * Método usado para crea un comentario
     */
    suspend fun createComment(comment: PostComment): Boolean {
        return try {
            val newComment = db.collection("postComments").document()
            newComment.set(comment).await()
            // Incrementar comentarios en 1 al crear un comentario
            val post = db.collection("posts").document(comment.idPost)
            post.update("totalComments", FieldValue.increment(1)).await()
            true
        } catch (e: Exception) {
            //Log.d("forum_repo", "Error: ${e.message}")
            println("Error: ${e.message}")
            false
        }
    }


    /**
     * Método usado para obtener los comentarios de un post
     */
    suspend fun getCommentsByPost(idPost: String): List<CommentWithInfo> {
        return try {
            val comments = mutableListOf<CommentWithInfo>()

            val commentsDocs = db.collection("postComments")
                .whereEqualTo("idPost", idPost)
                .get()
                .await()

            for(document in commentsDocs){
                val commentObj = document.toObject(PostComment::class.java)
                if(commentObj != null){
                    val userInfo = db.collection("users").document(commentObj.idUser).get().await()
                    val userInfoObj = userInfo.toObject(User::class.java)

                    if(userInfoObj != null){
                        comments.add(
                            CommentWithInfo(userInfoObj, commentObj)
                        )
                    }
                }
            }

            return comments
        } catch (e: Exception) {
            //Log.d("user_repo", "Error: ${e.message}")
            println("Error: ${e.message}")
            emptyList()
        }
    }

}

data class PostWithInfo(
    val user: User,
    val post: Post
)

data class CommentWithInfo(
    val user: User,
    val comment: PostComment
)