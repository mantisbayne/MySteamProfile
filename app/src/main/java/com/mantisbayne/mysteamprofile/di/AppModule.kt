package com.mantisbayne.mysteamprofile.di

import com.mantisbayne.mysteamprofile.domain.repository.SteamOwnedGamesRepository
import com.mantisbayne.mysteamprofile.domain.usecase.GetOwnedGamesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSteamOwnedGamesRepository(): SteamOwnedGamesRepository {
        return SteamOwnedGamesRepository()
    }

    @Provides
    @Singleton
    fun provideGetOwnedGamesUseCase(repository: SteamOwnedGamesRepository): GetOwnedGamesUseCase {
        return GetOwnedGamesUseCase(repository)
    }
}
