package com.example.smile.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.*
import com.example.smile.viewmodel.SmileStateHolder

@Composable
fun SmileOverlay() {
    val smile = SmileStateHolder.smileProbability
    var isSmiling by remember { mutableStateOf(false) }
    var startTime by remember { mutableStateOf(0L) }
    var showDialog by remember { mutableStateOf(false) }

    if (smile > 0.6f) {
        if (!isSmiling) {
            isSmiling = true
            startTime = System.currentTimeMillis()
        }

        val duration = (System.currentTimeMillis() - startTime) / 1000
        if (duration >= 5) {
            showDialog = true
        }

        // 🎉 Lottie animation
        val composition by rememberLottieComposition(LottieCompositionSpec.Asset("confetti.json"))
        val progress by animateLottieCompositionAsState(
            composition = composition,
            iterations = LottieConstants.IterateForever
        )

        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.fillMaxSize()
        )

        // Optional smile meter
        LinearProgressIndicator(progress = smile, modifier = Modifier.fillMaxWidth())
    } else {
        isSmiling = false
        showDialog = false
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Great Smile! 😊") },
            text = { Text("Keep smiling!") },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Thanks!")
                }
            }
        )
    }
}
