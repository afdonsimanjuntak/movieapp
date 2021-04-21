package io.afdon.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MainViewModel(state: SavedStateHandle) : ViewModel() {

    companion object {
        private const val STATE_API_KEY = "api_key"
    }

    private val _apiKey = state.getLiveData<String?>(STATE_API_KEY, null)
    val apiKey : LiveData<String?> = _apiKey

}