package com.erkuai.mycompose.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TestSideEffectActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // side effect

        // 前置知识：重组作用域、remember

        // 维基百科：https://zh.m.wikipedia.org/zh-sg/%E5%89%AF%E4%BD%9C%E7%94%A8_(%E8%AE%A1%E7%AE%97%E6%9C%BA%E7%A7%91%E5%AD%A6)
        // 附带效应
        // 副作用：函数的副作用，它的执行会修改外部的变量，实际是改变程序外部状态的行为
        setContent {

            SideEffect {

            }

//            TestDisposableEffect()
//            TestDisposableEffect2()
            TestRememberUpdatedState2()
//            TestRememberUpdatedState2()

//            Button(onClick = { /*TODO*/ }, modifier = Modifier.clickable {
//                lifecycleScope.launch {
//
//                }
//            }) {
//
//            }
        }

    }


    fun a() :Unit{

    }

    fun b() {
        var isToday = false
        isToday = true
    }

    var isToday = false
    fun c() {
        isToday = true
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
    // @Composable 函数是用来展示界面的，且单纯用来展示界面，而不能掺杂其它的逻辑，不能有副作用
    // 为什么程序的行为不可预期？因为它的调用不可预期 -> 主要是优化了重组的行为
    // @Composable 函数可能在执行了一半的时候被取消，那么假如有两段副作用代码，前面一段执行了，后面一段没执行，那么就会导致程序出错
    //             这是一种情况，还比如，函数内多次调用
    // eg：HasSideEffect()

    // 不要在 @Composable 函数里面写有副作用的代码，但是确实需要的时候怎么办？
    // API:SideEffect DisposableEffect LaunchedEffect rememberUpdatedState

    // 最直接解决：SideEffect
    // 在每次重组的过程中，保证 {} 里面包裹的代码不会先执行，而是等着外层 {} 所有的代码在这一轮重组时执行完，
    // 再来判断中途是否有新的重组，然后再做新的更新
    // eg:SolveSideEffect()
    // （本质上是一个回调！！）

    // DisposableEffect -> 可丢弃的 SideEffect，// 加了一个离开界面的回调
    // 什么叫可丢弃的？
    // var show = true
    // if (show){
    //    Text("")
    // }
    // {} 里面当 show 为 false 的时候，就离开了组合，它里面的内容就是可丢弃的
    // eg:TestDisposableEffect()、TestDisposableEffect2()

    // LaunchedEffect -> 同 DisposableEffect ，只是 LaunchedEffect 是面对协程的,源码都差不多
    // eg:TestLaunchedEffect
    // Compose 里面写协程的2种方式 ：LaunchedEffect,rememberCoroutineScope

    // rememberUpdatedState // 难的地方

    // rememberCoroutineScope


}

@Composable
fun TestRememberCoroutineScope() {

    val scope = rememberCoroutineScope()

    remember {
        scope.launch {

        }
    }


    // 直接调用是不行的，其实就是 LaunchedEffect 的实现：
    // 1、提供 CoroutineScope 启动协程
    // 2、remember 包裹，防止反复执行，因为在协程执行的过程中，可能会发生重组导致反复执行

    // rememberCoroutineScope 的意义在于，解决 非 Composable 函数里面使用协程
    Button(onClick = { /*TODO*/ }, modifier = Modifier.clickable {
        scope.launch {
            delay(2000)
        }

        // 也可以：
//        lifecycleScope.launch {
//
//        }
        // 但是"生命周期"变大了
    }) {

    }


}

@Composable
fun TestRememberUpdatedState2() {

    var content by remember { mutableStateOf("Hello") }
    Button(onClick = { content = "bye" }) {
        Text(text = content)

//        LaunchedEffect(Unit) {
//            delay(3000)
//            println("content:$content")
//        }
        InnerLaunchedEffect(content = content)
//        InnerLaunchedEffect2(content = content)
    }
}

@Composable
fun <T> rememberUpdatedState(newValue: T): State<T> = remember {
    mutableStateOf(newValue)
}.apply { value = newValue }

@Composable
fun InnerLaunchedEffect(content: String) {

    var newContent by remember { mutableStateOf(content) }
    newContent = content

    val newContent2 by rememberUpdatedState(newValue = content)
    LaunchedEffect(Unit) {
        delay(3000)
        println("content:$newContent2")
    }
}

@Composable
fun InnerLaunchedEffect2(content: String) {
    var newContent by remember { mutableStateOf(content) }
    newContent = content

//    val name by rememberUpdatedState(newValue = content)

    LaunchedEffect(Unit) {
        delay(3000)
        println("content:$newContent")
    }
}

@Composable
fun TestRememberUpdatedState() {

    // LaunchedEffect key不改变不重启，保证性能，

    var show by remember { mutableStateOf(false) }
    val content by remember { mutableStateOf("Hello") }
    LaunchedEffect(Unit) {
        delay(2000)
        println("content:$content")
    }
    // 像这种情况，show 改变了，但是 {} 里面的内容本来也没更新，没必要重启更新，怎么办
    // 因为这里有延时，我只需要在延时之后拿到最新的值就行了
    // 也就是说 show 改变了 不更新 {} 怎么做

    // -> rememberUpdatedState

    // 相似于 DisposableEffect ，它的特性容易将它用于订阅，先订阅后移除的时候，不应该重新订阅
}

@Composable
fun TestLaunchedEffect() {

    LaunchedEffect(Unit) {
        // "onDispose" 在这里是取消协程
        delay(5000)

    }

    // 根据之前的结论，那么在这里 Compose 里面使用协程，其实是
    // 1、在 Composable 组件显示完成之后，去启动一个协程
    // 2、在 key 发生改变的时候，去重启协程
    // 场景：弹窗显示3s之后消失 之类的，包括 Compose 里面的动画实现，都有协程的身影

}

@Composable
fun TestDisposableEffect() {

    Button(onClick = { /*TODO*/ }) {

        DisposableEffect(Unit) { // 所在的 @Composable 组件在某一时刻出现在界面，然后又因为某些条件不显示了
            println("进入界面了") // eventbus

            onDispose {
                println("离开界面了")
            }
        }
        // 对于key参数：用来判断是否 {} 需要重新执行，并且 onDispose 里面的代码会先执行
        // key 变->重新执行，key 不变->不重新执行  : eg:TestDisposableEffect2()
    }

}

@Composable
 fun TestDisposableEffect2() {
    // 重组作用域：非inline且无返回值的Composable函数或lambda
    var show by remember { mutableStateOf(false) }
    Button(onClick = { show = !show }) {
        Text(text = "111")
        if (show) {
            Text(text = "2222")
        }

        SideEffect {
            println("111 进入界面了") // 一定会重复执行
        }

        DisposableEffect(Unit) {
            println("222 进入界面了")
//            delay(3000)
            onDispose {
                println("222 离开界面了")
            }
        }
    }
}

@Composable
fun HasSideEffect() {
    var count = 0
    Column {
        // 还有其它的代码执行

        //
        //
        for (i in 0 until 3) {
            count++  // 副作用代码
        }
    }
    Text(text = "$count") // Column 如果发生多次重组，那么count的值不可预期
}

@Composable
fun SolveSideEffect(viewModel: ViewModel) {
    var count = 0
    Column {

        //

        //
        for (i in 0 until 3) {
            SideEffect { // 这样是不对的，因为 {} 包裹的代码会在重组结束之后执行
                count++  // 这里的最好做法是将数据提前处理好，再把它传进来
            }            // 大原则就是把业务处理和界面显示的逻辑分开！！！
        }
    }
    Text(text = "$viewModel")
}

// demo：https://developer.android.com/codelabs/jetpack-compose-advanced-state-side-effects?hl=zh-cn#3

// 所以到底是怎么处理副作用的？https://blog.51cto.com/u_15200109/2786146