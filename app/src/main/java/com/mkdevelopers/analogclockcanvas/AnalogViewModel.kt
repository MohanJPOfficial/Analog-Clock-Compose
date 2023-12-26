package com.mkdevelopers.analogclockcanvas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.Instant

class AnalogViewModel : ViewModel() {

    private val _secondThorneAngle = MutableStateFlow(0f)
    val secondThorneAngle = _secondThorneAngle.asStateFlow()

    private val _minutesThorneAngle = MutableStateFlow(0f)
    val minutesThorneAngle = _minutesThorneAngle.asStateFlow()

    private val _hourThorneAngle = MutableStateFlow(0f)
    val hourThorneAngle = _hourThorneAngle.asStateFlow()

    init {
        getCurrentSeconds()

        viewModelScope.launch {
            _secondThorneAngle.value  = (getCurrentSeconds() * 6f) - 90f

            while(true) {
                delay(1000)
                _secondThorneAngle.value   = _secondThorneAngle.value + 6f
            }
        }
        viewModelScope.launch {

            _minutesThorneAngle.value = (getCurrentMinutes() * 6f) - 90f
            _hourThorneAngle.value    = (((getCurrentHours() * 60f) / 12f) * 6f) - 90f

            while(true) {
                delay(1000)
                _minutesThorneAngle.value += (6f / 60f)
                _hourThorneAngle.value    += (30f / 3600f)
            }
        }
    }

    private fun getCurrentSeconds(): Float {
        val instant = Instant.now()

        return 0f //instant.epochSecond % 60f
    }

    private fun getCurrentMinutes(): Float {
        val instant = Instant.now()

        return ((instant.epochSecond) / 60f) % 60f + 30f   // add 30 minutes for IST.
    }

    private fun getCurrentHours(): Float {
        val instant = Instant.now()

        return ((instant.epochSecond) / 3600f) % 12f + 5.5f         // add 5.5 hours for IST.

    }
}