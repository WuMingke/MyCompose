package com.erkuai.mycompose.basewidget

import android.os.Bundle
import android.util.Log
import android.widget.OverScroller
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

class AnimationActivity2 : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        content1()
//        content2()
//        content3()
//        content4()
        content5()
    }

    private fun content5() {
        setContent {
            Column {
                var shown by remember { mutableStateOf(true) }
                Crossfade(targetState = shown, animationSpec = tween(3000)) {
                    if (it) {
                        Box(
                            Modifier
                                .size(50.dp)
                                .background(Color.Red)
                        ) {

                        }
                    } else {
                        Box(
                            Modifier
                                .size(25.dp)
                                .background(Color.Green)
                        ) {

                        }
                    }
                }

                Button(onClick = { shown = !shown }) {
                    Text(text = "change")
                }
            }

        }
    }

    private fun content4() {
        setContent {
            var shown by remember { mutableStateOf(true) }
            Column() {
                AnimatedVisibility(
                    visible = shown, enter = fadeIn(initialAlpha = 0.3f) + slideIn(tween()) { IntOffset(-it.width, -it.height) },
                ) {
                    Box(
                        Modifier
                            .size(50.dp)
                            .background(Color.Red)
                    ) {

                    }
                }
                Button(onClick = { shown = !shown }) {
                    Text(text = "change")
                }

            }

        }
    }

    private fun content3() {
        setContent {
            var big by remember { mutableStateOf(false) }
//            val size by animateDpAsState(if (big) 96.dp else 48.dp)
            val transition = updateTransition(big, label = "")
            val size by transition.animateDp(label = "") {
                if (it) 96.dp else 48.dp
            }

            val corner by transition.animateDp(label = "") {
                if (it) 0.dp else 18.dp
            }

            Box(
                Modifier
                    .size(size)
                    .clip(RoundedCornerShape(corner))
                    .background(Color.Green)
                    .clickable {
                        big = !big
                    }) {}
        }
    }

    private fun content2() {
        setContent {
            val anim = remember {
                Animatable(0.dp, Dp.VectorConverter)
            }
            val decay = remember {
                exponentialDecay<Dp>()
            }
            LaunchedEffect(key1 = Unit, block = {
                delay(1000)
                try {
                    anim.animateDecay(2000.dp, decay)
                } catch (e: Exception) {
                    Log.i("wmkwmk", "content2: 打断")
                }
            })
//            LaunchedEffect(key1 = Unit, block = {
//                delay(1500)
//                anim.animateDecay((-1000).dp, decay)
//            })

            LaunchedEffect(key1 = Unit, block = {
                delay(1100)
                anim.stop()

            })
            Box(
                Modifier
                    .padding(0.dp, anim.value, 0.dp, 0.dp)
                    .size(100.dp)
                    .background(Color.Green)
            ) {

            }
        }
    }

    private fun content1() {
        var big by mutableStateOf(false)

        setContent {
            val size by animateDpAsState(if (big) 100.dp else 50.dp)

            val anim = remember { Animatable(8.dp, Dp.VectorConverter) }

            //            splineBasedDecay<>() // Android自带的惯性滑动算法  同 OverScroller
            //            rememberSplineBasedDecay()
            val decay = remember {
                exponentialDecay<Dp>(2f)  // 指数衰减
            }

            LaunchedEffect(key1 = big, block = {
                //                anim.snapTo(200.dp)
                //                anim.animateTo(if (!big) 100.dp else 50.dp, spring(0.1f))
                //                anim.animateTo(if (!big) 100.dp else 50.dp, repeatable(2, tween()))
                //                anim.animateTo(if (!big) 100.dp else 50.dp, snap(3000))
                //                anim.animateTo(if (!big) 100.dp else 50.dp, keyframes {
                //                    durationMillis = 1000
                //                    150.dp at 150
                //                    200.dp at 160 with FastOutLinearInEasing  // easing 影响下一段动画
                //                    300.dp at 1000
                //                })
                anim.animateDecay(1000.dp, decay) {
                    Log.i("wmkwmk", "onCreate: ${this.value}")

                }
            })

            Box(
                Modifier
                    .padding(top = anim.value)
                    .size(48.dp)
                    .background(Color.Green)
                    .clickable {
                        big = !big
                    }) {

            }
        }
    }
}