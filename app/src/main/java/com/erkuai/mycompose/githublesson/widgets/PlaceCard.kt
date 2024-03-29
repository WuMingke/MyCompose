package com.erkuai.mycompose.githublesson.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erkuai.mycompose.githublesson.model.Place

@Composable
fun PlaceCard(place: Place) {
    val painter = painterResource(id = place.imgRes)
    Box(
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(
            elevation = 4.dp,
            color = Color.LightGray,
            shape = RoundedCornerShape(8.dp)
        ) {
            Image(
                painter = painter, contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(150.dp)
                    .height(200.dp)
            )
        }
        Text(
            text = place.description,
            modifier = Modifier.padding(16.dp),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}