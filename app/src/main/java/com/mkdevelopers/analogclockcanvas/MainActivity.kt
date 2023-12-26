package com.mkdevelopers.analogclockcanvas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            /*AnalogClockCanvasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Clock(
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.Center)
                        )
                    }
                }
            }*/

            val viewModel: AnalogViewModel by viewModels()

            val secondThorneAngle by viewModel.secondThorneAngle.collectAsState()
            val minuteThorneAngle by viewModel.minutesThorneAngle.collectAsState()
            val hourThorneAngle   by viewModel.hourThorneAngle.collectAsState()

            Box(modifier = Modifier.fillMaxSize()) {
                Clock(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center),
                    secondThorneAngle = secondThorneAngle,
                    minuteThorneAngle = minuteThorneAngle,
                    hourThorneAngle   = hourThorneAngle
                )
            }
        }
    }
}