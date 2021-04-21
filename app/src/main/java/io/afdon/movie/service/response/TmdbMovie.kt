package io.afdon.movie.service.response

import com.google.gson.annotations.SerializedName

data class TmdbMovie(
    @SerializedName("original_language") val originalLanguage: String?,
    @SerializedName("original_title") val originalTitle: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("video") val video: Boolean?,
    @SerializedName("vote_average") val voteAverage: Double?,
    @SerializedName("id") val id: Int?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("vote_count") val voteCount: Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("adult") val adult: Boolean?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("genre_ids") val genreIds: List<Int?>?,
    @SerializedName("popularity") val popularity: Double?,
    @SerializedName("media_type") val mediaType: String?
)