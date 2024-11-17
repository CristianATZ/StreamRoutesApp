package net.streamroutes.sreamroutesapp.features.settings.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.streamroutes.sreamroutesapp.features.settings.data.SettingsRepositoryImp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {
    @Provides
    @Singleton
    fun provideUserPreferencesRepository(
        @ApplicationContext context: Context
    ): SettingsRepositoryImp {
        return SettingsRepositoryImp(context)
    }
}