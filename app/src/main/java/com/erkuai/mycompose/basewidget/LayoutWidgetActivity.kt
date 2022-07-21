package com.erkuai.mycompose.basewidget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.erkuai.mycompose.ui.theme.MyComposeTheme

class LayoutWidgetActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
//            MyComposeTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
////                    BaseLayout()
//                    BaseLayout2()
//                }
//            }

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
//                ConsLayout()
                MyLazyColumn()
            }
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyLazyColumn() {
    val dataList = arrayListOf<Int>().apply {
        for (index in 0..100) {
            add(index)
        }
    }

    LazyColumn(
        modifier = Modifier
            .background(Color.Cyan)
            .fillMaxWidth(),
        content = {
            items(dataList.size, key = { index -> index }) { data ->
                Text(text = "$data")
            }

            stickyHeader {
                Text(text = "粘性标题")
            }

            items(dataList.size) { data ->
                Text(text = "$data")
            }
        }
    )

}

@Composable
fun ConsLayout() {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (one, two) = createRefs()
        val three = createRef()

        DefaultText2(text = "One",
            Modifier.constrainAs(one) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top, margin = 16.dp)
            })
    }
}

@Composable
fun DefaultText2(text: String, modifier: Modifier) {
    Text(
        text = text,
        modifier = modifier
            .size(100.dp)
            .background(Color.Gray),
        fontSize = 30.sp,
        textAlign = TextAlign.Center
    )
}


@Composable
fun BaseLayout2() {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DefaultText(text = "1")
        DefaultText(text = "2")
        DefaultText(text = "3")
    }
}

@Composable
fun DefaultText(text: String) {
    Text(
        text = text,
        Modifier
            .size(100.dp)
            .background(Color.Gray),
        fontSize = 30.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
fun BaseLayout() {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "One")
        Text(text = "Two")
        Text(text = "Three")
    }
}