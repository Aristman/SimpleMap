package ru.marslab.simplemap.feature.markers.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.marslab.simplemap.common.AppDataStorage
import ru.marslab.simplemap.feature.markers.data.MarkersDataRepository
import ru.marslab.simplemap.feature.markers.domain.MarkersRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MarkersModule {

    @Provides
    @Singleton
    fun provideMarkersRepository(appDataStorage: AppDataStorage): MarkersRepository =
        MarkersDataRepository(appDataStorage)
}
