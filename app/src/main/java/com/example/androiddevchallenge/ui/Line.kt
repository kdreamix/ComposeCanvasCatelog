package com.example.androiddevchallenge.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.typography
import kotlin.math.roundToInt

@Preview
@Composable
fun StraightLine() {
    Column(Modifier.padding(16.dp)) {

        var offsetStart by remember { mutableStateOf(IntOffset(100, 100)) }
        var offsetEnd by remember { mutableStateOf(IntOffset(200, 200)) }

        Text(text = "Line", style = typography.h4)
        Text(text = "Just a line", style = typography.body1)
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
                drawLine(
                    Color.White,
                    Offset(x1.toFloat(), y1.toFloat()),
                    Offset(x2.toFloat(), y2.toFloat()),
                    strokeWidth = 16.dp.toPx(),
                    cap = StrokeCap.Round
                )
            })

            ControlPoint(
                offset = { offsetStart },
                onOffsetChange = {
                    val (x, y) = it
                    val newX = (x + offsetStart.x).coerceIn(0,maxWidth)
                    val newY = (y + offsetStart.y).coerceIn(0,maxHeight)
                    offsetStart = IntOffset(newX, newY)
                })

            ControlPoint(
                offset = { offsetEnd },
                onOffsetChange = {
                    val (x, y) = it
                    val newX = (x + offsetEnd.x).coerceIn(0,maxWidth)
                    val newY = (y + offsetEnd.y).coerceIn(0,maxWidth)
                    offsetEnd = IntOffset(newX, newY)
                })
        }
    }

}