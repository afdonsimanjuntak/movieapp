package io.afdon.movie.repository.remote

import io.afdon.movie.mapper.ListMapperImpl
import io.afdon.movie.mapper.TmdbToMovieMapper
import io.afdon.movie.model.Movie
import io.afdon.movie.repository.local.LocalRepository
import io.afdon.movie.service.TmdbService

class TmdbApi(private val tmdbService: TmdbService, private val localRepository: LocalRepository) : RemoteRepository {

    override suspend fun getTrendingMovies() : List<Movie> {
        return try {
            val response = tmdbService.getTrendingMovies(localRepository.getTmdbApiKey())
            val mapper = ListMapperImpl(TmdbToMovieMapper())
            return mapper.map(response.tmdbMovies)
        } catch (e: Exception) {
            arrayListOf()
        }
    }
}