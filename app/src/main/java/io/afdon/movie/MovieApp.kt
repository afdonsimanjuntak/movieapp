package io.afdon.movie

import android.app.Application
import android.content.Context
import io.afdon.movie.di.component.AppComponent
import io.afdon.movie.di.component.DaggerAppComponent

class MovieApp : Application() {

    companion object {

        lateinit var INSTANCE: MovieApp private set

        val appContext: Context get() { return INSTANCE.applicationContext }
    }

    init {
        INSTANCE = this
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.create()
    }
}