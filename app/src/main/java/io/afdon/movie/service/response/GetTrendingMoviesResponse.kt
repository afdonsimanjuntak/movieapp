package io.afdon.movie.service.response

import com.google.gson.annotations.SerializedName

data class GetTrendingMoviesResponse(
    @SerializedName("page") val page: Int?,
    @SerializedName("results") val tmdbMovies: List<TmdbMovie?>?,
    @SerializedName("total_pages") val totalPages: Int?,
    @SerializedName("total_results") val totalResults: Int?
)