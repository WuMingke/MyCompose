package com.erkuai.mycompose.githublesson.t2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.erkuai.mycompose.githublesson.widgets.HorizontalSnackCard
import com.erkuai.mycompose.githublesson.widgets.PlaceCard

@Composable
fun T2_5_3_Content() {

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxHeight()
            .background(Color(0xffECEFF1)),
        content = {
            item {
                Text(
                    color = Color(0xffE53935),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    text = "Snacks"
                )
            }
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    content = {
                        items(snacks){
                            HorizontalSnackCard(snack = it)
                        }
                    }
                )
            }

            item {
                Text(
                    color = Color(0xff4CAF50),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    text = "Places"
                )
            }
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    content = {
                        items(places) { place: Place ->
                            PlaceCard(place = place)
                        }
                    }
                )
            }
        })

}