package net.streamroutes.sreamroutesapp.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.streamroutes.sreamroutesapp.core.data.local.dao.PlaceDao
import net.streamroutes.sreamroutesapp.core.data.local.dao.PostDao
import net.streamroutes.sreamroutesapp.core.data.local.dao.RouteDao
import net.streamroutes.sreamroutesapp.core.data.local.dao.UserDao
import net.streamroutes.sreamroutesapp.core.data.local.database.LocalDatabase
import net.streamroutes.sreamroutesapp.core.domain.network.OpenRouteServiceApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.openrouteservice.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOpenRouteServiceApi(retrofit: Retrofit): OpenRouteServiceApi {
        return retrofit.create(OpenRouteServiceApi::class.java)
    }


    /**
     * Inyección de dependencias de room
     */
    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext context: Context): LocalDatabase {
        return Room.databaseBuilder(
            context,
            LocalDatabase::class.java,
            "local_database"
        ).build()
    }

    @Provides
    fun providePostDao(database: LocalDatabase): PostDao {
        return database.postDao()
    }

    @Provides
    fun provideUserDao(database: LocalDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    fun provideRouteDao(database: LocalDatabase): RouteDao {
        return database.routeDao()
    }

    @Provides
    fun providePlaceDao(database: LocalDatabase): PlaceDao {
        return database.placeDao()
    }
}