package com.audiobooks.podcasts.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.audiobooks.podcasts.model.Podcast
import com.audiobooks.podcasts.ui.theme.PodcastsTheme
import com.audiobooks.podcasts.viewmodel.PodcastListViewModel
import kotlinx.coroutines.delay

@Composable
fun PodcastListScreen(onShowDetails: (podcast: Podcast) -> Unit) {
    val viewModel: PodcastListViewModel = viewModel()
    val podcasts = viewModel.podcasts.value

    PodcastListUI(
        podcasts = podcasts,
        onShowDetails = onShowDetails
    )
}

@Composable
private fun PodcastListUI(
    podcasts: List<Podcast>,
    onShowDetails: (podcast: Podcast) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(
            text = "Podcasts",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )

        when {
            podcasts.isEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
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
    var isClicked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 8.dp)
                .clickable {
                    if (!isClicked) {
                        isClicked = true
                        onShowDetails(podcast)
                    }
                }
        ) {
            Image(
                painter = rememberAsyncImagePainter(podcast.image),
                contentDescription = podcast.title,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = podcast.title, style = MaterialTheme.typography.titleMedium)
                Text(text = podcast.publisher, style = MaterialTheme.typography.titleSmall, color = Color.Gray, fontStyle = FontStyle.Italic )
            }
        }
        HorizontalDivider(Modifier.padding(horizontal = 16.dp), thickness = 1.dp, color = Color.LightGray.copy(alpha = 0.2f))

        // Reset click state after delay
        LaunchedEffect(isClicked) {
            // Prevent multiple clicks for 500ms
            delay(1000)
            isClicked = false
        }

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
        )
    }
}
