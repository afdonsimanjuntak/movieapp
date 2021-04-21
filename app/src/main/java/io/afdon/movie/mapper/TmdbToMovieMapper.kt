package io.afdon.movie.mapper

import io.afdon.movie.model.Movie
import io.afdon.movie.service.response.TmdbMovie

class TmdbToMovieMapper : Mapper<TmdbMovie, Movie> {

    override fun map(input: TmdbMovie?): Movie? {
        return input?.let {
            Movie(
                it.originalLanguage,
                it.originalTitle,
                it.posterPath,
                it.video,
                it.voteAverage,
                it.id,
                it.releaseDate,
                it.voteCount,
                it.title,
                it.adult,
                it.backdropPath,
                it.overview,
                it.genreIds,
                it.popularity,
                it.mediaType
            )
        }
    }
}