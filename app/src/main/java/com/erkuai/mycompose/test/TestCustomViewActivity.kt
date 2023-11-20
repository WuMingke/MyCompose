package com.erkuai.mycompose.test

import android.graphics.Camera
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.*
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
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
//                CustomLayout {
//                    Text(text = "123")
//                    Text(text = "456")
//                }

                // 3.
//                SubcomposeLayoutTest()

                // 4.
                LookaheadLayoutTest()
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LookaheadLayoutTest() {
    /**
     * LookaheadLayout 主要用来做过渡动画的，类似于 传统的Transition Api
     *  可以先拿到自己的预先的测量结果，然后根据这个测量结果，再测量自己
     *
     * 会经历两遍测量
     *
     * 共享元素的动画
     *
     * LookaheadLayout 被替换为 LookaheadScope
     */
    LookaheadScope {

    }
//    LookaheadLayout({
//        Column {
//            Text(
//                "123",
//                Modifier
////                    .layout { measurable, constraints -> // 第一遍时要执行，但是如果constraints没有变化，其实是会跳过的
////                        layout()
////                    }
//                    .intermediateLayout { // 在第一遍测量的时候被跳过，在第二遍测量的时候才会执行
//                            measurable, constraints, lookaheadSize ->
//                        val placeable = measurable.measure(Constraints.fixed(lookaheadSize.width, lookaheadSize.height * 2))
//                        layout(placeable.width, placeable.height) {
//                            placeable.placeRelative(0, 0)
//                        }
//                    }
////                    .layout { measurable, constraints -> // 第一遍时要执行
////                        layout()
////                    }
//            )
//            Text(text = "456")
//        }
//    }) { measurables, constraints ->
//        val placeables = measurables.map {
//            it.measure(constraints)
//        }
//        val width = placeables.maxOf { it.width }
//        val height = placeables.maxOf { it.height }
//        layout(width, height) {
//            placeables.forEach { it.placeRelative(0, 0) }
//        }
//    }
}

@Composable
fun SubcomposeLayoutTest() {

    /**
     * 布局流程：
     * 组合（还没有发生测量，所以有关布局的数据拿不到）
     * 测量
     * 布局
     * 绘制
     *
     * SubcomposeLayout 可以提前拿到测量阶段的数据
     * 缺点是：
     *   性能消耗比较大，因为混合了布局流程，高频的重复测量导致了高频的重组，导致了界面卡顿
     */

    BoxWithConstraints { // 最简单的 SubcomposeLayout 的包装
        constraints // 可以获得 父组件的约束
    }

    SubcomposeLayout() { constraints ->
        val measurable = subcompose(1) { // 1组合 ，在组合阶段已经可以拿到constraints了
            Text(text = "")
        }[0]

        val placeable = measurable.measure(constraints) // 2测量

        layout(placeable.width, placeable.height) { // 3布局
            placeable.placeRelative(0, 0)
        }

        // SubcomposeLayout 提供了在测量过程中去自由地调配一个子界面的各个部分之间的组合、测量、布局过程的调用时机，
        // 以此来提供  动态化布局 的功能
    }

    // eg:1
//    Scaffold {
//
//    }

    // eg:2
    LazyColumn {

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