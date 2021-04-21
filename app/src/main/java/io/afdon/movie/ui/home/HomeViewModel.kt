package io.afdon.movie.ui.home

import android.view.View
import androidx.lifecycle.*
import io.afdon.movie.model.Movie
import io.afdon.movie.repository.remote.RemoteRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel(state: SavedStateHandle) : ViewModel() {

    @Inject lateinit var remoteRepository: RemoteRepository

    private val _movies = MutableLiveData<List<MovieListAdapter.Item>>()
    val movies : LiveData<List<MovieListAdapter.Item>> = _movies

    private val _noDataVisibility = MutableLiveData(View.GONE)
    val noDataVisibility : LiveData<Int> = _noDataVisibility

    fun getTrendingMovies() {
        viewModelScope.launch {
            val result = remoteRepository.getTrendingMovies()
            if (result.isEmpty()) {
                _noDataVisibility.value = View.VISIBLE
            } else {
                _noDataVisibility.value = View.GONE
            }
            _movies.value = result.map { movie ->
                MovieListAdapter.Item(movie)
            }
        }
    }
}