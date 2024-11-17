package net.streamroutes.sreamroutesapp.features.settings.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.streamroutes.sreamroutesapp.features.settings.data.ApparenceRepositoryImp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {
    @Provides
    @Singleton
    fun provideApparencePreferencesRepository(
        @ApplicationContext context: Context
    ): ApparenceRepositoryImp {
        return ApparenceRepositoryImp(context)
    }
}