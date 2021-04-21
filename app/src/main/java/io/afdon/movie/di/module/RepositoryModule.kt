package io.afdon.movie.di.module

import dagger.Module
import dagger.Provides
import io.afdon.movie.repository.local.LocalRepository
import io.afdon.movie.repository.local.PrefStorage
import io.afdon.movie.repository.remote.RemoteRepository
import io.afdon.movie.repository.remote.TmdbApi
import io.afdon.movie.service.TmdbService
import javax.inject.Singleton

@Module
object RepositoryModule {

    @Singleton
    @JvmStatic
    @Provides
    fun provideLocalRepository() : LocalRepository {
        return PrefStorage()
    }

    @Singleton
    @JvmStatic
    @Provides
    fun provideRemoteRepository(tmdbService: TmdbService, localRepository: LocalRepository) : RemoteRepository {
        return TmdbApi(tmdbService, localRepository)
    }
}