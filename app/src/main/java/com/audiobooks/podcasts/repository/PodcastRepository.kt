package com.audiobooks.podcasts.repository

import com.audiobooks.podcasts.model.Podcast
import com.audiobooks.podcasts.network.ApiService
import com.audiobooks.podcasts.network.PodcastApi

class PodcastRepository(
    private val service: PodcastApi = ApiService.service
) {
    // Modify if needed
    suspend fun getPodcasts(): Result<List<Podcast>> {
        return try {
            Result.success(service.getBestPodcasts().podcasts)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}