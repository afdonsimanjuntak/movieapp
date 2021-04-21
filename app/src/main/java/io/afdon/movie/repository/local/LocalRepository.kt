package io.afdon.movie.repository.local

interface LocalRepository {

    fun getTmdbApiKey() : String?

    fun storeTmdbApiKey(apiKey: String?)
}