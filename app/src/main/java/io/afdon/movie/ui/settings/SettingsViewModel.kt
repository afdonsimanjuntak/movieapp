package io.afdon.movie.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import io.afdon.movie.event.ToastEvent
import io.afdon.movie.repository.local.LocalRepository
import io.afdon.movie.utils.Constants
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

class SettingsViewModel(state: SavedStateHandle) : ViewModel() {

    @Inject lateinit var localRepository: LocalRepository

    val apiKey = state.getLiveData<String?>(Constants.STATE_API_KEY, null)

    fun setSavedApiKey() {
        localRepository.getTmdbApiKey()?.let { apiKey.value = it }
    }

    fun saveApiKey() : Boolean  {
        return if (apiKey.value.isNullOrBlank()) {
            EventBus.getDefault().post(ToastEvent("Api Key is empty"))
            false
        } else {
            localRepository.storeTmdbApiKey(apiKey.value)
            true
        }
    }
}