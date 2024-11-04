package net.streamroutes.sreamroutesapp.core.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import net.streamroutes.sreamroutesapp.core.domain.model.User

class UserRepository(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {

    /**
     * Método usado para dar de alta a un usuario
     */
    suspend fun signUpUser(
        user: User,
    ): Result<User> {
        return try {
            val result = auth.createUserWithEmailAndPassword(user.email, user.password).await()

            result.user?.let { firebaseUser ->  
                val userId = firebaseUser.uid

                firestore.collection("users").document(userId)
                    .set(user)
                    .await()

                Result.success(user)
            } ?: Result.failure(Exception("No se pudo registrar el usuario"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    /**
     * Método usaro para auenticar a un usuario mediante correo y contraseña
     */
    suspend fun loginUser(
        user: User
    ): Result<FirebaseUser?> {
        return try {
            val result = auth.signInWithEmailAndPassword(user.email, user.password).await()
            Result.success(result.user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    /**
     * Método usado para cerrar sesión de un usuario
     */
    fun signOut(){
        auth.signOut()
    }


    /**
     * Método usado para obtener la información del usuario autenticado (como modelo FirebasUser)
     */
    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }


    /**
     * Método usado para obtener la información del usuario autenticado (como modelo User)
     */
    suspend fun getUserData(uid: String): Result<User> {
        return try {
            val snapshot = firestore.collection("users").document(uid).get().await()
            val user = snapshot.toObject(User::class.java)
            user?.let {
                Result.success(it)
            } ?: Result.failure(Exception("No se pudo obtener información del usuario"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    /**
     * Método usado para editar la información del usuario autenticado
     */
    suspend fun updateUserData(uid: String, updatedUser: User): Result<Unit> {
        return try {
            firestore.collection("users").document(uid)
                .set(updatedUser)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Log.d("UPDATE", e.toString())
            Result.failure(e)
        }
    }


}