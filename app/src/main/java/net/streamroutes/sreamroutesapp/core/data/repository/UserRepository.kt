package net.streamroutes.sreamroutesapp.core.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import net.streamroutes.sreamroutesapp.core.domain.model.User
import net.streamroutes.sreamroutesapp.utils.DateUtils
import net.streamroutes.sreamroutesapp.utils.DateUtils.getCurrentDate

class UserRepository(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) {

    /**
     * Método usado para dar de alta a un usuario
     */
    suspend fun signUpUser(
        username: String,
        email: String,
        password: String,
    ): Result<User> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()

            result.user?.let { firebaseUser ->  
                val userId = firebaseUser.uid

                val user = User(
                    username = username,
                    email = email,
                    createdAt = getCurrentDate()
                )

                db.collection("users").document(userId)
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
        email: String,
        password: String
    ): Result<FirebaseUser?> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
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
            val snapshot = db.collection("users").document(uid).get().await()
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
    suspend fun updateUserData(uid: String, updatedFields: Map<String, Any>): Result<Unit> {
        return try {
            db.collection("users").document(uid)
                .update(updatedFields)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    /**
     * Método usado para cambiar solo la contraseña del usuario en firebase auth
     */
    suspend fun updateUserPassword(newPass: String): Result<Unit> {
        return try {
            val user = auth.currentUser
            user?.let {
                it.updatePassword(newPass).await()
                Result.success(Unit)
            } ?: Result.failure(Exception("Usuario no autenticado"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    /**
     * Método usado para enviar el correo de restablecimiento de contraseña de una cuenta
     */
    suspend fun resetPassword(email: String): Result<Unit> {
        return try {
            auth.sendPasswordResetEmail(email).await()
            Result.success(Unit)
        } catch (e: Exception){
            Result.failure(e)
        }
    }


}