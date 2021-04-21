package io.afdon.movie.model

data class Movie(
    val originalLanguage: String?,
    val originalTitle: String?,
    val posterPath: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val id: Int?,
    val releaseDate: String?,
    val voteCount: Int?,
    val title: String?,
    val adult: Boolean?,
    val backdropPath: String?,
    val overview: String?,
    val genreIds: List<Int?>?,
    val popularity: Double?,
    val mediaType: String?
)