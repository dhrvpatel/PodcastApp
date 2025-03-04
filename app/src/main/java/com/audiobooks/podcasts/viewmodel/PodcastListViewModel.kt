package com.audiobooks.podcasts.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.audiobooks.podcasts.model.Podcast
import com.audiobooks.podcasts.repository.PodcastRepository
import androidx.compose.runtime.State
import kotlinx.coroutines.launch

class PodcastListViewModel : ViewModel() {
    private val repository = PodcastRepository()

    private val _podcasts = mutableStateOf<List<Podcast>>(emptyList())
    val podcasts: State<List<Podcast>> get() = _podcasts

    init {
        fetchPodcasts()
    }

    private fun fetchPodcasts() {
        viewModelScope.launch {
            try {
                _podcasts.value = repository.getPodcasts().getOrThrow()
            } catch (e: Exception) {
                Log.e("PodcastListViewModel", "Error: ${e.message}", e)
            }
        }
    }
}
