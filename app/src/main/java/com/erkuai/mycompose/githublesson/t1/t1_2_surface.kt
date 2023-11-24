package com.erkuai.mycompose.githublesson.t1

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erkuai.mycompose.githublesson.widgets.StyleableTutorialText
import com.erkuai.mycompose.githublesson.widgets.TutorialHeader

@Composable
fun T1_2_Content() {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            TutorialHeader(text = "Clickable")
            StyleableTutorialText(
                text = "1-) Adding clickable to Modifier makes a component clickable." +
                        "\nPadding before clickable makes " +
                        "**clickable area smaller than component's total area**."
            )
            ClickableModifierExample()

            /*****************/

            TutorialHeader(text = "Surface")
            StyleableTutorialText(text = "2-) Surface can clips it's children to selected shape.")
            SurfaceShapeExample()



            StyleableTutorialText(text = "3-) Surface can set Z index and border of it's children.")
            SurfaceZIndexExample()



            StyleableTutorialText(text = "4-) Surface can set content color for Text and Image.")
            SurfaceContentColorExample()

            /*****************/

            StyleableTutorialText(
                text = "5-) Components can have offset in both x and y axes. Surface inside" +
                        " another surface gets clipped when it overflows from it's parent."
            )
            SurfaceClickPropagationExample()
        }
    }
}

@Composable
fun SurfaceClickPropagationExample() {
    val context = LocalContext.current
    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .clickable {
            Toast
                .makeText(context, "Box Clicked", Toast.LENGTH_SHORT)
                .show()
        }) {

        Surface(elevation = 10.dp, shape = RoundedCornerShape(10.dp), color = Color(0xfffdd835),
            modifier = Modifier
                .size(150.dp)
                .padding(12.dp)
//                .clip(RoundedCornerShape(10.dp))  /// Surface: Modifier.clip 之后 没有阴影了 elevation 无效
                .clickable {
                    Toast
                        .makeText(
                            context,
                            "Surface(Left) inside Box is clicked!",
                            Toast.LENGTH_SHORT
                        )
                        .show()
                }) {
            Surface(
                shape = CircleShape,
                modifier = Modifier
                    .size(80.dp)
                    .offset(x = 50.dp, y = (-20).dp)
//                    .clip(CircleShape)
                    .clickable {
                        Toast
                            .makeText(
                                context,
                                "Surface inside Surface is clicked!",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    },
                color = Color(0xFF26C6DA),
                elevation = 12.dp,

                ) {}

        }


        Surface(
            shape = CutCornerShape(10.dp),
            modifier = Modifier
                .size(110.dp)
                .padding(12.dp)
                .offset(x = 110.dp, y = 20.dp)
//                .clip(
//                    CutCornerShape(10.dp)
//                )
                .clickable {
                    Toast
                        .makeText(
                            context,
                            "Surface(Right) inside Box is clicked!",
                            Toast.LENGTH_SHORT
                        )
                        .show()
                },
            color = Color(0xFFF4511E),
            elevation = 8.dp
        ) {

        }

        Surface(
            shape = RectangleShape,
            modifier = Modifier.size(40.dp),
            color = Color(0xFFFDD835),
            elevation = 5.dp,
            border = BorderStroke(5.dp, color = Color(0xFFFF6F00))
        ) {}

    }
}

@Composable
fun SurfaceContentColorExample() {
    Surface(
        modifier = Modifier.padding(12.dp),
        shape = RoundedCornerShape(10.dp),
        color = Color(0xFFFDD835),
        contentColor = Color(0xFF26C6DA)
    ) {
        Text(
            text = "Text inside Surface uses Surface's content color as a default color.",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp),
//            color = Color.Red
        )
    }
}

@Composable
fun SurfaceZIndexExample() {
    Row {
        val modifier = Modifier
            .aspectRatio(1f)
            .weight(1f)
            .padding(12.dp)
        Surface(
            shape = RectangleShape,
            modifier = modifier,
            color = Color(0xFFFDD835),
            elevation = 5.dp,
            border = BorderStroke(5.dp, color = Color(0xFFFF6F00))
        ) {}

        Surface(
            shape = RoundedCornerShape(5.dp),
            modifier = modifier,
            color = Color(0xFFF4511E),
            elevation = 8.dp,
            border = BorderStroke(3.dp, color = Color(0xFF6D4C41))
        ) {}

        Surface(
            shape = CircleShape,
            modifier = modifier,
            color = Color(0xFF26C6DA),
            elevation = 11.dp,
            border = BorderStroke(2.dp, color = Color(0xFF004D40))
        ) {}
        Surface(
            shape = CutCornerShape(topStartPercent = 20),
            modifier = modifier,
            color = Color(0xFFB2FF59),
            elevation = 15.dp,
            border = BorderStroke(2.dp, color = Color(0xFFd50000))
        ) {}
    }
}

@Composable
fun SurfaceShapeExample() {
    Row {
        val modifier = Modifier
            .aspectRatio(1f)
            .weight(1f)
            .padding(12.dp)
        Surface(modifier = modifier, RectangleShape, color = Color(0xFFFDD835)) {}
        Surface(modifier = modifier, RoundedCornerShape(5.dp), color = Color(0xFFF4511E)) {}
        Surface(modifier = modifier, CircleShape, color = Color(0xFF26C6DA)) {}
        Surface(modifier = modifier, CutCornerShape(10.dp), color = Color(0xFF7E57C2)) {}
    }
}

@Composable
fun ClickableModifierExample() {
    val context = LocalContext.current
    Row(modifier = Modifier.height(120.dp)) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(Color(0xff388e3c))
                .clickable {
                    Toast
                        .makeText(context, "Click me", Toast.LENGTH_SHORT)
                        .show()
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                text = "Click Me"
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(Color(0xFF1E88E5))
                .padding(15.dp)
                .clickable(onClick = {
                    Toast
                        .makeText(context, "Clicked me", Toast.LENGTH_SHORT)
                        .show()
                }),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                text = "Click Me"
            )
        }
    }
}