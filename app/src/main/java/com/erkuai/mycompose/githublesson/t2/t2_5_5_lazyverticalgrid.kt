package com.erkuai.mycompose.githublesson.t2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.erkuai.mycompose.R
import com.erkuai.mycompose.githublesson.model.Snack
import com.erkuai.mycompose.githublesson.model.snacks
import com.erkuai.mycompose.githublesson.widgets.GridSnackCard

@Composable
fun T2_5_5_Content() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),

        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffECEFF1)),
        content = {
            items(snacks) {
//                GridSnackCard(snack = it)
                GridSnackCardWithTitle(snack = it)
            }
        },
    )
}

@Composable
fun GridSnackCardWithTitle(snack: Snack) {
    Column(
        modifier = Modifier
            .heightIn(min = 200.dp)
            .shadow(elevation = 1.dp, shape = RoundedCornerShape(5.dp))
            .background(Color.White)
    ) {
        val density = LocalDensity.current
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = snack.imageUrl)
                    .apply(block = fun ImageRequest.Builder.() {
                        placeholder(drawableResId = R.drawable.photo)
                    }
                    ).build()),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(8.dp)),
            contentDescription = "",
        )
        var padding by remember { mutableStateOf(0.dp) }

        Text(
            text = "Snack ${snack.name}",
            modifier = Modifier
                .padding(start = 6.dp, end = 6.dp, top = 6.dp, bottom = padding)
                .wrapContentHeight(),
            fontSize = 20.sp,
            onTextLayout = {
                val lineCount = it.lineCount
                val height = with(density) {
                    (it.size.height / lineCount).toDp() /// 一行的高度
                }
                padding = 4.dp + if (lineCount > 1) 0.dp else height

                println("lineCount: $lineCount, Height: $height,padding:$padding,Snack ${snack.name}")

            }
        )

    }
}