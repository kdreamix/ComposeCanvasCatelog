package com.example.androiddevchallenge.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

val circleRadius = 12.dp
val circleSize = 24.dp

@Composable
fun Screen() {
    Cubic()
//    StraightLine()
}

@Composable
fun ControlTextField(
    label: @Composable (() -> Unit)? = null,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = label,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.width(80.dp)
    )
}

@Composable
fun ControlPoint(
    color: Color = Color.Red,
    offset: Density.() -> IntOffset,
    onOffsetChange: (IntOffset) -> Unit,
) {
    Box(modifier = Modifier
        .offset {
            offset() - IntOffset(
                circleRadius.roundToPx(),
                circleRadius.roundToPx()
            )
        }
        .size(circleSize)
        .background(color = color, CircleShape)
        .pointerInput(Unit) {
            detectDragGestures { change, dragAmount ->
                change.consumeAllChanges()
                onOffsetChange(
                    IntOffset(
                        dragAmount.x.roundToInt(),
                        dragAmount.y.roundToInt()
                    )
                )
            }
        })
}