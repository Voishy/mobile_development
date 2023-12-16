package com.capstonebangkit.voishy.ui.screen.voice

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.capstonebangkit.voishy.components.SectionText
import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import android.speech.SpeechRecognizer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Composable
fun VoiceScreen(navController: NavHostController) {
    val context = LocalContext.current
    val speechRecognizer = remember { SpeechRecognizer.createSpeechRecognizer(context) }

    val viewModel: VoiceViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return VoiceViewModel(context, speechRecognizer) as T
            }
        }
    )

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            viewModel.startListening()
        } else {
            // Handle permission denial
        }
    }

    val speechText = "Ladies and gentlemen, welcome. Today, we embark on a journey unlike any other, exploring innovations, fostering creativity, and celebrating diversity. Together, our collaboration shapes futures, inspires dreams, and achieves the extraordinary. Thank you for uniting in this momentous occasion, where possibilities become realities and visions transform into achievements. Let's forge ahead with optimism, determination, and unparalleled zeal. Embrace this adventure wholeheartedly. Onward to success!"
    val shouldNavigate by viewModel.navigationEvent.collectAsState()

    var isRecording by remember { mutableStateOf(false) }

    if (shouldNavigate) {
        navController.navigate("resultScreen")
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        SectionText(
            title = "Practice Your Speaking!",
            description = "Let’s tame the shyness with speaking the following text and see the results",
            color = Color.Black,
            type = "medium"
        )

        Text(text = speechText)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                    if (isRecording) {
                        viewModel.stopListening()
                    } else {
                        viewModel.startListening()
                    }
                    isRecording = !isRecording
                } else {
                    requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = if (isRecording) Color.Red else Color.Blue),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
            modifier = Modifier.height(48.dp).fillMaxWidth()
        ) {
            Icon(imageVector = Icons.Default.RecordVoiceOver, contentDescription = "Record", tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = if (isRecording) "Recording" else "Tap to Record", color = Color.White, fontSize = 16.sp)
        }
    }
}
