package io.afdon.movie.repository.local

import android.content.Context
import android.content.SharedPreferences
import io.afdon.movie.MovieApp

class PrefStorage : LocalRepository {

    companion object {
        private const val SP_API_KEY = "api_key"
    }

    private val sp: SharedPreferences = MovieApp.appContext.getSharedPreferences(MovieApp.appContext.packageName, Context.MODE_PRIVATE)

    override fun getTmdbApiKey(): String? {
        return sp.getString(SP_API_KEY, null)
    }

    override fun storeTmdbApiKey(apiKey: String?) {
        sp.edit().putString(SP_API_KEY, apiKey).apply()
    }
}