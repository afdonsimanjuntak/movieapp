package io.afdon.movie.repository.remote

import io.afdon.movie.model.Movie

interface RemoteRepository {

    suspend fun getTrendingMovies() : List<Movie>
}