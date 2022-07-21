package com.erkuai.mycompose.basewidget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode

class CustomViewActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "标题")
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    finish()
                                }) {
                                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                            }
                        }
                    )
                }) {
                DrawLineTest()
            }
        }
    }
}

@Composable
fun DrawLineTest() {

    Canvas(modifier = Modifier.fillMaxSize(), onDraw = {
//        drawLine(Color.Red, Offset(0f, 0f), Offset(100f, 100f), 30f)
//        drawRect(Color.Black, Offset(100f, 100f), Size(50f, 50f))
    })
}


@Composable
fun CanvasTest() {

    val points = arrayListOf<Offset>().apply {
        add(Offset(100f, 100f))
        add(Offset(200f, 200f))
        add(Offset(300f, 300f))
        add(Offset(400f, 400f))
    }
    Canvas(
        modifier = Modifier.fillMaxSize(),
        onDraw = {
            drawPoints(points, PointMode.Polygon, Brush.linearGradient(arrayListOf<Color>().apply {
                add(Color.Cyan)
                add(Color.Red)
                add(Color.Black)
            }), strokeWidth = 30f)
        })
}