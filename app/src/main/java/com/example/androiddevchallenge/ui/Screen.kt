package com.example.androiddevchallenge.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

data class ItemData(
    val title: String,
    val description: String,
    val drawAction: DrawScope.() -> Any,
)

val circleRadius = 12.dp
val circleSize = 24.dp

@Composable
fun Screen(
) {
    StraightLine()
}

@Composable
fun Item(
    title: String,
    description: String,
    drawAction: DrawScope.() -> Any = {},
    headerControls: @Composable () -> Unit = {},
) {
    Column {
        Text(text = title)
        Text(text = description)
        headerControls()
        Box(modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .background(Color.Black)
            .padding(16.dp)
            .drawBehind {
                drawAction()
            }) {

        }
    }

}

@Preview
@Composable
fun StraightLine() {
    Column {
        var offsetStart by remember { mutableStateOf(IntOffset(100, 100)) }
        var offsetEnd by remember { mutableStateOf(IntOffset(200, 200)) }

        Text(text = "Line")
        Text(text = "Just a line")
        OutlinedTextField(
            value = offsetStart.x.toString(),
            onValueChange = { offsetStart = offsetStart.copy(x = it.toInt()) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = offsetStart.y.toString(),
            onValueChange = { offsetStart = offsetStart.copy(x = it.toInt()) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = offsetEnd.x.toString(),
            onValueChange = { offsetEnd = offsetEnd.copy(x = it.toInt()) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = offsetEnd.y.toString(),
            onValueChange = { offsetEnd = offsetEnd.copy(y = it.toInt()) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Box(
            modifier = Modifier
                .background(Color.Black)
                .padding(16.dp)
                .fillMaxWidth()
                .aspectRatio(1f)

        ) {
            Canvas(modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray), onDraw = {
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
            // Control 1
            Box(modifier = Modifier
                .offset {
                    offsetStart - IntOffset(
                        circleRadius.roundToPx(),
                        circleRadius.roundToPx()
                    )
                }
                .size(circleSize)
                .background(color = Color.Red, CircleShape)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consumeAllChanges()
                        offsetStart += IntOffset(
                            dragAmount.x.roundToInt(),
                            dragAmount.y.roundToInt()
                        )
                    }
                })
            // Control 2
            Box(modifier = Modifier
                .offset {
                    offsetEnd - IntOffset(
                        circleRadius.roundToPx(),
                        circleRadius.roundToPx()
                    )
                }
                .size(circleSize)
                .background(color = Color.Red, CircleShape)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consumeAllChanges()
                        offsetEnd += IntOffset(
                            dragAmount.x.roundToInt(),
                            dragAmount.y.roundToInt()
                        )

                    }
                })
        }
    }
}