package com.erkuai.mycompose.githublesson.t2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.erkuai.mycompose.githublesson.model.Snack
import com.erkuai.mycompose.githublesson.model.snacks
import com.erkuai.mycompose.githublesson.widgets.SnackCard
import kotlinx.coroutines.launch

@Composable
fun T2_5_2_Content() {

    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val list = remember { mutableStateListOf<Snack>() }

    Column {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            state = scrollState,
            modifier = Modifier.weight(1f),
            content = {
                items(list) {
                    SnackCard(snack = it)
                }
            })
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    coroutineScope.launch { scrollState.animateScrollToItem(0) }
                },
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "top")
            }
            Button(
                onClick = {
                    if (list.size <= 0) return@Button
                    coroutineScope.launch { scrollState.animateScrollToItem(list.size - 1) }
                },
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "bottom")
            }
            Button(onClick = {
                if (list.size < snacks.size) {
                    list.add(snacks[list.size])
                }
            }) {
                Text(text = "add")
            }
            Button(onClick = {
                if (list.size > 0) {
                    list.removeLast()
                }
            }) {
                Text(text = "remove")
            }
        }
    }
}