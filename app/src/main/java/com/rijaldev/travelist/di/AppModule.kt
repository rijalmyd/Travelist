package com.rijaldev.travelist.di

import com.rijaldev.travelist.data.TourismRepository
import com.rijaldev.travelist.data.TourismRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideTourismRepository(tourismRepositoryImpl: TourismRepositoryImpl): TourismRepository
}