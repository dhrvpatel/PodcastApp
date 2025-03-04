package com.audiobooks.podcasts.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.audiobooks.podcasts.model.Podcast
import com.audiobooks.podcasts.ui.theme.PodcastsTheme

@Composable
fun PodcastDetailsScreen(podcast: Podcast) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = podcast.title, style = MaterialTheme.typography.headlineLarge)
        Text(text = podcast.publisher, style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = rememberAsyncImagePainter(podcast.image),
            contentDescription = podcast.title,
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = podcast.description, style = MaterialTheme.typography.bodyMedium)
    }
}


@Preview(showBackground = true)
@Composable
private fun PodcastDetailsScreenPreview() {
    PodcastsTheme {
        PodcastDetailsScreen(
            podcast = Podcast(
                title = "Example Podcast Title",
                description="The Ed Mylett Show showcases the greatest peak-performers across all industries in one place",
                id="abc",
                image="https://cdn-images-3.listennotes.com/podcasts/the-ed-mylett-show-ed-mylett-cumulus-guxpvEVnHTJ-PEUIT9RBhZD.1400x1400.jpg",
                publisher="Podcast Publisher"
            )
        )
    }
}
