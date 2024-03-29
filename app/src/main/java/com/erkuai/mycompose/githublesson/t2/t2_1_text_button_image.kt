package com.erkuai.mycompose.githublesson.t2

import android.graphics.Paint
import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.IconToggleButton
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.erkuai.mycompose.R
import com.erkuai.mycompose.githublesson.widgets.TutorialHeader
import com.erkuai.mycompose.githublesson.widgets.TutorialText2
import kotlinx.coroutines.delay

@Composable
fun T2_1_Content() {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            TutorialHeader(text = "Text")
            TutorialText2(text = "Font Color")
            TextSampleRow {
                CustomText(text = "Red 700", color = Color(0xffd32f2f))
                CustomText(text = "Purple 700", color = Color(0xff7B1FA2))
                CustomText(text = "Green 700", color = Color(0xff1976D2))
                CustomText(text = "Teal 700", color = Color(0xff00796B))
            }

            /*****************/

            TutorialHeader(text = "Button")
            ButtonExample()

            /*****************/

//            BitmapExample()
//            BitmapExample2()
//            BitmapExample3()
            BitmapExample4()

        }
    }
}

@Composable
fun BitmapExample4() {
    Image(
        modifier = Modifier
            .height(100.dp)
            .width(100.dp),
        painter = painterResource(id = R.drawable.photo), contentDescription = "",
        contentScale = ContentScale.Crop,
    )
}

@Composable
fun BitmapExample3() {

    var start by remember { mutableStateOf(false) }
    val animate = animateFloatAsState(targetValue = if (start) 45f else 0f, label = "")
    Image(
        modifier = Modifier
            .height(100.dp)
            .width(100.dp)
            .graphicsLayer {
                rotationX = animate.value
                rotationY = animate.value
                rotationZ = animate.value
            }
            .shadow(8.dp, CircleShape)
            .clip(CircleShape)
            .clickable {
                start = !start
            },
        painter = painterResource(id = R.drawable.photo), contentDescription = "",
        contentScale = ContentScale.Crop,
    )

}

@Composable
fun BitmapExample2() {
    val bitmap = ImageBitmap.imageResource(id = R.drawable.photo)
    val onDraw: DrawScope.() -> Unit = {
        drawImage(bitmap)

        val paint = Paint().apply {
            textSize = 50f
            color = Color.Red.toArgb()
        }
        drawContext.canvas.nativeCanvas.drawText("android", center.x, center.y, paint)
    }


    val (width, height) = with(LocalDensity.current) {
        Pair(
            bitmap.width.toDp(),
            bitmap.height.toDp()
        )
    }
    Canvas(
        modifier = Modifier
            .width(width)
            .height(height),
        onDraw = onDraw,
    )
}

@Composable
fun BitmapExample() {
    TutorialText2(text = "Draw over ImageBitmap with Painter")

    val bitmap = ImageBitmap.imageResource(id = R.drawable.photo)
    val customPainter = object : Painter() {
        override val intrinsicSize: Size
            get() = Size(bitmap.width.toFloat(), bitmap.height.toFloat())

        override fun DrawScope.onDraw() {
            drawImage(bitmap)
            drawLine(
                color = Color.Red,
                start = Offset(0f, 0f),
                end = Offset(intrinsicSize.width, intrinsicSize.height),
                strokeWidth = 5f
            )
        }

    }
    Image(painter = customPainter, contentDescription = "")
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterialApi::class)
@Composable
fun ButtonExample() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Button(
            modifier = Modifier.background(Color.Gray),
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Red
            ),
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
            Text(text = "Button")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "TextButton")
        }
        OutlinedButton(onClick = { /*TODO*/ }) {
            Text(text = "OutlinedButton")
        }
    }

    Row {
        IconButton(onClick = { /*TODO*/ }) {
            Log.i("wmkwmk", "----------------------------------------------")

            Icon(imageVector = Icons.Default.AccountBox, contentDescription = "")
        }

        Log.i("wmkwmk", "--------------------333333333--------------------------")


        var checked by remember { mutableStateOf(false) }
        val tint by animateColorAsState(
            targetValue = if (checked) Color(0xffE91E63) else Color(0xffB0BEC5),
            animationSpec = tween(durationMillis = 1000),
            label = "",
        )
        IconToggleButton(
            checked = checked,
            onCheckedChange = {
                checked = it
            },
        ) {
            Log.i("wmkwmk", "ButtonExample: ")
            Icon(
                imageVector = Icons.Filled.Favorite,
                tint = tint,
                contentDescription = "",
            )
        }
    }


    Row {
        Box(
            modifier = Modifier.clickable(
                indication = rememberRipple(

                ),
                interactionSource = remember { MutableInteractionSource() },
            ) {

            }
        ) {

        }

        ExtendedFloatingActionButton(text = { /*TODO*/ }, onClick = { /*TODO*/ })
    }

    FlowRow(maxItemsInEachRow = 2, modifier = Modifier.fillMaxWidth()) {
        Chip(onClick = { /*TODO*/ }) {
            Text(text = "Chip")
        }
    }

    Badge(backgroundColor = Color.Red) {
        Text(text = "111")
    }

    BadgedBox(badge = { Text(text = "000") }) {
        Text(text = "2222")
    }


    var content by remember { mutableStateOf("") }
    TextField(
        value = content,
        onValueChange = {
            content = it
        },
        placeholder = {
            Text(text = "placeholder")
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
    )

    var str by remember { mutableStateOf("") }

}

@Composable
private fun CustomText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    style: TextStyle = LocalTextStyle.current
) {

    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        style = style
    )
}

@Composable
fun TextSampleRow(content: @Composable () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        content()
    }
}

