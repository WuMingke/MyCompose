package com.erkuai.mycompose.githublesson.t3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import com.erkuai.mycompose.githublesson.widgets.TutorialText2

@Composable
fun T3_1_CONTENT() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .background(Color.LightGray)
        ) {
            Text(
                text = "align START with space",
                modifier = Modifier
                    .background(Color(0xFF8BC34A))
                    .customAlign()

            )
            Text(
                text = "align CENTER with space",
                modifier = Modifier
                    .background(Color(0xFF8BC34A))
                    .customAlign(align = HorizontalAlign.CENTER)
            )
            Text(
                text = "align END with space",
                modifier = Modifier
                    .background(Color(0xFF8BC34A))
                    .customAlign(align = HorizontalAlign.END)
            )
        }

        Column {

        }

    }
}

enum class HorizontalAlign {
    START, CENTER, END
}

fun Modifier.customAlign(space: Int = 60, align: HorizontalAlign = HorizontalAlign.START) =
    this.then(
        layout { measurable, constraints ->
            val placeable = measurable.measure(constraints)
            val width = placeable.measuredWidth + 2 * space
            layout(width, placeable.measuredHeight) {
                when (align) {
                    HorizontalAlign.START -> {
                        placeable.placeRelative(0, 0)
                    }

                    HorizontalAlign.CENTER -> {
                        placeable.placeRelative(space, 0)
                    }

                    HorizontalAlign.END -> {
                        placeable.placeRelative(2 * space, 0)
                    }
                }
            }
        }
    )