package net.streamroutes.sreamroutesapp.features.maps.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.streamroutes.sreamroutesapp.core.data.repository.RouteRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapsModule {
    @Singleton
    @Provides
    fun provideRouteRepository(db: FirebaseFirestore) : RouteRepository {
        return RouteRepository(db)
    }
}