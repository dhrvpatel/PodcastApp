package com.audiobooks.podcasts.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.audiobooks.podcasts.model.Podcast
import com.audiobooks.podcasts.ui.theme.PodcastsTheme
import com.audiobooks.podcasts.viewmodel.PodcastListViewModel

@Composable
fun PodcastListScreen(onShowDetails: (podcast: Podcast) -> Unit) {
    val viewModel: PodcastListViewModel = viewModel()
    val podcasts = viewModel.podcasts.value
    val errorMessage = viewModel.errorMessage.value

    PodcastListUI(
        podcasts = podcasts,
        onShowDetails = onShowDetails,
        errorMessage = errorMessage
    )
}

@Composable
private fun PodcastListUI(
    podcasts: List<Podcast>,
    onShowDetails: (podcast: Podcast) -> Unit,
    errorMessage: String?
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Podcasts",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp).padding(horizontal = 16.dp)
        )

        when {
            errorMessage != null -> {
                Text(
                    text = "Error: $errorMessage",
                    color = MaterialTheme.colorScheme.error
                )
            }
            podcasts.isEmpty() -> {
                CircularProgressIndicator(
                    modifier = Modifier.fillMaxSize()
                )
            }
            else -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(podcasts) { podcast ->
                        PodcastListItem(podcast = podcast, onShowDetails = onShowDetails)
                    }
                }
            }
        }
    }
}

@Composable
private fun PodcastListItem(
    podcast: Podcast,
    onShowDetails: (Podcast) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onShowDetails(podcast) }
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).padding(bottom = 8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(podcast.image),
                contentDescription = podcast.title,
                modifier = Modifier.size(64.dp).clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = podcast.title, style = MaterialTheme.typography.titleMedium)
                Text(text = podcast.publisher, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
        }
        HorizontalDivider(Modifier.padding(horizontal = 16.dp), thickness = 1.dp, color = Color.LightGray)
    }
}


@Preview(showBackground = true)
@Composable
private fun PodcastListUIPreview() {
    PodcastsTheme {
        PodcastListUI(
            podcasts = listOf(
                Podcast(
                    title = "Example Podcast Title",
                    description = "Sample Description",
                    id = "abc",
                    image = "https://cdn-images-3.listennotes.com/podcasts/the-ed-mylett-show-ed-mylett-cumulus-guxpvEVnHTJ-PEUIT9RBhZD.1400x1400.jpg",
                    publisher = "Podcast Publisher"
                )
            ),
            onShowDetails = {},
            errorMessage = null
        )
    }
}
