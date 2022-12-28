package com.erkuai.mycompose.test

import android.graphics.Camera
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.erkuai.mycompose.R
import com.erkuai.mycompose.ui.theme.MyComposeTheme

class TestCustomViewActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyComposeTheme {
//                DrawTextBehind()
//                DrawTextWithContent()
//                DrawCanvas()
//                Box(modifier = Modifier.padding(50.dp)) {
//                    DrawImage()
//                }
                //以下：自定义布局
                // 1.
//                Modifier.layout { measurable, constraints ->
//
//                }
                // 2.
//                Layout(measurePolicy = ,){
//
//                }
                CustomLayout {
                    Text(text = "123")
                    Text(text = "456")
                }
            }
        }
    }
}

@Composable
fun CustomLayout(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Layout(content = content, modifier = modifier) { measurables, constraints ->
        var width = 0
        var height = 0
        val placeables = measurables.map {
            it.measure(constraints).also { placeable ->
                width = kotlin.math.max(width, placeable.width)
                height += placeable.height
            }
        }
        layout(width, height) {
            var totalHeight = 0
            placeables.forEach {
                it.placeRelative(0, totalHeight)
                totalHeight += it.height
            }
        }
    }
}

@Composable
fun DrawImage() {
    val bitmap = ImageBitmap.imageResource(R.drawable.img)

    val paint by remember { mutableStateOf(Paint()) }
    val camera by remember { mutableStateOf(Camera()) }/*.apply {
        value.rotateX(45f)
    }*/

    val rotateAnimation by remember { mutableStateOf(Animatable(0f)) }

    LaunchedEffect(key1 = Unit, block = {
        rotateAnimation.animateTo(360f, infiniteRepeatable(tween(2000)))
    })


    Canvas(modifier = Modifier
        .size(100.dp)
        /*    .graphicsLayer {
                rotationX = 50f
            }*/, onDraw = {
//        rotate(50f) {
//            drawImage(bitmap, dstSize = IntSize(size.width.toInt(), size.height.toInt()))
//        }

            drawIntoCanvas { // 下沉到原生的Canvas
                it.translate(size.width / 2, size.height / 2)
                it.rotate(-45f)
                camera.save()
                camera.rotateX(rotateAnimation.value)
                camera.applyToCanvas(it.nativeCanvas) // 将camera和原生的canvas绑定
                camera.restore()
                it.rotate(45f)
                it.translate(-size.width / 2, -size.height / 2)
                it.drawImageRect(image = bitmap, dstSize = IntSize(size.width.toInt(), size.height.toInt()), paint = paint)
            }

//            drawImage(bitmap, dstSize = IntSize(size.width.toInt(), size.height.toInt()))

        })
}

@Composable
fun DrawCanvas() {
    Canvas(modifier = Modifier.size(80.dp)) {

    }
}

@Preview
@Composable
fun DrawTextWithContent() {
    Text(text = "345", modifier = Modifier.drawWithContent {
        // 1.
        drawRect(Color.Blue)
        drawContent() // 2. 控制顺序，这一步是控件本身的绘制，上面是背景，下面是前景
        // 3.
    })
}

@Composable
fun DrawTextBehind() {
    Text(text = "123", modifier = Modifier.drawBehind {
        drawRect(Color.Blue)
    })
}