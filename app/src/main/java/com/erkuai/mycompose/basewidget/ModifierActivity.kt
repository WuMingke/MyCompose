package com.erkuai.mycompose.basewidget

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.swipeable
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.CombinedModifier
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.*
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.drawText
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.erkuai.mycompose.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

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
//        m11()
//        m12()

//        m15()
//        m17()

//        m18()
//        m19()
//        m20()
//        m23()
    }

    private fun m24() {
        val linearLayout = LinearLayout(this)
        val composeView = ComposeView(this).apply {
            setContent {
                // TODO:
            }
        }
        linearLayout.addView(composeView)

        setContent {
            AndroidView(factory = {
                TextView(it).apply {
                    text = ""
                }
            }) {

            }
        }

        Snapshot

    }

    private fun m23() {
        setContent {
            Text("123123132",
                Modifier
                    .background(Color.Red)
                    .height(200.dp)
                    .pointerInput(Unit) {
                        awaitPointerEventScope {

                        } // 第一种，只执行一次

                        awaitEachGesture { // 第二种，循环取
                            while (true) {
                                var event = awaitPointerEvent()
                                Log.i("wmkwmk", "发生了1：${event.type}")
                                event = awaitPointerEvent()
                                Log.i("wmkwmk", "发生了2：${event.type}")
                                event = awaitPointerEvent()
                                Log.i("wmkwmk", "发生了3：${event.type}")
                                event = awaitPointerEvent()
                                Log.i("wmkwmk", "发生了4：${event.type}")
                            }
                        }
                    })
        }
    }

    private fun m22() {
        setContent {
            Modifier.pointerInput(Unit) {
                detectTransformGestures { centroid, pan, zoom, rotation ->

                }
            }
        }
    }

    private fun m21() {
        setContent {
            Modifier.pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    // 可以做二维滑动
                }
            }
        }
    }


    private fun m20() {
        setContent {
            var offsetY by remember { mutableStateOf(0f) }
            val dispatcher = remember { NestedScrollDispatcher() }
            val connection = remember {
                object : NestedScrollConnection {
                    override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset {
//                        return super.onPostScroll(consumed, available, source)
                        offsetY += available.y
                        return available
                    }
                }
            }
            Column(modifier = Modifier
                .height(300.dp)
                .offset { IntOffset(0, offsetY.roundToInt()) }
                .draggable(rememberDraggableState(onDelta = {
                    val consumed = dispatcher.dispatchPreScroll(Offset(0f, it), NestedScrollSource.Drag)
                    offsetY += it - consumed.y
                    dispatcher.dispatchPostScroll(Offset(0f, it), Offset.Zero, NestedScrollSource.Drag)
                }), orientation = Orientation.Vertical)
                .nestedScroll(connection, dispatcher)
            ) {
                repeat(10) {
                    Text(text = "第 $it 项")
                }

                LazyColumn() {
                    items(15) {
                        Text(text = "内部第 $it")
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    private fun m19() {
        setContent {
            Modifier.scrollable(
                rememberScrollableState {
                    it
                }, orientation = Orientation.Vertical, overscrollEffect = ScrollableDefaults.overscrollEffect()
            )

        }
    }

    private fun m18() {
        setContent {
            Column {
                val source = remember { MutableInteractionSource() }
                Text(text = "wewewewewewew", Modifier.draggable(state = rememberDraggableState {
                    Log.i("wmkwmk", "m18: 移动了 $it 像素")
                }, orientation = Orientation.Horizontal, interactionSource = source))

                val isDragged by source.collectIsDraggedAsState()
                Text(if (isDragged) "拖动中" else "静止")


                Button(
                    onClick = { /*TODO*/ }, interactionSource = source
                ) {

                }
                val isPressed by source.collectIsPressedAsState()
                Text(if (isPressed) "按下" else "抬起")

            }
        }
    }

    private fun m17() {
        setContent {
            val state = rememberLazyListState()
            val data = arrayListOf<Int>().apply {
                repeat(50) {
                    add(it)
                }
            }
            Column {
                LazyColumn(modifier = Modifier.weight(1f), state = state) {
                    items(data) {
                        Text(text = "Number:$it")
                    }
                }
                val scope = rememberCoroutineScope()
                Button(onClick = {
                    scope.launch {
                        state.animateScrollToItem(10)
                    }
                }) {

                }
            }

        }
    }

    private fun m16() {
//        Modifier.clickable { }
//        Modifier.combinedClickable { }
//        Modifier.pointerInput {
//            detectTapGestures { }
//        }
//        Modifier.pointerInput(Unit){
//            awaitEachGesture {
//                val pointerEvent = awaitPointerEvent()
//            }
//        }
//        Modifier.scrollable()
//        Modifier.draggable()
    }

    private fun m15() {
        setContent {
            Text(text = "123", Modifier.drawBehind {
                drawRect(Color.Red)
            })
            Modifier.drawWithContent {
                drawContent()
            }

            Box(Modifier.padding(50.dp)) {
                val bitmap = ImageBitmap.imageResource(R.drawable.img)
                Canvas(
                    Modifier
                        .size(200.dp)
                        .graphicsLayer { rotationX = 45f }) {
                    rotate(45f) {
                        drawImage(bitmap, dstSize = IntSize(size.width.roundToInt(), size.height.roundToInt()))
                    }

                    drawIntoCanvas {

                    }
                }
            }

        }
    }


    private fun m14() {
        setContent {
            suspend {
                snapshotFlow { }.collect {

                }
            }

        }
    }

    private val mLiveData = MutableLiveData("")
    private val mFlow = MutableStateFlow("")

    private fun m13() {
        setContent {
            mLiveData.observeAsState()

            LaunchedEffect(Unit) {
                mFlow.collect {

                }
            }

            mFlow.collectAsState()

            produceState(initialValue = "") {
                value = ""
            }
        }
    }

    private fun m12() {
        setContent {
            var welcome by remember { mutableStateOf("welcome") }
            CustomLaunchedEffect(welcome = welcome)
            Button(onClick = { welcome = "gun" }) {
                Text(welcome)
            }
        }
    }

    @Composable
    fun CustomLaunchedEffect(welcome: String) {
        var result by remember { mutableStateOf(welcome) }
        result = welcome
        LaunchedEffect(Unit) {
            delay(3000)
            Log.i("wmkwmk", "CustomLaunchedEffect: $result")
        }
    }

    private fun m11() {
        setContent {
            var showText by remember { mutableStateOf(false) }
            Button(onClick = { showText = !showText }) {
                Text(text = "button")
                if (showText) {
                    Text(text = "111")
                }
                DisposableEffect(key1 = Unit, effect = {
                    Log.i("wmkwmk", "@@@ in in in in")
                    onDispose {
                        Log.i("wmkwmk", "@@@ out out out")
                    }
                })
            }

        }
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