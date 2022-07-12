package com.erkuai.mycompose.basewidget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
    }
}