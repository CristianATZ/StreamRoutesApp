package net.streamroutes.sreamroutesapp.core.data.repository

import android.util.Log
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import net.streamroutes.sreamroutesapp.core.data.local.dao.PostDao
import net.streamroutes.sreamroutesapp.core.data.local.dao.UserDao
import net.streamroutes.sreamroutesapp.core.data.local.entity.PostEntity
import net.streamroutes.sreamroutesapp.core.data.local.entity.UserEntity
import net.streamroutes.sreamroutesapp.core.domain.model.CommentWithInfo
import net.streamroutes.sreamroutesapp.core.domain.model.Post
import net.streamroutes.sreamroutesapp.core.domain.model.PostComment
import net.streamroutes.sreamroutesapp.core.domain.model.PostWithInfo
import net.streamroutes.sreamroutesapp.core.domain.model.User
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ForumRepository @Inject constructor(
    private val db: FirebaseFirestore,
    private val postDao: PostDao,
    private val userDao: UserDao
) {
    /**
     * Método usado para obtener todos los posts de todos los usuarios
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
            return posts
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
            println("Error: ${e.message}")
            emptyList()
        }
    }


    /**
     * Método usado para almacenar una publicación en Room
     */
    suspend fun savePost(post: PostWithInfo): Boolean {
        return try {
            // Verificar que no existe la publicacion en Room para guardarla
            val existsPost = postDao.existsPost(post.post.idPost)
            if(existsPost == 0){
                val postRoom = PostEntity(
                    idPost = post.post.idPost,
                    idUser = post.post.idUser,
                    date = post.post.date,
                    hour = post.post.hour,
                    description = post.post.description,
                    likes = post.post.likes,
                    totalComments = post.post.totalComments
                )
                postDao.insertPost(postRoom)
            }

            // Si ese usuario que emitio la publicación no existe en
            // la base de datos local de Room se debe de crear
            val existsUser = userDao.existsUser(post.post.idUser)
            if(existsUser == 0){
                val userRoom = UserEntity(
                    idUser = post.user.idUser,
                    names = post.user.names,
                    username = post.user.username,
                    lastName1 = post.user.lastName1,
                    lastName2 = post.user.lastName2,
                    email = post.user.email,
                    description = post.user.description,
                    phoneNumber = post.user.phoneNumber,
                    gender = post.user.gender,
                    address = post.user.address,
                    neighborhood = post.user.neighborhood,
                    numberAddress = post.user.numberAddress,
                    country = post.user.country,
                    state = post.user.state,
                    birthday = post.user.birthday,
                    createdAt = post.user.createdAt
                )
                userDao.insertUser(userRoom)
            }

            return true
        } catch (e: Exception) {
            println("Error: ${e.message}")
            return false
        }
    }

    /**
     * Método usado para obtener todas las publicaciones guardadas
     * en el dispositivo
     */
    suspend fun getAllPostsLocal(): List<PostWithInfo>{
        val posts = postDao.getAllPosts()
        val postsWithInfo = mutableListOf<PostWithInfo>()

        for(post in posts){
            val user = userDao.getUser(post.idUser)

            if(user != null){
                val postLocal = Post(
                    idPost = post.idPost,
                    idUser = post.idUser,
                    date = post.date,
                    hour = post.hour,
                    description = post.description,
                    likes = post.likes,
                    totalComments = post.totalComments
                )

                val userLocal = User(
                    idUser = user.idUser,
                    names = user.names,
                    username = user.username,
                    lastName1 = user.lastName1,
                    lastName2 = user.lastName2,
                    email = user.email,
                    description = user.description,
                    phoneNumber = user.phoneNumber,
                    gender = user.gender,
                    address = user.address,
                    neighborhood = user.neighborhood,
                    numberAddress = user.numberAddress,
                    country = user.country,
                    state = user.state,
                    birthday = user.birthday,
                    createdAt = user.createdAt
                )

                postsWithInfo.add(
                    PostWithInfo(
                        userLocal, postLocal
                    )
                )
            }

        }

        return postsWithInfo
    }

}


