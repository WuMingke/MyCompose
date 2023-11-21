package com.erkuai.mycompose.githublesson.t1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erkuai.mycompose.githublesson.t1.widgets.StyleableTutorialText
import com.erkuai.mycompose.githublesson.t1.widgets.TutorialHeader
import com.erkuai.mycompose.githublesson.t1.widgets.TutorialText2

@Preview(showSystemUi = true)
@Composable
fun T1_Screen() {
    T1_Content()
}

@Composable
fun T1_Content() {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            TutorialHeader(text = "Row")
            StyleableTutorialText(text = "1-) **Row** is a layout composable that places its children in a horizontal sequence.")
            RowExample()

            TutorialHeader(text = "Column")
            StyleableTutorialText(text = "2-) **Column** is a layout composable that places its children in a vertical sequence.")
            ColumnExample()

        }
    }
}

@Composable
fun RowExample() {
    TutorialText2(text = "Arrangement.Start")
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
        RowTexts()
    }

    TutorialText2(text = "Arrangement.End")
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        RowTexts()
    }

    TutorialText2(text = "Arrangement.Center")
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        RowTexts()
    }

    TutorialText2(text = "Arrangement.SpaceEvenly")
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        RowTexts()
    }

    TutorialText2(text = "Arrangement.SpaceAround")
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
        RowTexts()
    }

    TutorialText2(text = "Arrangement.SpaceBetween")
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        RowTexts()
    }
}

@Composable
fun ColumnExample() {
    val modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .height(200.dp)
        .background(Color.LightGray)

    TutorialText2(text = "Arrangement.Top")
    Column(modifier = modifier, verticalArrangement = Arrangement.Top) { ColumnTexts() }

    TutorialText2(text = "Arrangement.Bottom")
    Column(modifier = modifier, verticalArrangement = Arrangement.Bottom) { ColumnTexts() }

    TutorialText2(text = "Arrangement.Center")
    Column(modifier = modifier, verticalArrangement = Arrangement.Center) { ColumnTexts() }

    TutorialText2(text = "Arrangement.SpaceEvenly")
    Column(modifier = modifier, verticalArrangement = Arrangement.SpaceEvenly) { ColumnTexts() }

    TutorialText2(text = "Arrangement.SpaceAround")
    Column(modifier = modifier, verticalArrangement = Arrangement.SpaceAround) { ColumnTexts() }

    TutorialText2(text = "Arrangement.SpaceBetween")
    Column(modifier = modifier, verticalArrangement = Arrangement.SpaceBetween) { ColumnTexts() }

}

@Composable
fun RowTexts() {
    Text(
        text = "Row1", modifier = Modifier
            .background(Color(0xffff9800))
            .padding(4.dp)
    )
    Text(
        text = "Row1", modifier = Modifier
            .background(Color(0xffffa726))
            .padding(4.dp)
    )
    Text(
        text = "Row1", modifier = Modifier
            .background(Color(0xffffb74d))
            .padding(4.dp)
    )
}

@Composable
fun ColumnTexts() {
    Text(
        text = "Column1", modifier = Modifier
            .background(Color(0xff8bc34a))
            .padding(4.dp)
    )
    Text(
        text = "Column1", modifier = Modifier
            .background(Color(0xff9ccc65))
            .padding(4.dp)
    )
    Text(
        text = "Column1", modifier = Modifier
            .background(Color(0xffaed581))
            .padding(4.dp)
    )
}