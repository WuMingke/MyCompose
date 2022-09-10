package com.erkuai.mycompose.basewidget

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.erkuai.mycompose.R
import com.erkuai.mycompose.ui.theme.MyComposeTheme

class BaseWidgetActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BaseWidget()
//                    Text(text = stringResource(id = R))
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun BaseWidget() {

//    var index by remember { mutableStateOf(0) }
    var index = 0

    Column(modifier = Modifier.fillMaxSize()) {
        val show = true
        val showName = if (show) "显示" else "不显示"
        Text(text = showName)


        Button(onClick = {
            index++
        }) {
            Text(text = "Add")
        }
        Text(text = "$index")

        Column {
            Text(
                modifier = Modifier.width(100.dp), text = "Hello !", color = Color.Green,
                fontFamily = FontFamily.Cursive, textAlign = TextAlign.Center
            )
//            Text(text = stringResource())
            val string = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Blue)) {
                    append("N")
                }
                append("icole")

                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Red)) {
                    append("!!!")
                }
                append("zzz")
            }

            Text(text = string)

            SelectionContainer {
                Text(text = stringResource(id = R.string.app_name))
//                Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "")
            }

//            val context = Linto
            ClickableText(text = string, onClick = {

            })

            var text by remember { mutableStateOf("hello") }
            TextField(value = text, onValueChange = {
                text = it
            }, label = { Text(text = "hint") })

            OutlinedTextField(value = text, onValueChange = {
                text = it
            }, label = { Text(text = "标签") })

            BasicTextField(
                value = text, onValueChange = {
                    text = it
                }, keyboardOptions = KeyboardOptions(
                    KeyboardCapitalization.Characters,
                    autoCorrect = true
                )
            )


            Button(
                onClick = { text = "按了按钮。。" }, Modifier.padding(5.dp),
                border = BorderStroke(
                    0.dp,
                    Brush.linearGradient(
                        colors = listOf(Color.Blue, Color.Gray),
                        start = Offset(0f, 0f),
                        end = Offset(100f, 100f)
                    )
                )
            ) {
                Text(text = "按钮")
            }


            Image(
                bitmap = BitmapFactory.decodeResource(
                    LocalContext.current.resources,
                    R.drawable.img
                )
                    .asImageBitmap(), contentDescription = ""
            )

            CircularProgressIndicator()

            LinearProgressIndicator(progress = 0.5f)

        }
    }
}