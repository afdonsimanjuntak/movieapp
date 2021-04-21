package io.afdon.movie.service

import io.afdon.movie.service.response.GetTrendingMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbService {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/"
    }

    @GET("/3/trending/movie/week")
    suspend fun getTrendingMovies(
        @Query("api_key") apiKey: String?
    ) : GetTrendingMoviesResponse
}