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
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.LayoutModifier
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout

class TestModifierActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                MyColumn()
//                MyConstraintLayout()
//                SameParam()
//                MyLayout()
                MyPadding()
            }
        }

    }

}

@Composable
fun MyLayout2() {
//    Modifier.background()
}

// part 7
@Composable
fun MyPadding() {
    Box(modifier = Modifier.background(Color.Yellow)) {
        Text(text = "456", modifier = Modifier.layout { measurable, constraints ->
            val myPadding = 10.dp.roundToPx()
            val placeable = measurable.measure(
                constraints.copy(
                    maxWidth = constraints.maxWidth - myPadding * 2,
                    maxHeight = constraints.maxHeight - myPadding * 2
                )
            )
            layout(placeable.width + myPadding * 2, placeable.height + myPadding * 2) {
                placeable.placeRelative(myPadding, myPadding)
            }
        })

        // 等同于
//        Modifier.padding(10.dp)
    }

}

// part 6
@Composable
fun MyLayout() {
    Text(text = "456", modifier = Modifier.layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        layout(placeable.width, placeable.height) {
            placeable.placeRelative(0, 0)
        }

    })
}

// part 5
@Composable
fun MyModifier(modifier: Modifier = Modifier) { //

}

// part 4
@Composable
fun SameParam() {
    Box(
        modifier = Modifier
            .background(Color.Red)
            .wrapContentSize()
    ) {
        Text(
            text = "123",
            modifier = Modifier
//                .size(100.dp)
//                .size(50.dp)
//                .background(color = Color.Red)
//                .background(color = Color.Blue)

        )
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