package com.erkuai.mycompose.basewidget

import android.os.Bundle
import android.webkit.WebSettings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erkuai.mycompose.ui.theme.MyComposeTheme

class BaseWidgetActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    BaseWidget()
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
                Text(text = "2312314124")
            }

//            val context = Linto
            ClickableText(text = string, onClick = {

            })
        }
    }
}