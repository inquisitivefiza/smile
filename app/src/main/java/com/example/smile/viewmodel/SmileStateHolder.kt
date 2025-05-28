package com.example.smile.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue

object SmileStateHolder {
    var smileProbability by mutableFloatStateOf(0f)
        private set

    fun updateSmileProbability(newProb: Float) {
        Log.d("SmileStateHolder", "Updating smile probability to: $newProb")
        smileProbability = newProb
    }
}

