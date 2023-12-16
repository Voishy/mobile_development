package com.capstonebangkit.voishy.data


import com.capstonebangkit.voishy.data.network.ApiServiceArticle
import com.capstonebangkit.voishy.data.response.DataItem

class ArticleRepository private constructor(
    private val apiServiceArticle: ApiServiceArticle
){
    companion object {
        @Volatile
        private var instance: ArticleRepository? = null

        fun getInstance(
            apiServiceArticle: ApiServiceArticle
        ) : ArticleRepository =
            instance ?: synchronized(this) {
                instance ?: ArticleRepository(apiServiceArticle)
            }.also { instance = it }
    }

    suspend fun getArticles() : List<DataItem> {
        val response = apiServiceArticle.getArticle()
        return response.data
    }
}