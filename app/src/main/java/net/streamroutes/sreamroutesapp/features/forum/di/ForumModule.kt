package net.streamroutes.sreamroutesapp.features.forum.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.streamroutes.sreamroutesapp.core.data.repository.ForumRepository
import net.streamroutes.sreamroutesapp.core.data.repository.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ForumModule {
    @Singleton
    @Provides
    fun provideForumRepository(db: FirebaseFirestore) : ForumRepository {
        return ForumRepository(db)
    }
}