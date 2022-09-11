package com.erkuai.mycompose.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout

class TestModifierActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                MyColumn()
                MyConstraintLayout()
            }
        }

    }

}

// part 3  .9图

// part 2
@Composable
fun MyConstraintLayout() {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (nick, age) = createRefs()
        createHorizontalChain(nick, age, chainStyle = ChainStyle.Packed)
        Text(text = "nickNick", modifier = Modifier
            .constrainAs(nick) {
//                top.linkTo(parent.top)  // 这两个都不用写约束，Chain已经实现了
//                bottom.linkTo(parent.bottom)
//                start.linkTo(parent.start)
//                end.linkTo(age.start)
            }
            .height(80.dp)  // 如果height比padding小？
            .padding(top = 50.dp)
            .background(color = Color.Blue))
        Text(text = "nameName", modifier = Modifier
            .constrainAs(age) {
                top.linkTo(nick.top) // 实际没有加padding的对齐
//                top.linkTo(nick.top, margin = 50.dp) // 1
//                Modifier.padding(top = 50.dp)        // 2 要么更上面一样写padding属性
//                bottom.linkTo(nick.bottom)
//                start.linkTo(nick.end)
//                end.linkTo(parent.end)
            }
            .background(color = Color.Red))

    }
}


// part 1
@Composable
fun MyColumn() {
    Column {
        Text(
            text = "111111",
            modifier = Modifier
                .padding(top = 10.dp)
                .background(color = Color.Yellow)
                .padding(top = 20.dp)
        )
//        Text(
//            text = "222222",
//            modifier = Modifier.background(color = Color.Blue)
//        )
    }
}