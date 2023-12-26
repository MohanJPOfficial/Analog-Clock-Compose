package com.mkdevelopers.analogclockcanvas

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ClockStyle(
    val radius: Dp = 100.dp,
    val normalLineColor: Color = Color.LightGray,
    val fiveStepColor: Color = Color.Black,
    val normalLineLength: Dp = 15.dp,
    val fiveStepLineLength: Dp = 25.dp,
    val hourThornColor: Color = Color.Black,
    val minuteThornColor: Color = Color.Black,
    val secondThornColor: Color = Color.Red
)
