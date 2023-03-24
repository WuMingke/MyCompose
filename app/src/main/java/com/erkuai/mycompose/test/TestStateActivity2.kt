package com.erkuai.mycompose.test

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

class TestStateActivity2 : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {

            val names = remember { mutableStateListOf("111", "222") }

            val processNames by remember {
                derivedStateOf {
                    names.map {
                        it.uppercase()
                    }
                }
            }

            Column {
                processNames.forEachIndexed { index, s ->
                    Text(s, Modifier.clickable {
                        names[index] = "000"
                    })
                }

                CompositionLocalProvider(localName.provides("mingke")) {
                    ShowName()
                }
            }

            LocalContext.current

            MaterialTheme {

            }
        }
    }
}

val localName = compositionLocalOf { "" }


@Composable
fun ShowName() {
    Text(text = localName.current)
}













