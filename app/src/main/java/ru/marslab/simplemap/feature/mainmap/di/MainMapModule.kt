package ru.marslab.simplemap.feature.mainmap.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.marslab.simplemap.App
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MainMapModule {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = App.dataStoreName)

    @Provides
    @Singleton
    fun getDataStorePreferences(@ApplicationContext context: Context): DataStore<Preferences> =
        context.dataStore
}
