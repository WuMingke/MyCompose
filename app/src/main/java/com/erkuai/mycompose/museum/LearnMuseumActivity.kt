package com.erkuai.mycompose.museum

import android.inputmethodservice.Keyboard.Row
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.drag
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.gestures.horizontalDrag
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Colors
import androidx.compose.material.DismissValue
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.ListItem
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Surface
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Switch
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.lightColors
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material.rememberDismissState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.IntrinsicMeasurable
import androidx.compose.ui.layout.IntrinsicMeasureScope
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.paging.Pager
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.erkuai.mycompose.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

class LearnMuseumActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_learn_museum)

        val data = arrayListOf<String>().apply {
            repeat(30) {
                add("learn !!!")
            }
        }

        setContent {
//            MessageCard(name = "android")
//            Conversation(messages = data)
//            TestAlertDialog()
//            TestDialog()
//            TestButton()
//            TestImage()
//            TestSlider()
//            TestText()
//            TestTextField()
//            TestBox()
//            TestFlowLayout()
//            TestScaffold()
//            TestBottomSheetLayout()
//            TestPager()
//            TestSwipeToRefresh()
//            TestLayout()
//            TestIntrinsicSize(text1 = "11", text2 = "22")
//            IntrinsicRow()
//            TestSubcomposeRow()
//            TestAnimation()
//            TestAnimation2()
//            TestAnimation3()
//            Gesture()
//            TestSwipe()
//            TestAnimation4()
//            TestAnimation5()
//            SwitchBlock()
//            TestCustomTheme()
//            TestScroll()
//            TestScroll2()
//            TestDraggable()
//            TestSwipeable()
//            TestGesture2()
//            TestCustomFling()
//            TestDraw()
//            TestDrawContent()
//            TestReCompose()
        }

        testSnapShot()
    }

    private fun testSnapShot() {
        val TAG = "wmkwmk=="
        val dog = Dog()
        dog.name.value = "Spot"
        val snapshot1 = Snapshot.takeMutableSnapshot()
        val snapshot2 = Snapshot.takeMutableSnapshot()
        Log.i(TAG, "${dog.name.value}")
        snapshot1.enter {
            dog.name.value = "Fido"
            Log.i(TAG, "in snapshot1 => ${dog.name.value}")
        }
        Log.i(TAG, "${dog.name.value}")
        snapshot2.enter {
            dog.name.value = "Fluffy"
            Log.i(TAG, "in snapshot2 => ${dog.name.value}")
        }
        Log.i(TAG, "before snapshot1 apply => ${dog.name.value}")
        snapshot1.apply()
        Log.i(TAG, "after snapshot1 apply => ${dog.name.value}")

        Log.i(TAG, "before snapshot2 apply => ${dog.name.value}")
        snapshot2.apply()
        Log.i(TAG, "after snapshot2 apply => ${dog.name.value}")

    }

    class Dog {
        var name: MutableState<String> = mutableStateOf("")
    }

    @Composable
    fun TestReCompose() {
        var text by remember { mutableStateOf("") }
        Log.i("=== wmkwmk ===", "Foo")
        Button(onClick = {
            text = "$text $text"
        }.also {
            Log.i("=== wmkwmk ===", "button")
        }) {
            Log.i("=== wmkwmk ===", "button lambda")
            Text(text = text).also {
                Log.i("=== wmkwmk ===", "text")
            }
        }
    }

    @Composable
    fun TestDrawContent() {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .size(100.dp)
                    .drawWithContent {
                        drawCircle(
                            Color.Red, 18.dp.toPx() / 2f,
                            center = Offset(drawContext.size.width, 0f)
                        )
                        drawContent()
                    }) {
                Image(painter = painterResource(id = R.drawable.img), contentDescription = "")
            }
        }
    }

    @Composable
    fun TestDraw() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val radius = 300.dp
            val width = 30.dp
            Canvas(modifier = Modifier.size(radius), onDraw = {
                drawCircle(
                    brush = Brush.sweepGradient(listOf(Color.Red, Color.Green, Color.Red), Offset(radius.toPx() / 2f, radius.toPx() / 2f)),
                    radius = radius.toPx() / 2f,
                    style = Stroke(width = width.toPx())
                )
            })
        }
    }


    // TODO: mingke 测试
    @Composable
    fun TestList(pager: Pager<Int, String>) {
        val items = pager.flow.collectAsLazyPagingItems()
        LazyColumn(content = {
            items(items, key = {

            }) {

            }
        })
    }

    @Composable
    fun TestCustomFling() {
        var offset by remember {
            mutableStateOf(
                androidx.compose.animation.core.Animatable(
                    Offset.Zero,
                    Offset.VectorConverter
                )
            )
        }
        Modifier.pointerInput(Unit) {
            forEachGesture {
                offset.stop()
            }

            awaitEachGesture {
                awaitFirstDown()
                // TODO: mingke 未实现
            }

        }

    }

    @Composable
    fun TestGesture2() {
        var str by remember { mutableStateOf("") }

        val modifier = Modifier.pointerInput(Unit) {
            detectDragGestures(
                onDragStart = {

                },
                onDragEnd = {

                },
                onDragCancel = {

                }
            ) { change, dragAmount ->

            }

            detectTapGestures(
                onDoubleTap = null,
                onLongPress = null,
                onPress = {

                }
            ) {

            }

            detectTransformGestures { centroid, pan, zoom, rotation ->

            }

            awaitEachGesture {

                val event = awaitPointerEvent() // 事件之源
                val change = event.changes[0]
                str = "${event.type}\n${change.position.x}\n${change.position.y}"
            }

            awaitPointerEventScope {

//                awaitFirstDown()
            }
        }


        Box(modifier = Modifier
            .pointerInput(Unit) {
                awaitEachGesture {

                    val event = awaitPointerEvent() // 事件之源
                    val change = event.changes[0]
                    str = "${event.type}\n${change.position.x}\n${change.position.y}"

                }


            }
            .fillMaxSize()) {
            Text(text = str, color = Color.Black)
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun TestSwipeable() {
        val blockSize = 48.dp
        val blockSizePx = with(LocalDensity.current) { blockSize.toPx() }
        val swipeableState = rememberSwipeableState(initialValue = Status.CLOSE)
        val anchors = mapOf(0f to Status.CLOSE, blockSizePx to Status.OPEN)
        Box(
            modifier = Modifier
                .size(height = blockSize, width = blockSize * 2)
                .background(Color.LightGray)
        ) {
            Box(modifier = Modifier
                .offset {
                    IntOffset(swipeableState.offset.value.roundToInt(), 0)
                }
                .swipeable(
                    state = swipeableState,
                    anchors = anchors,
                    thresholds = { from, to ->
                        if (from == Status.CLOSE) {
                            FractionalThreshold(0.3f)
                        } else {
                            FractionalThreshold(0.5f)
                        }
                    },
                    orientation = Orientation.Horizontal
                )
                .size(blockSize)
                .background(Color.DarkGray)
            )
        }
    }

    enum class Status {
        CLOSE, OPEN
    }

    @Composable
    fun TestDraggable() {
        var offsetX by remember { mutableFloatStateOf(0f) }
        val length = 50.dp
        val boxSlideLengthPx = with(LocalDensity.current) { length.toPx() }
        val draggableState = rememberDraggableState(onDelta = {
            offsetX = (offsetX + it).coerceIn(0f, 3 * boxSlideLengthPx)
        })
        Box(
            modifier = Modifier
                .width(length * 4)
                .height(length)
                .background(Color.LightGray)
        ) {
            Box(
                modifier = Modifier
                    .size(length)
                    .offset {
                        IntOffset(offsetX.roundToInt(), 0)
                    }
                    .draggable(
                        orientation = Orientation.Horizontal,
                        state = draggableState
                    )
                    .background(Color.DarkGray)
            ) {

            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun TestScroll2() {
        var offset by remember { mutableFloatStateOf(0f) }
        var offset2 by remember { mutableFloatStateOf(0f) }
        val scrollState = rememberScrollableState(consumeScrollDelta = {
            offset += it
            it
        })
        Box(
            modifier = Modifier
                .size(150.dp)
                .scrollable(
                    orientation = Orientation.Vertical,
                    state = scrollState
                )
                .combinedClickable {
                    offset2 = 200f
                }
                .background(Color.LightGray)
//                .clickable {
//                    offset = 100f
//                }
        ) {
            Column(modifier = Modifier) {
                Text(text = offset.toString())
                Text(text = offset2.toString())
            }
        }
    }

    @Composable
    fun TestScroll() {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .size(100.dp)
                .background(Color.LightGray)
                .verticalScroll(scrollState)
        ) {
            repeat(10) {
                Text(text = "Item $it", modifier = Modifier.padding(vertical = 3.dp))
            }
        }
    }

    @Composable
    fun TestCustomTheme() {
        CustomTheme {
            Text(text = "this is test custom theme", color = MaterialTheme.colors.primary)
        }
    }

    @Composable
    fun CustomTheme(content: @Composable () -> Unit) {

        MaterialTheme(
            colors = lightColors(primary = Color.Red),
            content = content
        )
    }

    sealed class SwitchState {
        object OPEN : SwitchState()
        object CLOSE : SwitchState()
    }

    @Composable
    fun SwitchBlock() {
        var selectState: SwitchState by remember { mutableStateOf(SwitchState.CLOSE) }
        val transition = updateTransition(targetState = selectState, label = "")
        val selectBarPaddingValues by transition.animateDp(transitionSpec = { tween(1000) }, label = "") {
            when (it) {
                SwitchState.CLOSE -> 40.dp
                else -> 0.dp
            }
        }
        val textAlpha by transition.animateFloat(transitionSpec = { tween(1000) }, label = "") {
            when (it) {
                SwitchState.CLOSE -> 1f
                else -> 0f
            }
        }
        Box(modifier = Modifier
            .size(150.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.Red)
            .clickable {
                selectState = if (selectState == SwitchState.OPEN) SwitchState.CLOSE else SwitchState.OPEN
            }) {
            Image(
                painter = painterResource(id = R.drawable.img), contentDescription = "",
                contentScale = ContentScale.FillWidth
            )
            Text(
                text = "click me", fontSize = 30.sp, fontWeight = FontWeight.W900,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.Center)
                    .alpha(textAlpha)
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(top = selectBarPaddingValues)
                    .background(color = Color(0xff5fb878))
            ) {
                Row(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .alpha(1 - textAlpha)
                ) {
                    Icon(
                        Icons.Default.Star, contentDescription = "",
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "chose", fontSize = 20.sp,
                        fontWeight = FontWeight.W900, color = Color.White
                    )
                }
            }
        }
    }

    @Composable
    fun TestAnimation5() {
        var text by remember { mutableStateOf("") }
        var focusState by remember { mutableStateOf(false) }
        val size by animateFloatAsState(targetValue = if (focusState) 1f else 0.5f, label = "")
        val focusManager = LocalFocusManager.current
        Column(modifier = Modifier
            .fillMaxSize()
            .clickable {
                if (focusState) {
                    focusManager.clearFocus()
                }
            }) {
            TextField(
                value = text, onValueChange = { text = it },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .onFocusChanged {
                        focusState = it.isFocused
                    }
                    .fillMaxWidth(size)
            )
        }

    }

    @Composable
    fun TestAnimation4() {
        var change by remember { mutableStateOf(false) }
        var flag by remember { mutableStateOf(false) }
        Log.i("=== wmkwmk ===", "-----------change  1------- $change")

        val buttonSize by animateDpAsState(
            targetValue = if (change) 32.dp else 24.dp, label = "",
            finishedListener = {
                Log.i("=== wmkwmk ===", "-----------结束------- ${it.value}")
            })
        if (buttonSize == 32.dp) { /// 直到变到了32dp
            change = false
        }
        Log.i("=== wmkwmk ===", "-----------change  2------- $change")
        IconButton(onClick = {
            change = true
            flag = !flag
        }) {

            Log.i("=== wmkwmk ===", "-----------change  value------- ${buttonSize.value}")

            Icon(
                Icons.Rounded.Favorite, "",
                modifier = Modifier.size(buttonSize),
                tint = if (flag) Color.Red else Color.Gray
            )

        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun TestSwipe() {
//        Box(modifier = Modifier
//            .background(Color.Red)
//            .fillMaxWidth()
//            .height(50.dp)
//            .swipeToDismiss {
//                Toast
//                    .makeText(this, "delete", Toast.LENGTH_SHORT)
//                    .show()
//            })
        val dismissState = rememberDismissState()
        SwipeToDismiss(state = dismissState, background = {
            val color by animateColorAsState(
                when (dismissState.targetValue) {
                    DismissValue.Default -> Color.LightGray
                    DismissValue.DismissedToEnd -> Color.Green
                    DismissValue.DismissedToStart -> Color.Red
                }, label = ""
            )
            Box(
                Modifier
                    .fillMaxSize()
                    .background(color)
            )
        }) {
            Card {
                androidx.compose.material3.ListItem(
                    headlineContent = { Text("Cupcake") },
                    supportingContent = { Text("Swipe me left or right!") }
                )
                HorizontalDivider()
            }
        }
    }

    private fun Modifier.swipeToDismiss(
        onDismissed: () -> Unit
    ): Modifier = composed {
        val offsetX = remember { androidx.compose.animation.core.Animatable(0f) }
        pointerInput(Unit) {
            val decay = splineBasedDecay<Float>(this)
            coroutineScope {
                while (true) {
                    val pointerId = awaitPointerEventScope { awaitFirstDown().id }
                    val velocityTracker = VelocityTracker()
                    offsetX.stop()
                    awaitPointerEventScope {
                        horizontalDrag(pointerId) { change ->
                            launch { offsetX.snapTo(offsetX.value + change.positionChange().x) }
                            velocityTracker.addPosition(change.uptimeMillis, change.position)
                        }
                    }
                    val velocity = velocityTracker.calculateVelocity().x
                    val targetOffsetX = decay.calculateTargetValue(offsetX.value, velocity)
                    offsetX.updateBounds(lowerBound = -size.width.toFloat(), upperBound = size.width.toFloat())
                    launch {
                        if (targetOffsetX.absoluteValue <= size.width) {
                            offsetX.animateTo(targetValue = 0f, initialVelocity = velocity)
                        } else {
                            offsetX.animateDecay(velocity, decay)
                            onDismissed()
                        }
                    }
                }
            }
        }.offset {
            Log.i("=== wmkwmk ===", "${offsetX.value.roundToInt()}")
            IntOffset(offsetX.value.roundToInt(), 0)
        }
    }

    @Composable
    fun Gesture() {
        val offset = remember {
            androidx.compose.animation.core.Animatable(initialValue = Offset(0f, 0f), typeConverter = Offset.VectorConverter)
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    coroutineScope {
                        while (true) {
                            val position = awaitPointerEventScope {
                                awaitFirstDown().position
                            }
                            launch {
                                offset.animateTo(position)
                            }
                        }
                    }
                }
        ) {
            val offsetPoint = with(LocalDensity.current) { 20.dp.toPx() } // 居中！！！
            val centerOffset = Offset(offset.value.x - offsetPoint, offset.value.y - offsetPoint)
            Box(modifier = Modifier
                .size(40.dp)
                .offset { centerOffset.toIntOffset() }
                .background(Color.Red))
        }

    }

    private fun Offset.toIntOffset() = IntOffset(x.roundToInt(), y.roundToInt())


    // TODO: mingke  如何只保证在按钮点击的时候才运行动画？？？
    @Composable
    fun TestAnimation3() {
        var flag by remember { mutableStateOf(false) }
        val color = remember { Animatable(Color.Gray) }
        Column {
            Box(
                modifier = Modifier
                    .size(300.dp)
                    .background(color = color.value)
            )
            Button(onClick = { flag = !flag }) {
                Text(text = "change")
            }
        }
        LaunchedEffect(key1 = flag, block = {
            color.animateTo(
                targetValue = if (flag) Color.Green else Color.Red,
                animationSpec = tween(2000)
            )
        })
    }

    @Composable
    fun TestAnimation2() {
        var flag by remember { mutableStateOf(false) }
        Column {
            Crossfade(targetState = flag, animationSpec = tween(2000), label = "") {
                when (it) {
                    true -> Box(
                        modifier = Modifier
                            .background(Color.Red)
                            .size(200.dp)
                    )

                    else -> Box(
                        modifier = Modifier
                            .background(Color.Blue)
                            .size(200.dp)
                    )
                }
            }
            Button(onClick = { flag = !flag }) {
                Text(text = "change")
            }
        }
    }

    @Composable
    fun TestAnimation() {
        var state by remember { mutableStateOf(true) }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(
                visible = state,
                enter = slideInVertically { -40 } + expandVertically(expandFrom = Alignment.Top) + fadeIn(initialAlpha = 0.3f),
                exit = shrinkHorizontally() + fadeOut()
            ) {
                Text(text = "this is a normal text")
            }
            Spacer(modifier = Modifier.padding(vertical = 50.dp))
            Button(onClick = { state = !state }) {
                Text(text = if (state) "隐藏" else "显示")
            }
        }
    }

    @Composable
    fun TestSubcomposeRow() {
        SubcomposeRow(modifier = Modifier.fillMaxWidth(), text = {
            Text(
                text = "11", modifier = Modifier
                    .background(color = Color.Red)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
            Text(text = "22", modifier = Modifier.wrapContentWidth())
        }) {
            val height = with(LocalDensity.current) {
                it.toDp()
            }
            Divider(
                modifier = Modifier
                    .width(4.dp)
                    .height(height), color = Color.Black
            )
        }
    }

    @Composable
    fun SubcomposeRow(modifier: Modifier, text: @Composable () -> Unit, divider: @Composable (Int) -> Unit) {
        SubcomposeLayout(modifier = modifier) { constraints ->
            var maxHeight = 0
            val placeables = subcompose("text", text).map {
                val placeable = it.measure(constraints)
                maxHeight = placeable.height.coerceAtLeast(maxHeight)
                placeable
            }
            val dividerPlaceable = subcompose("divider") {
                divider(maxHeight)
            }.map {
                it.measure(constraints.copy(minWidth = 0))
            }

            val midPos = constraints.maxWidth / 2
            layout(constraints.maxWidth, constraints.maxHeight) {
                placeables.forEach {
                    it.placeRelative(0, 0)
                }
                dividerPlaceable.forEach {
                    it.placeRelative(midPos, 0)
                }
            }
        }
    }

    /// 自定义固有特性测量
    @Composable
    fun IntrinsicRow() {
        TestIntrinsicSizeCustom(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Text(
                text = "Left", modifier = Modifier
                    .wrapContentWidth(Alignment.Start)
                    .layoutId("main")
            )
            Divider(
                color = Color.Black, modifier = Modifier
                    .width(4.dp)
                    .fillMaxHeight()
                    .layoutId("divider")
            )
            Text(
                text = "Right", modifier = Modifier
                    .wrapContentWidth(Alignment.End)
                    .layoutId("main")
            )

        }
    }

    @Composable
    fun TestIntrinsicSizeCustom(modifier: Modifier, content: @Composable () -> Unit) {
        Layout(
            modifier = modifier,
            content = content,
            measurePolicy = object : MeasurePolicy {
                override fun MeasureScope.measure(measurables: List<Measurable>, constraints: Constraints): MeasureResult {
                    val dividerConstraints = constraints.copy(minWidth = 0)
                    val mainPlaceables = measurables.filter {
                        it.layoutId == "main"
                    }.map {
                        it.measure(constraints = constraints)
                    }
                    val dividerPlaceable = measurables.first { it.layoutId == "divider" }.measure(dividerConstraints)
                    val midPos = constraints.maxWidth / 2
                    return layout(constraints.maxWidth, constraints.maxHeight) {
                        mainPlaceables.forEach {
                            it.placeRelative(0, 0)
                        }
                        dividerPlaceable.placeRelative(midPos, 0)
                    }
                }

                /**
                 * @param measurables 子组件的宽高信息
                 * @param width 父组件所能提供的最大宽度
                 */
                override fun IntrinsicMeasureScope.minIntrinsicHeight(measurables: List<IntrinsicMeasurable>, width: Int): Int {
                    var maxHeight = 0
                    measurables.forEach {
                        // minIntrinsicHeight(width = width) 在给定宽度的情况下能够保证正确显示的最小高度
                        maxHeight = it.minIntrinsicHeight(width = width).coerceAtLeast(maxHeight)
                    }
                    return maxHeight
                }

                override fun IntrinsicMeasureScope.maxIntrinsicHeight(measurables: List<IntrinsicMeasurable>, width: Int): Int {
                    return 0
                }
            })
    }

    @Composable
    fun TestIntrinsicSize(modifier: Modifier = Modifier, text1: String, text2: String) {
        Row(
            modifier = modifier
                .background(Color.Gray)
                .height(IntrinsicSize.Min)
        ) {
            Text(
                text = text1,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp)
                    .wrapContentWidth(Alignment.Start),
            )
            Divider(
                color = Color.Red, modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
            )
            Text(
                text = text2,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp)
                    .background(Color.Yellow)
                    .wrapContentWidth(Alignment.End),
            )

        }
    }

    @Composable
    fun TestLayout() {
//        Row {
//            Text(text = "normal", modifier = Modifier.padding(top = 24.dp))
//            Text(text = "firstBaselineToTop", modifier = Modifier.firstBaselineToTop(24.dp)) // 自定义Modifier和layout
//        }

        CustomLayout {
            Text(text = "1")
            Text(text = "2")
            Text(text = "3")
            Text(text = "4")
        }

    }

    @Composable
    fun CustomLayout(
        modifier: Modifier = Modifier,
        content: @Composable () -> Unit
    ) {
        Layout(modifier = modifier, content = content) { measurables, constraints ->
            val placeables = measurables.map {
                it.measure(constraints.copy(minWidth = 0, minHeight = 0))
            }
            var yPosition = 0
            layout(constraints.maxWidth, constraints.maxHeight) {
                placeables.forEach { placeable ->
                    placeable.placeRelative(x = 0, y = yPosition)
                    yPosition += placeable.height
                }
            }
        }
    }


    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun TestSwipeToRefresh() {
        val list = remember { List(4) { "Item $it" }.toMutableStateList() }
        var refreshing by remember { mutableStateOf(false) }
        val scope = rememberCoroutineScope()
        val state = rememberPullRefreshState(refreshing = refreshing, onRefresh = {
            scope.launch {
                refreshing = true
                delay(1000)
                list += "Item ${list.size + 1}"
                refreshing = false
            }
        })
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(state)
        ) {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(list) {
                    Text(text = it)
                }
            }
            PullRefreshIndicator(
                refreshing = refreshing,
                state = state,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun TestPager() {
        val pages = listOf("home", "favorite", "mine")
        val pagerState = rememberPagerState(initialPage = 1) { pages.size }
        val scope = rememberCoroutineScope()
        Column {
            TabRow(
                modifier = Modifier.fillMaxWidth(),
                selectedTabIndex = pagerState.currentPage,
                indicator = {/// 这里源码测量的时候就是maxWidth，有问题。
//                    Text(
//                        modifier = Modifier.background(Color.Yellow),
//                        text = pages[pagerState.currentPage], fontSize = 16.sp, fontWeight = FontWeight.W900, style = TextStyle(color = Color.Red)
//                    )
                    Box(
                        Modifier
                            .width(it[pagerState.currentPage].width)
                            .height(TabRowDefaults.IndicatorHeight)
                            .background(color = Color.Green)
                    )
                }
            ) {
                pages.forEachIndexed { index, s ->
                    Tab(selected = pagerState.currentPage == index, onClick = { scope.launch { pagerState.animateScrollToPage(index) } }) {
                        Text(text = s)
                    }
                }
            }

            HorizontalPager(
                state = pagerState, modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray)
            ) { page ->
                Text(text = "Page: $page", modifier = Modifier.background(Color.Red))
            }
        }


    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun TestBottomSheetLayout() {
        val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
        val scope = rememberCoroutineScope()

        ModalBottomSheetLayout(
            sheetState = state,
            sheetContent = {
                Column {
                    ListItem {
                        Text(text = "no.1")
                    }
                }
            }
        ) {
            Text(text = "BottomSheetLayout", modifier = Modifier.clickable {
                scope.launch {
                    state.show()
                }
            })
        }

        BackHandler(
            enabled = state.isVisible,
            onBack = {
                scope.launch {
                    state.hide()
                }
            }
        )
    }

    @Composable
    fun TestScaffold() {
        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()
        var selectItem by remember { mutableStateOf(0) }
        val items = listOf("home", "favorite", "setting")
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { scaffoldState.drawerState.open() } }) {
                            Icon(Icons.Default.Menu, "")
                        }
                    },
                    title = {
                        Text(text = "this is title")
                    }
                )
            },
            bottomBar = {
//                BottomNavigation {
//                    items.forEachIndexed { index, item ->
//                        BottomNavigationItem(
//                            selected = selectItem == index,
//                            onClick = { selectItem = index },
//                            icon = {
//                                when (index) {
//                                    0 -> Icon(Icons.Filled.Home, "")
//                                    1 -> Icon(Icons.Filled.Favorite, "")
//                                    else -> Icon(Icons.Filled.Settings, "")
//                                }
//                            },
//                        )
//                    }
//                }
                MyNavigation(selectItem) {
                    selectItem = it
                }
            },
            drawerContent = {
                AppDrawContent(scaffoldState = scaffoldState, scope = scope)
            },
        ) {
            AppContent(modifier = Modifier.padding(it), item = items[selectItem])
        }
    }

    @Composable
    fun MyNavigation(select: Int, newIndex: (newSelected: Int) -> Unit) {
        BottomNavigation(backgroundColor = Color.White) {
            (0..2).forEachIndexed { index, _ ->
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .clickable(
                            onClick = {
                                newIndex.invoke(index)
                            },
                            indication = null,
                            interactionSource = MutableInteractionSource()
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    MyNavigationIcon(index = index, selectedItem = select)
                    Spacer(modifier = Modifier.padding(top = 2.dp))
                    AnimatedVisibility(visible = select == index) {
                        Surface(
                            shape = CircleShape,
                            modifier = Modifier.size(5.dp),
                            color = Color(0xff252527)
                        ) { }
                    }
                }
            }
        }
    }

    @Composable
    fun MyNavigationIcon(index: Int, selectedItem: Int) {
        val alpha = if (selectedItem != index) 0.5f else 1f

        CompositionLocalProvider(LocalContentAlpha provides alpha) {
            when (index) {
                0 -> Icon(Icons.Filled.Home, "")
                1 -> Icon(Icons.Filled.Favorite, "")
                else -> Icon(Icons.Filled.Settings, "")
            }
        }
    }

    @Composable
    fun AppContent(modifier: Modifier, item: String) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "current page is:")
            Text(text = item)
        }
    }

    @Composable
    fun AppDrawContent(scaffoldState: ScaffoldState, scope: CoroutineScope) {

    }

    @OptIn(ExperimentalMaterialApi::class, ExperimentalLayoutApi::class)
    @Composable
    fun TestFlowLayout() {
        val filters = listOf(
            "Washer/Dryer", "Ramp access", "Garden", "Cats OK", "Dogs OK", "Smoke-free"
        )
        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            filters.forEach { title ->
                var select by remember { mutableStateOf(false) }
//                FilterChip(selected = select, onClick = { select = !select },
//                    leadingIcon = {
//                        if (select) {
//                            Icon(Icons.Default.Check, null)
//                        }
//                    }) {
//                    Text(text = title)
//                }
                Text(text = title)
            }
        }
    }

    @Composable
    fun TestBox() {
        Box {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(Color.Green)
            )
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.Red)
            )
            Text(text = "12")
        }
    }

    @Composable
    fun TestTextField() {
        Column {
            var text by remember { mutableStateOf("") }
//            TextField(value = text, onValueChange = {
//                text = it
//            })
            BasicTextField(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .background(Color.Red),
                value = text,
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                ),
                onValueChange = { text = it },
                decorationBox = {
                    if (text.isEmpty()) {
                        Text(text = "placeholder")
                    } else {
                        it.invoke()
                    }
                }
            )
        }

    }

    @Composable
    fun TestText() {
        Column(
            modifier = Modifier
                .background(color = Color.Red)
                .fillMaxWidth(),
        ) {
            Text(text = "this is a title", style = MaterialTheme.typography.h6)
            Text(text = "this is a content", style = MaterialTheme.typography.body2)
            Text(
                text = "this tests spacing",
                style = TextStyle(
                    fontWeight = FontWeight.W900,
                    fontSize = 20.sp,
                    letterSpacing = 7.sp
                ),
            )
            val context = LocalContext.current
            Text(
                text = "this tests maxLines".repeat(4),
                style = MaterialTheme.typography.h3,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 30.sp,
                modifier = Modifier.clickable(
                    onClick = {
                        Toast.makeText(context, "111", Toast.LENGTH_SHORT).show()
                    },
                    indication = null,
                    interactionSource = remember(::MutableInteractionSource)
                )
            )
            Text(text = buildAnnotatedString {
                append("now:")
                withStyle(style = SpanStyle(fontWeight = FontWeight.W900)) {
                    append("2023")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF0E9FF2),
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(" 11/06")
                }
            })

            val text = buildAnnotatedString {
                append("勾选即代表同意")
                pushStringAnnotation(
                    tag = "tag",
                    annotation = "content content content ..."
                )
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF0E9FF2),
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("用户协议")
                }
                pop()
            }
            ClickableText(text = text, onClick = {
                text.getStringAnnotations(
                    tag = "tag",
                    start = it,
                    end = it,
                ).firstOrNull()?.let { annotation ->
                    Toast.makeText(context, "click ${annotation.item}", Toast.LENGTH_SHORT).show()
                }
            })


        }
    }

    @Composable
    fun TestSlider() {
        var progress by remember { mutableStateOf(0f) }
        Slider(
            value = progress,
            colors = SliderDefaults.colors(
                thumbColor = Color.White,
                activeTrackColor = Color(0xFF0079D3)
            ),
            onValueChange = {
                progress = it
            })
    }

    @Composable
    fun TestImage() {  // Coil
        val url =
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsafe-img.xhscdn.com%2Fbw1%2F55c38ab5-7cff-46ef-ab2a-5149b36d30c7%3FimageView2%2F2%2Fw%2F1080%2Fformat%2Fjpg&refer=http%3A%2F%2Fsafe-img.xhscdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1701836210&t=f87ded5ec42382e01b341f509557682f"
//        Image(painter = rememberAsyncImagePainter(model = url), contentDescription = "")
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .crossfade(3000)
                .build(),
            contentDescription = ""
        )
    }

    @Composable
    fun TestButton() {
        Button(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Filled.Favorite, contentDescription = "",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "confirm")
        }
    }

    @Composable
    fun TestDialog() {
        var flag by remember { mutableStateOf(false) }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Button(onClick = { flag = true }) {
                Text(text = "open dialog")
            }
        }
        if (flag) {
            Dialog(onDismissRequest = { /*flag = false*/ }) {
                Box(
                    modifier = Modifier
                        .size(300.dp)
                        .background(Color.Cyan),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        LinearProgressIndicator()
                        Text(text = "loading ...")
                    }
                }
            }
        }
    }

    @Composable
    fun TestAlertDialog() {
        val openDialog = remember { mutableStateOf(true) }
        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = { openDialog.value = false },
                title = {
                    Text(
                        text = "location service",
                        fontWeight = FontWeight.W700,
                        style = MaterialTheme.typography.h6
                    )
                },
                text = {
                    Text(
                        text = "this means we will offer good location service",
                        fontSize = 16.sp
                    )
                },
                buttons = {
                    Row {
                        Text(
                            text = "confirm",
                            modifier = Modifier.clickable {
                                openDialog.value = false
                            },
                        )
                        Text(
                            text = "cancel",
                            modifier = Modifier.clickable {
                                openDialog.value = false
                            },
                        )
                    }
                },
            )
        }
    }

    @Composable
    fun Conversation(messages: List<String>) {
        LazyColumn {
            items(messages) {
                MessageCard(name = it)
            }
        }
    }

    @Composable
    fun MessageCard(name: String) {
        var isExpanded by remember { mutableStateOf(false) }
        val surfaceColor by animateColorAsState(
            targetValue = if (isExpanded) Color(0xFFCCCCCC) else MaterialTheme.colors.surface,
            label = ""
        )
        Surface(
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    isExpanded = !isExpanded
                },
            shape = MaterialTheme.shapes.medium,
            elevation = 5.dp,
            color = surfaceColor
        ) {
            Row(modifier = Modifier.padding(8.dp)) {
                Image(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape),
                    painter = painterResource(id = R.drawable.img),
                    contentScale = ContentScale.Crop,
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                Column(modifier = Modifier.align(alignment = Alignment.CenterVertically)) {
                    Text(text = "Hello,$name !", color = MaterialTheme.colors.secondaryVariant)
                    Text(
                        text = "another text,another text,another text,another text",
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        modifier = Modifier.animateContentSize()
                    )
                }
            }
        }
    }

    //    @Preview(showSystemUi = true)
    @Composable
    fun PreviewMessageCard() {
        MessageCard(name = "Android")
    }

}

fun Modifier.firstBaselineToTop(
    firstBaselineToTop: Dp
) = this.then(
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        val firstBaseline = placeable[FirstBaseline]
        val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
        val height = placeable.height + placeableY
        layout(placeable.width, height) {
            placeable.placeRelative(0, placeableY)
        }
    }
)
