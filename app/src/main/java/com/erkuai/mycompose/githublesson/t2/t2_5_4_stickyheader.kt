package com.erkuai.mycompose.githublesson.t2

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erkuai.mycompose.githublesson.model.Place
import com.erkuai.mycompose.githublesson.model.Snack
import com.erkuai.mycompose.githublesson.model.places
import com.erkuai.mycompose.githublesson.model.snacks
import com.erkuai.mycompose.githublesson.model.snacksOrdered
import com.erkuai.mycompose.githublesson.widgets.HorizontalSnackCard
import com.erkuai.mycompose.githublesson.widgets.PlaceCard
import com.erkuai.mycompose.githublesson.widgets.SnackCard

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun T2_5_4_Content() {
    val grouped: Map<Char, List<Snack>> = snacksOrdered.groupBy { it.name[0] }

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            grouped.forEach {
                stickyHeader {
                    SnackHeader(text = it.key)
                }
                items(snacks) {
                    SnackCard(snack = it)
                }
            }
        })
}

@Composable
fun SnackHeader(text: Char) {
    Text(
        text = "$text",
        fontSize = 24.sp,
        color = Color(0xff039BE5),
        modifier = Modifier
            .background(Color(0xffE3F2FD))
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp)
    )
}