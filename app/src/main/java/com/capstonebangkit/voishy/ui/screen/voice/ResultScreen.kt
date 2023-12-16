package com.capstonebangkit.voishy.ui.screen.voice

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import android.speech.SpeechRecognizer
import android.util.Log
import androidx.compose.material3.Button
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.compose.ui.platform.LocalContext
import com.capstonebangkit.voishy.DataStoreManager
import com.capstonebangkit.voishy.components.SectionText

@Composable
fun ResultScreen(navigateBack: () -> Unit) {
    val context = LocalContext.current

    // Here you collect the latest values of correct and missed words from DataStore
    val correctWords = DataStoreManager.readCorrectWords(context).collectAsState(initial = "").value
    val missedWords = DataStoreManager.readMissedWords(context).collectAsState(initial = "").value

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.padding(vertical = 16.dp, horizontal = 32.dp)) {
            SectionText(title = "Results", description = "Here's your results", color = Color.Black, type = "large")
            SectionText(title = "Correct Words", description = "$correctWords", color = Color.Black, type = "medium")
            SectionText(title = "Missed Words", description = "$missedWords", color = Color.Black, type = "medium")
        }
    }
}

