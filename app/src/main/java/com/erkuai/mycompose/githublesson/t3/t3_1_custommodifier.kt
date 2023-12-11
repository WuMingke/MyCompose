package com.erkuai.mycompose.githublesson.t3

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.neverEqualPolicy
import androidx.compose.runtime.referentialEqualityPolicy
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.erkuai.mycompose.R
import com.erkuai.mycompose.githublesson.widgets.TutorialText2
import kotlin.math.roundToInt
import kotlin.random.Random

@Composable
fun T3_1_CONTENT() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .background(Color.LightGray)
        ) {
            Text(
                text = "align START with space",
                modifier = Modifier
                    .background(Color(0xFF8BC34A))
                    .customAlign()

            )
            Text(
                text = "align CENTER with space",
                modifier = Modifier
                    .background(Color(0xFF8BC34A))
                    .customAlign(align = HorizontalAlign.CENTER)
            )
            Text(
                text = "align END with space",
                modifier = Modifier
                    .background(Color(0xFF8BC34A))
                    .customAlign(align = HorizontalAlign.END)
            )
        }

        Column {
            var offset by remember { mutableFloatStateOf(0f) }
            Row(modifier = Modifier.border(2.dp, Color.Red)) {
                Box(modifier = Modifier
                    .offset {
                        IntOffset(offset.toInt(), 0)

                    }
                    .zIndex(2f)
                    .shadow(2.dp)
                    .border(2.dp, Color.Green)
                    .background(Color(0xffFFA726))
                    .size(120.dp)
                )
                Box(
                    modifier = Modifier
                        .zIndex(1f)
                        .background(Color(0xff42A5F5))
                        .size(120.dp)
                )
            }

            Text(text = "offset ${offset.round2Digits()}")
            Slider(
                value = offset,
                onValueChange = {
                    offset = it
                },
                valueRange = 0f..1000f
            )
        }

        Column {
            val context = LocalContext.current
            var scaleX by remember { mutableFloatStateOf(1f) }
            var width by remember { mutableFloatStateOf(0f) }
            Row(modifier = Modifier.border(2.dp, Color.Red)) {
                Image(
                    modifier = Modifier
                        .graphicsLayer {
                            translationX = (width * scaleX - width) / 2
                            this.scaleX = scaleX
                        }
                        .onSizeChanged {
                            width = it.width.toFloat()
                        }
                        .zIndex(2f)
                        .size(120.dp),
                    painter = painterResource(id = R.drawable.photo),
                    contentDescription = ""
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text("Scale: ${(scaleX.round2Digits())}")
            Slider(
                value = scaleX,
                onValueChange = {
                    scaleX = it
                },
                valueRange = 0f..10f
            )
        }


        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.LightGray)
                .wrapContentHeight(unbounded = true/*, align = Alignment.Top*/)
                .wrapContentWidth(unbounded = true)
        ) {
            Text(
                modifier = Modifier.background(Color.Green),
                text = "Starting: Intent { act=android.intent.action.MAIN cat=[android.intent.category.LAUNCHER] cmp=com.erkuai.mycompose/.githublesson.GithubLessonActivity }\n",
                fontSize = 25.sp
            )
        }


        CustomLayout(
            modifier = Modifier
                .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                .background(getRandomColor())
                .fillMaxWidth(),
            label = "Parent"
        ) {
            println("===> Parent Scope")
            CustomLayout(
                modifier = Modifier
                    .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                    .background(getRandomColor())
                    .fillMaxWidth()
                    .size(50.dp),
                label = "Child1"
            ) {
                println("===> Child1 Scope")

                Box(
                    modifier = Modifier
                        .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                        .background(getRandomColor())
                        .size(100.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    println("===> Box Scope")
                    Text(text = "Box Content", color = Color.White)
                }
            }
            CustomLayout(
                modifier = Modifier
                    .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                    .background(getRandomColor()),
                label = "Child2 Outer"
            ) {
                println("===> Child2 Outer Scope")

                CustomLayout(
                    modifier = Modifier
                        .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                        .background(getRandomColor()),
                    label = "Child2 Inner"
                ) {
                    println("===> Child2 Inner Scope")
                    Text("Child2 Bottom Content")
                }
            }
        }

        Column(
            modifier = Modifier
                .background(getRandomColor())
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            var counter by remember { mutableStateOf(0) }
            val myData = remember { MyData() }
            var myVal = 0

            Text("myVal: $myVal, myData value: ${myData.value}")

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                onClick = {
                    counter++
                    myVal++
                    myData.value = myData.value + 1
                }) {
                Text("Counter: $counter, myVal: $myVal, myData value: ${myData.value}")
            }
        }

        SnapshotPolicySample()
    }
}


@Composable
private fun SnapshotPolicySample() {
    var counter by remember {
        mutableStateOf(
            value = 0,
            // 🔥Setting policy changes whether recomposition should
            // be triggered when same value is set in this example
            policy = referentialEqualityPolicy(),
//            policy = structuralEqualityPolicy(),
//            policy = neverEqualPolicy()
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {

        Button(
            modifier = Modifier.fillMaxWidth(),
            // Depending on which policy is used setting same value will trigger recomposition
            onClick = { counter = 1 }) {
            SideEffect {
                println("Recomposing with $counter")
            }
            Text(text = "Click to set MutableState value")
        }

        Text(
            modifier = Modifier
                .border(2.dp, getRandomColor())
                .fillMaxWidth()
                .padding(10.dp),
            text = "counter: $counter",
            fontSize = 16.sp
        )
    }
}

class MyData(var value: Int = 0)


fun getRandomColor() = Color(
    red = Random.nextInt(256),
    green = Random.nextInt(256),
    blue = Random.nextInt(256),
    alpha = 255
)

@Composable
fun CustomLayout(
    modifier: Modifier = Modifier,
    label: String = "",
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content,
        measurePolicy = { measurables, constraints ->
            val placeables = measurables.map {
                it.measure(constraints.copy(minWidth = 0, minHeight = 0))
            }
            val contentWidth = placeables.maxOf { it.width }
            val contentHeight = placeables.sumOf { it.height }
            val layoutWidth = if (constraints.hasBoundedWidth && constraints.hasFixedWidth) {
                constraints.maxWidth
            } else {
                contentWidth.coerceIn(constraints.minWidth, constraints.maxWidth)
            }
            val layoutHeight = if (constraints.hasBoundedHeight && constraints.hasFixedHeight) {
                constraints.maxHeight
            } else {
                contentHeight.coerceIn(constraints.minHeight, constraints.maxHeight)
            }
            layout(layoutWidth, layoutHeight) {
                var y = 0
                placeables.forEach { placeable ->
                    placeable.placeRelative(0, y)
                    y += placeable.height
                }
            }
        }
    )
}

private fun Float.round2Digits() = (this * 100).roundToInt() / 100f


enum class HorizontalAlign {
    START, CENTER, END
}

fun Modifier.customAlign(space: Int = 60, align: HorizontalAlign = HorizontalAlign.START) =
    this.then(
        layout { measurable, constraints ->
            val placeable = measurable.measure(constraints)
            val width = placeable.measuredWidth + 2 * space
            layout(width, placeable.measuredHeight) {
                when (align) {
                    HorizontalAlign.START -> {
                        placeable.placeRelative(0, 0)
                    }

                    HorizontalAlign.CENTER -> {
                        placeable.placeRelative(space, 0)
                    }

                    HorizontalAlign.END -> {
                        placeable.placeRelative(2 * space, 0)
                    }
                }
            }
        }
    )