package net.streamroutes.sreamroutesapp.features.parkingApp.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.streamroutes.sreamroutesapp.core.data.repository.ParkingRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ParkingModule {
    @Singleton
    @Provides
    fun provideParkingRepository(db: FirebaseFirestore) : ParkingRepository {
        return ParkingRepository(db)
    }
}