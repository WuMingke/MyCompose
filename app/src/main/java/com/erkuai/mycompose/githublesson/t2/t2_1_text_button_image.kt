package com.erkuai.mycompose.githublesson.t2

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.erkuai.mycompose.githublesson.t1.widgets.TutorialHeader
import com.erkuai.mycompose.githublesson.t1.widgets.TutorialText2

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

        }
    }
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

