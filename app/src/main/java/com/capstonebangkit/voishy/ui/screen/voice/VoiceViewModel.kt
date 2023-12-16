package com.capstonebangkit.voishy.ui.screen.voice

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstonebangkit.voishy.DataStoreManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VoiceViewModel(private val context: Context, private val speechRecognizer: SpeechRecognizer) : ViewModel() {
    init {
        Log.d("VoiceViewModel", "ViewModel created")
        // ...
    }


    private val _recognizedText = MutableStateFlow("")
    val recognizedText: StateFlow<String> = _recognizedText

    private val _navigationEvent = MutableStateFlow(false)
    val navigationEvent: StateFlow<Boolean> = _navigationEvent

//    private val _correctWords = MutableStateFlow<List<String>>(emptyList())
//    val correctWords: StateFlow<List<String>> = _correctWords
//
//    private val _missingWords = MutableStateFlow<List<String>>(emptyList())
//    val missingWords: StateFlow<List<String>> = _missingWords

    private val recognizerIntent: Intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
        putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
    }

    private val speechText = "Ladies and gentlemen, welcome. Today, we embark on a journey unlike any other, exploring innovations, fostering creativity, and celebrating diversity. Together, our collaboration shapes futures, inspires dreams, and achieves the extraordinary. Thank you for uniting in this momentous occasion, where possibilities become realities and visions transform into achievements. Let's forge ahead with optimism, determination, and unparalleled zeal. Embrace this adventure wholeheartedly. Onward to success!"

    init {
        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onResults(results: Bundle) {
                val matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                Log.d("voicespoken", matches.toString())
                processResults(matches?.joinToString(" ") ?: "")
            }

            override fun onReadyForSpeech(params: Bundle) {
                // Implement as needed
            }

            override fun onRmsChanged(rmsdB: Float) {
                // Implement as needed
            }

            override fun onBufferReceived(buffer: ByteArray) {
                // Implement as needed
            }

            override fun onPartialResults(partialResults: Bundle) {
                // Implement as needed
            }

            override fun onEvent(eventType: Int, params: Bundle) {
                // Implement as needed
            }

            override fun onBeginningOfSpeech() {
                // Implement as needed
            }

            override fun onEndOfSpeech() {
                // Implement as needed
            }

            override fun onError(error: Int) {
                Log.e("VoiceViewModel", "Speech Recognition Error: $error")
            }
        })
    }

    private fun saveResultsToDataStore(correct: List<String>, missed: List<String>) {
        val correctWords = correct.joinToString(", ")
        val missedWords = missed.joinToString(", ")
        viewModelScope.launch {
            DataStoreManager.saveCorrectWords(context, correctWords)
            DataStoreManager.saveMissedWords(context, missedWords)
        }
    }

    private fun processResults(recognized: String) {
        val wordsSpoken = recognized.split(" ").toSet()
        val wordsInSpeech = speechText.split(" ").toSet()

        val correct = wordsSpoken.intersect(wordsInSpeech).toList()
        val missed = wordsInSpeech.minus(wordsSpoken).toList()

//        _correctWords.value = correct
//        _missingWords.value = missed

        Log.d("VoiceViewModel", "Correct words: $correct")
        Log.d("VoiceViewModel", "Missed words: $missed")

        _recognizedText.value = "Correct: ${correct.joinToString(", ")}\nMissed: ${missed.joinToString(", ")}"
        _navigationEvent.value = true

        saveResultsToDataStore(correct, missed)
    }


    fun startListening() {
        speechRecognizer.startListening(recognizerIntent)
    }

    fun stopListening() {
        speechRecognizer.stopListening()
    }

    override fun onCleared() {
        super.onCleared()
        speechRecognizer.destroy()
    }
}
