package com.capstonebangkit.voishy.ui.screen.detail.movie

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstonebangkit.voishy.data.Repository
import com.capstonebangkit.voishy.data.response.MovieDetailResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: Repository) : ViewModel() {

    private val _movieDetail = MutableStateFlow<MovieDetailResponse?>(null)
    val movieDetail: StateFlow<MovieDetailResponse?> = _movieDetail

    fun fetchMovieDetail(movieId: Int) {
        viewModelScope.launch {
            try {
                val detail = repository.getMovieDetailbyID(movieId)
                _movieDetail.value = detail
            } catch (e: Exception) {
                Log.e("MovieDetailViewModel", "Error fetching movie details", e)
            }
        }
    }
}
