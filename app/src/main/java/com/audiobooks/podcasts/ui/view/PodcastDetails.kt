package com.audiobooks.podcasts.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.audiobooks.podcasts.R
import com.audiobooks.podcasts.model.Podcast
import com.audiobooks.podcasts.ui.theme.PodcastsTheme

@Composable
fun PodcastDetailsScreen(podcast: Podcast, navController: NavController? = null) {
    var isFavourite by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Back Button
        Row(
            modifier = Modifier.align(Alignment.Start)
                .clickable{ navController?.popBackStack() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Back", fontSize = 16.sp, fontWeight = FontWeight.Medium)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Podcast Title & Publisher
        Text(podcast.title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        Text(podcast.publisher, style = MaterialTheme.typography.titleMedium, color = Color.Gray, fontStyle = FontStyle.Italic)

        Spacer(modifier = Modifier.height(16.dp))

        // Podcast Image
        Image(
            painter = rememberAsyncImagePainter(podcast.image),
            contentDescription = podcast.title,
            modifier = Modifier.size(180.dp).clip(RoundedCornerShape(12.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Favourite Button
        Button(
            onClick = { isFavourite = !isFavourite },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = if (isFavourite) Color.Gray else colorResource(R.color.favorite_button))
        ) {
            Text(if (isFavourite) "Favourited" else "Favourite", color = Color.White, modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Description
        Text(
            text = podcast.description, // TODO: Handle HTML text rendering properly
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = Color.Gray,
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 8.dp)
                .verticalScroll(rememberScrollState()
            )
        )
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
