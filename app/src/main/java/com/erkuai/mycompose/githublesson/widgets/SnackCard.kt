package com.erkuai.mycompose.githublesson.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.IconToggleButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.erkuai.mycompose.githublesson.model.Snack
import kotlin.random.Random

@Composable
fun SnackCard(
    snack: Snack,
    textColor: Color = remember(snack.id) {
        randomColor()
    },
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(160.dp)
) {
    Card(
        modifier = Modifier
            .background(Color.White)
            .clickable { }, // TODO: mingke 删除
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(contentAlignment = Alignment.TopEnd) {
            Column(Modifier.fillMaxSize()) {
                Text(text = "111")
            }

            FavoriteButton(modifier = Modifier.padding(12.dp), color = textColor)
        }
    }
}

@Composable
fun FavoriteButton(modifier: Modifier = Modifier, color: Color = Color(0xffe91e63)) {
    var isFavorite by remember { mutableStateOf(false) }
    IconToggleButton(checked = isFavorite, onCheckedChange = { isFavorite = !isFavorite }) {
        Icon(
            tint = color,
            modifier = modifier.graphicsLayer {
                scaleX = 1.3f
                scaleY = 1.3f
            },
            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = ""
        )
    }
}

private fun randomColor() = Color(
    Random.nextInt(255),
    Random.nextInt(255),
    Random.nextInt(255)
)