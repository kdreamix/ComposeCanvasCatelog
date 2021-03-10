package com.example.androiddevchallenge.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class ItemData(
    val title: String,
    val description: String,
    val drawAction: DrawScope.() -> Any,
)

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

@Composable
fun StraightLine() {
    Column {
        var offsetStart by remember { mutableStateOf(Offset.Zero) }
        var offsetEnd by remember { mutableStateOf(Offset(100f, 100f)) }

        Text(text = "Line")
        Text(text = "Just a line")
        OutlinedTextField(
            value = offsetStart.x.toString(),
            onValueChange = { offsetStart = offsetStart.copy(x = it.toFloat()) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = offsetStart.y.toString(),
            onValueChange = { offsetStart = offsetStart.copy(x = it.toFloat()) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = offsetEnd.x.toString(),
            onValueChange = { offsetEnd = offsetEnd.copy(x = it.toFloat()) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = offsetEnd.y.toString(),
            onValueChange = { offsetEnd = offsetEnd.copy(y = it.toFloat()) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Box(modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .background(Color.Black)
            .padding(16.dp)
            .drawBehind {
                val width = this.drawContext.size.width
                val height = this.drawContext.size.height
                drawLine(Color.White, offsetStart, offsetEnd,strokeWidth = 16.dp.toPx(),cap = StrokeCap.Round)
            }) {

        }
    }
}