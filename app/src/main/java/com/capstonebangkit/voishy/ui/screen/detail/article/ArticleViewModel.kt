package com.capstonebangkit.voishy.ui.screen.detail.article

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstonebangkit.voishy.data.ArticleRepository
import com.capstonebangkit.voishy.data.response.DataItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticleViewModel(private val articleRepository: ArticleRepository) : ViewModel() {

    private val _articleDetail = MutableStateFlow<DataItem?>(null)
    val articleDetail: StateFlow<DataItem?> = _articleDetail

    fun fetchArticleDetail(articleTitle: String) {
        viewModelScope.launch {
            try {
                // Assuming the article list is cached and you're fetching from it
                val articles = articleRepository.getArticles()
                _articleDetail.value = articles.find { it.title == articleTitle }
            } catch (e: Exception) {
                Log.e("ArticleDetailViewModel", "Error fetching article details", e)
            }
        }
    }
}
