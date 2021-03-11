package com.example.androiddevchallenge.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.typography
import kotlin.math.roundToInt

@Composable
fun Cubic() {
    Column(Modifier.padding(16.dp)) {

        var offsetStart by remember { mutableStateOf(IntOffset(100, 100)) }
        var offsetEnd by remember { mutableStateOf(IntOffset(200, 200)) }

        var controlPoint1 by remember { mutableStateOf(IntOffset(120, 120)) }
        var controlPoint2 by remember { mutableStateOf(IntOffset(140, 140)) }
        Text(text = "Cubic curve", style = typography.h4)
        Text(text = "Cubic curve", style = typography.body1)
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            ControlTextField(
                value = offsetStart.x.toString(),
                onValueChange = { offsetStart = offsetStart.copy(x = it.toInt()) },
                label = { Text("start x") },
            )
            ControlTextField(
                value = offsetStart.y.toString(),
                label = { Text("start y") },
                onValueChange = { offsetStart = offsetStart.copy(y = it.toInt()) },
            )
            ControlTextField(
                value = offsetEnd.x.toString(),
                label = { Text("end x") },
                onValueChange = { offsetEnd = offsetEnd.copy(x = it.toInt()) },
            )
            ControlTextField(
                value = offsetEnd.y.toString(),
                label = { Text("end y") },
                onValueChange = { offsetEnd = offsetEnd.copy(y = it.toInt()) },
            )
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            ControlTextField(
                value = controlPoint1.x.toString(),
                label = { Text("control1 x") },
                onValueChange = { controlPoint1 = controlPoint1.copy(x = it.toInt()) },
            )
            ControlTextField(
                value = controlPoint1.y.toString(),
                label = { Text("control1 y") },
                onValueChange = { controlPoint1 = controlPoint1.copy(y = it.toInt()) },
            )
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            ControlTextField(
                value = controlPoint2.x.toString(),
                label = { Text("control2 x") },
                onValueChange = { offsetEnd = controlPoint2.copy(x = it.toInt()) },
            )
            ControlTextField(
                value = controlPoint2.y.toString(),
                label = { Text("control2 y") },
                onValueChange = { controlPoint2 = controlPoint2.copy(y = it.toInt()) },
            )
        }


        Box(
            modifier = Modifier
                .background(Color.Black)
                .padding(16.dp)
                .fillMaxWidth()
                .aspectRatio(1f)

        ) {
            var maxWidth by remember { mutableStateOf(0) }
            var maxHeight by remember { mutableStateOf(0) }
            Canvas(modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray), onDraw = {
                maxWidth = drawContext.size.width.roundToInt()
                maxHeight = drawContext.size.height.roundToInt()
                val (x1, y1) = offsetStart
                val (x2, y2) = offsetEnd
                val (c1x, c1y) = controlPoint1
                val (c2x, c2y) = controlPoint2
                val path = Path().apply {
                    moveTo(x1.toFloat(), y1.toFloat())
                    cubicTo(
                        c1x.toFloat(),
                        c1y.toFloat(),
                        c2x.toFloat(),
                        c2y.toFloat(),
                        x2.toFloat(),
                        y2.toFloat()
                    )
                }
                drawPath(path, color = Color.White)
            })

            ControlPoint(
                color = Color.Blue,
                offset = { offsetStart },
                onOffsetChange = {
                    val (x, y) = it
                    val newX = (x + offsetStart.x).coerceIn(0, maxWidth)
                    val newY = (y + offsetStart.y).coerceIn(0, maxHeight)
                    offsetStart = IntOffset(newX, newY)
                })

            ControlPoint(
                offset = { controlPoint1 },
                onOffsetChange = {
                    val (x, y) = it
                    val newX = (x + controlPoint1.x).coerceIn(0, maxWidth)
                    val newY = (y + controlPoint1.y).coerceIn(0, maxHeight)
                    controlPoint1 = IntOffset(newX, newY)
                })

            ControlPoint(
                offset = { controlPoint2 },
                onOffsetChange = {
                    val (x, y) = it
                    val newX = (x + controlPoint2.x).coerceIn(0, maxWidth)
                    val newY = (y + controlPoint2.y).coerceIn(0, maxHeight)
                    controlPoint2 = IntOffset(newX, newY)
                })

            ControlPoint(
                color = Color.Blue,
                offset = { offsetEnd },
                onOffsetChange = {
                    val (x, y) = it
                    val newX = (x + offsetEnd.x).coerceIn(0, maxWidth)
                    val newY = (y + offsetEnd.y).coerceIn(0, maxHeight)
                    offsetEnd = IntOffset(newX, newY)
                })
        }
    }
}