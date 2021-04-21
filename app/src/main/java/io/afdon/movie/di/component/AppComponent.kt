package io.afdon.movie.di.component

import dagger.Component
import io.afdon.movie.di.module.RepositoryModule
import io.afdon.movie.di.module.RetrofitModule
import io.afdon.movie.ui.home.HomeViewModel
import io.afdon.movie.ui.settings.SettingsViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [
    RepositoryModule::class,
    RetrofitModule::class
])
interface AppComponent {

    fun inject(homeViewModel: HomeViewModel)

    fun inject(settingsViewModel: SettingsViewModel)
}