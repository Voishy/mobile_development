package com.capstonebangkit.voishy.ui.screen.detail.movie

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.capstonebangkit.voishy.R
import com.capstonebangkit.voishy.ui.screen.ViewModelFactory

@Composable
fun MovieDetailScreen(movieId: String) {
    // Get the ViewModelFactory instance
    val viewModelFactory = ViewModelFactory.getInstance()

    // Instantiate the MovieViewModel
    val movieViewModel: MovieViewModel = viewModel(factory = viewModelFactory)

    // Fetch the movie details
    LaunchedEffect(movieId) {
        movieViewModel.fetchMovieDetail(movieId.toInt())
    }

    // Observe the movie details from the ViewModel
    val movieDetail = movieViewModel.movieDetail.collectAsState().value

    // UI code to display the movie details
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        movieDetail?.let {
            Banner(titleString = it.title.toString(), urlPhoto = it.backdropPath.toString())
            Text(
                text = "Runtime: ${it.runtime} minutes",
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(vertical = 2.dp, horizontal = 16.dp)
            )
            Text(
                text = "Synopsis",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)
            )
            Text(
                text = it.overview.toString(), style = MaterialTheme.typography.bodyMedium.plus(
                    ParagraphStyle(
                        TextAlign.Justify
                    )
                ),
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun Banner(modifier: Modifier = Modifier, titleString: String, urlPhoto: String) {
    Box(modifier = modifier) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/original/$urlPhoto",
            contentDescription = "Movie Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.requiredHeight(300.dp)
        )
        Column(
            modifier = Modifier
                .matchParentSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = titleString, style = MaterialTheme.typography.displaySmall.plus(
                    ParagraphStyle(textAlign = TextAlign.Start)
                ), color = Color.White
            )
        }
    }
}