package com.example.smile.utils

import android.content.Context
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.example.smile.viewmodel.SmileStateHolder
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions

class SmileAnalyzer(val context: Context) : ImageAnalysis.Analyzer {

    private val faceDetector = FaceDetection.getClient(
        FaceDetectorOptions.Builder()
            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
            .enableTracking()
            .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL) // Required for smileProbability
            .build()
    )

    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image ?: return
        val inputImage = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

        faceDetector.process(inputImage)
            .addOnSuccessListener { faces ->
                for (face in faces) {
                    val smileProb = face.smilingProbability ?: 0f
                    SmileStateHolder.updateSmileProbability(smileProb)
                }
            }
            .addOnCompleteListener {
                imageProxy.close()
            }
    }
}
