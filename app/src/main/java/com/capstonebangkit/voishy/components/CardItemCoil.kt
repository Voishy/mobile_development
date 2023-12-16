package com.capstonebangkit.voishy.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.capstonebangkit.voishy.R
import com.capstonebangkit.voishy.ui.theme.VoishyTheme

@Composable
fun CardItemCoil(materialTitle: String, photoUrl: String, onCardClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .width(300.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/original/$photoUrl",
                contentDescription = "Movie Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth() // Fixed width for the image
                    .aspectRatio(3f / 4f) // Maintaining the aspect ratio
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = materialTitle,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.widthIn(max = 150.dp)
                )
                OutlinedButton(
                    onClick = onCardClick
                ) {
                    Text("More")
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CardItemCoil() {
    VoishyTheme {
        CardItemCoil(
            materialTitle = "The Godfather",
            photoUrl = "rSPw7tgCH9c6NqICZef4kZjFOQ5.jpg",
            onCardClick = {}
        )
    }
}