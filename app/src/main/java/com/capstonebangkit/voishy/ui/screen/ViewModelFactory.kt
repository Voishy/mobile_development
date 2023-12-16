package com.capstonebangkit.voishy.ui.screen

import android.content.Context
import android.speech.SpeechRecognizer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstonebangkit.voishy.data.ArticleRepository
import com.capstonebangkit.voishy.data.Injection
import com.capstonebangkit.voishy.data.Repository
import com.capstonebangkit.voishy.data.response.MovieDetailResponse
import com.capstonebangkit.voishy.ui.screen.detail.article.ArticleViewModel
import com.capstonebangkit.voishy.ui.screen.detail.movie.MovieViewModel
import com.capstonebangkit.voishy.ui.screen.home.HomeViewModel
import com.capstonebangkit.voishy.ui.screen.materials.MaterialsViewModel
import com.capstonebangkit.voishy.ui.screen.voice.VoiceViewModel

class ViewModelFactory private constructor(private val repository: Repository?, private val articleRepository: ArticleRepository?, private val context: Context?) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(articleRepository!!) as T
        }
        if (modelClass.isAssignableFrom(MaterialsViewModel::class.java)) {
            return MaterialsViewModel(repository!!, articleRepository!!) as T
        }
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(repository!!) as T
        }
        if (modelClass.isAssignableFrom(ArticleViewModel::class.java)) {
            return ArticleViewModel(articleRepository!!) as T
        }
//        if (modelClass.isAssignableFrom(VoiceViewModel::class.java)) {
//            val ctx = context ?: throw IllegalStateException("Context must be provided for VoiceViewModel")
//            return VoiceViewModel(speechRecognizer = SpeechRecognizer.createSpeechRecognizer(ctx), context = context) as T
//        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance() : ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(), Injection.provideArticleRepository(), context = null)
            }.also { instance = it }

//        fun getInstanceVoice(context: Context) : ViewModelFactory =
//            instance ?: synchronized(this) {
//                instance ?: ViewModelFactory(repository = null, articleRepository = null, context)
//            }.also { instance = it }

    }
}