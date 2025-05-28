package com.example.smile

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smile.ui.CameraPermissionHandler
import com.example.smile.ui.theme.SmileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmileTheme {
                MainScreen() // ← This is where your composable is called
            }
        }
    }
}

@Composable
fun MainScreen() {
    var showCamera by remember { mutableStateOf(false) }

    if (showCamera) {
        Log.d("MainScreen", "Camera screen is being shown") // Debug log
        CameraPermissionHandler()
    } else {
        Log.d("MainScreen", "Initial screen shown") // Debug log
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Boost Your Mood with a Smile! 😊", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                Log.d("MainScreen", "Button clicked: showCamera = true")
                showCamera = true
            }) {
                Text("Boost Mood with Mirror")
            }
        }
    }
}
