package com.capstonebangkit.voishy.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstonebangkit.voishy.data.ArticleRepository
import com.capstonebangkit.voishy.data.Repository
import com.capstonebangkit.voishy.data.response.DataItem
import com.capstonebangkit.voishy.data.response.ResultsItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val articleRepository: ArticleRepository): ViewModel() {
    private val _articlesList = MutableStateFlow<List<DataItem>>(emptyList())
    val articlesList : StateFlow<List<DataItem>> get() = _articlesList

    init {
        viewModelScope.launch {
            _articlesList.value = articleRepository.getArticles()
        }
    }
}