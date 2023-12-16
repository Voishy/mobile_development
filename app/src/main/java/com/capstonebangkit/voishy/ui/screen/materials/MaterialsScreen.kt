package com.capstonebangkit.voishy.ui.screen.materials

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.capstonebangkit.voishy.components.CardItem
import com.capstonebangkit.voishy.components.CardItemCoil
import com.capstonebangkit.voishy.components.SectionText
import com.capstonebangkit.voishy.data.response.DataItem
import com.capstonebangkit.voishy.data.response.ResultsItem
import com.capstonebangkit.voishy.ui.screen.ViewModelFactory

@Composable
fun MaterialsScreen(
    materialsViewModel: MaterialsViewModel = viewModel(factory = ViewModelFactory.getInstance()),
    navController: NavController
) {
    val movieList = materialsViewModel.movieList.collectAsState().value
    val articleList = materialsViewModel.articlesList.collectAsState().value
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)) {
            SectionText(
                title = "Let's Learn!",
                description = "Read our helpful material regarding speaking and more!",
                color = Color.Black,
                type = "large"
            )
            ArticlesContent(movies = articleList, navController)
        }

        Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)) {
            SectionText(
                title = "Movie Recommendation",
                description = "Here are some movie recommendation for you to watch and learn",
                color = Color.Black,
                type = "medium"
            )
            MoviesContent(movies = movieList, navController)
        }
    }
}

@Composable
fun MoviesContent(movies: List<ResultsItem>, navController: NavController) {
    LazyRow() {
        items(movies, key = { it.id!!.toLong() }) {
            if (it.originalLanguage == "en") {
                CardItemCoil(
                    materialTitle = it.title.toString(),
                    photoUrl = it.posterPath.toString(),
                    onCardClick = {
                        navController.navigate("movieDetail/${it.id}")
                    }
                )
            }
        }
    }
}


@Composable
fun ArticlesContent(movies: List<DataItem>, navController: NavController) {
    LazyRow() {
        items(movies) {
            CardItem(
                materialTitle = it.title.toString(),
                materialReadTime = it.times.toString(),
                onCardClick = {
                    navController.navigate("articleDetail/${it.title}")
                }
            )
        }
    }
}