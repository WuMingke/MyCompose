package com.erkuai.mycompose.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.lifecycleScope
import coil.compose.AsyncImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TestStateActivity : ComponentActivity() {


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        var nick = "mingKE"
//
//        setContent {
//            Text(text = nick)
//        }
//    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
////        var nick = "mingKE"
//        val nick = mutableStateOf("mingKE")
//
//        setContent {
//            Text(text = nick.value, fontSize = 30.sp)
//        }
//
//
//        lifecycleScope.launch {
//            delay(3000)
//            nick.value = "Nicole"
//        }
//    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent { //重组作用域

//            Wrap {
            var name by remember { mutableStateOf("mingKE") }
            Text(text = name, fontSize = 30.sp)
            lifecycleScope.launch {
                delay(3000)
                name = "Nicole"
            }
//            }

        }


        // 依赖于编译器插件，可能发生变化的地方，会被包起来  ->  重组作用域
        // 包一个Button
        // remember 包 -> "缓存"作用,防止多次初始化
        // 带参数的remember：

        // State Hoisting：状态提升
        // 什么是状态(控件属性)，无状态、"有状态"，状态都是指的内部状态
        // 怎么获取状态？
        // 只能传参
        // eg:TextField
        // 为什么？单一数据源的体现 -> 让上层修改数据，而不是内部修改，让内部不会是"数据源" -> 数据往下，事件往上的环
        // MVI
        //
        // https://juejin.cn/post/7022624191723601928?from=from_parent_mindnote
        // https://zhuanlan.zhihu.com/p/513822897
        // https://developer.android.com/jetpack/guide/ui-layer?hl=zh-cn

    }

}

@Composable
fun LoadImage(url: String) {
    val resultUrl = remember(url) { url }
    AsyncImage(model = resultUrl, contentDescription = "")
}

@Composable
fun MyText() {
    Text(text = "")
}