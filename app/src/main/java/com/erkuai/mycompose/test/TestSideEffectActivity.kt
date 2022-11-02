package com.erkuai.mycompose.test

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*

class TestSideEffectActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 副作用：函数的副作用，它的执行会修改外部的变量，实际是改变程序外部状态的行为
        setContent {
            SideEffect {

            }

            TestDisposableEffect()
        }
    }


    fun a() {

    }

    fun b() {
        var isBig = false
        isBig = true
    }

    var isSmall = false
    fun c() {
        isSmall = true
    }

    // 以下这个打印，也是属于副作用
    fun d() {
        println("output info")
    }

    // 副作用 实际定义：
    // 一个函数的调用，能不能被它的返回值所代替，如果可以，就是没有副作用；如果不行，就是有副作用
    // 替换所导致的差异，就是副作用本身

    // 意义：
    // 帮助开发者对函数的功能进行描述和要求：例如 写一个无副作用的函数？

    // 有什么用：
    // @Composable 函数都是无副作用的，这是框架要求的，如果有副作用，那会导致程序的行为不可预期
    // 为什么程序的行为不可预期？因为它的调用不可预期 -> 主要是优化了重组的行为
    // eg：HasSideEffect()

    // 不要在 @Composable 函数里面写有副作用的代码

    // 最直接解决：SideEffect
    // 保证 {} 里面包裹的代码执行完，再来判断中途是否有新的重组，然后再做新的更新

    // DisposableEffect -> 可丢弃的 SideEffect，加了一个离开界面的回调
    // eg:TestDisposableEffect

    // LaunchedEffect -> 同 DisposableEffect ，只是 LaunchedEffect 是面对协程的
    // eg:TestLaunchedEffect
    // Compose 里面写协程的2种方式 ：LaunchedEffect,rememberCoroutineScope

    // rememberUpdatedState ???不懂

    // rememberCoroutineScope


}

@Composable
fun TestLaunchedEffect() {

    LaunchedEffect(Unit) {

    }

//    rememberUpdatedState(newValue = )
}

@Composable
fun TestDisposableEffect() {

    Button(onClick = { /*TODO*/ }) {
        DisposableEffect(Unit) {
            println("进入界面了")
            onDispose {
                println("离开界面了")
            }
        }
    }

}

@Composable
fun HasSideEffect() {
    var count = 0
    Column {
        for (i in 0 until 3) {
            count++
        }
    }
    Text(text = "$count") // Column 如果发生多次重组，那么count的值不可预期
}