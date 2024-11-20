package net.streamroutes.sreamroutesapp.features.forum.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.streamroutes.sreamroutesapp.core.data.local.dao.PostDao
import net.streamroutes.sreamroutesapp.core.data.local.database.LocalDatabase
import net.streamroutes.sreamroutesapp.core.data.repository.ForumRepository
import net.streamroutes.sreamroutesapp.core.data.repository.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ForumModule {
    @Singleton
    @Provides
    fun provideForumRepository(db: FirebaseFirestore, postDao: PostDao) : ForumRepository {
        return ForumRepository(db, postDao)
    }

}