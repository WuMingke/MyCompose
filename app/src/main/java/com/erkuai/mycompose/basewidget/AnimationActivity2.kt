package com.erkuai.mycompose.basewidget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class AnimationActivity2 : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var big by mutableStateOf(false)

        setContent {
            val size by animateDpAsState(if (big) 100.dp else 50.dp)

            val anim = remember { Animatable(8.dp, Dp.VectorConverter) }
            LaunchedEffect(key1 = big, block = {
//                anim.snapTo(200.dp)
                anim.animateTo(if (!big) 100.dp else 50.dp, spring(Spring.DampingRatioHighBouncy))
            })

            Box(
                Modifier
                    .size(anim.value)
                    .background(Color.Green)
                    .clickable {
                        big = !big
                    }) {

            }
        }
    }
}