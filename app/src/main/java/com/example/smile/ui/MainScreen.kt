package com.example.smile.ui

import android.Manifest
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraPermissionHandler() {
    val cameraPermissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)

    if (cameraPermissionState.status.isGranted) {
        CameraPreview() // Your composable showing the camera preview
    } else {
        Button(onClick = { cameraPermissionState.launchPermissionRequest() }) {
            Text("Grant Camera Permission")
        }
    }
}
