package com.mkdevelopers.analogclockcanvas

import android.graphics.Color
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Clock(
    modifier: Modifier,
    style: ClockStyle = ClockStyle(),
    secondThorneAngle: Float = -90f,
    minuteThorneAngle: Float = -90f,
    hourThorneAngle: Float = -90f
) {
    val radius = style.radius

    Canvas(
        modifier = modifier
    ) {
        drawContext.canvas.nativeCanvas.apply {

            println("currentAngle - hour = $hourThorneAngle, minute = $minuteThorneAngle, second = $secondThorneAngle")

            drawCircle(
                center.x,
                center.y,
                radius.toPx(),
                Paint().apply {
                    color = Color.WHITE
                    setShadowLayer(
                        50f,
                        0f,
                        0f,
                        android.graphics.Color.argb(50, 0, 0, 0)
                    )
                }
            )

            /**
             * draw lines
             */
            for(i in 0..59) {

                val angleInRad = (i * 6) * (Math.PI.toFloat() / 180f) // which means get radian at every 6th degree

                val lineType = if(i % 5 == 0)
                    LineType.FIVE_STEP
                else
                    LineType.NORMAL

                val (lineLength, lineColor) = when(lineType) {
                    LineType.NORMAL    -> Pair(style.normalLineLength.toPx(), style.normalLineColor)
                    LineType.FIVE_STEP -> Pair(style.fiveStepLineLength.toPx(), style.fiveStepColor)
                }

                val lineStart = Offset(
                    x = (radius.toPx() - lineLength) * cos(angleInRad) + center.x,
                    y = (radius.toPx() - lineLength) * sin(angleInRad) + center.y
                )

                val lineEnd = Offset(
                    x = radius.toPx() * cos(angleInRad) + center.x,
                    y = radius.toPx() * sin(angleInRad) + center.y
                )

                drawLine(
                    color = lineColor,
                    start = lineStart,
                    end   = lineEnd,
                    strokeWidth = 1.dp.toPx()
                )
            }

            /**
             * draw thorns
             */
            val hourAngleInRad = getAngleInRadian(hourThorneAngle)
            val hourThorneLineEnd = Offset(
                x = (radius.toPx() - 50.dp.toPx()) * cos(hourAngleInRad) + center.x,
                y = (radius.toPx() - 50.dp.toPx()) * sin(hourAngleInRad) + center.y
            )

            val minuteAngleInRad = getAngleInRadian(minuteThorneAngle)
            val minuteThorneLineEnd = Offset(
                x = (radius.toPx() - 30.dp.toPx()) * cos(minuteAngleInRad) + center.x,
                y = (radius.toPx() - 30.dp.toPx()) * sin(minuteAngleInRad) + center.y
            )

            val secondAngleInRad = getAngleInRadian(secondThorneAngle)
            val secondThorneLineEnd = Offset(
                x = (radius.toPx() - 25.dp.toPx()) * cos(secondAngleInRad) + center.x,
                y = (radius.toPx() - 25.dp.toPx()) * sin(secondAngleInRad) + center.y
            )

            /**
             * draw second thorne
             */
            drawLine(
                color = style.secondThornColor,
                start = center,
                end   = secondThorneLineEnd,
                strokeWidth = 3.dp.toPx(),
                cap = StrokeCap.Round
            )

            /**
             * draw minute thorne
             */
            drawLine(
                color = style.minuteThornColor,
                start = center,
                end   = minuteThorneLineEnd,
                strokeWidth = 3.dp.toPx(),
                cap = StrokeCap.Round
            )

            /**
             * draw hour thorne
             */
            drawLine(
                color = style.hourThornColor,
                start = center,
                end   = hourThorneLineEnd,
                strokeWidth = 3.dp.toPx(),
                cap = StrokeCap.Round
            )
        }
    }
}

fun getAngleInRadian(angleInDegree: Float)
    = angleInDegree * (Math.PI.toFloat() / 180f)