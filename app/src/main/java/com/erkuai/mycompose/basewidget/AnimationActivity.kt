package com.erkuai.mycompose.basewidget

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.TestLooperManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.erkuai.mycompose.R
import kotlin.math.roundToInt

class AnimationActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
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

                TestSwipeable()

            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TestSwipeable() {
    val squareSize = 48.dp

    val swipableState = rememberSwipeableState(initialValue = 0)
    val sizePx = with(LocalDensity.current) { squareSize.toPx() }
    val anchors = mapOf(0f to 0, sizePx to 1)
    Box(
        modifier = Modifier
            .width(96.dp)
            .swipeable(
                state = swipableState,
                anchors = anchors,
                thresholds = { from, to -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal
            )
    ) {
        Box(modifier = Modifier
            .offset { IntOffset(swipableState.offset.value.roundToInt(), 0) }
            .size(squareSize)
            .background(Color.DarkGray))
    }

}


@OptIn(ExperimentalAnimationApi::class)
@Composable
inline fun TestAnimation() {
    var visible by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .size(360.dp)
            .padding(10.dp)
    ) {
        Button(onClick = { visible = !visible }) {
            Text(text = stringResource(R.string.string_visible_animation))
        }

//        AnimatedVisibility(visible = visible) {
//            Text(
//                text = "全球领导人年度峰会走向数字化：小屏幕承载大决策",
//                modifier = Modifier.size(150.dp)
//            )
//        }

        Text(
            text = "全球领导人年度峰会走向数字化：小屏幕承载大决策.全球领导人年度峰会走向数字化：小屏幕承载大决策.",
            modifier = Modifier
                .size(150.dp)
                .animateContentSize(),
            maxLines = if (visible) 2 else Int.MAX_VALUE
        )
    }
}