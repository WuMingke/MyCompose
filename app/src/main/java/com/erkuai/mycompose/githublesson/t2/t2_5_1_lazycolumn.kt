package com.erkuai.mycompose.githublesson.t2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.erkuai.mycompose.githublesson.model.Snack
import com.erkuai.mycompose.githublesson.model.snacks
import com.erkuai.mycompose.githublesson.widgets.SnackCard

@Composable
fun T2_5_1_Content() {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            items(snacks) { item: Snack ->
                SnackCard(snack = item)
            }
        })
}