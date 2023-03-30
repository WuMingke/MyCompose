package com.erkuai.mycompose.basewidget

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.CombinedModifier
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.*
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp

class ModifierActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        m1()
//        m2()
//        m3()
//        m4()
//        m5()
//        m6()
//        m8()

    }

    @OptIn(ExperimentalComposeUiApi::class)
    private fun m10() {
        setContent {

//            Modifier.hea

            val key = modifierLocalOf { "key" }
            Modifier
                .modifierLocalProvider(key) { "value" }
                .modifierLocalConsumer {
                    val value = key.current
                }


            Modifier.onGloballyPositioned { }

//            LookaheadLayout(content = {})
            Modifier.onPlaced { }
            Modifier.onSizeChanged { }
//            OnRemeasuredModifier
            Modifier.semantics {

            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun m9() {
        setContent {
//            Text(text = "345", Modifier.stringData())
            CustomLayout {
                Text(text = "123", Modifier.stringData())
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    @Composable
    fun CustomLayout(modifier: Modifier = Modifier, content: @Composable CustomLayoutScope.() -> Unit) {
        Layout({ CustomLayoutScope.content() }, modifier) { measurables, constraints ->
            measurables.forEach {
                val data = it.parentData as? String // 取数据
            }
            layout(100, 100) {

            }
        }
    }

    @LayoutScopeMarker // 隔离环境,避免污染。可以参考Row的源码
    @Immutable
    object CustomLayoutScope {

        @Stable
        fun Modifier.stringData() = then(object : ParentDataModifier {
            override fun Density.modifyParentData(parentData: Any?): Any? { // parentData 右边提供的parentData
                return "111" // Modifier 提供数据
            }
        })
    }


    private fun m8() {

        setContent {

            Row {
                Box(
                    Modifier
                        .size(40.dp)
                        .background(Color.Blue)
                        .weight(1f)
                ) {

                }
                Box(
                    Modifier
                        .size(40.dp)
                        .background(Color.Red)
                ) {

                }
                Box(
                    Modifier
                        .size(40.dp)
                        .background(Color.Green)
                ) {

                }
            }

//            ParentDataModifier
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    private fun m7() {
        setContent {
            Modifier.clickable { }
            Box(Modifier.combinedClickable(onLongClick = {

            }, onDoubleClick = {

            }) {

            }) {

            }

            Modifier.pointerInput(Unit) {
//                detectTapGestures() {
//
//                }

                forEachGesture {
                    awaitPointerEventScope {
                        val down = awaitFirstDown()

                    }
                }

            }

        }
    }

    private fun m6() {
        setContent {
            Box(
                Modifier
                    .background(Color.Red)
                    .background(Color.Green)
                    .size(80.dp)
            ) {

            }
        }
    }

    private fun m5() {
        setContent {
            Text("123", Modifier.drawWithContent {
                drawCircle(Color.Green)
                drawContent()
//                        drawContext.canvas.drawCircle(center, 20f, Paint())

            }
                /*.background(Color.Red)*/)

//            Modifier.alpha()
        }
    }

    private fun m4() {
        setContent {
            Box(
                Modifier
                    .background(Color.Red)
                    .size(40.dp)

            ) {
                Box(
                    Modifier
                        .size(40.dp)
                        .background(Color.Green)
                        .padding(10.dp)

                ) {

                }
            }
        }
    }

    private fun m3() {
        setContent {
            Text("123",
                Modifier
                    .layout { measurable, constraints ->
                        val result = measurable.measure(constraints)
                        layout(result.width, result.height) {
                            result.placeRelative(10.dp.roundToPx(), 0)
                        }
                    }
                    .background(Color.Red))
        }

    }

    private fun m2() {
        setContent {
            Modifier.composed { Modifier.padding(8.dp) }
        }
    }

    private fun m1() {
        setContent {
            Box(
                Modifier
                    .background(Color.Green)
                    .size(80.dp)
                    .size(40.dp)
            ) {

            }
        }
    }
}