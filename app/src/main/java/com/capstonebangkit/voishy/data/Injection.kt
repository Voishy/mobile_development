package com.capstonebangkit.voishy.data

import com.capstonebangkit.voishy.data.network.ApiConfig

object Injection {
    fun provideRepository(): Repository {
        val apiService = ApiConfig.getApiService()
        return Repository.getInstance(apiService)
    }

    fun provideArticleRepository(): ArticleRepository {
        val apiServiceArticles = ApiConfig.getApiServiceArticles()
        return ArticleRepository.getInstance(apiServiceArticles)
    }
}