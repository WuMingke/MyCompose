package com.erkuai.mycompose.githublesson.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.erkuai.mycompose.R
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
            .background(Color.White),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(contentAlignment = Alignment.TopEnd) {
            Column(Modifier.fillMaxSize()) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model =
                        ImageRequest.Builder(LocalContext.current).data(data = snack.imageUrl)
                            .apply {
                                crossfade(true)
                                placeholder(R.drawable.photo)
                            }.build()
                    ),
                    modifier = modifier,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = snack.name,
                        color = textColor,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(text = "$${snack.price}", fontSize = 14.sp)
                        Text(text = "Tags:${snack.tagline}", fontSize = 14.sp)
                    }
                }
            }

            FavoriteButton(modifier = Modifier.padding(12.dp), color = textColor)
        }
    }
}

@Composable
fun HorizontalSnackCard(
    modifier: Modifier = Modifier,
    snack: Snack,
    textColor: Color = remember(snack.id) { randomColor() }
) {
    Box(contentAlignment = Alignment.TopEnd) {
        Column {
            Image(
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(160.dp)
                    .clip(RoundedCornerShape(8.dp)),
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = snack.imageUrl)
                        .apply(block = fun ImageRequest.Builder.() {
                            crossfade(true)
                            placeholder(drawableResId = R.drawable.photo)
                        }).build()
                ),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                modifier = Modifier.padding(2.dp),
                color = textColor,
                fontWeight = FontWeight.Bold,
                text = snack.name
            )
        }
        FavoriteButton(modifier = Modifier.padding(12.dp), color = textColor)
    }
}

@Composable
fun GridSnackCard(
    modifier: Modifier = Modifier,
    snack: Snack,
) {
    Box(contentAlignment = Alignment.TopEnd, modifier = modifier.padding(4.dp)) {


        Image(
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(120.dp)
                .clip(RoundedCornerShape(8.dp))
                .clickable { },
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = snack.imageUrl)
                    .apply(block = fun ImageRequest.Builder.() {
                        crossfade(true)
                        placeholder(drawableResId = R.drawable.img)
                    }).build()
            ),
            contentDescription = null
        )
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