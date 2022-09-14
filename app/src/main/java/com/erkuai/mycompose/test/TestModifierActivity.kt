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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.updateBounds
import com.erkuai.mycompose.R

class TestModifierActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                MyColumn()
                MyConstraintLayout()
//                SameParam()
//                MyLayout()
//                MyPadding()
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
        Modifier.padding(10.dp)
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
fun SameParam(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .background(Color.Red)
            .wrapContentSize()
    ) {
        Text(
            text = "123",
            modifier = modifier
//                .size(100.dp)
//                .size(50.dp)
                .background(color = Color.Red)
                .background(color = Color.Blue)

        )
    }

}

// part 3  .9图
@Composable
fun MyNinePatch() {
    val image = ContextCompat.getDrawable(LocalContext.current, R.drawable.message_gz_qinmz)
//    Box{
//        image
//        Text(text = )
//    }
    Text(text = "1111111111111111", fontSize = 12.sp, modifier = Modifier
        .drawBehind {
            image?.updateBounds(0, 0, size.width.toInt(), size.height.toInt())
            image?.draw(drawContext.canvas.nativeCanvas)
        }
        .padding(start = 50.dp, end = 8.dp)
        .height(16.dp))
}

// part 2
@Composable
fun MyConstraintLayout() {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (nick, age) = createRefs()
        createHorizontalChain(nick, age, chainStyle = ChainStyle.Packed)
        Text(text = "nickNick", modifier = Modifier
            .constrainAs(nick) {
            }
            .height(50.dp)  // 如果height比padding小？
            .background(color = Color.Blue)
            .height(80.dp))


        Text(text = "nameName", modifier = Modifier
            .constrainAs(age) {
            }
            .height(80.dp)
            .padding(top = 50.dp)
            .background(color = Color.Red))

    }
}


// part 1
@Composable
fun MyColumn() {
    Column {
//        Text(
//            text = "111111",
//            mod1 = Modifier
//                .padding(top = 10.dp)
//                .background(color = Color.Yellow)
//                .padding(top = 20.dp)
//        )
//        Text(
//            text = "222222",
//            modifier = Modifier.background(color = Color.Blue)
//        )
    }
}